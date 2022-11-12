package org.jeecg.modules.abr.tsmScriptTemplate.service;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempScript;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 脚本
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
public interface ITsmScrtempScriptService extends IService<TsmScrtempScript> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<TsmScrtempScript>
   */
	public List<TsmScrtempScript> selectByMainId(String mainId);
}
