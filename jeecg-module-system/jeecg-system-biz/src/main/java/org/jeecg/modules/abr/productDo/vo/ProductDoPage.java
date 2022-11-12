package org.jeecg.modules.abr.productDo.vo;

import java.util.List;
import org.jeecg.modules.abr.productDo.entity.ProductDo;
import org.jeecg.modules.abr.productDo.entity.ProductDoRole;
import org.jeecg.modules.abr.productDo.entity.ProductDoParm;
import org.jeecg.modules.abr.productDo.entity.ProductDoOper;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 方案执行
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="product_doPage对象", description="方案执行")
public class ProductDoPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**产品*/
	@Excel(name = "产品", width = 15, dictTable = "product", dicText = "name", dicCode = "id")
    @Dict(dictTable = "product", dicText = "name", dicCode = "id")
	@ApiModelProperty(value = "产品")
    private java.lang.String productId;
	/**环境类型*/
	@Excel(name = "环境类型", width = 15, dicCode = "env_type")
    @Dict(dicCode = "env_type")
	@ApiModelProperty(value = "环境类型")
    private java.lang.String envType;
	/**方案*/
	@Excel(name = "方案", width = 15, dictTable = "product_case", dicText = "name", dicCode = "id")
    @Dict(dictTable = "product_case", dicText = "name", dicCode = "id")
	@ApiModelProperty(value = "方案")
    private java.lang.String prodCaseId;
	/**执行名称*/
	@Excel(name = "执行名称", width = 15)
	@ApiModelProperty(value = "执行名称")
    private java.lang.String name;
	/**是否检查兼容性*/
	@Excel(name = "是否检查兼容性", width = 15, dicCode = "is_open")
    @Dict(dicCode = "is_open")
	@ApiModelProperty(value = "是否检查兼容性")
    private java.lang.String isCmptOpened;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
	@ApiModelProperty(value = "软删除标记")
    private java.lang.String delFlag;
	/**耗用时间*/
	@Excel(name = "耗用时间", width = 15)
	@ApiModelProperty(value = "耗用时间")
    private java.lang.String elapsedTime;
	/**执行人*/
	@Excel(name = "执行人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "执行人")
    private java.lang.String doOwner;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
    private java.lang.String note;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "do_status")
    @Dict(dicCode = "do_status")
	@ApiModelProperty(value = "状态")
    private java.lang.String doStatus;
	/**开始时间*/
	@Excel(name = "开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开始时间")
    private java.util.Date startTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结束时间")
    private java.util.Date endTime;
	/**执行日志*/
	@Excel(name = "执行日志", width = 15)
	@ApiModelProperty(value = "执行日志")
    private java.lang.String urlDoLog;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;

	@ExcelCollection(name="执行角色")
	@ApiModelProperty(value = "执行角色")
	private List<ProductDoRole> productDoRoleList;
	@ExcelCollection(name="执行参数")
	@ApiModelProperty(value = "执行参数")
	private List<ProductDoParm> productDoParmList;
	@ExcelCollection(name="执行操作")
	@ApiModelProperty(value = "执行操作")
	private List<ProductDoOper> productDoOperList;

}
