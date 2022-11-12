package org.jeecg.modules.abr.productCase.service;

import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 方案操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductCaseOperService extends IService<ProductCaseOper> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<ProductCaseOper>
	 */
	public List<ProductCaseOper> selectByMainId(String mainId);
}
