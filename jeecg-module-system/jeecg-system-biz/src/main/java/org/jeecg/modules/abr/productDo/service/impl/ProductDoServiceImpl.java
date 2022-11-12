package org.jeecg.modules.abr.productDo.service.impl;

import org.jeecg.modules.abr.productDo.entity.ProductDo;
import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import org.jeecg.modules.abr.productDo.mapper.ProductDoRoleMapper;
import org.jeecg.modules.abr.productDo.mapper.ProductDoParmMapper;
import org.jeecg.modules.abr.productDo.mapper.ProductDoOperMapper;
import org.jeecg.modules.abr.productDo.mapper.ProductDoMapper;
import org.jeecg.modules.abr.productDo.service.IProductDoService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 方案执行
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductDoServiceImpl extends ServiceImpl<ProductDoMapper, ProductDo> implements IProductDoService {

	@Autowired
	private ProductDoMapper productDoMapper;
	@Autowired
	private ProductDoRoleMapper productDoRoleMapper;
	@Autowired
	private ProductDoParmMapper productDoParmMapper;
	@Autowired
	private ProductDoOperMapper productDoOperMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ProductDo productDo, List<ProductDoRole> productDoRoleList,List<ProductDoParm> productDoParmList,List<ProductDoOper> productDoOperList) {
		productDoMapper.insert(productDo);
		if(productDoRoleList!=null && productDoRoleList.size()>0) {
			for(ProductDoRole entity:productDoRoleList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoRoleMapper.insert(entity);
			}
		}
		if(productDoParmList!=null && productDoParmList.size()>0) {
			for(ProductDoParm entity:productDoParmList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoParmMapper.insert(entity);
			}
		}
		if(productDoOperList!=null && productDoOperList.size()>0) {
			for(ProductDoOper entity:productDoOperList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoOperMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ProductDo productDo,List<ProductDoRole> productDoRoleList,List<ProductDoParm> productDoParmList,List<ProductDoOper> productDoOperList) {
		productDoMapper.updateById(productDo);
		
		//1.先删除子表数据
		productDoRoleMapper.deleteByMainId(productDo.getId());
		productDoParmMapper.deleteByMainId(productDo.getId());
		productDoOperMapper.deleteByMainId(productDo.getId());
		
		//2.子表数据重新插入
		if(productDoRoleList!=null && productDoRoleList.size()>0) {
			for(ProductDoRole entity:productDoRoleList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoRoleMapper.insert(entity);
			}
		}
		if(productDoParmList!=null && productDoParmList.size()>0) {
			for(ProductDoParm entity:productDoParmList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoParmMapper.insert(entity);
			}
		}
		if(productDoOperList!=null && productDoOperList.size()>0) {
			for(ProductDoOper entity:productDoOperList) {
				//外键设置
				entity.setProdDoId(productDo.getId());
				productDoOperMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		productDoRoleMapper.deleteByMainId(id);
		productDoParmMapper.deleteByMainId(id);
		productDoOperMapper.deleteByMainId(id);
		productDoMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			productDoRoleMapper.deleteByMainId(id.toString());
			productDoParmMapper.deleteByMainId(id.toString());
			productDoOperMapper.deleteByMainId(id.toString());
			productDoMapper.deleteById(id);
		}
	}
	
}
