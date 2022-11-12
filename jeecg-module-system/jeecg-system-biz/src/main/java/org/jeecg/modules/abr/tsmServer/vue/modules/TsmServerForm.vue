<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="主机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hostId">
              <j-dict-select-tag type="list" v-model="model.hostId" dictCode="ai_host where del_flag=0,name,id" placeholder="请选择主机" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="IP" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ip">
              <j-dict-select-tag type="list" v-model="model.ip" dictCode="ai_host where del_flag=0,ip,id" placeholder="请选择IP" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tsm服务端口" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tsmPort">
              <a-input v-model="model.tsmPort" placeholder="请输入tsm服务端口"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="oc服务端口" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ocPort">
              <a-input v-model="model.ocPort" placeholder="请输入oc服务端口"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="API凭据" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="apiCert">
              <j-dict-select-tag type="list" v-model="model.apiCert" dictCode="ai_cert where del_flag=0,name,id" placeholder="请选择API凭据" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="API状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="apiStatus">
              <a-input v-model="model.apiStatus" placeholder="请输入API状态" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="服务名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="serverName">
              <a-input v-model="model.serverName" placeholder="请输入服务名" disabled ></a-input>
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
    name: 'TsmServerForm',
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
            tsmPort:"1500",
            ocPort:"11090",
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
           hostId: [
              { required: true, message: '请输入主机!'},
           ],
           ip: [
              { required: true, message: '请输入IP!'},
           ],
           tsmPort: [
              { required: true, message: '请输入tsm服务端口!'},
           ],
           ocPort: [
              { required: true, message: '请输入oc服务端口!'},
           ],
        },
        url: {
          add: "/tsmServer/tsmServer/add",
          edit: "/tsmServer/tsmServer/edit",
          queryById: "/tsmServer/tsmServer/queryById"
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