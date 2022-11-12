<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-model-item label="产品" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <j-dict-select-tag type="list" v-model="model.productId" dictCode="product,name,id" placeholder="请选择产品" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="环境类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="envType">
              <j-dict-select-tag type="list" v-model="model.envType" dictCode="env_type" placeholder="请选择环境类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="方案" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="prodCaseId">
              <j-dict-select-tag type="list" v-model="model.prodCaseId" dictCode="product_case,name,id" placeholder="请选择方案" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="执行名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入执行名称" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="执行人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="doOwner">
              <j-select-user-by-dep v-model="model.doOwner" :multi="true" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="note">
              <a-textarea v-model="model.note" rows="4" placeholder="请输入备注" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="执行角色" :key="refKeys[0]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[0]"
          :loading="productDoRoleTable.loading"
          :columns="productDoRoleTable.columns"
          :dataSource="productDoRoleTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="执行参数" :key="refKeys[1]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[1]"
          :loading="productDoParmTable.loading"
          :columns="productDoParmTable.columns"
          :dataSource="productDoParmTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="执行操作" :key="refKeys[2]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[2]"
          :loading="productDoOperTable.loading"
          :columns="productDoOperTable.columns"
          :dataSource="productDoOperTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { JVxeTableModelMixin } from '@/mixins/JVxeTableModelMixin.js'
  import { JVXETypes } from '@/components/jeecg/JVxeTable'
  import { getRefPromise,VALIDATE_FAILED} from '@/components/jeecg/JVxeTable/utils/vxeUtils.js'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'

  export default {
    name: 'ProductDoForm',
    mixins: [JVxeTableModelMixin],
    components: {
      JFormContainer,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        model:{
         },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
           productId: [
              { required: true, message: '请输入产品!'},
           ],
           envType: [
              { required: true, message: '请输入环境类型!'},
           ],
           prodCaseId: [
              { required: true, message: '请输入方案!'},
           ],
        },
        refKeys: ['productDoRole', 'productDoParm', 'productDoOper', ],
        tableKeys:['productDoRole', 'productDoParm', 'productDoOper', ],
        activeKey: 'productDoRole',
        // 执行角色
        productDoRoleTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '名称',
              key: 'name',
               type: JVXETypes.input,
               disabled:true,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '内存总量',
              key: 'memTotal',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '最低内存总量',
              key: 'memTotalMin',
               type: JVXETypes.input,
               disabled:true,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '解析结果',
              key: 'analysisResult',
               type: JVXETypes.textarea,
               disabled:true,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '备注',
              key: 'note',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '编码',
              key: 'code',
               type: JVXETypes.input,
               disabled:true,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '产品',
              key: 'productId',
              type: JVXETypes.select,
              options:[],
              dictCode:"product,short_name,id",
              disabled:true,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '最低操作系统版本',
              key: 'osLevelMin',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '产品版本',
              key: 'prodVerId',
              type: JVXETypes.select,
              options:[],
              dictCode:"product_version,version,id",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '执行服务器',
              key: 'doHostId',
              type: JVXETypes.select,
              options:[],
              dictCode:"ai_host,name,id",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '执行凭据',
              key: 'doCertId',
              type: JVXETypes.select,
              options:[],
              dictCode:"ai_cert,name,id",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '执行人',
              key: 'doOwner',
              type: JVXETypes.userSelect,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '操作系统类型',
              key: 'osType',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '操作系统版本',
              key: 'osLevel',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 执行参数
        productDoParmTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '参数名称',
              key: 'parmName',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '参数描述',
              key: 'parmDesc',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '参数值',
              key: 'parmValue',
               type: JVXETypes.textarea,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 执行操作
        productDoOperTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '执行序号',
              key: 'doNumber',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行标题',
              key: 'doTitle',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行编码',
              key: 'doCode',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '是否禁用',
              key: 'isEnabled',
              type: JVXETypes.checkbox,
                customValue: ['Y', 'N'],
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行类型',
              key: 'doType',
              type: JVXETypes.select,
              options:[],
              dictCode:"do_type",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行人',
              key: 'doOwner',
              type: JVXETypes.userSelect,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行主机',
              key: 'doHost',
              type: JVXETypes.select,
              options:[],
              dictCode:"ai_host,name,id",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行凭据',
              key: 'doCert',
              type: JVXETypes.select,
              options:[],
              dictCode:"ai_cert,name,id",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行变量',
              key: 'doVars',
               type: JVXETypes.textarea,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行依赖',
              key: 'doDep',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/productDo/productDo/add",
          edit: "/productDo/productDo/edit",
          queryById: "/productDo/productDo/queryById",
          productDoRole: {
            list: '/productDo/productDo/queryProductDoRoleByMainId'
          },
          productDoParm: {
            list: '/productDo/productDo/queryProductDoParmByMainId'
          },
          productDoOper: {
            list: '/productDo/productDo/queryProductDoOperByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
      addBefore(){
        this.productDoRoleTable.dataSource=[]
        this.productDoParmTable.dataSource=[]
        this.productDoOperTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.productDoRole.list, params, this.productDoRoleTable)
          this.requestSubTableData(this.url.productDoParm.list, params, this.productDoParmTable)
          this.requestSubTableData(this.url.productDoOper.list, params, this.productDoOperTable)
        }
      },
      //校验所有一对一子表表单
        validateSubForm(allValues){
            return new Promise((resolve,reject)=>{
              Promise.all([
              ]).then(() => {
                resolve(allValues)
              }).catch(e => {
                if (e.error === VALIDATE_FAILED) {
                  // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                  this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
                } else {
                  console.error(e)
                }
              })
            })
        },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          productDoRoleList: allValues.tablesValue[0].tableData,
          productDoParmList: allValues.tablesValue[1].tableData,
          productDoOperList: allValues.tablesValue[2].tableData,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>