package com.wiserun.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class FileUtil {

	/**
	 * 追加替换文件名字
	 */
	public static String appendReplaceFileName(String srcFilePath, String arName) {
		if (StringUtils.isEmpty(srcFilePath)) {
			return null;
		}
		StringBuffer path = new StringBuffer(
				FilenameUtils.getFullPath(srcFilePath));
		path.append(FilenameUtils.getBaseName(srcFilePath));
		path.append(arName);
		path.append(FilenameUtils.EXTENSION_SEPARATOR_STR);
		path.append(FilenameUtils.getExtension(srcFilePath));
		return path.toString();
	}

	/**
	 * 文件下载
	 */
	public static void download(HttpServletResponse response,
			String localFilePath) throws Exception {
		download(response, localFilePath, FilenameUtils.getName(localFilePath));
	}

	/**
	 * 文件下载
	 */
	public static void download(HttpServletResponse response,
			String localFilePath, String downloadName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		File dFile = new File(localFilePath);
		long fileLength = dFile.length();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(downloadName, "UTF-8"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(dFile));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}

	}

}
