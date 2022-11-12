<template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :width="800" @ok="handleSubmit">
      <BasicForm @register="registerForm" ref="formRef"/>
  <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="执行角色" key="productDoRole" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productDoRole"
          :loading="productDoRoleTable.loading"
          :columns="productDoRoleTable.columns"
          :dataSource="productDoRoleTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="执行参数" key="productDoParm" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productDoParm"
          :loading="productDoParmTable.loading"
          :columns="productDoParmTable.columns"
          :dataSource="productDoParmTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="执行操作" key="productDoOper" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="productDoOper"
          :loading="productDoOperTable.loading"
          :columns="productDoOperTable.columns"
          :dataSource="productDoOperTable.dataSource"
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
    import {ref, computed, unref,reactive} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import { JVxeTable } from '/@/components/jeecg/JVxeTable'
    import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts'
    import {formSchema,productDoRoleColumns,productDoParmColumns,productDoOperColumns} from '../ProductDo.data';
    import {saveOrUpdate,productDoRoleList,productDoParmList,productDoOperList} from '../ProductDo.api';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    const formDisabled = ref(false);
    const refKeys = ref(['productDoRole', 'productDoParm', 'productDoOper', ]);
    const activeKey = ref('productDoRole');
    const productDoRole = ref();
    const productDoParm = ref();
    const productDoOper = ref();
    const tableRefs = {productDoRole, productDoParm, productDoOper, };
    const productDoRoleTable = reactive({
          loading: false,
          dataSource: [],
          columns:productDoRoleColumns
    })
    const productDoParmTable = reactive({
          loading: false,
          dataSource: [],
          columns:productDoParmColumns
    })
    const productDoOperTable = reactive({
          loading: false,
          dataSource: [],
          columns:productDoOperColumns
    })
    //表单配置
    const [registerForm, {setProps,resetFields, setFieldsValue, validate}] = useForm({
        //labelWidth: 150,
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: {span: 24}
    });
     //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await reset();
        setModalProps({confirmLoading: false,showCancelBtn:data?.showFooter,showOkBtn:data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        formDisabled.value = !data?.showFooter;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
             requestSubTableData(productDoRoleList, {id:data?.record?.id}, productDoRoleTable)
             requestSubTableData(productDoParmList, {id:data?.record?.id}, productDoParmTable)
             requestSubTableData(productDoOperList, {id:data?.record?.id}, productDoOperTable)
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //方法配置
    const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys);

    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : '编辑'));

    async function reset(){
      await resetFields();
      activeKey.value = 'productDoRole';
      productDoRoleTable.dataSource = [];
      productDoParmTable.dataSource = [];
      productDoOperTable.dataSource = [];
    }
    function classifyIntoFormData(allValues) {
         let main = Object.assign({}, allValues.formValue)
         return {
           ...main, // 展开
           productDoRoleList: allValues.tablesValue[0].tableData,
           productDoParmList: allValues.tablesValue[1].tableData,
           productDoOperList: allValues.tablesValue[2].tableData,
         }
       }
    //表单提交事件
    async function requestAddOrEdit(values) {
        try {
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }
</script>

<style lang="less" scoped>
	/** 时间和数字输入框样式 */
    :deep(.ant-input-number){
		width: 100%
	}

	:deep(.ant-calendar-picker){
		width: 100%
	}
</style>