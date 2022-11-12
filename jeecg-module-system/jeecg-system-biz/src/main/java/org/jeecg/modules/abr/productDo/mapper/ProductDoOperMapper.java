package org.jeecg.modules.abr.productDo.mapper;

import java.util.List;
import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 执行操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
public interface ProductDoOperMapper extends BaseMapper<ProductDoOper> {

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
   * @return List<ProductDoOper>
   */
	public List<ProductDoOper> selectByMainId(@Param("mainId") String mainId);
}
