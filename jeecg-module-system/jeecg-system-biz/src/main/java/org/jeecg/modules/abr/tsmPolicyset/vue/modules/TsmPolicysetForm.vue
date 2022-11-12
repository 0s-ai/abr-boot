<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="服务器" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tsmHostId">
              <j-dict-select-tag type="list" v-model="model.tsmHostId" dictCode="ai_host where del_flag=0,name,id" placeholder="请选择服务器" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="策略域" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="domainName">
              <j-dict-select-tag type="list" v-model="model.domainName" dictCode="tsm_domains where del_flag=0,name,id" placeholder="请选择策略域" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="策略集" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="setName">
              <a-input v-model="model.setName" placeholder="请输入策略集"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="默认管理类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="defmgmtclass">
              <a-input v-model="model.defmgmtclass" placeholder="请输入默认管理类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <a-input v-model="model.description" placeholder="请输入描述"  ></a-input>
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
    name: 'TsmPolicysetForm',
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
           tsmHostId: [
              { required: true, message: '请输入服务器!'},
           ],
           domainName: [
              { required: true, message: '请输入策略域!'},
           ],
           setName: [
              { required: true, message: '请输入策略集!'},
           ],
        },
        url: {
          add: "/tsmPolicyset/tsmPolicyset/add",
          edit: "/tsmPolicyset/tsmPolicyset/edit",
          queryById: "/tsmPolicyset/tsmPolicyset/queryById"
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