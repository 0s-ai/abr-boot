package org.jeecg.modules.abr.productDoLog.controller;

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
import org.jeecg.modules.abr.productDoLog.entity.ProductDoLog;
import org.jeecg.modules.abr.productDoLog.service.IProductDoLogService;

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
 * @Description: 执行日志
 * @Author: jeecg-boot
 * @Date:   2022-10-29
 * @Version: V1.0
 */
@Api(tags="执行日志")
@RestController
@RequestMapping("/productDoLog/productDoLog")
@Slf4j
public class ProductDoLogController extends JeecgController<ProductDoLog, IProductDoLogService> {
	@Autowired
	private IProductDoLogService productDoLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param productDoLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "执行日志-分页列表查询")
	@ApiOperation(value="执行日志-分页列表查询", notes="执行日志-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ProductDoLog>> queryPageList(ProductDoLog productDoLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProductDoLog> queryWrapper = QueryGenerator.initQueryWrapper(productDoLog, req.getParameterMap());
		Page<ProductDoLog> page = new Page<ProductDoLog>(pageNo, pageSize);
		IPage<ProductDoLog> pageList = productDoLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param productDoLog
	 * @return
	 */
	@AutoLog(value = "执行日志-添加")
	@ApiOperation(value="执行日志-添加", notes="执行日志-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:product_do_log:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ProductDoLog productDoLog) {
		productDoLogService.save(productDoLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param productDoLog
	 * @return
	 */
	@AutoLog(value = "执行日志-编辑")
	@ApiOperation(value="执行日志-编辑", notes="执行日志-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:product_do_log:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ProductDoLog productDoLog) {
		productDoLogService.updateById(productDoLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "执行日志-通过id删除")
	@ApiOperation(value="执行日志-通过id删除", notes="执行日志-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:product_do_log:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		productDoLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "执行日志-批量删除")
	@ApiOperation(value="执行日志-批量删除", notes="执行日志-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:product_do_log:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productDoLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "执行日志-通过id查询")
	@ApiOperation(value="执行日志-通过id查询", notes="执行日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ProductDoLog> queryById(@RequestParam(name="id",required=true) String id) {
		ProductDoLog productDoLog = productDoLogService.getById(id);
		if(productDoLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productDoLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productDoLog
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_do_log:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductDoLog productDoLog) {
        return super.exportXls(request, productDoLog, ProductDoLog.class, "执行日志");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("product_do_log:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProductDoLog.class);
    }

}
