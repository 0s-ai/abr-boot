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
 * @Description: 执行角色
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@ApiModel(value="product_do_role对象", description="执行角色")
@Data
@TableName("product_do_role")
public class ProductDoRole implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**执行方案*/
    @ApiModelProperty(value = "执行方案")
    private java.lang.String prodDoId;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**内存总量*/
	@Excel(name = "内存总量", width = 15)
    @ApiModelProperty(value = "内存总量")
    private java.lang.String memTotal;
	/**最低内存总量*/
	@Excel(name = "最低内存总量", width = 15)
    @ApiModelProperty(value = "最低内存总量")
    private java.lang.String memTotalMin;
	/**解析结果*/
	@Excel(name = "解析结果", width = 15)
    @ApiModelProperty(value = "解析结果")
    private java.lang.String analysisResult;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String note;
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
	/**产品版本*/
	@Excel(name = "产品版本", width = 15, dictTable = "product_version", dicText = "version", dicCode = "id")
    @ApiModelProperty(value = "产品版本")
    private java.lang.String prodVerId;
	/**执行服务器*/
	@Excel(name = "执行服务器", width = 15, dictTable = "ai_host", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行服务器")
    private java.lang.String doHostId;
	/**执行凭据*/
	@Excel(name = "执行凭据", width = 15, dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "执行凭据")
    private java.lang.String doCertId;
	/**执行人*/
	@Excel(name = "执行人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "执行人")
    private java.lang.String doOwner;
	/**操作系统类型*/
	@Excel(name = "操作系统类型", width = 15)
    @ApiModelProperty(value = "操作系统类型")
    private java.lang.String osType;
	/**操作系统版本*/
	@Excel(name = "操作系统版本", width = 15)
    @ApiModelProperty(value = "操作系统版本")
    private java.lang.String osLevel;
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
