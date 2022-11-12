package org.jeecg.modules.abr.aiOperRun.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 操作运行
 * @Author: jeecg-boot
 * @Date:   2022-10-20
 * @Version: V1.0
 */
@Data
@TableName("ai_oper_run")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ai_oper_run对象", description="操作运行")
public class AiOperRun implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**服务器*/
	@Excel(name = "服务器", width = 15, dictTable = "ai_host", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_host", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "服务器")
    private java.lang.String serverId;
	/**凭据*/
	@Excel(name = "凭据", width = 15, dictTable = "ai_cert", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "凭据")
    private java.lang.String certId;
	/**运行操作*/
	@Excel(name = "运行操作", width = 15)
    @ApiModelProperty(value = "运行操作")
    private java.lang.String operId;
	/**操作变量*/
	@Excel(name = "操作变量", width = 15)
    @ApiModelProperty(value = "操作变量")
    private java.lang.String operVar;
	/**运行状态*/
	@Excel(name = "运行状态", width = 15)
    @ApiModelProperty(value = "运行状态")
    private java.lang.String runStatus;
	/**开始时间*/
	@Excel(name = "开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private java.util.Date startTime;
	/**完成时间*/
	@Excel(name = "完成时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "完成时间")
    private java.util.Date endTime;
	/**标准输出*/
	@Excel(name = "标准输出", width = 15)
    @ApiModelProperty(value = "标准输出")
    private java.lang.String stdout;
	/**结果输出*/
	@Excel(name = "结果输出", width = 15)
    @ApiModelProperty(value = "结果输出")
    private java.lang.String output;
	/**耗用时间*/
	@Excel(name = "耗用时间", width = 15)
    @ApiModelProperty(value = "耗用时间")
    private java.lang.String elapsedTime;
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
}
