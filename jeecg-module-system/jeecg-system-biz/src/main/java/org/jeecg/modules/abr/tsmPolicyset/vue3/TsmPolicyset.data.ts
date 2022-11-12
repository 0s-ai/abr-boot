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
    title: '策略集',
    align:"center",
    dataIndex: 'setName'
   },
   {
    title: '默认管理类',
    align:"center",
    dataIndex: 'defmgmtclass'
   },
   {
    title: '描述',
    align:"center",
    dataIndex: 'description'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
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
    label: '策略集',
    field: 'setName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入策略集!'},
          ];
     },
  },
  {
    label: '默认管理类',
    field: 'defmgmtclass',
    component: 'Input',
  },
  {
    label: '描述',
    field: 'description',
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