<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="主机" v-bind="validateInfos.hostId">
	          <j-dict-select-tag v-model:value="formData.hostId" dictCode="ai_host where del_flag=0,name,id" placeholder="请选择主机" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="IP" v-bind="validateInfos.ip">
	          <j-dict-select-tag v-model:value="formData.ip" dictCode="ai_host where del_flag=0,ip,id" placeholder="请选择IP" disabled/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="tsm服务端口" v-bind="validateInfos.tsmPort">
            <a-input v-model:value="formData.tsmPort" placeholder="请输入tsm服务端口" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="oc服务端口" v-bind="validateInfos.ocPort">
            <a-input v-model:value="formData.ocPort" placeholder="请输入oc服务端口" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="API凭据" v-bind="validateInfos.apiCert">
	          <j-dict-select-tag v-model:value="formData.apiCert" dictCode="ai_cert where del_flag=0,name,id" placeholder="请选择API凭据" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="API状态" v-bind="validateInfos.apiStatus">
            <a-input v-model:value="formData.apiStatus" placeholder="请输入API状态" disabled></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="服务名" v-bind="validateInfos.serverName">
            <a-input v-model:value="formData.serverName" placeholder="请输入服务名" disabled></a-input>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import moment from 'moment';
  import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../TsmServer.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    hostId: '',   
    ip: '',   
    tsmPort: '',   
    ocPort: '',   
    apiCert: '',   
    apiStatus: '',   
    serverName: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
    hostId: [{ required: true, message: '请输入主机!'},],
    ip: [{ required: true, message: '请输入IP!'},],
    tsmPort: [{ required: true, message: '请输入tsm服务端口!'},],
    ocPort: [{ required: true, message: '请输入oc服务端口!'},],
  };
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });
  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      //赋值
      Object.assign(formData, record);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    // 触发表单验证
    await validate();
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    height: 500px !important;
    overflow-y: auto;
    padding: 24px 24px 24px 24px;
  }
</style>
