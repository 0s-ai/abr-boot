package org.jeecg.modules.abr.productDo.service;

import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 执行操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductDoOperService extends IService<ProductDoOper> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<ProductDoOper>
	 */
	public List<ProductDoOper> selectByMainId(String mainId);
}
