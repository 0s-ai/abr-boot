package org.jeecg.modules.abr.tsmSched.controller;

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
import org.jeecg.modules.abr.tsmSched.entity.TsmSched;
import org.jeecg.modules.abr.tsmSched.service.ITsmSchedService;

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
 * @Description: 调度
 * @Author: jeecg-boot
 * @Date:   2022-10-31
 * @Version: V1.0
 */
@Api(tags="调度")
@RestController
@RequestMapping("/tsmSched/tsmSched")
@Slf4j
public class TsmSchedController extends JeecgController<TsmSched, ITsmSchedService> {
	@Autowired
	private ITsmSchedService tsmSchedService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmSched
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "调度-分页列表查询")
	@ApiOperation(value="调度-分页列表查询", notes="调度-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmSched>> queryPageList(TsmSched tsmSched,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmSched> queryWrapper = QueryGenerator.initQueryWrapper(tsmSched, req.getParameterMap());
		Page<TsmSched> page = new Page<TsmSched>(pageNo, pageSize);
		IPage<TsmSched> pageList = tsmSchedService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmSched
	 * @return
	 */
	@AutoLog(value = "调度-添加")
	@ApiOperation(value="调度-添加", notes="调度-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_sched:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmSched tsmSched) {
		tsmSchedService.save(tsmSched);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmSched
	 * @return
	 */
	@AutoLog(value = "调度-编辑")
	@ApiOperation(value="调度-编辑", notes="调度-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_sched:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmSched tsmSched) {
		tsmSchedService.updateById(tsmSched);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "调度-通过id删除")
	@ApiOperation(value="调度-通过id删除", notes="调度-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_sched:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmSchedService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "调度-批量删除")
	@ApiOperation(value="调度-批量删除", notes="调度-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_sched:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmSchedService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "调度-通过id查询")
	@ApiOperation(value="调度-通过id查询", notes="调度-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmSched> queryById(@RequestParam(name="id",required=true) String id) {
		TsmSched tsmSched = tsmSchedService.getById(id);
		if(tsmSched==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmSched);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmSched
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_sched:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmSched tsmSched) {
        return super.exportXls(request, tsmSched, TsmSched.class, "调度");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_sched:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmSched.class);
    }

}
