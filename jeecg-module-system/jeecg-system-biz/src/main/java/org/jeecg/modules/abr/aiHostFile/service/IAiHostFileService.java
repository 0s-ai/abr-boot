package org.jeecg.modules.abr.aiHostFile.service;

import org.jeecg.modules.abr.aiHostFile.entity.AiHostFlhis;
import org.jeecg.modules.abr.aiHostFile.entity.AiHostFile;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 主机文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
public interface IAiHostFileService extends IService<AiHostFile> {

	/**
	 * 添加一对多
	 *
	 * @param aiHostFile
	 * @param aiHostFlhisList
	 */
	public void saveMain(AiHostFile aiHostFile,List<AiHostFlhis> aiHostFlhisList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param aiHostFile
	 * @param aiHostFlhisList
	 */
	public void updateMain(AiHostFile aiHostFile,List<AiHostFlhis> aiHostFlhisList);
	
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
