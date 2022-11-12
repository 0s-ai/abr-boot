package org.jeecg.modules.abr.productCase.service;

import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 方案参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductCaseParmService extends IService<ProductCaseParm> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<ProductCaseParm>
	 */
	public List<ProductCaseParm> selectByMainId(String mainId);
}
