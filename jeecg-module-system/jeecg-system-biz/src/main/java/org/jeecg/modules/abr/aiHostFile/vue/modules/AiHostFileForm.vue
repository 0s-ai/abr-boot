<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="标题" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入标题"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="标识" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tag">
              <a-input v-model="model.tag" placeholder="请输入标识"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hostId">
              <j-dict-select-tag type="list" v-model="model.hostId" dictCode="ai_host,name,id" placeholder="请选择主机" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="密钥" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="certId">
              <j-dict-select-tag type="list" v-model="model.certId" dictCode="ai_cert,name,id" placeholder="请选择密钥" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="文件绝对路径" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="path">
              <a-input v-model="model.path" placeholder="请输入文件绝对路径"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="内容" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="content">
              <a-textarea v-model="model.content" rows="4" placeholder="请输入内容" />
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
    name: 'AiHostFileForm',
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
           title: [
              { required: true, message: '请输入标题!'},
           ],
           hostId: [
              { required: true, message: '请输入主机!'},
           ],
           certId: [
              { required: true, message: '请输入密钥!'},
           ],
           path: [
              { required: true, message: '请输入文件绝对路径!'},
           ],
        },
        url: {
          add: "/aiHostFile/aiHostFile/add",
          edit: "/aiHostFile/aiHostFile/edit",
          queryById: "/aiHostFile/aiHostFile/queryById"
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