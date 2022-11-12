<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="服务器" v-bind="validateInfos.tsmHostId">
	          <j-dict-select-tag v-model:value="formData.tsmHostId" dictCode="ai_host where del_flag=0,name,id" placeholder="请选择服务器" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="策略域" v-bind="validateInfos.domainName">
	          <j-dict-select-tag v-model:value="formData.domainName" dictCode="tsm_domains where del_flag=0,name,id" placeholder="请选择策略域" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="策略集" v-bind="validateInfos.setName">
	          <j-dict-select-tag v-model:value="formData.setName" dictCode="tsm_policyset where del_flag=0,set_name,id" placeholder="请选择策略集" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="类名称" v-bind="validateInfos.className">
	          <j-dict-select-tag v-model:value="formData.className" dictCode="tsm_mgmt where del_flag=0,class_name,id" placeholder="请选择类名称" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="目标存储池" v-bind="validateInfos.destPool">
	          <j-dict-select-tag v-model:value="formData.destPool" dictCode="tsm_stgpools where del_flag=0,name,id" placeholder="请选择目标存储池" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="备份副本组名称" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入备份副本组名称" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="保留版本数" v-bind="validateInfos.verexists">
            <a-input v-model:value="formData.verexists" placeholder="请输入保留版本数" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="删除对象保留版本数" v-bind="validateInfos.verdeleted">
            <a-input v-model:value="formData.verdeleted" placeholder="请输入删除对象保留版本数" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="保留天数" v-bind="validateInfos.retextra">
            <a-input v-model:value="formData.retextra" placeholder="请输入保留天数" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="保留唯一版本天数" v-bind="validateInfos.retonly">
            <a-input v-model:value="formData.retonly" placeholder="请输入保留唯一版本天数" :disabled="disabled"></a-input>
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
  import { saveOrUpdate } from '../TsmBuCpgroup.api';
  import { Form } from 'ant-design-vue';
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    tsmHostId: '',   
    domainName: '',   
    setName: '',   
    className: '',   
    destPool: '',   
    name: '',   
    verexists: '',   
    verdeleted: '',   
    retextra: '',   
    retonly: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
    tsmHostId: [{ required: true, message: '请输入服务器!'},],
    domainName: [{ required: true, message: '请输入策略域!'},],
    setName: [{ required: true, message: '请输入策略集!'},],
    className: [{ required: true, message: '请输入类名称!'},],
    destPool: [{ required: true, message: '请输入目标存储池!'},],
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
