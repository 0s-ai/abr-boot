package org.jeecg.modules.abr.productCase.mapper;

import java.util.List;
import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 方案参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ProductCaseParmMapper extends BaseMapper<ProductCaseParm> {

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
   * @return List<ProductCaseParm>
   */
	public List<ProductCaseParm> selectByMainId(@Param("mainId") String mainId);
}
