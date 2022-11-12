package org.jeecg.modules.abr.productCase.entity;

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
 * @Description: 方案参数
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@ApiModel(value="product_case_parm对象", description="方案参数")
@Data
@TableName("product_case_parm")
public class ProductCaseParm implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**方案*/
    @ApiModelProperty(value = "方案")
    private java.lang.String prodCaseId;
	/**角色编码*/
	@Excel(name = "角色编码", width = 15)
    @ApiModelProperty(value = "角色编码")
    private java.lang.String roleCode;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
    private java.lang.String parmName;
	/**参数描述*/
	@Excel(name = "参数描述", width = 15)
    @ApiModelProperty(value = "参数描述")
    private java.lang.String parmDesc;
	/**参数类型*/
	@Excel(name = "参数类型", width = 15, dicCode = "parm_type")
    @ApiModelProperty(value = "参数类型")
    private java.lang.String parmType;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
    private java.lang.String parmValue;
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
