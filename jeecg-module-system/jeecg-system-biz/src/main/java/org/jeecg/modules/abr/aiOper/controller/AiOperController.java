package org.jeecg.modules.abr.aiOper.controller;

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
import org.jeecg.modules.abr.aiOper.entity.AiOper;
import org.jeecg.modules.abr.aiOper.service.IAiOperService;

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
 * @Description: 操作定义
 * @Author: jeecg-boot
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Api(tags="操作定义")
@RestController
@RequestMapping("/aiOper/aiOper")
@Slf4j
public class AiOperController extends JeecgController<AiOper, IAiOperService> {
	@Autowired
	private IAiOperService aiOperService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiOper
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "操作定义-分页列表查询")
	@ApiOperation(value="操作定义-分页列表查询", notes="操作定义-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiOper>> queryPageList(AiOper aiOper,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiOper> queryWrapper = QueryGenerator.initQueryWrapper(aiOper, req.getParameterMap());
		Page<AiOper> page = new Page<AiOper>(pageNo, pageSize);
		IPage<AiOper> pageList = aiOperService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiOper
	 * @return
	 */
	@AutoLog(value = "操作定义-添加")
	@ApiOperation(value="操作定义-添加", notes="操作定义-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiOper aiOper) {
		aiOperService.save(aiOper);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiOper
	 * @return
	 */
	@AutoLog(value = "操作定义-编辑")
	@ApiOperation(value="操作定义-编辑", notes="操作定义-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiOper aiOper) {
		aiOperService.updateById(aiOper);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作定义-通过id删除")
	@ApiOperation(value="操作定义-通过id删除", notes="操作定义-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiOperService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "操作定义-批量删除")
	@ApiOperation(value="操作定义-批量删除", notes="操作定义-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiOperService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "操作定义-通过id查询")
	@ApiOperation(value="操作定义-通过id查询", notes="操作定义-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiOper> queryById(@RequestParam(name="id",required=true) String id) {
		AiOper aiOper = aiOperService.getById(id);
		if(aiOper==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiOper);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiOper
    */
    //@RequiresPermissions("org.jeecg.modules.abr:ai_oper:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiOper aiOper) {
        return super.exportXls(request, aiOper, AiOper.class, "操作定义");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("ai_oper:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiOper.class);
    }

}
