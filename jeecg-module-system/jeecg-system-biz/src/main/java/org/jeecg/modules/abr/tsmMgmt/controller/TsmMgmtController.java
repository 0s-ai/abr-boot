package org.jeecg.modules.abr.tsmMgmt.controller;

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
import org.jeecg.modules.abr.tsmMgmt.entity.TsmMgmt;
import org.jeecg.modules.abr.tsmMgmt.service.ITsmMgmtService;

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
 * @Description: 管理类
 * @Author: jeecg-boot
 * @Date:   2022-10-24
 * @Version: V1.0
 */
@Api(tags="管理类")
@RestController
@RequestMapping("/tsmMgmt/tsmMgmt")
@Slf4j
public class TsmMgmtController extends JeecgController<TsmMgmt, ITsmMgmtService> {
	@Autowired
	private ITsmMgmtService tsmMgmtService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmMgmt
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "管理类-分页列表查询")
	@ApiOperation(value="管理类-分页列表查询", notes="管理类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmMgmt>> queryPageList(TsmMgmt tsmMgmt,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmMgmt> queryWrapper = QueryGenerator.initQueryWrapper(tsmMgmt, req.getParameterMap());
		Page<TsmMgmt> page = new Page<TsmMgmt>(pageNo, pageSize);
		IPage<TsmMgmt> pageList = tsmMgmtService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmMgmt
	 * @return
	 */
	@AutoLog(value = "管理类-添加")
	@ApiOperation(value="管理类-添加", notes="管理类-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_mgmt:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmMgmt tsmMgmt) {
		tsmMgmtService.save(tsmMgmt);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmMgmt
	 * @return
	 */
	@AutoLog(value = "管理类-编辑")
	@ApiOperation(value="管理类-编辑", notes="管理类-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_mgmt:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmMgmt tsmMgmt) {
		tsmMgmtService.updateById(tsmMgmt);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "管理类-通过id删除")
	@ApiOperation(value="管理类-通过id删除", notes="管理类-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_mgmt:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmMgmtService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "管理类-批量删除")
	@ApiOperation(value="管理类-批量删除", notes="管理类-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_mgmt:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmMgmtService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "管理类-通过id查询")
	@ApiOperation(value="管理类-通过id查询", notes="管理类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmMgmt> queryById(@RequestParam(name="id",required=true) String id) {
		TsmMgmt tsmMgmt = tsmMgmtService.getById(id);
		if(tsmMgmt==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmMgmt);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmMgmt
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_mgmt:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmMgmt tsmMgmt) {
        return super.exportXls(request, tsmMgmt, TsmMgmt.class, "管理类");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_mgmt:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmMgmt.class);
    }

}
