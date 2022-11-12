import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/tsmScriptTemplate/tsmScriptTemplate/list',
  save='/tsmScriptTemplate/tsmScriptTemplate/add',
  edit='/tsmScriptTemplate/tsmScriptTemplate/edit',
  deleteOne = '/tsmScriptTemplate/tsmScriptTemplate/delete',
  deleteBatch = '/tsmScriptTemplate/tsmScriptTemplate/deleteBatch',
  importExcel = '/tsmScriptTemplate/tsmScriptTemplate/importExcel',
  exportXls = '/tsmScriptTemplate/tsmScriptTemplate/exportXls',
  tsmScrtempParmList = '/tsmScriptTemplate/tsmScriptTemplate/listTsmScrtempParmByMainId',
  tsmScrtempParmSave='/tsmScriptTemplate/tsmScriptTemplate/addTsmScrtempParm',
  tsmScrtempParmEdit='/tsmScriptTemplate/tsmScriptTemplate/editTsmScrtempParm',
  tsmScrtempParmDelete = '/tsmScriptTemplate/tsmScriptTemplate/deleteTsmScrtempParm',
  tsmScrtempParmDeleteBatch = '/tsmScriptTemplate/tsmScriptTemplate/deleteBatchTsmScrtempParm',
  tsmScrtempScriptList = '/tsmScriptTemplate/tsmScriptTemplate/listTsmScrtempScriptByMainId',
  tsmScrtempScriptSave='/tsmScriptTemplate/tsmScriptTemplate/addTsmScrtempScript',
  tsmScrtempScriptEdit='/tsmScriptTemplate/tsmScriptTemplate/editTsmScrtempScript',
  tsmScrtempScriptDelete = '/tsmScriptTemplate/tsmScriptTemplate/deleteTsmScrtempScript',
  tsmScrtempScriptDeleteBatch = '/tsmScriptTemplate/tsmScriptTemplate/deleteBatchTsmScrtempScript',
}
/**
 * 导出api
 * @param params
 */
export const getExportUrl = Api.exportXls;

/**
 * 导入api
 */
export const getImportUrl = Api.importExcel;
/**
 * 列表接口
 * @param params
 */
export const list = (params) =>
  defHttp.get({url: Api.list, params});

/**
 * 删除单个
 */
export const deleteOne = (params,handleSuccess) => {
  return defHttp.delete({url: Api.deleteOne, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const batchDelete = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.deleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const saveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({url: url, params});
}
/**
 * 列表接口
 * @param params
 */
export const tsmScrtempParmList = (params) => {
  if(params['tsmScrtempId']){
    return defHttp.get({url: Api.tsmScrtempParmList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const tsmScrtempParmDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.tsmScrtempParmDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const tsmScrtempParmDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.tsmScrtempParmDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  tsmScrtempParmSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.tsmScrtempParmEdit : Api.tsmScrtempParmSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const tsmScrtempParmImportUrl = '/tsmScriptTemplate/tsmScriptTemplate/importTsmScrtempParm'

/**
 * 导出
 */
export const tsmScrtempParmExportXlsUrl = '/tsmScriptTemplate/tsmScriptTemplate/exportTsmScrtempParm'
/**
 * 列表接口
 * @param params
 */
export const tsmScrtempScriptList = (params) => {
  if(params['tsmScrtempId']){
    return defHttp.get({url: Api.tsmScrtempScriptList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const tsmScrtempScriptDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.tsmScrtempScriptDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const tsmScrtempScriptDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.tsmScrtempScriptDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  tsmScrtempScriptSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.tsmScrtempScriptEdit : Api.tsmScrtempScriptSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const tsmScrtempScriptImportUrl = '/tsmScriptTemplate/tsmScriptTemplate/importTsmScrtempScript'

/**
 * 导出
 */
export const tsmScrtempScriptExportXlsUrl = '/tsmScriptTemplate/tsmScriptTemplate/exportTsmScrtempScript'
