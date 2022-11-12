package org.jeecg.modules.abr.tsmBuCpgroup.controller;

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
import org.jeecg.modules.abr.tsmBuCpgroup.entity.TsmBuCpgroup;
import org.jeecg.modules.abr.tsmBuCpgroup.service.ITsmBuCpgroupService;

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
 * @Description: 备份副本组
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
@Api(tags="备份副本组")
@RestController
@RequestMapping("/tsmBuCpgroup/tsmBuCpgroup")
@Slf4j
public class TsmBuCpgroupController extends JeecgController<TsmBuCpgroup, ITsmBuCpgroupService> {
	@Autowired
	private ITsmBuCpgroupService tsmBuCpgroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmBuCpgroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "备份副本组-分页列表查询")
	@ApiOperation(value="备份副本组-分页列表查询", notes="备份副本组-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmBuCpgroup>> queryPageList(TsmBuCpgroup tsmBuCpgroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmBuCpgroup> queryWrapper = QueryGenerator.initQueryWrapper(tsmBuCpgroup, req.getParameterMap());
		Page<TsmBuCpgroup> page = new Page<TsmBuCpgroup>(pageNo, pageSize);
		IPage<TsmBuCpgroup> pageList = tsmBuCpgroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmBuCpgroup
	 * @return
	 */
	@AutoLog(value = "备份副本组-添加")
	@ApiOperation(value="备份副本组-添加", notes="备份副本组-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_bu_cpgroup:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmBuCpgroup tsmBuCpgroup) {
		tsmBuCpgroupService.save(tsmBuCpgroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmBuCpgroup
	 * @return
	 */
	@AutoLog(value = "备份副本组-编辑")
	@ApiOperation(value="备份副本组-编辑", notes="备份副本组-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_bu_cpgroup:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmBuCpgroup tsmBuCpgroup) {
		tsmBuCpgroupService.updateById(tsmBuCpgroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "备份副本组-通过id删除")
	@ApiOperation(value="备份副本组-通过id删除", notes="备份副本组-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_bu_cpgroup:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmBuCpgroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "备份副本组-批量删除")
	@ApiOperation(value="备份副本组-批量删除", notes="备份副本组-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_bu_cpgroup:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmBuCpgroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "备份副本组-通过id查询")
	@ApiOperation(value="备份副本组-通过id查询", notes="备份副本组-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmBuCpgroup> queryById(@RequestParam(name="id",required=true) String id) {
		TsmBuCpgroup tsmBuCpgroup = tsmBuCpgroupService.getById(id);
		if(tsmBuCpgroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmBuCpgroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmBuCpgroup
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_bu_cpgroup:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmBuCpgroup tsmBuCpgroup) {
        return super.exportXls(request, tsmBuCpgroup, TsmBuCpgroup.class, "备份副本组");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_bu_cpgroup:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmBuCpgroup.class);
    }

}
