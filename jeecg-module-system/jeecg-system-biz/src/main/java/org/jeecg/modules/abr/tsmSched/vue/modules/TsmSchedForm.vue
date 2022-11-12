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
            <a-form-model-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="关联客户机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="assocNode">
              <j-multi-select-tag type="list_multi" v-model="model.assocNode" dictCode="tsm_nodes where del_flag=0,name,id" placeholder="请选择关联客户机" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="开始时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="startTime">
              <a-input v-model="model.startTime" placeholder="请输入开始时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="频率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="duration">
              <j-dict-select-tag type="list" v-model="model.duration" dictCode="duration_type" placeholder="请选择频率" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="运行脚本" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="objects">
              <a-input v-model="model.objects" placeholder="请输入运行脚本"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="同步标记" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="syncFlag">
              <a-input v-model="model.syncFlag" placeholder="请输入同步标记"  ></a-input>
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
    name: 'TsmSchedForm',
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
            duration:"1",
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
           name: [
              { required: true, message: '请输入名称!'},
           ],
           startTime: [
              { required: true, message: '请输入开始时间!'},
           ],
           duration: [
              { required: true, message: '请输入频率!'},
           ],
           objects: [
              { required: true, message: '请输入运行脚本!'},
           ],
        },
        url: {
          add: "/tsmSched/tsmSched/add",
          edit: "/tsmSched/tsmSched/edit",
          queryById: "/tsmSched/tsmSched/queryById"
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