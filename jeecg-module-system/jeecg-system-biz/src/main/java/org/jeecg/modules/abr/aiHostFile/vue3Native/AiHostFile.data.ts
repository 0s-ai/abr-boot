import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '标题',
    align: "center",
    dataIndex: 'title'
  },
  {
    title: '标识',
    align: "center",
    dataIndex: 'tag'
  },
  {
    title: '主机',
    align: "center",
    dataIndex: 'hostId_dictText'
  },
  {
    title: '密钥',
    align: "center",
    dataIndex: 'certId_dictText'
  },
  {
    title: '文件绝对路径',
    align: "center",
    dataIndex: 'path'
  },
  {
    title: '内容',
    align: "center",
    dataIndex: 'content'
  },
  {
    title: '版本',
    align: "center",
    dataIndex: 'version'
  },
  {
    title: 'Hash值',
    align: "center",
    dataIndex: 'gitUuid'
  },
  {
    title: '文件状态',
    align: "center",
    dataIndex: 'fileStatus'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '标题',
    field: 'title',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入标题!'},
             ];
    },
  },
  {
    label: '标识',
    field: 'tag',
    component: 'Input',
  },
  {
    label: '主机',
    field: 'hostId',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_host,name,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入主机!'},
             ];
    },
  },
  {
    label: '密钥',
    field: 'certId',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "ai_cert,name,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入密钥!'},
             ];
    },
  },
  {
    label: '文件绝对路径',
    field: 'path',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入文件绝对路径!'},
             ];
    },
  },
  {
    label: '内容',
    field: 'content',
    component: 'InputTextArea',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
