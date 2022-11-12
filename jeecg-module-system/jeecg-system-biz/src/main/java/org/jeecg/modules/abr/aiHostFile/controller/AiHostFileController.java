package org.jeecg.modules.abr.aiHostFile.controller;

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
import org.jeecg.modules.abr.aiHostFile.entity.AiHostFlhis;
import org.jeecg.modules.abr.aiHostFile.entity.AiHostFile;
import org.jeecg.modules.abr.aiHostFile.vo.AiHostFilePage;
import org.jeecg.modules.abr.aiHostFile.service.IAiHostFileService;
import org.jeecg.modules.abr.aiHostFile.service.IAiHostFlhisService;
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
 * @Description: 主机文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Api(tags="主机文件")
@RestController
@RequestMapping("/aiHostFile/aiHostFile")
@Slf4j
public class AiHostFileController {
	@Autowired
	private IAiHostFileService aiHostFileService;
	@Autowired
	private IAiHostFlhisService aiHostFlhisService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiHostFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "主机文件-分页列表查询")
	@ApiOperation(value="主机文件-分页列表查询", notes="主机文件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiHostFile>> queryPageList(AiHostFile aiHostFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiHostFile> queryWrapper = QueryGenerator.initQueryWrapper(aiHostFile, req.getParameterMap());
		Page<AiHostFile> page = new Page<AiHostFile>(pageNo, pageSize);
		IPage<AiHostFile> pageList = aiHostFileService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiHostFilePage
	 * @return
	 */
	@AutoLog(value = "主机文件-添加")
	@ApiOperation(value="主机文件-添加", notes="主机文件-添加")
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiHostFilePage aiHostFilePage) {
		AiHostFile aiHostFile = new AiHostFile();
		BeanUtils.copyProperties(aiHostFilePage, aiHostFile);
		aiHostFileService.saveMain(aiHostFile, aiHostFilePage.getAiHostFlhisList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiHostFilePage
	 * @return
	 */
	@AutoLog(value = "主机文件-编辑")
	@ApiOperation(value="主机文件-编辑", notes="主机文件-编辑")
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiHostFilePage aiHostFilePage) {
		AiHostFile aiHostFile = new AiHostFile();
		BeanUtils.copyProperties(aiHostFilePage, aiHostFile);
		AiHostFile aiHostFileEntity = aiHostFileService.getById(aiHostFile.getId());
		if(aiHostFileEntity==null) {
			return Result.error("未找到对应数据");
		}
		aiHostFileService.updateMain(aiHostFile, aiHostFilePage.getAiHostFlhisList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "主机文件-通过id删除")
	@ApiOperation(value="主机文件-通过id删除", notes="主机文件-通过id删除")
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiHostFileService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "主机文件-批量删除")
	@ApiOperation(value="主机文件-批量删除", notes="主机文件-批量删除")
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiHostFileService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "主机文件-通过id查询")
	@ApiOperation(value="主机文件-通过id查询", notes="主机文件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiHostFile> queryById(@RequestParam(name="id",required=true) String id) {
		AiHostFile aiHostFile = aiHostFileService.getById(id);
		if(aiHostFile==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiHostFile);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "主机历史文件-通过主表ID查询")
	@ApiOperation(value="主机历史文件-通过主表ID查询", notes="主机历史文件-通过主表ID查询")
	@GetMapping(value = "/queryAiHostFlhisByMainId")
	public Result<IPage<AiHostFlhis>> queryAiHostFlhisListByMainId(@RequestParam(name="id",required=true) String id) {
		List<AiHostFlhis> aiHostFlhisList = aiHostFlhisService.selectByMainId(id);
		IPage <AiHostFlhis> page = new Page<>();
		page.setRecords(aiHostFlhisList);
		page.setTotal(aiHostFlhisList.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiHostFile
    */
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiHostFile aiHostFile) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<AiHostFile> queryWrapper = QueryGenerator.initQueryWrapper(aiHostFile, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

     //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
           List<String> selectionList = Arrays.asList(selections.split(","));
           queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<AiHostFile>  aiHostFileList = aiHostFileService.list(queryWrapper);

      // Step.3 组装pageList
      List<AiHostFilePage> pageList = new ArrayList<AiHostFilePage>();
      for (AiHostFile main : aiHostFileList) {
          AiHostFilePage vo = new AiHostFilePage();
          BeanUtils.copyProperties(main, vo);
          List<AiHostFlhis> aiHostFlhisList = aiHostFlhisService.selectByMainId(main.getId());
          vo.setAiHostFlhisList(aiHostFlhisList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "主机文件列表");
      mv.addObject(NormalExcelConstants.CLASS, AiHostFilePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("主机文件数据", "导出人:"+sysUser.getRealname(), "主机文件"));
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
    //@RequiresPermissions("org.jeecg.modules.abr:ai_host_file:importExcel")
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
              List<AiHostFilePage> list = ExcelImportUtil.importExcel(file.getInputStream(), AiHostFilePage.class, params);
              for (AiHostFilePage page : list) {
                  AiHostFile po = new AiHostFile();
                  BeanUtils.copyProperties(page, po);
                  aiHostFileService.saveMain(po, page.getAiHostFlhisList());
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
