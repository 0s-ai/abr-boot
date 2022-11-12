package org.jeecg.modules.abr.productDo.service;

import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 执行角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface IProductDoRoleService extends IService<ProductDoRole> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<ProductDoRole>
	 */
	public List<ProductDoRole> selectByMainId(String mainId);
}
