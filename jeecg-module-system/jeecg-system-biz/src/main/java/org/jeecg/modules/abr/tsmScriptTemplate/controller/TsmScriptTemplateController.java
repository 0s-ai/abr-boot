package org.jeecg.modules.abr.tsmScriptTemplate.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempParm;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScrtempScript;
import org.jeecg.modules.abr.tsmScriptTemplate.entity.TsmScriptTemplate;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScriptTemplateService;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScrtempParmService;
import org.jeecg.modules.abr.tsmScriptTemplate.service.ITsmScrtempScriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: 脚本模板
 * @Author: jeecg-boot
 * @Date:   2022-11-06
 * @Version: V1.0
 */
@Api(tags="脚本模板")
@RestController
@RequestMapping("/tsmScriptTemplate/tsmScriptTemplate")
@Slf4j
public class TsmScriptTemplateController extends JeecgController<TsmScriptTemplate, ITsmScriptTemplateService> {

	@Autowired
	private ITsmScriptTemplateService tsmScriptTemplateService;

	@Autowired
	private ITsmScrtempParmService tsmScrtempParmService;

	@Autowired
	private ITsmScrtempScriptService tsmScrtempScriptService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param tsmScriptTemplate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "脚本模板-分页列表查询")
	@ApiOperation(value="脚本模板-分页列表查询", notes="脚本模板-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmScriptTemplate>> queryPageList(TsmScriptTemplate tsmScriptTemplate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmScriptTemplate> queryWrapper = QueryGenerator.initQueryWrapper(tsmScriptTemplate, req.getParameterMap());
		Page<TsmScriptTemplate> page = new Page<TsmScriptTemplate>(pageNo, pageSize);
		IPage<TsmScriptTemplate> pageList = tsmScriptTemplateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param tsmScriptTemplate
     * @return
     */
    @AutoLog(value = "脚本模板-添加")
    @ApiOperation(value="脚本模板-添加", notes="脚本模板-添加")
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody TsmScriptTemplate tsmScriptTemplate) {
        tsmScriptTemplateService.save(tsmScriptTemplate);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param tsmScriptTemplate
     * @return
     */
    @AutoLog(value = "脚本模板-编辑")
    @ApiOperation(value="脚本模板-编辑", notes="脚本模板-编辑")
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody TsmScriptTemplate tsmScriptTemplate) {
        tsmScriptTemplateService.updateById(tsmScriptTemplate);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "脚本模板-通过id删除")
    @ApiOperation(value="脚本模板-通过id删除", notes="脚本模板-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        tsmScriptTemplateService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "脚本模板-批量删除")
    @ApiOperation(value="脚本模板-批量删除", notes="脚本模板-批量删除")
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.tsmScriptTemplateService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmScriptTemplate tsmScriptTemplate) {
        return super.exportXls(request, tsmScriptTemplate, TsmScriptTemplate.class, "脚本模板");
    }

    /**
     * 导入
     * @return
     */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_script_template:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmScriptTemplate.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-参数-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "参数-通过主表ID查询")
	@ApiOperation(value="参数-通过主表ID查询", notes="参数-通过主表ID查询")
	@GetMapping(value = "/listTsmScrtempParmByMainId")
    public Result<IPage<TsmScrtempParm>> listTsmScrtempParmByMainId(TsmScrtempParm tsmScrtempParm,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<TsmScrtempParm> queryWrapper = QueryGenerator.initQueryWrapper(tsmScrtempParm, req.getParameterMap());
        Page<TsmScrtempParm> page = new Page<TsmScrtempParm>(pageNo, pageSize);
        IPage<TsmScrtempParm> pageList = tsmScrtempParmService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param tsmScrtempParm
	 * @return
	 */
	@AutoLog(value = "参数-添加")
	@ApiOperation(value="参数-添加", notes="参数-添加")
	@PostMapping(value = "/addTsmScrtempParm")
	public Result<String> addTsmScrtempParm(@RequestBody TsmScrtempParm tsmScrtempParm) {
		tsmScrtempParmService.save(tsmScrtempParm);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param tsmScrtempParm
	 * @return
	 */
	@AutoLog(value = "参数-编辑")
	@ApiOperation(value="参数-编辑", notes="参数-编辑")
	@RequestMapping(value = "/editTsmScrtempParm", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editTsmScrtempParm(@RequestBody TsmScrtempParm tsmScrtempParm) {
		tsmScrtempParmService.updateById(tsmScrtempParm);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数-通过id删除")
	@ApiOperation(value="参数-通过id删除", notes="参数-通过id删除")
	@DeleteMapping(value = "/deleteTsmScrtempParm")
	public Result<String> deleteTsmScrtempParm(@RequestParam(name="id",required=true) String id) {
		tsmScrtempParmService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "参数-批量删除")
	@ApiOperation(value="参数-批量删除", notes="参数-批量删除")
	@DeleteMapping(value = "/deleteBatchTsmScrtempParm")
	public Result<String> deleteBatchTsmScrtempParm(@RequestParam(name="ids",required=true) String ids) {
	    this.tsmScrtempParmService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportTsmScrtempParm")
    public ModelAndView exportTsmScrtempParm(HttpServletRequest request, TsmScrtempParm tsmScrtempParm) {
		 // Step.1 组装查询条件
		 QueryWrapper<TsmScrtempParm> queryWrapper = QueryGenerator.initQueryWrapper(tsmScrtempParm, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<TsmScrtempParm> pageList = tsmScrtempParmService.list(queryWrapper);
		 List<TsmScrtempParm> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "参数");
		 mv.addObject(NormalExcelConstants.CLASS, TsmScrtempParm.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("参数报表", "导出人:" + sysUser.getRealname(), "参数"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importTsmScrtempParm/{mainId}")
    public Result<?> importTsmScrtempParm(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<TsmScrtempParm> list = ExcelImportUtil.importExcel(file.getInputStream(), TsmScrtempParm.class, params);
				 for (TsmScrtempParm temp : list) {
                    temp.setTsmScrtempId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 tsmScrtempParmService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-参数-end----------------------------------------------*/

    /*--------------------------------子表处理-脚本-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "脚本-通过主表ID查询")
	@ApiOperation(value="脚本-通过主表ID查询", notes="脚本-通过主表ID查询")
	@GetMapping(value = "/listTsmScrtempScriptByMainId")
    public Result<IPage<TsmScrtempScript>> listTsmScrtempScriptByMainId(TsmScrtempScript tsmScrtempScript,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<TsmScrtempScript> queryWrapper = QueryGenerator.initQueryWrapper(tsmScrtempScript, req.getParameterMap());
        Page<TsmScrtempScript> page = new Page<TsmScrtempScript>(pageNo, pageSize);
        IPage<TsmScrtempScript> pageList = tsmScrtempScriptService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param tsmScrtempScript
	 * @return
	 */
	@AutoLog(value = "脚本-添加")
	@ApiOperation(value="脚本-添加", notes="脚本-添加")
	@PostMapping(value = "/addTsmScrtempScript")
	public Result<String> addTsmScrtempScript(@RequestBody TsmScrtempScript tsmScrtempScript) {
		tsmScrtempScriptService.save(tsmScrtempScript);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param tsmScrtempScript
	 * @return
	 */
	@AutoLog(value = "脚本-编辑")
	@ApiOperation(value="脚本-编辑", notes="脚本-编辑")
	@RequestMapping(value = "/editTsmScrtempScript", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editTsmScrtempScript(@RequestBody TsmScrtempScript tsmScrtempScript) {
		tsmScrtempScriptService.updateById(tsmScrtempScript);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "脚本-通过id删除")
	@ApiOperation(value="脚本-通过id删除", notes="脚本-通过id删除")
	@DeleteMapping(value = "/deleteTsmScrtempScript")
	public Result<String> deleteTsmScrtempScript(@RequestParam(name="id",required=true) String id) {
		tsmScrtempScriptService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "脚本-批量删除")
	@ApiOperation(value="脚本-批量删除", notes="脚本-批量删除")
	@DeleteMapping(value = "/deleteBatchTsmScrtempScript")
	public Result<String> deleteBatchTsmScrtempScript(@RequestParam(name="ids",required=true) String ids) {
	    this.tsmScrtempScriptService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportTsmScrtempScript")
    public ModelAndView exportTsmScrtempScript(HttpServletRequest request, TsmScrtempScript tsmScrtempScript) {
		 // Step.1 组装查询条件
		 QueryWrapper<TsmScrtempScript> queryWrapper = QueryGenerator.initQueryWrapper(tsmScrtempScript, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<TsmScrtempScript> pageList = tsmScrtempScriptService.list(queryWrapper);
		 List<TsmScrtempScript> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "脚本");
		 mv.addObject(NormalExcelConstants.CLASS, TsmScrtempScript.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("脚本报表", "导出人:" + sysUser.getRealname(), "脚本"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importTsmScrtempScript/{mainId}")
    public Result<?> importTsmScrtempScript(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
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
				 List<TsmScrtempScript> list = ExcelImportUtil.importExcel(file.getInputStream(), TsmScrtempScript.class, params);
				 for (TsmScrtempScript temp : list) {
                    temp.setTsmScrtempId(mainId);
				 }
				 long start = System.currentTimeMillis();
				 tsmScrtempScriptService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-脚本-end----------------------------------------------*/




}
