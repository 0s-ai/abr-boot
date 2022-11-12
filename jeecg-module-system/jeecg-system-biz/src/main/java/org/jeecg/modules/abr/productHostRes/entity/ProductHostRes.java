package org.jeecg.modules.abr.productHostRes.entity;

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
 * @Description: 产品主机资源
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Data
@TableName("product_host_res")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="product_host_res对象", description="产品主机资源")
public class ProductHostRes implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**产品*/
	@Excel(name = "产品", width = 15, dictTable = "product", dicText = "short_name", dicCode = "id")
	@Dict(dictTable = "product", dicText = "short_name", dicCode = "id")
    @ApiModelProperty(value = "产品")
    private java.lang.String productId;
	/**主机*/
	@Excel(name = "主机", width = 15, dictTable = "ai_host", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_host", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "主机")
    private java.lang.String hostId;
	/**资源类型*/
	@Excel(name = "资源类型", width = 15, dicCode = "resource_type")
	@Dict(dicCode = "resource_type")
    @ApiModelProperty(value = "资源类型")
    private java.lang.String resourceType;
	/**资源分类*/
	@Excel(name = "资源分类", width = 15, dicCode = "resource_flag")
	@Dict(dicCode = "resource_flag")
    @ApiModelProperty(value = "资源分类")
    private java.lang.String resourceFlag;
	/**资源名称*/
	@Excel(name = "资源名称", width = 15)
    @ApiModelProperty(value = "资源名称")
    private java.lang.String resourceName;
	/**资源编码*/
	@Excel(name = "资源编码", width = 15)
    @ApiModelProperty(value = "资源编码")
    private java.lang.String resourceCode;
	/**资源值*/
	@Excel(name = "资源值", width = 15)
    @ApiModelProperty(value = "资源值")
    private java.lang.String resourceValue;
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
