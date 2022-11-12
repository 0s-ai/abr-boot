package org.jeecg.modules.abr.productDo.controller;

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
import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import org.jeecg.modules.abr.productDo.entity.ProductDo;
import org.jeecg.modules.abr.productDo.vo.ProductDoPage;
import org.jeecg.modules.abr.productDo.service.IProductDoService;
import org.jeecg.modules.abr.productDo.service.IProductDoRoleService;
import org.jeecg.modules.abr.productDo.service.IProductDoParmService;
import org.jeecg.modules.abr.productDo.service.IProductDoOperService;
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
 * @Description: 方案执行
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="方案执行")
@RestController
@RequestMapping("/productDo/productDo")
@Slf4j
public class ProductDoController {
	@Autowired
	private IProductDoService productDoService;
	@Autowired
	private IProductDoRoleService productDoRoleService;
	@Autowired
	private IProductDoParmService productDoParmService;
	@Autowired
	private IProductDoOperService productDoOperService;
	
	/**
	 * 分页列表查询
	 *
	 * @param productDo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "方案执行-分页列表查询")
	@ApiOperation(value="方案执行-分页列表查询", notes="方案执行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ProductDo>> queryPageList(ProductDo productDo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ProductDo> queryWrapper = QueryGenerator.initQueryWrapper(productDo, req.getParameterMap());
		Page<ProductDo> page = new Page<ProductDo>(pageNo, pageSize);
		IPage<ProductDo> pageList = productDoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param productDoPage
	 * @return
	 */
	@AutoLog(value = "方案执行-添加")
	@ApiOperation(value="方案执行-添加", notes="方案执行-添加")
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ProductDoPage productDoPage) {
		ProductDo productDo = new ProductDo();
		BeanUtils.copyProperties(productDoPage, productDo);
		productDoService.saveMain(productDo, productDoPage.getProductDoRoleList(),productDoPage.getProductDoParmList(),productDoPage.getProductDoOperList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param productDoPage
	 * @return
	 */
	@AutoLog(value = "方案执行-编辑")
	@ApiOperation(value="方案执行-编辑", notes="方案执行-编辑")
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ProductDoPage productDoPage) {
		ProductDo productDo = new ProductDo();
		BeanUtils.copyProperties(productDoPage, productDo);
		ProductDo productDoEntity = productDoService.getById(productDo.getId());
		if(productDoEntity==null) {
			return Result.error("未找到对应数据");
		}
		productDoService.updateMain(productDo, productDoPage.getProductDoRoleList(),productDoPage.getProductDoParmList(),productDoPage.getProductDoOperList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "方案执行-通过id删除")
	@ApiOperation(value="方案执行-通过id删除", notes="方案执行-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		productDoService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "方案执行-批量删除")
	@ApiOperation(value="方案执行-批量删除", notes="方案执行-批量删除")
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productDoService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "方案执行-通过id查询")
	@ApiOperation(value="方案执行-通过id查询", notes="方案执行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ProductDo> queryById(@RequestParam(name="id",required=true) String id) {
		ProductDo productDo = productDoService.getById(id);
		if(productDo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productDo);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "执行角色通过主表ID查询")
	@ApiOperation(value="执行角色主表ID查询", notes="执行角色-通主表ID查询")
	@GetMapping(value = "/queryProductDoRoleByMainId")
	public Result<List<ProductDoRole>> queryProductDoRoleListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductDoRole> productDoRoleList = productDoRoleService.selectByMainId(id);
		return Result.OK(productDoRoleList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "执行参数通过主表ID查询")
	@ApiOperation(value="执行参数主表ID查询", notes="执行参数-通主表ID查询")
	@GetMapping(value = "/queryProductDoParmByMainId")
	public Result<List<ProductDoParm>> queryProductDoParmListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductDoParm> productDoParmList = productDoParmService.selectByMainId(id);
		return Result.OK(productDoParmList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "执行操作通过主表ID查询")
	@ApiOperation(value="执行操作主表ID查询", notes="执行操作-通主表ID查询")
	@GetMapping(value = "/queryProductDoOperByMainId")
	public Result<List<ProductDoOper>> queryProductDoOperListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductDoOper> productDoOperList = productDoOperService.selectByMainId(id);
		return Result.OK(productDoOperList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productDo
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductDo productDo) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ProductDo> queryWrapper = QueryGenerator.initQueryWrapper(productDo, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<ProductDo> productDoList = productDoService.list(queryWrapper);

      // Step.3 组装pageList
      List<ProductDoPage> pageList = new ArrayList<ProductDoPage>();
      for (ProductDo main : productDoList) {
          ProductDoPage vo = new ProductDoPage();
          BeanUtils.copyProperties(main, vo);
          List<ProductDoRole> productDoRoleList = productDoRoleService.selectByMainId(main.getId());
          vo.setProductDoRoleList(productDoRoleList);
          List<ProductDoParm> productDoParmList = productDoParmService.selectByMainId(main.getId());
          vo.setProductDoParmList(productDoParmList);
          List<ProductDoOper> productDoOperList = productDoOperService.selectByMainId(main.getId());
          vo.setProductDoOperList(productDoOperList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "方案执行列表");
      mv.addObject(NormalExcelConstants.CLASS, ProductDoPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("方案执行数据", "导出人:"+sysUser.getRealname(), "方案执行"));
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
    //@RequiresPermissions("org.jeecg.modules.abr:product_do:importExcel")
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
              List<ProductDoPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ProductDoPage.class, params);
              for (ProductDoPage page : list) {
                  ProductDo po = new ProductDo();
                  BeanUtils.copyProperties(page, po);
                  productDoService.saveMain(po, page.getProductDoRoleList(),page.getProductDoParmList(),page.getProductDoOperList());
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
