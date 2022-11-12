<template>
  <a-spin :spinning="loading">
    <a-form v-bind="formItemLayout">
      <a-row>
        <a-col :span="24">
          <a-form-item label="名称" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入名称" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="编码" v-bind="validateInfos.code">
            <a-input v-model:value="formData.code" placeholder="请输入编码" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="产品" v-bind="validateInfos.productId">
	          <j-dict-select-tag v-model:value="formData.productId" dictCode="product,name,id" placeholder="请选择产品" :disabled="disabled"/>
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
      <a-tab-pane tab="方案角色" key="productCaseRole" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productCaseRoleTableRef"
          :loading="productCaseRoleTable.loading"
          :columns="productCaseRoleTable.columns"
          :dataSource="productCaseRoleTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
      <a-tab-pane tab="方案参数" key="productCaseParm" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productCaseParmTableRef"
          :loading="productCaseParmTable.loading"
          :columns="productCaseParmTable.columns"
          :dataSource="productCaseParmTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
      <a-tab-pane tab="方案操作" key="productCaseOper" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="productCaseOperTableRef"
          :loading="productCaseOperTable.loading"
          :columns="productCaseOperTable.columns"
          :dataSource="productCaseOperTable.dataSource"
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
  import { queryProductCaseRoleListByMainId, queryProductCaseParmListByMainId, queryProductCaseOperListByMainId, queryDataById, saveOrUpdate } from '../ProductCase.api';
  import { JVxeTable } from '/@/components/jeecg/JVxeTable';
  import {productCaseRoleColumns, productCaseParmColumns, productCaseOperColumns} from '../ProductCase.data';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import { Form } from 'ant-design-vue';
  const useForm = Form.useForm;

  export default defineComponent({
    name: "ProductCaseForm",
    components:{
      JDictSelectTag,
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
      const productCaseRoleTableRef = ref();
      const productCaseRoleTable = reactive<Record<string, any>>({
        loading: false,
        columns: productCaseRoleColumns,
        dataSource: []
      });
      const productCaseParmTableRef = ref();
      const productCaseParmTable = reactive<Record<string, any>>({
        loading: false,
        columns: productCaseParmColumns,
        dataSource: []
      });
      const productCaseOperTableRef = ref();
      const productCaseOperTable = reactive<Record<string, any>>({
        loading: false,
        columns: productCaseOperColumns,
        dataSource: []
      });
      const activeKey = ref('productCaseRole');
      const formData = reactive<Record<string, any>>({
        id: '',
        name: '',
        code: '',
        productId: '',
        note: '',
      });

      //表单验证
      const validatorRules = reactive({
        name: [{ required: true, message: '请输入名称!'},],
        code: [{ required: true, message: '请输入编码!'},],
        productId: [{ required: true, message: '请输入产品!'},],
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
        productCaseRoleTable.dataSource = [];
        productCaseParmTable.dataSource = [];
        productCaseOperTable.dataSource = [];
      }

      async function edit(row) {
        //主表数据
        await queryMainData(row.id);
        //子表数据
        const productCaseRoleDataList = await queryProductCaseRoleListByMainId(row['id']);
        productCaseRoleTable.dataSource = [...productCaseRoleDataList];
        const productCaseParmDataList = await queryProductCaseParmListByMainId(row['id']);
        productCaseParmTable.dataSource = [...productCaseParmDataList];
        const productCaseOperDataList = await queryProductCaseOperListByMainId(row['id']);
        productCaseOperTable.dataSource = [...productCaseOperDataList];
      }

      async function queryMainData(id) {
        const row = await queryDataById(id);
        Object.keys(row).map(k => {
          formData[k] = row[k];
        });
      }

      const {getSubFormAndTableData, transformData} = useValidateAntFormAndTable(activeKey, {
        'productCaseRole': productCaseRoleTableRef,
        'productCaseParm': productCaseParmTableRef,
        'productCaseOper': productCaseOperTableRef,
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
        productCaseRoleTableRef,
        productCaseRoleTable,
        productCaseParmTableRef,
        productCaseParmTable,
        productCaseOperTableRef,
        productCaseOperTable,
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