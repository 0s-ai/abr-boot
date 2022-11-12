package org.jeecg.modules.abr.tsmStgplvols.entity;

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
 * @Description: 存储池卷
 * @Author: jeecg-boot
 * @Date:   2022-10-25
 * @Version: V1.0
 */
@Data
@TableName("tsm_stgplvols")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_stgplvols对象", description="存储池卷")
public class TsmStgplvols implements Serializable {
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
	/**存储池名称*/
	@Excel(name = "存储池名称", width = 15, dictTable = "tsm_stgpools where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tsm_stgpools where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "存储池名称")
    private java.lang.String stgpoolName;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**设备类名称*/
	@Excel(name = "设备类名称", width = 15)
    @ApiModelProperty(value = "设备类名称")
    private java.lang.String devclassName;
	/**容量*/
	@Excel(name = "容量", width = 15)
    @ApiModelProperty(value = "容量")
    private java.lang.String estCapacityName;
	/**使用率*/
	@Excel(name = "使用率", width = 15)
    @ApiModelProperty(value = "使用率")
    private java.lang.String pctUtilized;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String status;
	/**读写权限*/
	@Excel(name = "读写权限", width = 15)
    @ApiModelProperty(value = "读写权限")
    private java.lang.String access;
	/**变更时间*/
	@Excel(name = "变更时间", width = 15)
    @ApiModelProperty(value = "变更时间")
    private java.lang.String chgTime;
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
