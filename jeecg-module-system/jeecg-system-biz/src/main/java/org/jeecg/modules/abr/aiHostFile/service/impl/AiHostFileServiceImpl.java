package org.jeecg.modules.abr.aiHostFile.service.impl;

import org.jeecg.modules.abr.aiHostFile.entity.AiHostFile;
import org.jeecg.modules.abr.aiHostFile.entity.AiHostFlhis;
import org.jeecg.modules.abr.aiHostFile.mapper.AiHostFlhisMapper;
import org.jeecg.modules.abr.aiHostFile.mapper.AiHostFileMapper;
import org.jeecg.modules.abr.aiHostFile.service.IAiHostFileService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 主机文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Service
public class AiHostFileServiceImpl extends ServiceImpl<AiHostFileMapper, AiHostFile> implements IAiHostFileService {

	@Autowired
	private AiHostFileMapper aiHostFileMapper;
	@Autowired
	private AiHostFlhisMapper aiHostFlhisMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(AiHostFile aiHostFile, List<AiHostFlhis> aiHostFlhisList) {
		aiHostFileMapper.insert(aiHostFile);
		if(aiHostFlhisList!=null && aiHostFlhisList.size()>0) {
			for(AiHostFlhis entity:aiHostFlhisList) {
				//外键设置
				entity.setHostFileId(aiHostFile.getId());
				aiHostFlhisMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(AiHostFile aiHostFile,List<AiHostFlhis> aiHostFlhisList) {
		aiHostFileMapper.updateById(aiHostFile);
		
		//1.先删除子表数据
		aiHostFlhisMapper.deleteByMainId(aiHostFile.getId());
		
		//2.子表数据重新插入
		if(aiHostFlhisList!=null && aiHostFlhisList.size()>0) {
			for(AiHostFlhis entity:aiHostFlhisList) {
				//外键设置
				entity.setHostFileId(aiHostFile.getId());
				aiHostFlhisMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		aiHostFlhisMapper.deleteByMainId(id);
		aiHostFileMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			aiHostFlhisMapper.deleteByMainId(id.toString());
			aiHostFileMapper.deleteById(id);
		}
	}
	
}
