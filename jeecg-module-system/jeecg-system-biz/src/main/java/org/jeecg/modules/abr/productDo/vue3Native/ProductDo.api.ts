import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/productDo/productDo/list',
  save='/productDo/productDo/add',
  edit='/productDo/productDo/edit',
  deleteOne = '/productDo/productDo/delete',
  deleteBatch = '/productDo/productDo/deleteBatch',
  importExcel = '/productDo/productDo/importExcel',
  exportXls = '/productDo/productDo/exportXls',
  queryDataById = '/productDo/productDo/queryById',
  productDoRoleList = '/productDo/productDo/queryProductDoRoleByMainId',
  productDoParmList = '/productDo/productDo/queryProductDoParmByMainId',
  productDoOperList = '/productDo/productDo/queryProductDoOperByMainId',
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
 * 查询子表数据
 * @param params
 */
export const queryProductDoRoleListByMainId = (id) => defHttp.get({url: Api.productDoRoleList, params:{ id }});
/**
 * 查询子表数据
 * @param params
 */
export const queryProductDoParmListByMainId = (id) => defHttp.get({url: Api.productDoParmList, params:{ id }});
/**
 * 查询子表数据
 * @param params
 */
export const queryProductDoOperListByMainId = (id) => defHttp.get({url: Api.productDoOperList, params:{ id }});

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
* 根据id查询数据
* @param params
*/
export const queryDataById = (id) => defHttp.get({url: Api.queryDataById, params:{ id }});

