import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '主机',
    align: "center",
    dataIndex: 'hostId_dictText'
  },
  {
    title: 'IP',
    align: "center",
    dataIndex: 'ip_dictText'
  },
  {
    title: 'tsm服务端口',
    align: "center",
    dataIndex: 'tsmPort'
  },
  {
    title: 'oc服务端口',
    align: "center",
    dataIndex: 'ocPort'
  },
  {
    title: 'API凭据',
    align: "center",
    dataIndex: 'apiCert_dictText'
  },
  {
    title: 'API状态',
    align: "center",
    dataIndex: 'apiStatus'
  },
  {
    title: '服务名',
    align: "center",
    dataIndex: 'serverName'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "主机",
    field: 'hostId',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host where del_flag=0,name,id"
    },
    colProps: {span: 6},
  },
  {
    label: "IP",
    field: 'ip',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host where del_flag=0,ip,id"
    },
    colProps: {span: 6},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '主机',
    field: 'hostId',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host where del_flag=0,name,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入主机!'},
             ];
    },
  },
  {
    label: 'IP',
    field: 'ip',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host where del_flag=0,ip,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入IP!'},
             ];
    },
    dynamicDisabled: true
  },
  {
    label: 'tsm服务端口',
    field: 'tsmPort',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入tsm服务端口!'},
             ];
    },
  },
  {
    label: 'oc服务端口',
    field: 'ocPort',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入oc服务端口!'},
             ];
    },
  },
  {
    label: 'API凭据',
    field: 'apiCert',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_cert where del_flag=0,name,id"
    },
  },
  {
    label: 'API状态',
    field: 'apiStatus',
    component: 'Input',
    dynamicDisabled: true
  },
  {
    label: '服务名',
    field: 'serverName',
    component: 'Input',
    dynamicDisabled: true
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
