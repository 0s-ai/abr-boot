package org.jeecg.modules.abr.aiHostFile.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

/**
 * @Description: 主机文件
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@ApiModel(value="ai_host_file对象", description="主机文件")
@Data
@TableName("ai_host_file")
public class AiHostFile implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private java.lang.String title;
	/**标识*/
	@Excel(name = "标识", width = 15)
    @ApiModelProperty(value = "标识")
    private java.lang.String tag;
	/**主机*/
	@Excel(name = "主机", width = 15, dictTable = "ai_host", dicText = "name", dicCode = "id")
    @Dict(dictTable = "ai_host", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "主机")
    private java.lang.String hostId;
	/**密钥*/
	@Excel(name = "密钥", width = 15, dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @Dict(dictTable = "ai_cert", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "密钥")
    private java.lang.String certId;
	/**文件绝对路径*/
	@Excel(name = "文件绝对路径", width = 15)
    @ApiModelProperty(value = "文件绝对路径")
    private java.lang.String path;
	/**版本*/
	@Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private java.lang.String version;
	/**Hash值*/
	@Excel(name = "Hash值", width = 15)
    @ApiModelProperty(value = "Hash值")
    private java.lang.String gitUuid;
	/**文件状态*/
	@Excel(name = "文件状态", width = 15)
    @ApiModelProperty(value = "文件状态")
    private java.lang.String fileStatus;
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
