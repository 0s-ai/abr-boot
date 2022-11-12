<template>
  <BasicModal
    v-bind="$attrs"
    @register="registerModal"
    destroyOnClose
    :title="title"
    :width="800"
    @ok="handleSubmit"
  >
    <BasicForm @register="registerForm" ref="formRef" />
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="方案角色" key="productCaseRole" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productCaseRole"
          :loading="productCaseRoleTable.loading"
          :columns="productCaseRoleTable.columns"
          :dataSource="productCaseRoleTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
        />
      </a-tab-pane>
      <a-tab-pane tab="方案参数" key="productCaseParm" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productCaseParm"
          :loading="productCaseParmTable.loading"
          :columns="productCaseParmTable.columns"
          :dataSource="productCaseParmTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
        />
      </a-tab-pane>
      <a-tab-pane tab="方案操作" key="productCaseOper" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productCaseOper"
          :loading="productCaseOperTable.loading"
          :columns="productCaseOperTable.columns"
          :dataSource="productCaseOperTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
        />
      </a-tab-pane>
    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
import { ref, computed, unref, reactive } from "vue";
import { BasicModal, useModalInner } from "/@/components/Modal";
import { BasicForm, useForm } from "/@/components/Form/index";
import { JVxeTable } from "/@/components/jeecg/JVxeTable";
import { useJvxeMethod } from "/@/hooks/system/useJvxeMethods.ts";
import {
  formSchema,
  productCaseRoleColumns,
  productCaseParmColumns,
  productCaseOperColumns,
} from "../ProductCase.data";
import {
  saveOrUpdate,
  productCaseRoleList,
  productCaseParmList,
  productCaseOperList,
} from "../ProductCase.api";
import { VALIDATE_FAILED } from "/@/utils/common/vxeUtils";
// Emits声明
const emit = defineEmits(["register", "success"]);
const isUpdate = ref(true);
const formDisabled = ref(false);
const refKeys = ref(["productCaseRole", "productCaseParm", "productCaseOper"]);
const activeKey = ref("productCaseRole");
const productCaseRole = ref();
const productCaseParm = ref();
const productCaseOper = ref();
const tableRefs = { productCaseRole, productCaseParm, productCaseOper };
const productCaseRoleTable = reactive({
  loading: false,
  dataSource: [],
  columns: productCaseRoleColumns,
});
const productCaseParmTable = reactive({
  loading: false,
  dataSource: [],
  columns: productCaseParmColumns,
});
const productCaseOperTable = reactive({
  loading: false,
  dataSource: [],
  columns: productCaseOperColumns,
});
//表单配置
const [registerForm, { setProps, resetFields, setFieldsValue, validate }] = useForm({
  //labelWidth: 150,
  schemas: formSchema,
  showActionButtonGroup: false,
  baseColProps: { span: 24 },
});
//表单赋值
const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
  //重置表单
  await reset();
  setModalProps({
    confirmLoading: false,
    showCancelBtn: data?.showFooter,
    showOkBtn: data?.showFooter,
  });
  isUpdate.value = !!data?.isUpdate;
  formDisabled.value = !data?.showFooter;
  if (unref(isUpdate)) {
    //表单赋值
    await setFieldsValue({
      ...data.record,
    });
    requestSubTableData(
      productCaseRoleList,
      { id: data?.record?.id },
      productCaseRoleTable
    );
    requestSubTableData(
      productCaseParmList,
      { id: data?.record?.id },
      productCaseParmTable
    );
    requestSubTableData(
      productCaseOperList,
      { id: data?.record?.id },
      productCaseOperTable
    );
  }
  // 隐藏底部时禁用整个表单
  setProps({ disabled: !data?.showFooter });
});
//方法配置
const [handleChangeTabs, handleSubmit, requestSubTableData, formRef] = useJvxeMethod(
  requestAddOrEdit,
  classifyIntoFormData,
  tableRefs,
  activeKey,
  refKeys
);

//设置标题
const title = computed(() => (!unref(isUpdate) ? "新增" : "编辑"));

async function reset() {
  await resetFields();
  activeKey.value = "productCaseRole";
  productCaseRoleTable.dataSource = [];
  productCaseParmTable.dataSource = [];
  productCaseOperTable.dataSource = [];
}
function classifyIntoFormData(allValues) {
  let main = Object.assign({}, allValues.formValue);
  return {
    ...main, // 展开
    productCaseRoleList: allValues.tablesValue[0].tableData,
    productCaseParmList: allValues.tablesValue[1].tableData,
    productCaseOperList: allValues.tablesValue[2].tableData,
  };
}
//表单提交事件
async function requestAddOrEdit(values) {
  try {
    setModalProps({ confirmLoading: true });
    //提交表单
    await saveOrUpdate(values, isUpdate.value);
    //关闭弹窗
    closeModal();
    //刷新列表
    emit("success");
  } finally {
    setModalProps({ confirmLoading: false });
  }
}
</script>

<style lang="less" scoped>
/** 时间和数字输入框样式 */
:deep(.ant-input-number) {
  width: 100%;
}

:deep(.ant-calendar-picker) {
  width: 100%;
}
</style>
