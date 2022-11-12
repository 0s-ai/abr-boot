package org.jeecg.modules.abr.productCase.vo;

import java.util.List;
import org.jeecg.modules.abr.productCase.entity.ProductCase;
import org.jeecg.modules.abr.productCase.entity.ProductCaseRole;
import org.jeecg.modules.abr.productCase.entity.ProductCaseParm;
import org.jeecg.modules.abr.productCase.entity.ProductCaseOper;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 产品方案
 * @Author: jeecg-boot
 * @Date:   2022-11-05
 * @Version: V1.0
 */
@Data
@ApiModel(value="product_casePage对象", description="产品方案")
public class ProductCasePage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
	@ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**编码*/
	@Excel(name = "编码", width = 15)
	@ApiModelProperty(value = "编码")
    private java.lang.String code;
	/**产品*/
	@Excel(name = "产品", width = 15, dictTable = "product", dicText = "name", dicCode = "id")
    @Dict(dictTable = "product", dicText = "name", dicCode = "id")
	@ApiModelProperty(value = "产品")
    private java.lang.String productId;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
    private java.lang.String note;
	/**版本*/
	@Excel(name = "版本", width = 15)
	@ApiModelProperty(value = "版本")
    private java.lang.String version;
	/**软删除标记*/
	@Excel(name = "软删除标记", width = 15)
	@ApiModelProperty(value = "软删除标记")
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

	@ExcelCollection(name="方案角色")
	@ApiModelProperty(value = "方案角色")
	private List<ProductCaseRole> productCaseRoleList;
	@ExcelCollection(name="方案参数")
	@ApiModelProperty(value = "方案参数")
	private List<ProductCaseParm> productCaseParmList;
	@ExcelCollection(name="方案操作")
	@ApiModelProperty(value = "方案操作")
	private List<ProductCaseOper> productCaseOperList;

}
