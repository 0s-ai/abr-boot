package org.jeecg.modules.abr.aiCert.entity;

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
 * @Description: 凭据管理
 * @Author: jeecg-boot
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Data
@TableName("ai_cert")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ai_cert对象", description="凭据管理")
public class AiCert implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**凭据名称*/
	@Excel(name = "凭据名称", width = 15)
    @ApiModelProperty(value = "凭据名称")
    private java.lang.String name;
	/**凭据类型*/
	@Excel(name = "凭据类型", width = 15, dicCode = "cert_type")
	@Dict(dicCode = "cert_type")
    @ApiModelProperty(value = "凭据类型")
    private java.lang.String certType;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
    private java.lang.String username;
	/**凭据密码*/
	@Excel(name = "凭据密码", width = 15)
    @ApiModelProperty(value = "凭据密码")
    private java.lang.String password;
	/**凭据内容*/
	@Excel(name = "凭据内容", width = 15)
    @ApiModelProperty(value = "凭据内容")
    private java.lang.String content;
	/**有效标记*/
	@Excel(name = "有效标记", width = 15, dicCode = "valid_status")
	@Dict(dicCode = "valid_status")
    @ApiModelProperty(value = "有效标记")
    private java.lang.String validStatus;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
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
