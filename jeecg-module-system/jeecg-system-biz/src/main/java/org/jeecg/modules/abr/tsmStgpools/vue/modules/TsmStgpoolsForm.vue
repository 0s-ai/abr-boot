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
            <a-form-model-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="存储池类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="poolType">
              <j-dict-select-tag type="list" v-model="model.poolType" dictCode="stgpool_type" placeholder="请选择存储池类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最大容量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="maxSize">
              <a-input v-model="model.maxSize" placeholder="请输入最大容量"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="devClass">
              <a-input v-model="model.devClass" placeholder="请输入设备类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="描述" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <a-input v-model="model.description" placeholder="请输入描述"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="存储类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stgType">
              <a-input v-model="model.stgType" placeholder="请输入存储类型"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="云连接名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cloudConn">
              <a-input v-model="model.cloudConn" placeholder="请输入云连接名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="云存储类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cloudStgc">
              <a-input v-model="model.cloudStgc" placeholder="请输入云存储类"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pctUtilize">
              <a-input v-model="model.pctUtilize" placeholder="请输入使用率"  ></a-input>
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
    name: 'TsmStgpoolsForm',
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
           name: [
              { required: true, message: '请输入名称!'},
           ],
           poolType: [
              { required: true, message: '请输入存储池类型!'},
           ],
           devClass: [
              { required: true, message: '请输入设备类!'},
           ],
        },
        url: {
          add: "/tsmStgpools/tsmStgpools/add",
          edit: "/tsmStgpools/tsmStgpools/edit",
          queryById: "/tsmStgpools/tsmStgpools/queryById"
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