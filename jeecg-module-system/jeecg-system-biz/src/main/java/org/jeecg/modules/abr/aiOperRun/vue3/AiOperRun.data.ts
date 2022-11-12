import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '服务器',
    align:"center",
    dataIndex: 'serverId_dictText'
   },
   {
    title: '凭据',
    align:"center",
    dataIndex: 'certId_dictText'
   },
   {
    title: '运行操作',
    align:"center",
    dataIndex: 'operId'
   },
   {
    title: '操作变量',
    align:"center",
    dataIndex: 'operVar'
   },
   {
    title: '运行状态',
    align:"center",
    dataIndex: 'runStatus_dictText'
   },
   {
    title: '开始时间',
    align:"center",
    dataIndex: 'startTime'
   },
   {
    title: '完成时间',
    align:"center",
    dataIndex: 'endTime'
   },
   {
    title: '标准输出',
    align:"center",
    dataIndex: 'stdout'
   },
   {
    title: '结果输出',
    align:"center",
    dataIndex: 'output'
   },
   {
    title: '耗用时间',
    align:"center",
    dataIndex: 'elapsedTime'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '服务器',
    field: 'serverId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"ai_host,name,id"
     },
  },
  {
    label: '凭据',
    field: 'certId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"ai_cert,name,id"
     },
  },
  {
    label: '运行操作',
    field: 'operId',
    component: 'Input',
  },
  {
    label: '操作变量',
    field: 'operVar',
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



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}