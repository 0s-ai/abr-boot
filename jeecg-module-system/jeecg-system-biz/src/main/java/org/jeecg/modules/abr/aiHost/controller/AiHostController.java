package org.jeecg.modules.abr.aiHost.controller;

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
import org.jeecg.modules.abr.aiHost.entity.AiHost;
import org.jeecg.modules.abr.aiHost.service.IAiHostService;

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
 * @Description: 主机配置
 * @Author: jeecg-boot
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Api(tags="主机配置")
@RestController
@RequestMapping("/aiHost/aiHost")
@Slf4j
public class AiHostController extends JeecgController<AiHost, IAiHostService> {
	@Autowired
	private IAiHostService aiHostService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiHost
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "主机配置-分页列表查询")
	@ApiOperation(value="主机配置-分页列表查询", notes="主机配置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiHost>> queryPageList(AiHost aiHost,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiHost> queryWrapper = QueryGenerator.initQueryWrapper(aiHost, req.getParameterMap());
		Page<AiHost> page = new Page<AiHost>(pageNo, pageSize);
		IPage<AiHost> pageList = aiHostService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiHost
	 * @return
	 */
	@AutoLog(value = "主机配置-添加")
	@ApiOperation(value="主机配置-添加", notes="主机配置-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_host:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiHost aiHost) {
		aiHostService.save(aiHost);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiHost
	 * @return
	 */
	@AutoLog(value = "主机配置-编辑")
	@ApiOperation(value="主机配置-编辑", notes="主机配置-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_host:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiHost aiHost) {
		aiHostService.updateById(aiHost);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "主机配置-通过id删除")
	@ApiOperation(value="主机配置-通过id删除", notes="主机配置-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_host:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiHostService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "主机配置-批量删除")
	@ApiOperation(value="主机配置-批量删除", notes="主机配置-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_host:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiHostService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "主机配置-通过id查询")
	@ApiOperation(value="主机配置-通过id查询", notes="主机配置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiHost> queryById(@RequestParam(name="id",required=true) String id) {
		AiHost aiHost = aiHostService.getById(id);
		if(aiHost==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiHost);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiHost
    */
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiHost aiHost) {
        return super.exportXls(request, aiHost, AiHost.class, "主机配置");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("ai_host:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiHost.class);
    }

}
