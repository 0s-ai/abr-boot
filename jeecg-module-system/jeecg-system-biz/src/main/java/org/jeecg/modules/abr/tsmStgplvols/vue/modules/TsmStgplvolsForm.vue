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
            <a-form-model-item label="存储池名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stgpoolName">
              <j-dict-select-tag type="list" v-model="model.stgpoolName" dictCode="tsm_stgpools where del_flag=0,name,id" placeholder="请选择存储池名称" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="设备类名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="devclassName">
              <a-input v-model="model.devclassName" placeholder="请输入设备类名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="容量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="estCapacityName">
              <a-input v-model="model.estCapacityName" placeholder="请输入容量"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="使用率" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pctUtilized">
              <a-input v-model="model.pctUtilized" placeholder="请输入使用率"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
              <a-input v-model="model.status" placeholder="请输入状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="读写权限" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="access">
              <a-input v-model="model.access" placeholder="请输入读写权限"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="变更时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="chgTime">
              <a-input v-model="model.chgTime" placeholder="请输入变更时间"  ></a-input>
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
    name: 'TsmStgplvolsForm',
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
           stgpoolName: [
              { required: true, message: '请输入存储池名称!'},
           ],
           name: [
              { required: true, message: '请输入名称!'},
           ],
        },
        url: {
          add: "/tsmStgplvols/tsmStgplvols/add",
          edit: "/tsmStgplvols/tsmStgplvols/edit",
          queryById: "/tsmStgplvols/tsmStgplvols/queryById"
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