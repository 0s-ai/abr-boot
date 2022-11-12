package org.jeecg.modules.abr.tsmStgpools.entity;

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
 * @Description: 存储池
 * @Author: jeecg-boot
 * @Date:   2022-10-25
 * @Version: V1.0
 */
@Data
@TableName("tsm_stgpools")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_stgpools对象", description="存储池")
public class TsmStgpools implements Serializable {
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
	/**存储池类型*/
	@Excel(name = "存储池类型", width = 15, dicCode = "stgpool_type")
	@Dict(dicCode = "stgpool_type")
    @ApiModelProperty(value = "存储池类型")
    private java.lang.String poolType;
	/**最大容量*/
	@Excel(name = "最大容量", width = 15)
    @ApiModelProperty(value = "最大容量")
    private java.lang.String maxSize;
	/**设备类*/
	@Excel(name = "设备类", width = 15)
    @ApiModelProperty(value = "设备类")
    private java.lang.String devClass;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**存储类型*/
	@Excel(name = "存储类型", width = 15)
    @ApiModelProperty(value = "存储类型")
    private java.lang.String stgType;
	/**云连接名称*/
	@Excel(name = "云连接名称", width = 15)
    @ApiModelProperty(value = "云连接名称")
    private java.lang.String cloudConn;
	/**云存储类*/
	@Excel(name = "云存储类", width = 15)
    @ApiModelProperty(value = "云存储类")
    private java.lang.String cloudStgc;
	/**使用率*/
	@Excel(name = "使用率", width = 15)
    @ApiModelProperty(value = "使用率")
    private java.lang.String pctUtilize;
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
