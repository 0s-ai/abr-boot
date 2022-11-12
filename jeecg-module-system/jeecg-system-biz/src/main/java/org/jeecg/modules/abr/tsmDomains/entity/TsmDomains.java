package org.jeecg.modules.abr.tsmDomains.entity;

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
 * @Description: 策略域
 * @Author: jeecg-boot
 * @Date:   2022-10-24
 * @Version: V1.0
 */
@Data
@TableName("tsm_domains")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_domains对象", description="策略域")
public class TsmDomains implements Serializable {
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
	/**策略域*/
	@Excel(name = "策略域", width = 15)
    @ApiModelProperty(value = "策略域")
    private java.lang.String name;
	/**服务器状态*/
	@Excel(name = "服务器状态", width = 15)
    @ApiModelProperty(value = "服务器状态")
    private java.lang.String serverstatus;
	/**已迁移对象数量*/
	@Excel(name = "已迁移对象数量", width = 15)
    @ApiModelProperty(value = "已迁移对象数量")
    private java.lang.String providesSpmg;
	/**同步标记*/
	@Excel(name = "同步标记", width = 15)
    @ApiModelProperty(value = "同步标记")
    private java.lang.String syncFlag;
	/**客户机数量*/
	@Excel(name = "客户机数量", width = 15)
    @ApiModelProperty(value = "客户机数量")
    private java.lang.String numClients;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
    @ApiModelProperty(value = "软删除标记")
    @TableLogic
    private java.lang.String delFlag;
	/**已归档对象数量*/
	@Excel(name = "已归档对象数量", width = 15)
    @ApiModelProperty(value = "已归档对象数量")
    private java.lang.String providesArch;
	/**管理类数量*/
	@Excel(name = "管理类数量", width = 15)
    @ApiModelProperty(value = "管理类数量")
    private java.lang.String mgmtclassCount;
	/**已备份对象数量*/
	@Excel(name = "已备份对象数量", width = 15)
    @ApiModelProperty(value = "已备份对象数量")
    private java.lang.String providesBkup;
	/**选项集数量*/
	@Excel(name = "选项集数量", width = 15)
    @ApiModelProperty(value = "选项集数量")
    private java.lang.String optsetCount;
	/**调度数量*/
	@Excel(name = "调度数量", width = 15)
    @ApiModelProperty(value = "调度数量")
    private java.lang.String scheduleCount;
	/**默认管理类*/
	@Excel(name = "默认管理类", width = 15)
    @ApiModelProperty(value = "默认管理类")
    private java.lang.String defMc;
	/**备份目标*/
	@Excel(name = "备份目标", width = 15)
    @ApiModelProperty(value = "备份目标")
    private java.lang.String defdestbkup;
	/**归档目标*/
	@Excel(name = "归档目标", width = 15)
    @ApiModelProperty(value = "归档目标")
    private java.lang.String defdestarch;
	/**迁移目标*/
	@Excel(name = "迁移目标", width = 15)
    @ApiModelProperty(value = "迁移目标")
    private java.lang.String defdestspman;
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
