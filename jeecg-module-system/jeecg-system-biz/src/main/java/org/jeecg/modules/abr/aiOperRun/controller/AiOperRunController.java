package org.jeecg.modules.abr.aiOperRun.controller;

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
import org.jeecg.modules.abr.aiOperRun.entity.AiOperRun;
import org.jeecg.modules.abr.aiOperRun.service.IAiOperRunService;

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
 * @Description: 操作运行
 * @Author: jeecg-boot
 * @Date:   2022-10-20
 * @Version: V1.0
 */
@Api(tags="操作运行")
@RestController
@RequestMapping("/aiOperRun/aiOperRun")
@Slf4j
public class AiOperRunController extends JeecgController<AiOperRun, IAiOperRunService> {
	@Autowired
	private IAiOperRunService aiOperRunService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiOperRun
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "操作运行-分页列表查询")
	@ApiOperation(value="操作运行-分页列表查询", notes="操作运行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiOperRun>> queryPageList(AiOperRun aiOperRun,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiOperRun> queryWrapper = QueryGenerator.initQueryWrapper(aiOperRun, req.getParameterMap());
		Page<AiOperRun> page = new Page<AiOperRun>(pageNo, pageSize);
		IPage<AiOperRun> pageList = aiOperRunService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiOperRun
	 * @return
	 */
	@AutoLog(value = "操作运行-添加")
	@ApiOperation(value="操作运行-添加", notes="操作运行-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper_run:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiOperRun aiOperRun) {
		aiOperRunService.save(aiOperRun);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiOperRun
	 * @return
	 */
	@AutoLog(value = "操作运行-编辑")
	@ApiOperation(value="操作运行-编辑", notes="操作运行-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper_run:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiOperRun aiOperRun) {
		aiOperRunService.updateById(aiOperRun);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "操作运行-通过id删除")
	@ApiOperation(value="操作运行-通过id删除", notes="操作运行-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper_run:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiOperRunService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "操作运行-批量删除")
	@ApiOperation(value="操作运行-批量删除", notes="操作运行-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:ai_oper_run:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiOperRunService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "操作运行-通过id查询")
	@ApiOperation(value="操作运行-通过id查询", notes="操作运行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiOperRun> queryById(@RequestParam(name="id",required=true) String id) {
		AiOperRun aiOperRun = aiOperRunService.getById(id);
		if(aiOperRun==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiOperRun);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiOperRun
    */
    //@RequiresPermissions("org.jeecg.modules.abr:ai_oper_run:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiOperRun aiOperRun) {
        return super.exportXls(request, aiOperRun, AiOperRun.class, "操作运行");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("ai_oper_run:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiOperRun.class);
    }

}
