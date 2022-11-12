package org.jeecg.modules.abr.tsmServer.entity;

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
 * @Description: tsm服务器
 * @Author: jeecg-boot
 * @Date:   2022-10-22
 * @Version: V1.0
 */
@Data
@TableName("tsm_server")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tsm_server对象", description="tsm服务器")
public class TsmServer implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**主机*/
	@Excel(name = "主机", width = 15, dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_host where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "主机")
    private java.lang.String hostId;
	/**IP*/
	@Excel(name = "IP", width = 15, dictTable = "ai_host where del_flag=0", dicText = "ip", dicCode = "id")
	@Dict(dictTable = "ai_host where del_flag=0", dicText = "ip", dicCode = "id")
    @ApiModelProperty(value = "IP")
    private java.lang.String ip;
	/**tsm服务端口*/
	@Excel(name = "tsm服务端口", width = 15)
    @ApiModelProperty(value = "tsm服务端口")
    private java.lang.String tsmPort;
	/**oc服务端口*/
	@Excel(name = "oc服务端口", width = 15)
    @ApiModelProperty(value = "oc服务端口")
    private java.lang.String ocPort;
	/**API凭据*/
	@Excel(name = "API凭据", width = 15, dictTable = "ai_cert where del_flag=0", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ai_cert where del_flag=0", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "API凭据")
    private java.lang.String apiCert;
	/**API状态*/
	@Excel(name = "API状态", width = 15)
    @ApiModelProperty(value = "API状态")
    private java.lang.String apiStatus;
	/**服务名*/
	@Excel(name = "服务名", width = 15)
    @ApiModelProperty(value = "服务名")
    private java.lang.String serverName;
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
