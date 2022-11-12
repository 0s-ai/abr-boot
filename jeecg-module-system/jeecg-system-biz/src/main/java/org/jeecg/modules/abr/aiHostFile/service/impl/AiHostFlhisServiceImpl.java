package org.jeecg.modules.abr.aiHostFile.service.impl;

import org.jeecg.modules.abr.aiHostFile.entity.AiHostFlhis;
import org.jeecg.modules.abr.aiHostFile.mapper.AiHostFlhisMapper;
import org.jeecg.modules.abr.aiHostFile.service.IAiHostFlhisService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 主机历史文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Service
public class AiHostFlhisServiceImpl extends ServiceImpl<AiHostFlhisMapper, AiHostFlhis> implements IAiHostFlhisService {
	
	@Autowired
	private AiHostFlhisMapper aiHostFlhisMapper;
	
	@Override
	public List<AiHostFlhis> selectByMainId(String mainId) {
		return aiHostFlhisMapper.selectByMainId(mainId);
	}
}
