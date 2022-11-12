import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '模板名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '模板编码',
    align:"center",
    dataIndex: 'code'
   },
   {
    title: '模板类型',
    align:"center",
    dataIndex: 'type_dictText'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'note'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '模板名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入模板名称!'},
                 {...rules.duplicateCheckRule('tsm_script_template', 'name',model,schema)[0]},
          ];
     },
  },
  {
    label: '模板编码',
    field: 'code',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入模板编码!'},
                 {...rules.duplicateCheckRule('tsm_script_template', 'code',model,schema)[0]},
          ];
     },
  },
  {
    label: '模板类型',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"template_type"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入模板类型!'},
          ];
     },
  },
  {
    label: '备注',
    field: 'note',
    component: 'InputTextArea',//TODO 注意string转换问题
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

//子表列表数据
export const tsmScrtempParmColumns: BasicColumn[] = [
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
    title: '默认值',
    align:"center",
    dataIndex: 'value'
   },
];
//子表表单数据
export const tsmScrtempParmFormSchema: FormSchema[] = [
  // TODO 子表隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
  {
    label: '名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '编码',
    field: 'code',
    component: 'Input',
  },
  {
    label: '默认值',
    field: 'value',
    component: 'InputTextArea',
  },
];
//子表列表数据
export const tsmScrtempScriptColumns: BasicColumn[] = [
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
    title: '内容',
    align:"center",
    dataIndex: 'content'
   },
];
//子表表单数据
export const tsmScrtempScriptFormSchema: FormSchema[] = [
  // TODO 子表隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
  {
    label: '名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '编码',
    field: 'code',
    component: 'Input',
  },
  {
    label: '内容',
    field: 'content',
    component: 'InputTextArea',
  },
];
