package org.jeecg.modules.abr.productCase.service.impl;

import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import org.jeecg.modules.abr.productCase.mapper.ProductCaseParmMapper;
import org.jeecg.modules.abr.productCase.service.IProductCaseParmService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 方案参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductCaseParmServiceImpl extends ServiceImpl<ProductCaseParmMapper, ProductCaseParm> implements IProductCaseParmService {
	
	@Autowired
	private ProductCaseParmMapper productCaseParmMapper;
	
	@Override
	public List<ProductCaseParm> selectByMainId(String mainId) {
		return productCaseParmMapper.selectByMainId(mainId);
	}
}
