package org.jeecg.modules.abr.productDo.service.impl;

import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import org.jeecg.modules.abr.productDo.mapper.ProductDoRoleMapper;
import org.jeecg.modules.abr.productDo.service.IProductDoRoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 执行角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductDoRoleServiceImpl extends ServiceImpl<ProductDoRoleMapper, ProductDoRole> implements IProductDoRoleService {
	
	@Autowired
	private ProductDoRoleMapper productDoRoleMapper;
	
	@Override
	public List<ProductDoRole> selectByMainId(String mainId) {
		return productDoRoleMapper.selectByMainId(mainId);
	}
}
