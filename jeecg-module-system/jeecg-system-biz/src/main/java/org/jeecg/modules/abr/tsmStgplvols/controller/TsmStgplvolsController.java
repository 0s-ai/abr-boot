package org.jeecg.modules.abr.tsmStgplvols.controller;

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
import org.jeecg.modules.abr.tsmStgplvols.entity.TsmStgplvols;
import org.jeecg.modules.abr.tsmStgplvols.service.ITsmStgplvolsService;

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
 * @Description: 存储池卷
 * @Author: jeecg-boot
 * @Date:   2022-10-25
 * @Version: V1.0
 */
@Api(tags="存储池卷")
@RestController
@RequestMapping("/tsmStgplvols/tsmStgplvols")
@Slf4j
public class TsmStgplvolsController extends JeecgController<TsmStgplvols, ITsmStgplvolsService> {
	@Autowired
	private ITsmStgplvolsService tsmStgplvolsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmStgplvols
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "存储池卷-分页列表查询")
	@ApiOperation(value="存储池卷-分页列表查询", notes="存储池卷-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmStgplvols>> queryPageList(TsmStgplvols tsmStgplvols,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmStgplvols> queryWrapper = QueryGenerator.initQueryWrapper(tsmStgplvols, req.getParameterMap());
		Page<TsmStgplvols> page = new Page<TsmStgplvols>(pageNo, pageSize);
		IPage<TsmStgplvols> pageList = tsmStgplvolsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmStgplvols
	 * @return
	 */
	@AutoLog(value = "存储池卷-添加")
	@ApiOperation(value="存储池卷-添加", notes="存储池卷-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_stgplvols:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmStgplvols tsmStgplvols) {
		tsmStgplvolsService.save(tsmStgplvols);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmStgplvols
	 * @return
	 */
	@AutoLog(value = "存储池卷-编辑")
	@ApiOperation(value="存储池卷-编辑", notes="存储池卷-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_stgplvols:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmStgplvols tsmStgplvols) {
		tsmStgplvolsService.updateById(tsmStgplvols);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存储池卷-通过id删除")
	@ApiOperation(value="存储池卷-通过id删除", notes="存储池卷-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_stgplvols:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmStgplvolsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存储池卷-批量删除")
	@ApiOperation(value="存储池卷-批量删除", notes="存储池卷-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_stgplvols:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmStgplvolsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "存储池卷-通过id查询")
	@ApiOperation(value="存储池卷-通过id查询", notes="存储池卷-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmStgplvols> queryById(@RequestParam(name="id",required=true) String id) {
		TsmStgplvols tsmStgplvols = tsmStgplvolsService.getById(id);
		if(tsmStgplvols==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmStgplvols);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmStgplvols
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_stgplvols:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmStgplvols tsmStgplvols) {
        return super.exportXls(request, tsmStgplvols, TsmStgplvols.class, "存储池卷");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_stgplvols:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmStgplvols.class);
    }

}
