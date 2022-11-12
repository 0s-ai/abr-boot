package org.jeecg.modules.abr.productDo.service.impl;

import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import org.jeecg.modules.abr.productDo.mapper.ProductDoOperMapper;
import org.jeecg.modules.abr.productDo.service.IProductDoOperService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 执行操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Service
public class ProductDoOperServiceImpl extends ServiceImpl<ProductDoOperMapper, ProductDoOper> implements IProductDoOperService {
	
	@Autowired
	private ProductDoOperMapper productDoOperMapper;
	
	@Override
	public List<ProductDoOper> selectByMainId(String mainId) {
		return productDoOperMapper.selectByMainId(mainId);
	}
}
