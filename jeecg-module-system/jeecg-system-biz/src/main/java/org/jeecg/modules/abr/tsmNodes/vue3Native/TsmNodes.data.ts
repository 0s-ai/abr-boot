import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '服务器',
    align: "center",
    dataIndex: 'tsmHostId_dictText'
  },
  {
    title: '名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '注册时间',
    align: "center",
    dataIndex: 'regTime'
  },
  {
    title: '授予删除归档权限',
    align: "center",
    dataIndex: 'archdelete'
  },
  {
    title: '同步标记',
    align: "center",
    dataIndex: 'syncFlag'
  },
  {
    title: '软删除标记',
    align: "center",
    dataIndex: 'delFlag'
  },
  {
    title: '授予删除备份权限',
    align: "center",
    dataIndex: 'backdelete'
  },
  {
    title: '密码过期天数',
    align: "center",
    dataIndex: 'passexp'
  },
  {
    title: '策略域',
    align: "center",
    dataIndex: 'domainName_dictText'
  },
  {
    title: '主机名',
    align: "center",
    dataIndex: 'tcpName'
  },
  {
    title: 'IP',
    align: "center",
    dataIndex: 'tcpAddress'
  },
  {
    title: '平台类型',
    align: "center",
    dataIndex: 'osType'
  },
  {
    title: '操作系统版本',
    align: "center",
    dataIndex: 'osVersion'
  },
  {
    title: 'tsm版本',
    align: "center",
    dataIndex: 'tsmVersion'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "服务器",
    field: 'tsmHostId',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host where del_flag=0,name,id"
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
    label: "策略域",
    field: 'domainName',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "tsm_domains where del_flag=0,name,id"
    },
    colProps: {span: 6},
  },
  {
    label: "主机名",
    field: 'tcpName',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "IP",
    field: 'tcpAddress',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "平台类型",
    field: 'osType',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "操作系统版本",
    field: 'osVersion',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "tsm版本",
    field: 'tsmVersion',
    component: 'Input',
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
      dictCode: "ai_host where del_flag=0,name,id"
    },
  },
  {
    label: '名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '同步标记',
    field: 'syncFlag',
    component: 'Input',
  },
  {
    label: '软删除标记',
    field: 'delFlag',
    component: 'Input',
  },
  {
    label: '密码',
    field: 'password',
    component: 'InputPassword',
  },
  {
    label: '授予删除备份权限',
    field: 'backdelete',
    component: 'Input',
  },
  {
    label: '密码过期天数',
    field: 'passexp',
    component: 'Input',
  },
  {
    label: '可用挂载点数量',
    field: 'maxnummp',
    component: 'Input',
  },
  {
    label: '策略域',
    field: 'domainName',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "tsm_domains where del_flag=0,name,id"
    },
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
