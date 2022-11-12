package org.jeecg.modules.abr.tsmScriptTemplate.service.impl;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempScript;
import org.jeecg.modules.abr.tsmScriptTemplate.mapper.TsmScrtempScriptMapper;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScrtempScriptService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 脚本
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
@Service
public class TsmScrtempScriptServiceImpl extends ServiceImpl<TsmScrtempScriptMapper, TsmScrtempScript> implements ITsmScrtempScriptService {
	
	@Autowired
	private TsmScrtempScriptMapper tsmScrtempScriptMapper;
	
	@Override
	public List<TsmScrtempScript> selectByMainId(String mainId) {
		return tsmScrtempScriptMapper.selectByMainId(mainId);
	}
}
