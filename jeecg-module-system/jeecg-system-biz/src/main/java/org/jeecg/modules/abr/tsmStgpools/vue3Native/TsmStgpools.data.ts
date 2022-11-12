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
    title: '存储池类型',
    align: "center",
    dataIndex: 'poolType_dictText'
  },
  {
    title: '最大容量',
    align: "center",
    dataIndex: 'maxSize'
  },
  {
    title: '设备类',
    align: "center",
    dataIndex: 'devClass'
  },
  {
    title: '描述',
    align: "center",
    dataIndex: 'description'
  },
  {
    title: '存储类型',
    align: "center",
    dataIndex: 'stgType'
  },
  {
    title: '云连接名称',
    align: "center",
    dataIndex: 'cloudConn'
  },
  {
    title: '云存储类',
    align: "center",
    dataIndex: 'cloudStgc'
  },
  {
    title: '使用率',
    align: "center",
    dataIndex: 'pctUtilize'
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
      dictCode: "ai_host where del_flag=0,name,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入服务器!'},
             ];
    },
  },
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
    label: '存储池类型',
    field: 'poolType',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "stgpool_type"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入存储池类型!'},
             ];
    },
  },
  {
    label: '最大容量',
    field: 'maxSize',
    component: 'Input',
  },
  {
    label: '设备类',
    field: 'devClass',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入设备类!'},
             ];
    },
  },
  {
    label: '描述',
    field: 'description',
    component: 'Input',
  },
  {
    label: '存储类型',
    field: 'stgType',
    component: 'Input',
  },
  {
    label: '云连接名称',
    field: 'cloudConn',
    component: 'Input',
  },
  {
    label: '云存储类',
    field: 'cloudStgc',
    component: 'Input',
  },
  {
    label: '使用率',
    field: 'pctUtilize',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
