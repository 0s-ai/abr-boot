package org.jeecg.modules.abr.aiHostFile.service;

import org.jeecg.modules.abr.aiHostFile.entity.AiHostFlhis;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 主机历史文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
public interface IAiHostFlhisService extends IService<AiHostFlhis> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<AiHostFlhis>
	 */
	public List<AiHostFlhis> selectByMainId(String mainId);
}
