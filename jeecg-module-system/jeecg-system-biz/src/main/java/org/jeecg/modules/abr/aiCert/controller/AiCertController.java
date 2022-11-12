package org.jeecg.modules.abr.aiCert.controller;

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
import org.jeecg.modules.abr.aiCert.entity.AiCert;
import org.jeecg.modules.abr.aiCert.service.IAiCertService;

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
 * @Description: 凭据管理
 * @Author: jeecg-boot
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Api(tags="凭据管理")
@RestController
@RequestMapping("/aiCert/aiCert")
@Slf4j
public class AiCertController extends JeecgController<AiCert, IAiCertService> {
	@Autowired
	private IAiCertService aiCertService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiCert
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "凭据管理-分页列表查询")
	@ApiOperation(value="凭据管理-分页列表查询", notes="凭据管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiCert>> queryPageList(AiCert aiCert,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiCert> queryWrapper = QueryGenerator.initQueryWrapper(aiCert, req.getParameterMap());
		Page<AiCert> page = new Page<AiCert>(pageNo, pageSize);
		IPage<AiCert> pageList = aiCertService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiCert
	 * @return
	 */
	@AutoLog(value = "凭据管理-添加")
	@ApiOperation(value="凭据管理-添加", notes="凭据管理-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_cert:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiCert aiCert) {
		aiCertService.save(aiCert);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiCert
	 * @return
	 */
	@AutoLog(value = "凭据管理-编辑")
	@ApiOperation(value="凭据管理-编辑", notes="凭据管理-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_cert:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiCert aiCert) {
		aiCertService.updateById(aiCert);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "凭据管理-通过id删除")
	@ApiOperation(value="凭据管理-通过id删除", notes="凭据管理-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_cert:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiCertService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "凭据管理-批量删除")
	@ApiOperation(value="凭据管理-批量删除", notes="凭据管理-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_cert:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiCertService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "凭据管理-通过id查询")
	@ApiOperation(value="凭据管理-通过id查询", notes="凭据管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiCert> queryById(@RequestParam(name="id",required=true) String id) {
		AiCert aiCert = aiCertService.getById(id);
		if(aiCert==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiCert);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiCert
    */
    //@RequiresPermissions("org.jeecg.modules.abr:ai_cert:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiCert aiCert) {
        return super.exportXls(request, aiCert, AiCert.class, "凭据管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("ai_cert:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiCert.class);
    }

}
