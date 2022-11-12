package org.jeecg.modules.abr.productDoLog.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: 执行日志
 * @Author: jeecg-boot
 * @Date:   2022-10-29
 * @Version: V1.0
 */
@Data
@TableName("product_do_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="product_do_log对象", description="执行日志")
public class ProductDoLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**执行方案*/
	@Excel(name = "执行方案", width = 15, dictTable = "product_do where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "product_do where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行方案")
    private java.lang.String prodDoId;
	/**执行序号*/
	@Excel(name = "执行序号", width = 15)
    @ApiModelProperty(value = "执行序号")
    private java.lang.String doNumber;
	/**执行标题*/
	@Excel(name = "执行标题", width = 15)
    @ApiModelProperty(value = "执行标题")
    private java.lang.String doTitle;
	/**执行编码*/
	@Excel(name = "执行编码", width = 15)
    @ApiModelProperty(value = "执行编码")
    private java.lang.String doCode;
	/**执行依赖*/
	@Excel(name = "执行依赖", width = 15)
    @ApiModelProperty(value = "执行依赖")
    private java.lang.String doDep;
	/**执行类型*/
	@Excel(name = "执行类型", width = 15, dicCode = "do_type")
	@Dict(dicCode = "do_type")
    @ApiModelProperty(value = "执行类型")
    private java.lang.String doType;
	/**执行人*/
	@Excel(name = "执行人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "执行人")
    private java.lang.String doOwner;
	/**执行主机*/
	@Excel(name = "执行主机", width = 15, dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行主机")
    private java.lang.String doHost;
	/**执行凭据*/
	@Excel(name = "执行凭据", width = 15, dictTable = "ai_cert where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_cert where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行凭据")
    private java.lang.String doCert;
	/**执行变量*/
	@Excel(name = "执行变量", width = 15)
    @ApiModelProperty(value = "执行变量")
    private java.lang.String doVars;
	/**执行状态*/
	@Excel(name = "执行状态", width = 15, dicCode = "do_status")
	@Dict(dicCode = "do_status")
    @ApiModelProperty(value = "执行状态")
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
	/**耗时*/
	@Excel(name = "耗时", width = 15)
    @ApiModelProperty(value = "耗时")
    private java.lang.String elapsedTime;
	/**标准输出*/
	@Excel(name = "标准输出", width = 15)
    @ApiModelProperty(value = "标准输出")
    private java.lang.String stdout;
	/**输出*/
	@Excel(name = "输出", width = 15)
    @ApiModelProperty(value = "输出")
    private java.lang.String output;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
    @ApiModelProperty(value = "软删除标记")
    @TableLogic
    private java.lang.String delFlag;
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
