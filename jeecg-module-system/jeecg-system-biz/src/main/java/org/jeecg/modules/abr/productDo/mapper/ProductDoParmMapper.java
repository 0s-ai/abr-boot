package org.jeecg.modules.abr.productDo.mapper;

import java.util.List;
import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 执行参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ProductDoParmMapper extends BaseMapper<ProductDoParm> {

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
   * @return List<ProductDoParm>
   */
	public List<ProductDoParm> selectByMainId(@Param("mainId") String mainId);
}
