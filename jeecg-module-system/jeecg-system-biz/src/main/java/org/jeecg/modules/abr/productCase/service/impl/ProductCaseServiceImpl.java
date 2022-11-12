package org.jeecg.modules.abr.productCase.service.impl;

import org.jeecg.modules.abr.productCase.entity.ProductCase;
import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseRoleMapper;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseParmMapper;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseOperMapper;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseMapper;
import org.jeecg.modules.abr.productCase.service.IProductCaseService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 产品方案
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductCaseServiceImpl extends ServiceImpl<ProductCaseMapper, ProductCase> implements IProductCaseService {

	@Autowired
	private ProductCaseMapper productCaseMapper;
	@Autowired
	private ProductCaseRoleMapper productCaseRoleMapper;
	@Autowired
	private ProductCaseParmMapper productCaseParmMapper;
	@Autowired
	private ProductCaseOperMapper productCaseOperMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ProductCase productCase, List<ProductCaseRole> productCaseRoleList,List<ProductCaseParm> productCaseParmList,List<ProductCaseOper> productCaseOperList) {
		productCaseMapper.insert(productCase);
		if(productCaseRoleList!=null && productCaseRoleList.size()>0) {
			for(ProductCaseRole entity:productCaseRoleList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseRoleMapper.insert(entity);
			}
		}
		if(productCaseParmList!=null && productCaseParmList.size()>0) {
			for(ProductCaseParm entity:productCaseParmList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseParmMapper.insert(entity);
			}
		}
		if(productCaseOperList!=null && productCaseOperList.size()>0) {
			for(ProductCaseOper entity:productCaseOperList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseOperMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ProductCase productCase,List<ProductCaseRole> productCaseRoleList,List<ProductCaseParm> productCaseParmList,List<ProductCaseOper> productCaseOperList) {
		productCaseMapper.updateById(productCase);
		
		//1.先删除子表数据
		productCaseRoleMapper.deleteByMainId(productCase.getId());
		productCaseParmMapper.deleteByMainId(productCase.getId());
		productCaseOperMapper.deleteByMainId(productCase.getId());
		
		//2.子表数据重新插入
		if(productCaseRoleList!=null && productCaseRoleList.size()>0) {
			for(ProductCaseRole entity:productCaseRoleList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseRoleMapper.insert(entity);
			}
		}
		if(productCaseParmList!=null && productCaseParmList.size()>0) {
			for(ProductCaseParm entity:productCaseParmList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseParmMapper.insert(entity);
			}
		}
		if(productCaseOperList!=null && productCaseOperList.size()>0) {
			for(ProductCaseOper entity:productCaseOperList) {
				//外键设置
				entity.setProdCaseId(productCase.getId());
				productCaseOperMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		productCaseRoleMapper.deleteByMainId(id);
		productCaseParmMapper.deleteByMainId(id);
		productCaseOperMapper.deleteByMainId(id);
		productCaseMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			productCaseRoleMapper.deleteByMainId(id.toString());
			productCaseParmMapper.deleteByMainId(id.toString());
			productCaseOperMapper.deleteByMainId(id.toString());
			productCaseMapper.deleteById(id);
		}
	}
	
}
