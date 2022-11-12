import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '产品',
    align:"center",
    dataIndex: 'productId_dictText'
   },
   {
    title: '环境类型',
    align:"center",
    dataIndex: 'envType_dictText'
   },
   {
    title: '方案',
    align:"center",
    dataIndex: 'prodCaseId_dictText'
   },
   {
    title: '执行名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '耗用时间',
    align:"center",
    dataIndex: 'elapsedTime'
   },
   {
    title: '执行人',
    align:"center",
    dataIndex: 'doOwner_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'note'
   },
   {
    title: '状态',
    align:"center",
    dataIndex: 'doStatus_dictText'
   },
   {
    title: '开始时间',
    align:"center",
    dataIndex: 'startTime'
   },
   {
    title: '结束时间',
    align:"center",
    dataIndex: 'endTime'
   },
   {
    title: '执行日志',
    align:"center",
    dataIndex: 'urlDoLog'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '产品',
    field: 'productId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"product,name,id"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入产品!'},
          ];
     },
  },
  {
    label: '环境类型',
    field: 'envType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"env_type"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入环境类型!'},
          ];
     },
  },
  {
    label: '方案',
    field: 'prodCaseId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"product_case,name,id"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入方案!'},
          ];
     },
  },
  {
    label: '执行名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '执行人',
    field: 'doOwner',
    component: 'JSelectUserByDept',
    componentProps:{
        labelKey:'realname',
     },
  },
  {
    label: '备注',
    field: 'note',
    component: 'InputTextArea',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
//子表单数据
//子表表格配置
export const productDoRoleColumns: JVxeColumn[] = [
    {
      title: '名称',
      key: 'name',
      type: JVxeTypes.input,
      disabled:true,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '内存总量',
      key: 'memTotal',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '最低内存总量',
      key: 'memTotalMin',
      type: JVxeTypes.input,
      disabled:true,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '解析结果',
      key: 'analysisResult',
      type: JVxeTypes.textarea,
      disabled:true,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '备注',
      key: 'note',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '编码',
      key: 'code',
      type: JVxeTypes.input,
      disabled:true,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '产品',
      key: 'productId',
      type: JVxeTypes.select,
      options:[],
      dictCode:"product,short_name,id",
      disabled:true,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '最低操作系统版本',
      key: 'osLevelMin',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '产品版本',
      key: 'prodVerId',
      type: JVxeTypes.select,
      options:[],
      dictCode:"product_version,version,id",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '执行服务器',
      key: 'doHostId',
      type: JVxeTypes.select,
      options:[],
      dictCode:"ai_host,name,id",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '执行凭据',
      key: 'doCertId',
      type: JVxeTypes.select,
      options:[],
      dictCode:"ai_cert,name,id",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '执行人',
      key: 'doOwner',
      type: JVxeTypes.userSelect,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '操作系统类型',
      key: 'osType',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '操作系统版本',
      key: 'osLevel',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
export const productDoParmColumns: JVxeColumn[] = [
    {
      title: '参数名称',
      key: 'parmName',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '参数描述',
      key: 'parmDesc',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '参数值',
      key: 'parmValue',
      type: JVxeTypes.textarea,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
export const productDoOperColumns: JVxeColumn[] = [
    {
      title: '执行序号',
      key: 'doNumber',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行标题',
      key: 'doTitle',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行编码',
      key: 'doCode',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '是否禁用',
      key: 'isEnabled',
      type: JVxeTypes.checkbox,
      customValue: ['Y', 'N'],
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行类型',
      key: 'doType',
      type: JVxeTypes.select,
      options:[],
      dictCode:"do_type",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行人',
      key: 'doOwner',
      type: JVxeTypes.userSelect,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行主机',
      key: 'doHost',
      type: JVxeTypes.select,
      options:[],
      dictCode:"ai_host,name,id",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行凭据',
      key: 'doCert',
      type: JVxeTypes.select,
      options:[],
      dictCode:"ai_cert,name,id",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行变量',
      key: 'doVars',
      type: JVxeTypes.textarea,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行依赖',
      key: 'doDep',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
