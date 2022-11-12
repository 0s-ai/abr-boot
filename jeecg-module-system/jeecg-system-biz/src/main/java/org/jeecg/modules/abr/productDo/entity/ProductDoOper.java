package org.jeecg.modules.abr.productDo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 执行操作
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@ApiModel(value="product_do_oper对象", description="执行操作")
@Data
@TableName("product_do_oper")
public class ProductDoOper implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**执行方案*/
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
	/**是否禁用*/
	@Excel(name = "是否禁用", width = 15)
    @ApiModelProperty(value = "是否禁用")
    private java.lang.String isEnabled;
	/**执行类型*/
	@Excel(name = "执行类型", width = 15, dicCode = "do_type")
    @ApiModelProperty(value = "执行类型")
    private java.lang.String doType;
	/**执行人*/
	@Excel(name = "执行人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "执行人")
    private java.lang.String doOwner;
	/**执行主机*/
	@Excel(name = "执行主机", width = 15, dictTable = "ai_host", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行主机")
    private java.lang.String doHost;
	/**执行凭据*/
	@Excel(name = "执行凭据", width = 15, dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行凭据")
    private java.lang.String doCert;
	/**执行变量*/
	@Excel(name = "执行变量", width = 15)
    @ApiModelProperty(value = "执行变量")
    private java.lang.String doVars;
	/**执行依赖*/
	@Excel(name = "执行依赖", width = 15)
    @ApiModelProperty(value = "执行依赖")
    private java.lang.String doDep;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private java.lang.Integer orderNum;
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
