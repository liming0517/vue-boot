package com.wiserun.develop.file.convert;

import java.io.File;

import org.jodconverter.JodConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.jodconverter.office.LocalOfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileConvert {
	private static final Logger logger = LoggerFactory.getLogger(FileConvert.class);
    
	public void office2pdf(String sysFilePath,String fileName) {
		logger.info("当前为转换类");
		//获取pdf文件路径
		//logger.info("sysFilePath=====>"+sysFilePath);
        String filePath=sysFilePath+fileName;
        String pdfPath="";
        int idx=filePath.lastIndexOf(".");
        String fileType=filePath.substring(idx,filePath.length());
        String Name=filePath.substring(0,idx);
        pdfPath=Name+".pdf";
        logger.info("fileType=====>"+fileType);
        DocumentFormat typeIn=DefaultDocumentFormatRegistry.TXT;
        //DocumentFormat typeOut=DefaultDocumentFormatRegistry.TXT;
        if(".doc".equalsIgnoreCase(fileType)) {
        	typeIn=DefaultDocumentFormatRegistry.DOC;
        }else if(".docx".equalsIgnoreCase(fileType)) {
        	typeIn=DefaultDocumentFormatRegistry.DOC;
        }
		else if(".xls".equalsIgnoreCase(fileType)) {
			typeIn=DefaultDocumentFormatRegistry.XLS;
		}
		else if(".xlsx".equalsIgnoreCase(fileType)) {
			typeIn=DefaultDocumentFormatRegistry.XLSX;
		}
		else if(".ppt".equalsIgnoreCase(fileType)) {
			typeIn=DefaultDocumentFormatRegistry.PPT;
		}
		else if(".pptx".equalsIgnoreCase(fileType)) {
			typeIn=DefaultDocumentFormatRegistry.PPTX;
		}
		else if(".txt".equalsIgnoreCase(fileType)) {
			typeIn=DefaultDocumentFormatRegistry.TXT;

		}else if(".pdf".equalsIgnoreCase(fileType)) {
			return;
		}
        logger.info("typeIn=====>"+typeIn.getMediaType());
        //logger.info("转换前的文档路径=====>"+filePath);
        //logger.info("转换后的pdf路径=====>"+pdfPath);
		File inputFile=new File(filePath);
		File outputFile=new File(pdfPath);
		if (!outputFile.exists()){
			try {
				outputFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LocalOfficeManager officeManager = LocalOfficeManager.install(); 

		try {
			if(!officeManager.isRunning()) {
				//logger.info("启动officeManager");
				officeManager.start();
			}
			//JodConverter.convert(inputFile).as(typeIn).to(outputFile).as(typeOut).execute();
			JodConverter.convert(inputFile).to(outputFile).execute();
			//logger.info("停止officeManager");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				officeManager.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
