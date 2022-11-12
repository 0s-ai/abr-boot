<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="凭据名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入凭据名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="凭据类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="certType">
              <j-dict-select-tag type="list" v-model="model.certType" dictCode="cert_type" placeholder="请选择凭据类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="账号名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="username">
              <a-input v-model="model.username" placeholder="请输入账号名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="凭据密码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="password">
              <a-input-password v-model="model.password" placeholder="请输入凭据密码" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="凭据内容" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="content">
              <a-input v-model="model.content" placeholder="请输入凭据内容"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="有效标记" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="validStatus">
              <j-dict-select-tag type="list" v-model="model.validStatus" dictCode="valid_status" placeholder="请选择有效标记" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'AiCertForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
            validStatus:"1",
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           name: [
              { required: true, message: '请输入凭据名称!'},
           ],
           certType: [
              { required: true, message: '请输入凭据类型!'},
           ],
           username: [
              { required: true, message: '请输入账号名称!'},
           ],
           password: [
              { required: true, message: '请输入凭据密码!'},
           ],
           content: [
              { required: true, message: '请输入凭据内容!'},
           ],
        },
        url: {
          add: "/aiCert/aiCert/add",
          edit: "/aiCert/aiCert/edit",
          queryById: "/aiCert/aiCert/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>