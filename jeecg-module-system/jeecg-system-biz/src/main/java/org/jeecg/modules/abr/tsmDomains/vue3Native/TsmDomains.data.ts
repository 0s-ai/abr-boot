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
    title: '策略域',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '已迁移对象数量',
    align: "center",
    dataIndex: 'providesSpmg'
  },
  {
    title: '客户机数量',
    align: "center",
    dataIndex: 'numClients'
  },
  {
    title: '已归档对象数量',
    align: "center",
    dataIndex: 'providesArch'
  },
  {
    title: '管理类数量',
    align: "center",
    dataIndex: 'mgmtclassCount'
  },
  {
    title: '已备份对象数量',
    align: "center",
    dataIndex: 'providesBkup'
  },
  {
    title: '选项集数量',
    align: "center",
    dataIndex: 'optsetCount'
  },
  {
    title: '调度数量',
    align: "center",
    dataIndex: 'scheduleCount'
  },
  {
    title: '默认管理类',
    align: "center",
    dataIndex: 'defMc'
  },
  {
    title: '备份目标',
    align: "center",
    dataIndex: 'defdestbkup'
  },
  {
    title: '归档目标',
    align: "center",
    dataIndex: 'defdestarch'
  },
  {
    title: '迁移目标',
    align: "center",
    dataIndex: 'defdestspman'
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
    label: "策略域",
    field: 'name',
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
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入服务器!'},
             ];
    },
  },
  {
    label: '策略域',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
      return [
              { required: true, message: '请输入策略域!'},
             ];
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
