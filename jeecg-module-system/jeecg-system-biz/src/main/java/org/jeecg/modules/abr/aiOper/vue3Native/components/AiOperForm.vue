<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
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
          <a-form-item label="操作类型" v-bind="validateInfos.operType">
	          <j-dict-select-tag v-model:value="formData.operType" dictCode="oper_type" placeholder="请选择操作类型" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="人工操作" v-bind="validateInfos.runbook">
	          <j-markdown-editor v-model:value="formData.runbook" id="runbook" placeholder="请输入人工操作" :disabled="disabled"></j-markdown-editor>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="自动操作" v-bind="validateInfos.playbook">
	          <j-markdown-editor v-model:value="formData.playbook" id="playbook" placeholder="请输入自动操作" :disabled="disabled"></j-markdown-editor>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="操作变量" v-bind="validateInfos.operVar">
	          <j-markdown-editor v-model:value="formData.operVar" id="operVar" placeholder="请输入操作变量" :disabled="disabled"></j-markdown-editor>
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
  import JMarkdownEditor from '/@/components/Form/src/jeecg/components/JMarkdownEditor.vue';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../AiOper.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    name: '',   
    code: '',   
    operType: '',   
    runbook: '',   
    playbook: '',   
    operVar: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
    name: [{ required: true, message: '请输入名称!'},],
    code: [{ required: true, message: '请输入编码!'},],
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
