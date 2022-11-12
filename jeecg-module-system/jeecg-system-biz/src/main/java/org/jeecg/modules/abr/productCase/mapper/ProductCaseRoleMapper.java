package org.jeecg.modules.abr.productCase.mapper;

import java.util.List;
import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 方案角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ProductCaseRoleMapper extends BaseMapper<ProductCaseRole> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<ProductCaseRole>
   */
	public List<ProductCaseRole> selectByMainId(@Param("mainId") String mainId);
}
