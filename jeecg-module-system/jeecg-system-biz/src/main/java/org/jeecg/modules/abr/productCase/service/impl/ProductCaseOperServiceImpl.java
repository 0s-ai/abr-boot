package org.jeecg.modules.abr.productCase.service.impl;

import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseOperMapper;
import org.jeecg.modules.abr.productCase.service.IProductCaseOperService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 方案操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductCaseOperServiceImpl extends ServiceImpl<ProductCaseOperMapper, ProductCaseOper> implements IProductCaseOperService {
	
	@Autowired
	private ProductCaseOperMapper productCaseOperMapper;
	
	@Override
	public List<ProductCaseOper> selectByMainId(String mainId) {
		return productCaseOperMapper.selectByMainId(mainId);
	}
}
