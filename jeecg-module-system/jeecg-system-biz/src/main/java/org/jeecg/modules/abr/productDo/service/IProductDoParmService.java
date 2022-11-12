package org.jeecg.modules.abr.productDo.service;

import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 执行参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductDoParmService extends IService<ProductDoParm> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<ProductDoParm>
	 */
	public List<ProductDoParm> selectByMainId(String mainId);
}
