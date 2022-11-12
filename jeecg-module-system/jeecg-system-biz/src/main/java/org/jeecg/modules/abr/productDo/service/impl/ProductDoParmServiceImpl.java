package org.jeecg.modules.abr.productDo.service.impl;

import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import org.jeecg.modules.abr.productDo.mapper.ProductDoParmMapper;
import org.jeecg.modules.abr.productDo.service.IProductDoParmService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 执行参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductDoParmServiceImpl extends ServiceImpl<ProductDoParmMapper, ProductDoParm> implements IProductDoParmService {
	
	@Autowired
	private ProductDoParmMapper productDoParmMapper;
	
	@Override
	public List<ProductDoParm> selectByMainId(String mainId) {
		return productDoParmMapper.selectByMainId(mainId);
	}
}
