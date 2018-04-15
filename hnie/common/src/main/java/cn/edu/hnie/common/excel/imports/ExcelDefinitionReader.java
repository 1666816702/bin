package cn.edu.hnie.common.excel.imports;

import java.util.Map;

import cn.edu.hnie.common.excel.imports.config.ExcelDefinition;

/**
 * Excel定义接口
 * @author lisuo
 *
 */
public interface ExcelDefinitionReader {
	/**
	 * 获取 ExcelDefinition注册信息
	 * @return
	 */
	Map<String, ExcelDefinition> getRegistry();
}
