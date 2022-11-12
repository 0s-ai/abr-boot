<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-model-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入名称" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">
              <a-input v-model="model.code" placeholder="请输入编码" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="产品" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="productId">
              <j-dict-select-tag type="list" v-model="model.productId" dictCode="product,name,id" placeholder="请选择产品" />
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
      <a-tab-pane tab="方案角色" :key="refKeys[0]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[0]"
          :loading="productCaseRoleTable.loading"
          :columns="productCaseRoleTable.columns"
          :dataSource="productCaseRoleTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="方案参数" :key="refKeys[1]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[1]"
          :loading="productCaseParmTable.loading"
          :columns="productCaseParmTable.columns"
          :dataSource="productCaseParmTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="方案操作" :key="refKeys[2]" :forceRender="true">
        <j-vxe-table
          keep-source
          :ref="refKeys[2]"
          :loading="productCaseOperTable.loading"
          :columns="productCaseOperTable.columns"
          :dataSource="productCaseOperTable.dataSource"
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
    name: 'ProductCaseForm',
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
           name: [
              { required: true, message: '请输入名称!'},
           ],
           code: [
              { required: true, message: '请输入编码!'},
           ],
           productId: [
              { required: true, message: '请输入产品!'},
           ],
        },
        refKeys: ['productCaseRole', 'productCaseParm', 'productCaseOper', ],
        tableKeys:['productCaseRole', 'productCaseParm', 'productCaseOper', ],
        activeKey: 'productCaseRole',
        // 方案角色
        productCaseRoleTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '名称',
              key: 'name',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '编码',
              key: 'code',
               type: JVXETypes.input,
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
              title: '最低内存总量',
              key: 'memTotalMin',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '备注',
              key: 'note',
               type: JVXETypes.textarea,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 方案参数
        productCaseParmTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '角色编码',
              key: 'roleCode',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
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
              title: '参数类型',
              key: 'parmType',
              type: JVXETypes.select,
              options:[],
              dictCode:"parm_type",
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
        // 方案操作
        productCaseOperTable: {
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
               type: JVXETypes.textarea,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '执行角色',
              key: 'doRole',
               type: JVXETypes.input,
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
            {
              title: '是否禁用',
              key: 'isEnabled',
               type: JVXETypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/productCase/productCase/add",
          edit: "/productCase/productCase/edit",
          queryById: "/productCase/productCase/queryById",
          productCaseRole: {
            list: '/productCase/productCase/queryProductCaseRoleByMainId'
          },
          productCaseParm: {
            list: '/productCase/productCase/queryProductCaseParmByMainId'
          },
          productCaseOper: {
            list: '/productCase/productCase/queryProductCaseOperByMainId'
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
        this.productCaseRoleTable.dataSource=[]
        this.productCaseParmTable.dataSource=[]
        this.productCaseOperTable.dataSource=[]
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
          this.requestSubTableData(this.url.productCaseRole.list, params, this.productCaseRoleTable)
          this.requestSubTableData(this.url.productCaseParm.list, params, this.productCaseParmTable)
          this.requestSubTableData(this.url.productCaseOper.list, params, this.productCaseOperTable)
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
          productCaseRoleList: allValues.tablesValue[0].tableData,
          productCaseParmList: allValues.tablesValue[1].tableData,
          productCaseOperList: allValues.tablesValue[2].tableData,
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