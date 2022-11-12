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
    title: '存储池名称',
    align: "center",
    dataIndex: 'stgpoolName_dictText'
  },
  {
    title: '名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '设备类名称',
    align: "center",
    dataIndex: 'devclassName'
  },
  {
    title: '容量',
    align: "center",
    dataIndex: 'estCapacityName'
  },
  {
    title: '使用率',
    align: "center",
    dataIndex: 'pctUtilized'
  },
  {
    title: '状态',
    align: "center",
    dataIndex: 'status'
  },
  {
    title: '读写权限',
    align: "center",
    dataIndex: 'access'
  },
  {
    title: '变更时间',
    align: "center",
    dataIndex: 'chgTime'
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
    label: '存储池名称',
    field: 'stgpoolName',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "tsm_stgpools where del_flag=0,name,id"
    },
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入存储池名称!'},
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
    label: '设备类名称',
    field: 'devclassName',
    component: 'Input',
  },
  {
    label: '容量',
    field: 'estCapacityName',
    component: 'Input',
  },
  {
    label: '使用率',
    field: 'pctUtilized',
    component: 'Input',
  },
  {
    label: '状态',
    field: 'status',
    component: 'Input',
  },
  {
    label: '读写权限',
    field: 'access',
    component: 'Input',
  },
  {
    label: '变更时间',
    field: 'chgTime',
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
