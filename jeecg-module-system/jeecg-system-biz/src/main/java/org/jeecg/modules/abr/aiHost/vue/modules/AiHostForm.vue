<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="ip" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ip">
              <a-input v-model="model.ip" placeholder="请输入ip"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="端口" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="port">
              <a-input v-model="model.port" placeholder="请输入端口"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="服务器类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hostType">
              <j-dict-select-tag type="list" v-model="model.hostType" dictCode="host_type" placeholder="请选择服务器类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="环境类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="envType">
              <j-dict-select-tag type="list" v-model="model.envType" dictCode="env_type" placeholder="请选择环境类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="测试凭据" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="certTest">
              <j-search-select-tag v-model="model.certTest" dict="ai_cert,name,id"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="自动化状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="aiStatus">
              <a-input v-model="model.aiStatus" placeholder="请输入自动化状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="note">
              <a-textarea v-model="model.note" rows="4" placeholder="请输入备注" />
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
    name: 'AiHostForm',
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
           name: [
              { required: true, message: '请输入名称!'},
           ],
           ip: [
              { required: true, message: '请输入ip!'},
           ],
           port: [
              { required: true, message: '请输入端口!'},
           ],
        },
        url: {
          add: "/aiHost/aiHost/add",
          edit: "/aiHost/aiHost/edit",
          queryById: "/aiHost/aiHost/queryById"
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