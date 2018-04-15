package cn.edu.hnie.zyjh.base;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.edu.hnie.common.base.BaseController;
import cn.edu.hnie.common.excel.imports.ExcelContext;
import cn.edu.hnie.common.excel.imports.result.ExcelImportResult;

/**
 * 批量导入 包含了批量导入基础功能和模版下载功能 所有涉及到批量导入的都继承该类
 * 
 * @author Administrator
 * 
 */
public class BatchBaseController extends BaseController {

	/**
	 * 文件上传，同时转换成excel导入模版对象
	 * 
	 * @param request
	 *            前端请求对象
	 * @param context
	 *            excel配置文件路径
	 * @param excelId
	 *            Excel配置文件中配置的id
	 * @return Excel导入结果
	 * @throws Exception
	 */
	protected ExcelImportResult fileUpload(HttpServletRequest request, String excelId) throws Exception {

		// 判断是否是文件上传请求
		if (ServletFileUpload.isMultipartContent(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = mRequest.getFile("file");

			ExcelContext context = new ExcelContext("template/excel-config.xml");

			// 是文件上传对象，获取上传文件的输入流
			InputStream srcinInputStream = file.getInputStream();
			// 对上传文件的输入流进行处理，跟本地的文件流处理方式相同

			// 第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息,
			// 比如数据批次号之类的,关于如何转换成javaBean,具体参考配置信息描述
			ExcelImportResult result = context.readExcel(excelId, 1, srcinInputStream);

			return result;
		}

		return null;
	}

	/**
	 * 模版下载，针对批量导入的时候才有该功能
	 * 
	 * @param request
	 *            请求体
	 * @param fileName
	 *            文件的名字
	 * @return 响应
	 * @throws Exception
	 */
	protected ResponseEntity<byte[]> downLoad(HttpServletRequest request, String excelId) throws Exception {

		// 得到文件所在位置
		ExcelContext context = new ExcelContext("template/excel-config.xml");
		// 将该文件加入到输入流之中
		InputStream in = context.getTemplateExcelStream(excelId);

		try {
			// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
			byte[] body = new byte[in.available()];

			// 读入到输入流里面
			in.read(body);

			// 防止中文乱码
			String realFileName = new String(excelId.getBytes("gbk"), "iso8859-1");

			// 设置响应头
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename=" + realFileName);

			// 设置响应码，消息体，响应头
			return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
		} finally {
			in.close();
		}
	}

	/**
	 * 校验上传的excel表头是否和模版匹配
	 * @param request 输入流
	 * @param excelId 配置模版标识
	 * @return
	 */
	protected boolean check(HttpServletRequest request, String excelId) {
		// 判断是否是文件上传请求
		if (ServletFileUpload.isMultipartContent(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = mRequest.getFile("file");

			ExcelContext context = new ExcelContext("template/excel-config.xml");

			// 是文件上传对象，获取上传文件的输入流
			InputStream srcinInputStream;
			try {
				srcinInputStream = file.getInputStream();
			} catch (IOException e) {
				return false;
			}

			// 第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息
			try {
				context.check(excelId, 1, srcinInputStream);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return false;
		}

		return true;
	}
}
