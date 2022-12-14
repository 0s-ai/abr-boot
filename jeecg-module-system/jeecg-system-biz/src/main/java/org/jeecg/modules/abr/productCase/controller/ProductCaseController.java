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
 * @Description: ????????????
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Api(tags="????????????")
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
	 * ??????????????????
	 *
	 * @param productCase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "????????????-??????????????????")
	@ApiOperation(value="????????????-??????????????????", notes="????????????-??????????????????")
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
	 *   ??????
	 *
	 * @param productCasePage
	 * @return
	 */
	@AutoLog(value = "????????????-??????")
	@ApiOperation(value="????????????-??????", notes="????????????-??????")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ProductCasePage productCasePage) {
		ProductCase productCase = new ProductCase();
		BeanUtils.copyProperties(productCasePage, productCase);
		productCaseService.saveMain(productCase, productCasePage.getProductCaseRoleList(),productCasePage.getProductCaseParmList(),productCasePage.getProductCaseOperList());
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param productCasePage
	 * @return
	 */
	@AutoLog(value = "????????????-??????")
	@ApiOperation(value="????????????-??????", notes="????????????-??????")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ProductCasePage productCasePage) {
		ProductCase productCase = new ProductCase();
		BeanUtils.copyProperties(productCasePage, productCase);
		ProductCase productCaseEntity = productCaseService.getById(productCase.getId());
		if(productCaseEntity==null) {
			return Result.error("?????????????????????");
		}
		productCaseService.updateMain(productCase, productCasePage.getProductCaseRoleList(),productCasePage.getProductCaseParmList(),productCasePage.getProductCaseOperList());
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "????????????-??????id??????")
	@ApiOperation(value="????????????-??????id??????", notes="????????????-??????id??????")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		productCaseService.delMain(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "????????????-????????????")
	@ApiOperation(value="????????????-????????????", notes="????????????-????????????")
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productCaseService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("?????????????????????");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "????????????-??????id??????")
	@ApiOperation(value="????????????-??????id??????", notes="????????????-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<ProductCase> queryById(@RequestParam(name="id",required=true) String id) {
		ProductCase productCase = productCaseService.getById(id);
		if(productCase==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(productCase);

	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/queryProductCaseRoleByMainId")
	public Result<List<ProductCaseRole>> queryProductCaseRoleListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseRole> productCaseRoleList = productCaseRoleService.selectByMainId(id);
		return Result.OK(productCaseRoleList);
	}
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/queryProductCaseParmByMainId")
	public Result<List<ProductCaseParm>> queryProductCaseParmListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseParm> productCaseParmList = productCaseParmService.selectByMainId(id);
		return Result.OK(productCaseParmList);
	}
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "????????????????????????ID??????")
	@ApiOperation(value="??????????????????ID??????", notes="????????????-?????????ID??????")
	@GetMapping(value = "/queryProductCaseOperByMainId")
	public Result<List<ProductCaseOper>> queryProductCaseOperListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ProductCaseOper> productCaseOperList = productCaseOperService.selectByMainId(id);
		return Result.OK(productCaseOperList);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param productCase
    */
    //@RequiresPermissions("org.jeecg.modules.abr:product_case:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductCase productCase) {
      // Step.1 ??????????????????????????????
      QueryWrapper<ProductCase> queryWrapper = QueryGenerator.initQueryWrapper(productCase, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //??????????????????????????????
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 ??????????????????
      List<ProductCase> productCaseList = productCaseService.list(queryWrapper);

      // Step.3 ??????pageList
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

      // Step.4 AutoPoi ??????Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "??????????????????");
      mv.addObject(NormalExcelConstants.CLASS, ProductCasePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("??????????????????", "?????????:"+sysUser.getRealname(), "????????????"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * ??????excel????????????
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
          // ????????????????????????
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
              return Result.OK("?????????????????????????????????:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("??????????????????:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("?????????????????????");
    }

}
