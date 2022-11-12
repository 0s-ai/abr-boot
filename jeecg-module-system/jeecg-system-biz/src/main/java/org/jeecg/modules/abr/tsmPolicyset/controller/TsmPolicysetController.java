package org.jeecg.modules.abr.tsmPolicyset.controller;

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
import org.jeecg.modules.abr.tsmPolicyset.entity.TsmPolicyset;
import org.jeecg.modules.abr.tsmPolicyset.service.ITsmPolicysetService;

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
 * @Description: 策略集
 * @Author: jeecg-boot
 * @Date:   2022-10-24
 * @Version: V1.0
 */
@Api(tags="策略集")
@RestController
@RequestMapping("/tsmPolicyset/tsmPolicyset")
@Slf4j
public class TsmPolicysetController extends JeecgController<TsmPolicyset, ITsmPolicysetService> {
	@Autowired
	private ITsmPolicysetService tsmPolicysetService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tsmPolicyset
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "策略集-分页列表查询")
	@ApiOperation(value="策略集-分页列表查询", notes="策略集-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TsmPolicyset>> queryPageList(TsmPolicyset tsmPolicyset,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TsmPolicyset> queryWrapper = QueryGenerator.initQueryWrapper(tsmPolicyset, req.getParameterMap());
		Page<TsmPolicyset> page = new Page<TsmPolicyset>(pageNo, pageSize);
		IPage<TsmPolicyset> pageList = tsmPolicysetService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tsmPolicyset
	 * @return
	 */
	@AutoLog(value = "策略集-添加")
	@ApiOperation(value="策略集-添加", notes="策略集-添加")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_policyset:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TsmPolicyset tsmPolicyset) {
		tsmPolicysetService.save(tsmPolicyset);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tsmPolicyset
	 * @return
	 */
	@AutoLog(value = "策略集-编辑")
	@ApiOperation(value="策略集-编辑", notes="策略集-编辑")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_policyset:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TsmPolicyset tsmPolicyset) {
		tsmPolicysetService.updateById(tsmPolicyset);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "策略集-通过id删除")
	@ApiOperation(value="策略集-通过id删除", notes="策略集-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_policyset:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		tsmPolicysetService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "策略集-批量删除")
	@ApiOperation(value="策略集-批量删除", notes="策略集-批量删除")
	//@RequiresPermissions("org.jeecg.modules.abr:tsm_policyset:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tsmPolicysetService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "策略集-通过id查询")
	@ApiOperation(value="策略集-通过id查询", notes="策略集-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TsmPolicyset> queryById(@RequestParam(name="id",required=true) String id) {
		TsmPolicyset tsmPolicyset = tsmPolicysetService.getById(id);
		if(tsmPolicyset==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tsmPolicyset);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tsmPolicyset
    */
    //@RequiresPermissions("org.jeecg.modules.abr:tsm_policyset:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TsmPolicyset tsmPolicyset) {
        return super.exportXls(request, tsmPolicyset, TsmPolicyset.class, "策略集");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("tsm_policyset:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TsmPolicyset.class);
    }

}
