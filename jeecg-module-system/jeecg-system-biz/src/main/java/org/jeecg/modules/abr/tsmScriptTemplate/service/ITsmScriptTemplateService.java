package org.jeecg.modules.abr.tsmScriptTemplate.service;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempScript;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScriptTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 脚本模板
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
public interface ITsmScriptTemplateService extends IService<TsmScriptTemplate> {

	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


}
