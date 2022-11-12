package org.jeecg.modules.abr.productCase.service.impl;

import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseRoleMapper;
import org.jeecg.modules.abr.productCase.service.IProductCaseRoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 方案角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductCaseRoleServiceImpl extends ServiceImpl<ProductCaseRoleMapper, ProductCaseRole> implements IProductCaseRoleService {
	
	@Autowired
	private ProductCaseRoleMapper productCaseRoleMapper;
	
	@Override
	public List<ProductCaseRole> selectByMainId(String mainId) {
		return productCaseRoleMapper.selectByMainId(mainId);
	}
}
