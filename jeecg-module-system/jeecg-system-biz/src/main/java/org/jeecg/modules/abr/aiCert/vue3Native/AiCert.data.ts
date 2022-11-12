import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '凭据名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '凭据类型',
    align: "center",
    dataIndex: 'certType_dictText'
  },
  {
    title: '账号名称',
    align: "center",
    dataIndex: 'username'
  },
  {
    title: '凭据密码',
    align: "center",
    dataIndex: 'password'
  },
  {
    title: '凭据内容',
    align: "center",
    dataIndex: 'content'
  },
  {
    title: '有效标记',
    align: "center",
    dataIndex: 'validStatus_dictText'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "凭据名称",
    field: 'name',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "凭据类型",
    field: 'certType',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "cert_type"
    },
    colProps: {span: 6},
  },
  {
    label: "账号名称",
    field: 'username',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "有效标记",
    field: 'validStatus',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "valid_status"
    },
    colProps: {span: 6},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '凭据名称',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入凭据名称!'},
             ];
    },
  },
  {
    label: '凭据类型',
    field: 'certType',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "cert_type"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入凭据类型!'},
             ];
    },
  },
  {
    label: '账号名称',
    field: 'username',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入账号名称!'},
             ];
    },
  },
  {
    label: '凭据密码',
    field: 'password',
    component: 'InputPassword',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入凭据密码!'},
             ];
    },
  },
  {
    label: '凭据内容',
    field: 'content',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入凭据内容!'},
             ];
    },
  },
  {
    label: '有效标记',
    field: 'validStatus',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "valid_status"
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
