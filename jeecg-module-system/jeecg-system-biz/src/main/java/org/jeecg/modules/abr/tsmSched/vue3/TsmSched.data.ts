import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '服务器',
    align:"center",
    dataIndex: 'tsmHostId_dictText'
   },
   {
    title: '策略域',
    align:"center",
    dataIndex: 'domainName_dictText'
   },
   {
    title: '名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '关联客户机',
    align:"center",
    dataIndex: 'assocNode_dictText'
   },
   {
    title: '开始日期',
    align:"center",
    dataIndex: 'startDate'
   },
   {
    title: '开始时间',
    align:"center",
    dataIndex: 'startTime'
   },
   {
    title: '频率',
    align:"center",
    dataIndex: 'duration_dictText'
   },
   {
    title: '运行脚本',
    align:"center",
    dataIndex: 'objects'
   },
   {
    title: '同步标记',
    align:"center",
    dataIndex: 'syncFlag'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "服务器",
      field: 'tsmHostId',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"ai_host where del_flag=0,name,id"
      },
      colProps: {span: 6},
 	},
	{
      label: "策略域",
      field: 'domainName',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"tsm_domains where del_flag=0,name,id"
      },
      colProps: {span: 6},
 	},
	{
      label: "名称",
      field: 'name',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "关联客户机",
      field: 'assocNode',
      component: 'JSelectMultiple',
      componentProps:{
        dictCode:"tsm_nodes where del_flag=0,name,id",
        triggerChange: true
     },
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '服务器',
    field: 'tsmHostId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"ai_host where del_flag=0,name,id"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入服务器!'},
          ];
     },
  },
  {
    label: '策略域',
    field: 'domainName',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"tsm_domains where del_flag=0,name,id"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入策略域!'},
          ];
     },
  },
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
    label: '关联客户机',
    field: 'assocNode',
    component: 'JSelectMultiple',
    componentProps:{
        dictCode:"tsm_nodes where del_flag=0,name,id"
     },
  },
  {
    label: '开始时间',
    field: 'startTime',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入开始时间!'},
          ];
     },
  },
  {
    label: '频率',
    field: 'duration',
    defaultValue: "1",
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"duration_type"
     },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入频率!'},
          ];
     },
    dynamicDisabled:true
  },
  {
    label: '运行脚本',
    field: 'objects',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入运行脚本!'},
          ];
     },
  },
  {
    label: '同步标记',
    field: 'syncFlag',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}