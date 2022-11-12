package org.jeecg.modules.abr.productCase.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import org.jeecg.modules.abr.productCase.entity.ProductCase;
import org.jeecg.modules.abr.productCase.vo.ProductCasePage;
import org.jeecg.modules.abr.productCase.service.IProductCaseService;
import org.jeecg.modules.abr.productCase.service.IProductCaseRoleService;
import org.jeecg.modules.abr.productCase.service.IProductCaseParmService;
import org.jeecg.modules.abr.productCase.service.IProductCaseOperService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 产品方案
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="产品方案")
@RestController
@RequestMapping("/productCase/productCase")
@Slf4j
public class ProductCaseController {
	@Autowired
	private IProductCaseService productCaseService;
	@Autowired
	private IProductCaseRoleService productCaseRoleService;
	@Autowired
	private IProductCaseParmService productCaseParmService;
	@Autowired
	private IProductCaseOperService productCaseOperService;
	
	/**
	 * 分页列表查询
	 *
	 * @param productCase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "产品方案-分页列表查询")
	@ApiOperation(value="产品方案-分页列表查询", notes="产品方案-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ProductCase>> queryPageList(ProductCase productCase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProductCase> queryWrapper = QueryGenerator.initQueryWrapper(productCase, req.getParameterMap());
		Page<ProductCase> page = new Page<ProductCase>(pageNo, pageSize);
		IPage<ProductCase> pageList = productCaseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param productCasePage
	 * @return
	 */
	@AutoLog(value = "产品方案-添加")
	@ApiOperation(value="产品方案-添加", notes="产品方案-添加")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ProductCasePage productCasePage) {
		ProductCase productCase = new ProductCase();
		BeanUtils.copyProperties(productCasePage, productCase);
		productCaseService.saveMain(productCase, productCasePage.getProductCaseRoleList(),productCasePage.getProductCaseParmList(),productCasePage.getProductCaseOperList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param productCasePage
	 * @return
	 */
	@AutoLog(value = "产品方案-编辑")
	@ApiOperation(value="产品方案-编辑", notes="产品方案-编辑")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ProductCasePage productCasePage) {
		ProductCase productCase = new ProductCase();
		BeanUtils.copyProperties(productCasePage, productCase);
		ProductCase productCaseEntity = productCaseService.getById(productCase.getId());
		if(productCaseEntity==null) {
			return Result.error("未找到对应数据");
		}
		productCaseService.updateMain(productCase, productCasePage.getProductCaseRoleList(),productCasePage.getProductCaseParmList(),productCasePage.getProductCaseOperList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "产品方案-通过id删除")
	@ApiOperation(value="产品方案-通过id删除", notes="产品方案-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		productCaseService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "产品方案-批量删除")
	@ApiOperation(value="产品方案-批量删除", notes="产品方案-批量删除")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productCaseService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "产品方案-通过id查询")
	@ApiOperation(value="产品方案-通过id查询", notes="产品方案-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ProductCase> queryById(@RequestParam(name="id",required=true) String id) {
		ProductCase productCase = productCaseService.getById(id);
		if(productCase==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productCase);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "方案角色通过主表ID查询")
	@ApiOperation(value="方案角色主表ID查询", notes="方案角色-通主表ID查询")
	@GetMapping(value = "/queryProductCaseRoleByMainId")
	public Result<List<ProductCaseRole>> queryProductCaseRoleListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseRole> productCaseRoleList = productCaseRoleService.selectByMainId(id);
		return Result.OK(productCaseRoleList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "方案参数通过主表ID查询")
	@ApiOperation(value="方案参数主表ID查询", notes="方案参数-通主表ID查询")
	@GetMapping(value = "/queryProductCaseParmByMainId")
	public Result<List<ProductCaseParm>> queryProductCaseParmListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseParm> productCaseParmList = productCaseParmService.selectByMainId(id);
		return Result.OK(productCaseParmList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "方案操作通过主表ID查询")
	@ApiOperation(value="方案操作主表ID查询", notes="方案操作-通主表ID查询")
	@GetMapping(value = "/queryProductCaseOperByMainId")
	public Result<List<ProductCaseOper>> queryProductCaseOperListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseOper> productCaseOperList = productCaseOperService.selectByMainId(id);
		return Result.OK(productCaseOperList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productCase
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductCase productCase) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ProductCase> queryWrapper = QueryGenerator.initQueryWrapper(productCase, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<ProductCase> productCaseList = productCaseService.list(queryWrapper);

      // Step.3 组装pageList
      List<ProductCasePage> pageList = new ArrayList<ProductCasePage>();
      for (ProductCase main : productCaseList) {
          ProductCasePage vo = new ProductCasePage();
          BeanUtils.copyProperties(main, vo);
          List<ProductCaseRole> productCaseRoleList = productCaseRoleService.selectByMainId(main.getId());
          vo.setProductCaseRoleList(productCaseRoleList);
          List<ProductCaseParm> productCaseParmList = productCaseParmService.selectByMainId(main.getId());
          vo.setProductCaseParmList(productCaseParmList);
          List<ProductCaseOper> productCaseOperList = productCaseOperService.selectByMainId(main.getId());
          vo.setProductCaseOperList(productCaseOperList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "产品方案列表");
      mv.addObject(NormalExcelConstants.CLASS, ProductCasePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("产品方案数据", "导出人:"+sysUser.getRealname(), "产品方案"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ProductCasePage> list = ExcelImportUtil.importExcel(file.getInputStream(), ProductCasePage.class, params);
              for (ProductCasePage page : list) {
                  ProductCase po = new ProductCase();
                  BeanUtils.copyProperties(page, po);
                  productCaseService.saveMain(po, page.getProductCaseRoleList(),page.getProductCaseParmList(),page.getProductCaseOperList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
