package org.jeecg.modules.abr.tsmScriptTemplate.service;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 参数
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
public interface ITsmScrtempParmService extends IService<TsmScrtempParm> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<TsmScrtempParm>
   */
	public List<TsmScrtempParm> selectByMainId(String mainId);
}
