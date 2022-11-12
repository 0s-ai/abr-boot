package org.jeecg.modules.abr.aiHost.entity;

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
 * @Description: 主机配置
 * @Author: jeecg-boot
 * @Date:   2022-10-19
 * @Version: V1.0
 */
@Data
@TableName("ai_host")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ai_host对象", description="主机配置")
public class AiHost implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**ip*/
	@Excel(name = "ip", width = 15)
    @ApiModelProperty(value = "ip")
    private java.lang.String ip;
	/**端口*/
	@Excel(name = "端口", width = 15)
    @ApiModelProperty(value = "端口")
    private java.lang.String port;
	/**服务器类型*/
	@Excel(name = "服务器类型", width = 15, dicCode = "host_type")
	@Dict(dicCode = "host_type")
    @ApiModelProperty(value = "服务器类型")
    private java.lang.String hostType;
	/**环境类型*/
	@Excel(name = "环境类型", width = 15, dicCode = "env_type")
	@Dict(dicCode = "env_type")
    @ApiModelProperty(value = "环境类型")
    private java.lang.String envType;
	/**测试凭据*/
	@Excel(name = "测试凭据", width = 15, dictTable = "ai_cert", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "测试凭据")
    private java.lang.String certTest;
	/**自动化状态*/
	@Excel(name = "自动化状态", width = 15)
    @ApiModelProperty(value = "自动化状态")
    private java.lang.String aiStatus;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String note;
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
