package org.jeecg.modules.abr.tsmArCpgroup.controller;

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
import org.jeecg.modules.abr.tsmArCpgroup.entity.TsmArCpgroup;
import org.jeecg.modules.abr.tsmArCpgroup.service.ITsmArCpgroupService;

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
 * @Description: 归档副本组
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
@Api(tags="归档副本组")
@RestController
@RequestMapping("/tsmArCpgroup/tsmArCpgroup")
@Slf4j
public class TsmArCpgroupController extends JeecgController<TsmArCpgroup, ITsmArCpgroupService> {
	@Autowired
	private ITsmArCpgroupService tsmArCpgroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmArCpgroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "归档副本组-分页列表查询")
	@ApiOperation(value="归档副本组-分页列表查询", notes="归档副本组-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmArCpgroup>> queryPageList(TsmArCpgroup tsmArCpgroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmArCpgroup> queryWrapper = QueryGenerator.initQueryWrapper(tsmArCpgroup, req.getParameterMap());
		Page<TsmArCpgroup> page = new Page<TsmArCpgroup>(pageNo, pageSize);
		IPage<TsmArCpgroup> pageList = tsmArCpgroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmArCpgroup
	 * @return
	 */
	@AutoLog(value = "归档副本组-添加")
	@ApiOperation(value="归档副本组-添加", notes="归档副本组-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_ar_cpgroup:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmArCpgroup tsmArCpgroup) {
		tsmArCpgroupService.save(tsmArCpgroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmArCpgroup
	 * @return
	 */
	@AutoLog(value = "归档副本组-编辑")
	@ApiOperation(value="归档副本组-编辑", notes="归档副本组-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_ar_cpgroup:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmArCpgroup tsmArCpgroup) {
		tsmArCpgroupService.updateById(tsmArCpgroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "归档副本组-通过id删除")
	@ApiOperation(value="归档副本组-通过id删除", notes="归档副本组-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_ar_cpgroup:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmArCpgroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "归档副本组-批量删除")
	@ApiOperation(value="归档副本组-批量删除", notes="归档副本组-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_ar_cpgroup:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmArCpgroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "归档副本组-通过id查询")
	@ApiOperation(value="归档副本组-通过id查询", notes="归档副本组-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmArCpgroup> queryById(@RequestParam(name="id",required=true) String id) {
		TsmArCpgroup tsmArCpgroup = tsmArCpgroupService.getById(id);
		if(tsmArCpgroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmArCpgroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmArCpgroup
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_ar_cpgroup:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmArCpgroup tsmArCpgroup) {
        return super.exportXls(request, tsmArCpgroup, TsmArCpgroup.class, "归档副本组");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_ar_cpgroup:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmArCpgroup.class);
    }

}
