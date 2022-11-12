<template>
  <a-spin :spinning="loading">
    <a-form v-bind="formItemLayout">
      <a-row>
        <a-col :span="24">
          <a-form-item label="产品" v-bind="validateInfos.productId">
	          <j-dict-select-tag v-model:value="formData.productId" dictCode="product,name,id" placeholder="请选择产品" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="环境类型" v-bind="validateInfos.envType">
	          <j-dict-select-tag v-model:value="formData.envType" dictCode="env_type" placeholder="请选择环境类型" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="方案" v-bind="validateInfos.prodCaseId">
	          <j-dict-select-tag v-model:value="formData.prodCaseId" dictCode="product_case,name,id" placeholder="请选择方案" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="执行名称" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入执行名称" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="执行人" v-bind="validateInfos.doOwner">
	          <j-select-user-by-dept v-model:value="formData.doOwner" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="备注" v-bind="validateInfos.note">
	          <a-textarea v-model:value="formData.note" rows="4" placeholder="请输入备注" :disabled="disabled"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>

		<!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane tab="执行角色" key="productDoRole" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productDoRoleTableRef"
          :loading="productDoRoleTable.loading"
          :columns="productDoRoleTable.columns"
          :dataSource="productDoRoleTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
      <a-tab-pane tab="执行参数" key="productDoParm" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productDoParmTableRef"
          :loading="productDoParmTable.loading"
          :columns="productDoParmTable.columns"
          :dataSource="productDoParmTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
      <a-tab-pane tab="执行操作" key="productDoOper" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productDoOperTableRef"
          :loading="productDoOperTable.loading"
          :columns="productDoOperTable.columns"
          :dataSource="productDoOperTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script lang="ts">
  import { defineComponent, ref, reactive, computed, toRaw } from 'vue';
  import { useValidateAntFormAndTable } from '/@/hooks/system/useJvxeMethods';
  import { queryProductDoRoleListByMainId, queryProductDoParmListByMainId, queryProductDoOperListByMainId, queryDataById, saveOrUpdate } from '../ProductDo.api';
  import { JVxeTable } from '/@/components/jeecg/JVxeTable';
  import {productDoRoleColumns, productDoParmColumns, productDoOperColumns} from '../ProductDo.data';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import JSelectUserByDept from '/@/components/Form/src/jeecg/components/JSelectUserByDept.vue';
  import { Form } from 'ant-design-vue';
  const useForm = Form.useForm;

  export default defineComponent({
    name: "ProductDoForm",
    components:{
      JDictSelectTag,
      JSelectUserByDept,
      JVxeTable,
    },
    props:{
      disabled:{
        type: Boolean,
        default: false
      }
    },
    emits:['success'],
    setup(props, {emit}) {
      const loading = ref(false);
      const productDoRoleTableRef = ref();
      const productDoRoleTable = reactive<Record<string, any>>({
        loading: false,
        columns: productDoRoleColumns,
        dataSource: []
      });
      const productDoParmTableRef = ref();
      const productDoParmTable = reactive<Record<string, any>>({
        loading: false,
        columns: productDoParmColumns,
        dataSource: []
      });
      const productDoOperTableRef = ref();
      const productDoOperTable = reactive<Record<string, any>>({
        loading: false,
        columns: productDoOperColumns,
        dataSource: []
      });
      const activeKey = ref('productDoRole');
      const formData = reactive<Record<string, any>>({
        id: '',
        productId: '',
        envType: '',
        prodCaseId: '',
        name: '',
        doOwner: '',
        note: '',
      });

      //表单验证
      const validatorRules = reactive({
        productId: [{ required: true, message: '请输入产品!'},],
        envType: [{ required: true, message: '请输入环境类型!'},],
        prodCaseId: [{ required: true, message: '请输入方案!'},],
      });
      const {resetFields, validate, validateInfos} = useForm(formData, validatorRules, {immediate: true});
      const dbData = {};
      const formItemLayout = {
        labelCol: {xs: {span: 24}, sm: {span: 5}},
        wrapperCol: {xs: {span: 24}, sm: {span: 16}},
      };

      const formDisabled = computed(() => {
        return props.disabled;
      });

      function add() {
        resetFields();
        productDoRoleTable.dataSource = [];
        productDoParmTable.dataSource = [];
        productDoOperTable.dataSource = [];
      }

      async function edit(row) {
        //主表数据
        await queryMainData(row.id);
        //子表数据
        const productDoRoleDataList = await queryProductDoRoleListByMainId(row['id']);
        productDoRoleTable.dataSource = [...productDoRoleDataList];
        const productDoParmDataList = await queryProductDoParmListByMainId(row['id']);
        productDoParmTable.dataSource = [...productDoParmDataList];
        const productDoOperDataList = await queryProductDoOperListByMainId(row['id']);
        productDoOperTable.dataSource = [...productDoOperDataList];
      }

      async function queryMainData(id) {
        const row = await queryDataById(id);
        Object.keys(row).map(k => {
          formData[k] = row[k];
        });
      }

      const {getSubFormAndTableData, transformData} = useValidateAntFormAndTable(activeKey, {
        'productDoRole': productDoRoleTableRef,
        'productDoParm': productDoParmTableRef,
        'productDoOper': productDoOperTableRef,
      });

      async function getFormData() {
        await validate();
        return transformData(toRaw(formData))
      }

      async function submitForm() {
        const mainData = await getFormData();
        const subData = await getSubFormAndTableData();
        const values = Object.assign({}, dbData, mainData, subData);
        console.log('表单提交数据', values)
        const isUpdate = values.id ? true : false
        await saveOrUpdate(values, isUpdate);
        //关闭弹窗
        emit('success');
      }
      
      function setFieldsValue(values) {
        if(values){
          Object.keys(values).map(k=>{
            formData[k] = values[k];
          });
        }
      }

      /**
       * 值改变事件触发-树控件回调
       * @param key
       * @param value
       */
      function handleFormChange(key, value) {
        formData[key] = value;
      }


      return {
        productDoRoleTableRef,
        productDoRoleTable,
        productDoParmTableRef,
        productDoParmTable,
        productDoOperTableRef,
        productDoOperTable,
        validatorRules,
        validateInfos,
        activeKey,
        loading,
        formData,
        setFieldsValue,
        handleFormChange,
        formItemLayout,
        formDisabled,
        getFormData,
        submitForm,
        add,
        edit
      }
    }
  });
</script>