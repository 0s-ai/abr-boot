package org.jeecg.modules.abr.tsmNodes.entity;

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
 * @Description: 客户机
 * @Author: jeecg-boot
 * @Date:   2022-10-31
 * @Version: V1.0
 */
@Data
@TableName("tsm_nodes")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_nodes对象", description="客户机")
public class TsmNodes implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**服务器*/
	@Excel(name = "服务器", width = 15, dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "服务器")
    private java.lang.String tsmHostId;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**注册时间*/
	@Excel(name = "注册时间", width = 15)
    @ApiModelProperty(value = "注册时间")
    private java.lang.String regTime;
	/**授予删除归档权限*/
	@Excel(name = "授予删除归档权限", width = 15)
    @ApiModelProperty(value = "授予删除归档权限")
    private java.lang.String archdelete;
	/**同步标记*/
	@Excel(name = "同步标记", width = 15)
    @ApiModelProperty(value = "同步标记")
    private java.lang.String syncFlag;
	/**调度名称*/
	@Excel(name = "调度名称", width = 15, dictTable = "tsm_sched where del_flag=0,name,id", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tsm_sched where del_flag=0,name,id", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "调度名称")
    private java.lang.String sched;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
    @ApiModelProperty(value = "软删除标记")
    @TableLogic
    private java.lang.String delFlag;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private java.lang.String password;
	/**授予删除备份权限*/
	@Excel(name = "授予删除备份权限", width = 15)
    @ApiModelProperty(value = "授予删除备份权限")
    private java.lang.String backdelete;
	/**密码过期天数*/
	@Excel(name = "密码过期天数", width = 15)
    @ApiModelProperty(value = "密码过期天数")
    private java.lang.String passexp;
	/**可用挂载点数量*/
	@Excel(name = "可用挂载点数量", width = 15)
    @ApiModelProperty(value = "可用挂载点数量")
    private java.lang.String maxnummp;
	/**策略域*/
	@Excel(name = "策略域", width = 15, dictTable = "tsm_domains where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tsm_domains where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "策略域")
    private java.lang.String domainName;
	/**主机名*/
	@Excel(name = "主机名", width = 15)
    @ApiModelProperty(value = "主机名")
    private java.lang.String tcpName;
	/**IP*/
	@Excel(name = "IP", width = 15)
    @ApiModelProperty(value = "IP")
    private java.lang.String tcpAddress;
	/**平台类型*/
	@Excel(name = "平台类型", width = 15)
    @ApiModelProperty(value = "平台类型")
    private java.lang.String osType;
	/**操作系统版本*/
	@Excel(name = "操作系统版本", width = 15)
    @ApiModelProperty(value = "操作系统版本")
    private java.lang.String osVersion;
	/**tsm版本*/
	@Excel(name = "tsm版本", width = 15)
    @ApiModelProperty(value = "tsm版本")
    private java.lang.String tsmVersion;
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
