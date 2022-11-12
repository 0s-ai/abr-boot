package org.jeecg.modules.abr.productCase.service;

import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import org.jeecg.modules.abr.productCase.entity.ProductCase;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 产品方案
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductCaseService extends IService<ProductCase> {

	/**
	 * 添加一对多
	 *
	 * @param productCase
	 * @param productCaseRoleList
	 * @param productCaseParmList
	 * @param productCaseOperList
	 */
	public void saveMain(ProductCase productCase,List<ProductCaseRole> productCaseRoleList,List<ProductCaseParm> productCaseParmList,List<ProductCaseOper> productCaseOperList) ;
	
	/**
	 * 修改一对多
	 *
   * @param productCase
   * @param productCaseRoleList
   * @param productCaseParmList
   * @param productCaseOperList
	 */
	public void updateMain(ProductCase productCase,List<ProductCaseRole> productCaseRoleList,List<ProductCaseParm> productCaseParmList,List<ProductCaseOper> productCaseOperList);
	
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
