package org.jeecg.modules.abr.productHostRes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.abr.productHostRes.entity.ProductHostRes;
import org.jeecg.modules.abr.productHostRes.service.IProductHostResService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 产品主机资源
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Api(tags="产品主机资源")
@RestController
@RequestMapping("/productHostRes/productHostRes")
@Slf4j
public class ProductHostResController extends JeecgController<ProductHostRes, IProductHostResService> {
	@Autowired
	private IProductHostResService productHostResService;
	
	/**
	 * 分页列表查询
	 *
	 * @param productHostRes
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "产品主机资源-分页列表查询")
	@ApiOperation(value="产品主机资源-分页列表查询", notes="产品主机资源-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ProductHostRes>> queryPageList(ProductHostRes productHostRes,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProductHostRes> queryWrapper = QueryGenerator.initQueryWrapper(productHostRes, req.getParameterMap());
		Page<ProductHostRes> page = new Page<ProductHostRes>(pageNo, pageSize);
		IPage<ProductHostRes> pageList = productHostResService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param productHostRes
	 * @return
	 */
	@AutoLog(value = "产品主机资源-添加")
	@ApiOperation(value="产品主机资源-添加", notes="产品主机资源-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:product_host_res:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ProductHostRes productHostRes) {
		productHostResService.save(productHostRes);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param productHostRes
	 * @return
	 */
	@AutoLog(value = "产品主机资源-编辑")
	@ApiOperation(value="产品主机资源-编辑", notes="产品主机资源-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:product_host_res:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ProductHostRes productHostRes) {
		productHostResService.updateById(productHostRes);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品主机资源-通过id删除")
	@ApiOperation(value="产品主机资源-通过id删除", notes="产品主机资源-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:product_host_res:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		productHostResService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产品主机资源-批量删除")
	@ApiOperation(value="产品主机资源-批量删除", notes="产品主机资源-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:product_host_res:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productHostResService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "产品主机资源-通过id查询")
	@ApiOperation(value="产品主机资源-通过id查询", notes="产品主机资源-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ProductHostRes> queryById(@RequestParam(name="id",required=true) String id) {
		ProductHostRes productHostRes = productHostResService.getById(id);
		if(productHostRes==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productHostRes);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productHostRes
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_host_res:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductHostRes productHostRes) {
        return super.exportXls(request, productHostRes, ProductHostRes.class, "产品主机资源");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("product_host_res:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProductHostRes.class);
    }

}
