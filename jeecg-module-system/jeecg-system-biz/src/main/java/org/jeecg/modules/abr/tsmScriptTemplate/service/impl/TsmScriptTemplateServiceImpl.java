package org.jeecg.modules.abr.tsmScriptTemplate.service.impl;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScriptTemplate;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempScript;
import org.jeecg.modules.abr.tsmScriptTemplate.mapper.TsmScrtempParmMapper;
import org.jeecg.modules.abr.tsmScriptTemplate.mapper.TsmScrtempScriptMapper;
import org.jeecg.modules.abr.tsmScriptTemplate.mapper.TsmScriptTemplateMapper;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScriptTemplateService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 脚本模板
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
@Service
public class TsmScriptTemplateServiceImpl extends ServiceImpl<TsmScriptTemplateMapper, TsmScriptTemplate> implements ITsmScriptTemplateService {

	@Autowired
	private TsmScriptTemplateMapper tsmScriptTemplateMapper;
	@Autowired
	private TsmScrtempParmMapper tsmScrtempParmMapper;
	@Autowired
	private TsmScrtempScriptMapper tsmScrtempScriptMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		tsmScrtempParmMapper.deleteByMainId(id);
		tsmScrtempScriptMapper.deleteByMainId(id);
		tsmScriptTemplateMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			tsmScrtempParmMapper.deleteByMainId(id.toString());
			tsmScrtempScriptMapper.deleteByMainId(id.toString());
			tsmScriptTemplateMapper.deleteById(id);
		}
	}
	
}
