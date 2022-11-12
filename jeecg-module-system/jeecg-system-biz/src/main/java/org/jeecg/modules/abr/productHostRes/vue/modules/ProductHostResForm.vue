<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="产品" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <j-dict-select-tag type="list" v-model="model.productId" dictCode="product,short_name,id" placeholder="请选择产品" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="主机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hostId">
              <j-dict-select-tag type="list" v-model="model.hostId" dictCode="ai_host,name,id" placeholder="请选择主机" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="资源类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resourceType">
              <j-dict-select-tag type="list" v-model="model.resourceType" dictCode="resource_type" placeholder="请选择资源类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="资源分类" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resourceFlag">
              <j-dict-select-tag type="list" v-model="model.resourceFlag" dictCode="resource_flag" placeholder="请选择资源分类" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="资源名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resourceName">
              <a-input v-model="model.resourceName" placeholder="请输入资源名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="资源编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resourceCode">
              <a-input v-model="model.resourceCode" placeholder="请输入资源编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="资源值" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="resourceValue">
              <a-input v-model="model.resourceValue" placeholder="请输入资源值"  ></a-input>
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
    name: 'ProductHostResForm',
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
        },
        url: {
          add: "/productHostRes/productHostRes/add",
          edit: "/productHostRes/productHostRes/edit",
          queryById: "/productHostRes/productHostRes/queryById"
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