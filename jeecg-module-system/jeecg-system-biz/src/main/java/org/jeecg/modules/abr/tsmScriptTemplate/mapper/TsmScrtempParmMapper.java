package org.jeecg.modules.abr.tsmScriptTemplate.mapper;

import java.util.List;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 参数
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
public interface TsmScrtempParmMapper extends BaseMapper<TsmScrtempParm> {

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
    * @return List<TsmScrtempParm>
    */
	public List<TsmScrtempParm> selectByMainId(@Param("mainId") String mainId);

}
