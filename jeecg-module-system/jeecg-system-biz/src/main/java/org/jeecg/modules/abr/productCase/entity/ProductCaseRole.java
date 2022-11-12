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
 * @Description: 方案角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@ApiModel(value="product_case_role对象", description="方案角色")
@Data
@TableName("product_case_role")
public class ProductCaseRole implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**方案*/
    @ApiModelProperty(value = "方案")
    private java.lang.String prodCaseId;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**编码*/
	@Excel(name = "编码", width = 15)
    @ApiModelProperty(value = "编码")
    private java.lang.String code;
	/**产品*/
	@Excel(name = "产品", width = 15, dictTable = "product", dicText = "short_name", dicCode = "id")
    @ApiModelProperty(value = "产品")
    private java.lang.String productId;
	/**最低操作系统版本*/
	@Excel(name = "最低操作系统版本", width = 15)
    @ApiModelProperty(value = "最低操作系统版本")
    private java.lang.String osLevelMin;
	/**最低内存总量*/
	@Excel(name = "最低内存总量", width = 15)
    @ApiModelProperty(value = "最低内存总量")
    private java.lang.String memTotalMin;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String note;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
    @ApiModelProperty(value = "软删除标记")
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
