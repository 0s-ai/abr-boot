package org.jeecg.modules.abr.tsmBuCpgroup.entity;

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
 * @Description: 备份副本组
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
@Data
@TableName("tsm_bu_cpgroup")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_bu_cpgroup对象", description="备份副本组")
public class TsmBuCpgroup implements Serializable {
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
	@Excel(name = "策略域", width = 15, dictTable = "tsm_domains where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tsm_domains where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "策略域")
    private java.lang.String domainName;
	/**策略集*/
	@Excel(name = "策略集", width = 15, dictTable = "tsm_policyset where del_flag=0", dicText = "set_name", dicCode = "id")
	@Dict(dictTable = "tsm_policyset where del_flag=0", dicText = "set_name", dicCode = "id")
    @ApiModelProperty(value = "策略集")
    private java.lang.String setName;
	/**类名称*/
	@Excel(name = "类名称", width = 15, dictTable = "tsm_mgmt where del_flag=0", dicText = "class_name", dicCode = "id")
	@Dict(dictTable = "tsm_mgmt where del_flag=0", dicText = "class_name", dicCode = "id")
    @ApiModelProperty(value = "类名称")
    private java.lang.String className;
	/**目标存储池*/
	@Excel(name = "目标存储池", width = 15, dictTable = "tsm_stgpools where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tsm_stgpools where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "目标存储池")
    private java.lang.String destPool;
	/**备份副本组名称*/
	@Excel(name = "备份副本组名称", width = 15)
    @ApiModelProperty(value = "备份副本组名称")
    private java.lang.String name;
	/**保留版本数*/
	@Excel(name = "保留版本数", width = 15)
    @ApiModelProperty(value = "保留版本数")
    private java.lang.String verexists;
	/**删除对象保留版本数*/
	@Excel(name = "删除对象保留版本数", width = 15)
    @ApiModelProperty(value = "删除对象保留版本数")
    private java.lang.String verdeleted;
	/**保留天数*/
	@Excel(name = "保留天数", width = 15)
    @ApiModelProperty(value = "保留天数")
    private java.lang.String retextra;
	/**保留唯一版本天数*/
	@Excel(name = "保留唯一版本天数", width = 15)
    @ApiModelProperty(value = "保留唯一版本天数")
    private java.lang.String retonly;
	/**同步标记*/
	@Excel(name = "同步标记", width = 15)
    @ApiModelProperty(value = "同步标记")
    private java.lang.String syncFlag;
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
