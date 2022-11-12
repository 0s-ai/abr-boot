import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '编码',
    align: "center",
    dataIndex: 'code'
  },
  {
    title: '操作类型',
    align: "center",
    dataIndex: 'operType_dictText'
  },
  {
    title: '人工操作',
    align: "center",
    dataIndex: 'runbook'
  },
  {
    title: '自动操作',
    align: "center",
    dataIndex: 'playbook'
  },
  {
    title: '操作变量',
    align: "center",
    dataIndex: 'operVar'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
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
    label: '编码',
    field: 'code',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入编码!'},
             ];
    },
  },
  {
    label: '操作类型',
    field: 'operType',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "oper_type"
    },
  },
  {
    label: '人工操作',
    field: 'runbook',
    component: 'JMarkdownEditor',//注意string转换问题
  },
  {
    label: '自动操作',
    field: 'playbook',
    component: 'JMarkdownEditor',//注意string转换问题
  },
  {
    label: '操作变量',
    field: 'operVar',
    component: 'JMarkdownEditor',//注意string转换问题
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
