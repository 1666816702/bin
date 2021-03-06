package cn.edu.hnie.common.excel.imports;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import cn.edu.hnie.common.excel.imports.config.ExcelDefinition;
import cn.edu.hnie.common.excel.imports.config.FieldValue;
import cn.edu.hnie.common.excel.imports.parsing.ExcelImport;
import cn.edu.hnie.common.excel.imports.result.ExcelImportResult;
import cn.edu.hnie.common.excel.imports.xml.XMLExcelDefinitionReader;
import cn.edu.hnie.common.utils.ReflectUtil;

/**
 * Excel上下文支持,只需指定location配置文件路径,即可使用
 * @author lisuo
 *
 */
public class ExcelContext  {
	
	private ExcelDefinitionReader definitionReader;
	
	/** 用于缓存Excel配置 */
	private Map<String,List<FieldValue>> fieldValueMap = new HashMap<String, List<FieldValue>>();
	
	/**导入*/
	private ExcelImport excelImport;
	
	/**
	 * @param location 配置文件类路径
	 */
	public ExcelContext(String locations) {
		try {
			//默认使用XMLExcelDefinitionReader
			definitionReader = new XMLExcelDefinitionReader(locations);
			excelImport = new ExcelImport(definitionReader);
		} catch (ExcelException e) {
			throw e;
		}catch(Exception e){
			throw new ExcelException(e);
		}
	}
	
	/**
	 * @param definitionReader 自定义实现ExcelDefinitionReader
	 */
	public ExcelContext (ExcelDefinitionReader definitionReader){
		try {
			if(definitionReader==null){
				throw new ExcelException("definitionReader 不能为空");
			}
			if(MapUtils.isEmpty(this.definitionReader.getRegistry())){
				throw new ExcelException("definitionReader Registry 不能为空");
			}
			this.definitionReader = definitionReader;
			excelImport = new ExcelImport(definitionReader);
		}catch(ExcelException e){
			throw e;
		}catch (Exception e) {
			throw new ExcelException(e);
		}
		
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param excelStream Excel文件流
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id, InputStream excelStream) throws Exception {
		return excelImport.readExcel(id,0, excelStream,null,false);
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param excelStream Excel文件流
	 * @param sheetIndex Sheet索引位
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id, InputStream excelStream,int sheetIndex) throws Exception {
		return excelImport.readExcel(id,0, excelStream,sheetIndex,false);
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param titleIndex 标题索引,从0开始
	 * @param excelStream Excel文件流
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id,int titleIndex, InputStream excelStream) throws Exception {
		return excelImport.readExcel(id,titleIndex, excelStream,null,false);
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param titleIndex 标题索引,从0开始
	 * @param excelStream Excel文件流
	 * @param multivalidate 是否逐条校验，默认单行出错立即抛出ExcelException，为true时为批量校验,可通过ExcelImportResult.hasErrors,和getErrors获取具体错误信息
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id,int titleIndex, InputStream excelStream,boolean multivalidate) throws Exception {
		return excelImport.readExcel(id,titleIndex, excelStream,null,multivalidate);
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param titleIndex 标题索引,从0开始
	 * @param excelStream Excel文件流
	 * @param sheetIndex Sheet索引位
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id,int titleIndex, InputStream excelStream,int sheetIndex) throws Exception {
		return excelImport.readExcel(id,titleIndex, excelStream,sheetIndex,false);
	}
	
	/***
	 * 读取Excel信息
	 * @param id 配置ID
	 * @param titleIndex 标题索引,从0开始
	 * @param excelStream Excel文件流
	 * @param sheetIndex Sheet索引位
	 * @param multivalidate 是否逐条校验，默认单行出错立即抛出ExcelException，为true时为批量校验,可通过ExcelImportResult.hasErrors,和getErrors获取具体错误信息
	 * @return ExcelImportResult
	 * @throws Exception 
	 */
	public ExcelImportResult readExcel(String id,int titleIndex, InputStream excelStream,int sheetIndex,boolean multivalidate) throws Exception {
		return excelImport.readExcel(id,titleIndex, excelStream,sheetIndex,multivalidate);
	}
	
	/**
	 * 获取Excel 配置文件中的字段
	 * @param key
	 * @return
	 */
	public List<FieldValue> getFieldValues(String key){
		List<FieldValue> list = fieldValueMap.get(key);
		if(list == null){
			ExcelDefinition def = definitionReader.getRegistry().get(key);
			if(def == null){
				throw new ExcelException("没有找到["+key+"]的配置信息");
			}
			//使用copy方式,避免使用者修改原生的配置信息
			List<FieldValue> fieldValues = def.getFieldValues();
			list = new ArrayList<FieldValue>(fieldValues.size());
			for(FieldValue fieldValue:fieldValues){
				FieldValue val = new FieldValue();
				ReflectUtil.copyProps(fieldValue, val);
				list.add(val);
			}
			fieldValueMap.put(key, list);
		}
		return list;
	}

	public boolean check(String excelId, int titleIndex, InputStream srcinInputStream) throws EncryptedDocumentException, InvalidFormatException, IOException {
		return excelImport.check(excelId, titleIndex, srcinInputStream);
		
	}

	public InputStream getTemplateExcelStream(String excelId) throws FileNotFoundException {
		return excelImport.getTemplateExcelStream(excelId);
	}
	
}
