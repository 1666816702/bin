package cn.edu.hnie.common.excel.imports.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel定义
 * 
 * @author lisuo
 *
 */
public class ExcelDefinition {

	/** ID,必须 */
	private String id;

	/** 全类名,必须 */
	private String className;

	/** Class信息 */
	private Class<?> clazz;
	
	/**
	 * 导入时需要与之校验的excel模版路径
	 */
	private String templateUrl;

	/** Field属性的全部定义 */
	private List<FieldValue> fieldValues = new ArrayList<FieldValue>();

	/** Excel 文件sheet索引，默认为0即，第一个 */
	private int sheetIndex = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public List<FieldValue> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(List<FieldValue> fieldValues) {
		this.fieldValues = fieldValues;
	}
	
	public int getSheetIndex() {
		return sheetIndex;
	}
	
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getTemplateUrl() {
		return templateUrl;
	}
	
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
}
