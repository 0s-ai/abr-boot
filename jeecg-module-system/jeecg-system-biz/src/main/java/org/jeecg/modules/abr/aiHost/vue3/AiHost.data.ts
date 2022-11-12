import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '名称',
    align:"center",
    sorter: true,
    dataIndex: 'name'
   },
   {
    title: 'ip',
    align:"center",
    sorter: true,
    dataIndex: 'ip'
   },
   {
    title: '端口',
    align:"center",
    dataIndex: 'port'
   },
   {
    title: '服务器类型',
    align:"center",
    dataIndex: 'hostType_dictText'
   },
   {
    title: '环境类型',
    align:"center",
    dataIndex: 'envType_dictText'
   },
   {
    title: '测试凭据',
    align:"center",
    dataIndex: 'certTest_dictText'
   },
   {
    title: '自动化状态',
    align:"center",
    dataIndex: 'aiStatus'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'note'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "名称",
      field: 'name',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "ip",
      field: 'ip',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "端口",
      field: 'port',
      component: 'Input',
      colProps: {span: 6},
 	},
	{
      label: "服务器类型",
      field: 'hostType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"host_type"
      },
      colProps: {span: 6},
 	},
	{
      label: "环境类型",
      field: 'envType',
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"env_type"
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
    label: 'ip',
    field: 'ip',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入ip!'},
          ];
     },
  },
  {
    label: '端口',
    field: 'port',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入端口!'},
          ];
     },
  },
  {
    label: '服务器类型',
    field: 'hostType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"host_type"
     },
  },
  {
    label: '环境类型',
    field: 'envType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"env_type"
     },
  },
  {
    label: '测试凭据',
    field: 'certTest',
    component: 'JSearchSelect',
    componentProps:{
       dict:"ai_cert,name,id"
    },
  },
  {
    label: '自动化状态',
    field: 'aiStatus',
    component: 'Input',
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



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}