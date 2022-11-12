import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '产品',
    align:"center",
    dataIndex: 'productId_dictText'
   },
   {
    title: '主机',
    align:"center",
    dataIndex: 'hostId_dictText'
   },
   {
    title: '资源类型',
    align:"center",
    dataIndex: 'resourceType_dictText'
   },
   {
    title: '资源分类',
    align:"center",
    dataIndex: 'resourceFlag_dictText'
   },
   {
    title: '资源名称',
    align:"center",
    dataIndex: 'resourceName'
   },
   {
    title: '资源编码',
    align:"center",
    dataIndex: 'resourceCode'
   },
   {
    title: '资源值',
    align:"center",
    dataIndex: 'resourceValue'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "产品",
      field: 'productId',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"product,short_name,id"
      },
      colProps: {span: 6},
 	},
	{
      label: "主机",
      field: 'hostId',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"ai_host,name,id"
      },
      colProps: {span: 6},
 	},
	{
      label: "资源类型",
      field: 'resourceType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"resource_type"
      },
      colProps: {span: 6},
 	},
	{
      label: "资源分类",
      field: 'resourceFlag',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"resource_flag"
      },
      colProps: {span: 6},
 	},
	{
      label: "资源名称",
      field: 'resourceName',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "资源编码",
      field: 'resourceCode',
      component: 'Input',
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '产品',
    field: 'productId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"product,short_name,id"
     },
  },
  {
    label: '主机',
    field: 'hostId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"ai_host,name,id"
     },
  },
  {
    label: '资源类型',
    field: 'resourceType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"resource_type"
     },
  },
  {
    label: '资源分类',
    field: 'resourceFlag',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"resource_flag"
     },
  },
  {
    label: '资源名称',
    field: 'resourceName',
    component: 'Input',
  },
  {
    label: '资源编码',
    field: 'resourceCode',
    component: 'Input',
  },
  {
    label: '资源值',
    field: 'resourceValue',
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