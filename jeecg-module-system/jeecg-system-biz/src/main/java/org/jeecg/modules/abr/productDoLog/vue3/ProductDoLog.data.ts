import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '执行方案',
    align:"center",
    dataIndex: 'prodDoId_dictText'
   },
   {
    title: '执行序号',
    align:"center",
    dataIndex: 'doNumber'
   },
   {
    title: '执行标题',
    align:"center",
    sorter: true,
    dataIndex: 'doTitle'
   },
   {
    title: '执行编码',
    align:"center",
    dataIndex: 'doCode'
   },
   {
    title: '执行依赖',
    align:"center",
    dataIndex: 'doDep'
   },
   {
    title: '执行类型',
    align:"center",
    dataIndex: 'doType_dictText'
   },
   {
    title: '执行人',
    align:"center",
    dataIndex: 'doOwner_dictText'
   },
   {
    title: '执行主机',
    align:"center",
    dataIndex: 'doHost_dictText'
   },
   {
    title: '执行凭据',
    align:"center",
    dataIndex: 'doCert_dictText'
   },
   {
    title: '执行变量',
    align:"center",
    dataIndex: 'doVars'
   },
   {
    title: '执行状态',
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
    title: '耗时',
    align:"center",
    dataIndex: 'elapsedTime'
   },
   {
    title: '标准输出',
    align:"center",
    dataIndex: 'stdout'
   },
   {
    title: '输出',
    align:"center",
    dataIndex: 'output'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "执行序号",
      field: 'doNumber',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "执行编码",
      field: 'doCode',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "执行类型",
      field: 'doType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"do_type"
      },
      colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '执行方案',
    field: 'prodDoId',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"product_do where del_flag=0,name,id"
     },
  },
  {
    label: '执行序号',
    field: 'doNumber',
    component: 'Input',
  },
  {
    label: '执行标题',
    field: 'doTitle',
    component: 'Input',
  },
  {
    label: '执行编码',
    field: 'doCode',
    component: 'Input',
  },
  {
    label: '执行依赖',
    field: 'doDep',
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