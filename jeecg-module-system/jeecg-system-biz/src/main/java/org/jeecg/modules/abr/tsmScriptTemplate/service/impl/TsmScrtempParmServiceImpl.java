package org.jeecg.modules.abr.tsmScriptTemplate.service.impl;

import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import org.jeecg.modules.abr.tsmScriptTemplate.mapper.TsmScrtempParmMapper;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScrtempParmService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 参数
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
@Service
public class TsmScrtempParmServiceImpl extends ServiceImpl<TsmScrtempParmMapper, TsmScrtempParm> implements ITsmScrtempParmService {
	
	@Autowired
	private TsmScrtempParmMapper tsmScrtempParmMapper;
	
	@Override
	public List<TsmScrtempParm> selectByMainId(String mainId) {
		return tsmScrtempParmMapper.selectByMainId(mainId);
	}
}
