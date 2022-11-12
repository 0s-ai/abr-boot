package org.jeecg.modules.abr.productDo.service;

import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import org.jeecg.modules.abr.productDo.entity.ProductDo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 方案执行
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductDoService extends IService<ProductDo> {

	/**
	 * 添加一对多
	 *
	 * @param productDo
	 * @param productDoRoleList
	 * @param productDoParmList
	 * @param productDoOperList
	 */
	public void saveMain(ProductDo productDo,List<ProductDoRole> productDoRoleList,List<ProductDoParm> productDoParmList,List<ProductDoOper> productDoOperList) ;
	
	/**
	 * 修改一对多
	 *
   * @param productDo
   * @param productDoRoleList
   * @param productDoParmList
   * @param productDoOperList
	 */
	public void updateMain(ProductDo productDo,List<ProductDoRole> productDoRoleList,List<ProductDoParm> productDoParmList,List<ProductDoOper> productDoOperList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
