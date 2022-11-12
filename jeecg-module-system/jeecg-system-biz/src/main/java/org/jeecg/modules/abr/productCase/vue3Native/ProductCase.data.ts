import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '编码',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '产品',
    align:"center",
    dataIndex: 'productId_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'note'
   },
   {
    title: '版本',
    align:"center",
    dataIndex: 'version'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "名称",
      field: "name",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "编码",
      field: "code",
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "产品",
      field: "productId",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"product,name,id"
      },
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入名称!'},
          ];
     },
  },
  {
    label: '编码',
    field: 'code',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入编码!'},
          ];
     },
  },
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
export const productCaseRoleColumns: JVxeColumn[] = [
    {
      title: '名称',
      key: 'name',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: true, message: '${title}不能为空' },
        ],
    },
    {
      title: '编码',
      key: 'code',
      type: JVxeTypes.input,
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
      title: '最低内存总量',
      key: 'memTotalMin',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '备注',
      key: 'note',
      type: JVxeTypes.textarea,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
export const productCaseParmColumns: JVxeColumn[] = [
    {
      title: '角色编码',
      key: 'roleCode',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
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
      title: '参数类型',
      key: 'parmType',
      type: JVxeTypes.select,
      options:[],
      dictCode:"parm_type",
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
export const productCaseOperColumns: JVxeColumn[] = [
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
      type: JVxeTypes.textarea,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '执行角色',
      key: 'doRole',
      type: JVxeTypes.input,
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
    {
      title: '是否禁用',
      key: 'isEnabled',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
