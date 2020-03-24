package com.wiserun.develop.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.temp.common.model.ResponseData;
import com.temp.common.util.FormatUtil;

import com.wiserun.develop.file.convert.FileConvert;
import com.wiserun.develop.file.model.S_File;
import com.wiserun.develop.file.model.S_FileDTO;
import com.wiserun.develop.file.service.FileService;
import com.wiserun.develop.filetemplate.model.FileTemplate;
import com.wiserun.develop.filetemplate.model.FileTemplateDTO;
import com.wiserun.develop.filetemplate.service.FileTemplateService;

import java.net.URLEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

@Api(description = "附件上传下载")
@RestController("FileController")
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
	private FileTemplateService ftservice;
	@Autowired
	private FileService service;
    @Value("${files.path}")
    private  String sysFilePath="E:\\UPLOAD\\";
    
    @Value("${vue.frontUrl}")
    private  String frontUrl="http://192.168.111.45:9090";
    @Autowired
	private HttpSession session;
    

    
    //附件列表显示
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResponseData list(S_FileDTO request) {
        return FormatUtil.success(FormatUtil.formatList(service.getPageList(request),
                service.getTotal(request)));
    }
    //附件模板列表显示
	@ApiOperation(value = "附件模板信息列表", notes = "根据参数查询附件模板信息")
    @RequestMapping(value = "/template/list", method = { RequestMethod.POST })
    public ResponseData list(@RequestBody FileTemplateDTO container) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", ftservice.getPageList(container));
        map.put("total", ftservice.getTotal(container));
        
        return FormatUtil.success(map);
    }
		//附件模板下拉框
		@ApiOperation(value = "附件模板下拉框", notes = "根据参数查询附件模板信息")
	    @RequestMapping(value = "/template/list1", method = { RequestMethod.POST })
	    public ResponseData list1(@RequestBody FileTemplateDTO container) {
			//System.out.println("-------------------------------------:"+sysFilePath);
	        Map<String, Object> map = new HashMap<>();
	        map.put("list", ftservice.getPageList1(container));        
	        return FormatUtil.success(map);
	    }
		
		//附件模板下拉框对应的附件列表
		@ApiOperation(value = "附件模板下拉框对应的附件列表", notes = "根据参数查询附件模板对应的附件列表信息")
	    @RequestMapping(value = "/template/filelist1", method = { RequestMethod.POST })
	    public ResponseData Filelist1(@RequestBody S_FileDTO container) {
			S_File s=new S_File();
			s.setSF_BussinessCode(container.getSF_BussinessCode());
	        Map<String, Object> map = new HashMap<>();
	        map.put("Filelist", service.getListBySFT_Code(s));
	        
	        return FormatUtil.success(map);
	    }
	

	
	@ApiOperation(value = "保存", notes = "保存或新增附件模板详细信息")
    @RequestMapping(value = "/template/save", method = { RequestMethod.POST })
    public ResponseData Save(@RequestBody FileTemplateDTO container) {
		  int i=  ftservice.save(container);
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		Map<String, Object> map = new HashMap<>();
		map.put("Succeed", r);
        map.put("SFT_Code", session.getAttribute("SFT_Code"));
        return FormatUtil.success(map);
    }
	
    
    //上传方法
	@RequestMapping(value="/file/upload",method={ RequestMethod.POST })
	 public ResponseData uploadFiles(@RequestParam(value="fileID",required=false) String fileID,@RequestParam("file")MultipartFile file){
		logger.error("fileID=======>"+fileID);
		 S_File s_file=new S_File();
		// 判断文件是否为空 
	    if (!file.isEmpty()) { 
	      try { 
	        // 文件保存路径 
	    	String fileName = file.getOriginalFilename();
	        String filePath = sysFilePath; 
	        String username=(String) session.getAttribute("user");
	      //当文件夹不存在时创建
	        java.io.File f = new java.io.File(filePath);
			if(!f.exists()){
				f.mkdirs();
			}
			Date now = new Date(); 
			//拿到session里存储的登录人名字
			Object username1=session.getAttribute("user");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");//编号
			String code = dateFormat1.format(now);
			code=code.replaceAll("/","");
			code=code.replaceAll(" ","");
			int shu=(int) ((Math.random()*9+1)*10000);
			String s=code+shu;
			// 转存文件 
			String ming=fileName.substring(0,fileName.lastIndexOf("."));
			String ming2=fileName.substring(fileName.lastIndexOf("."), fileName.length());
			String newfileName=ming+username+s+ming2;			
			//System.out.println("----------------------------------------:"+session.getAttribute("user"));
			//System.out.println("----------------------------------------:"+username1);
			File dest = new File(filePath + newfileName);
			file.transferTo(dest);			
			//System.out.println(filePath);
			//System.out.println(newfileName);
	       	
	        if(!ming2.equalsIgnoreCase(".pdf")) {
	        	 FileConvert fc=new FileConvert();
	 	         fc.office2pdf(filePath,newfileName);
	        }	       
	        //将信息存入数据库
	        //拿到后缀名并判断得到类型
	        String houzhui=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());	
	      	String type= puanduan(houzhui);  
	        //创建UUID成数据附件编号
	        UUID uuid = UUID.randomUUID();
	        String strID = uuid.toString().replaceAll("-", "");	  
	      //  System.out.println("---------------------------------------------"+uuid.toString());
	        //得到当前时间和当前登录人
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
			String hehe = dateFormat.format(now);
			s_file.setSF_Code(strID);
			System.out.println("-----------------------------"+s_file.getSF_Code());
			s_file.setSF_CreateTime(hehe);
			s_file.setSF_CreateUser(username);
			s_file.setSF_State("1");
			s_file.setSF_FileName(fileName);			
			s_file.setSF_FileType(type);
			s_file.setSF_Path(filePath+newfileName);
			s_file.setSF_TempPath(strID+"."+houzhui);            
			s_file.setSF_BussinessCode(fileID);
	      } catch (Exception e) { 
	        e.printStackTrace(); 
	      } 
	    } 
		  int i= service.add(s_file);
		  String r;
		  if(i==1) {
			   r="Succees";
		  }else {
			   r="Fail ";
		  }
		  Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
	    return FormatUtil.success(map); 
	  } 
	 
	
    //删除文件方法
	@RequestMapping(value="/file/delete",method={ RequestMethod.POST })
	 public ResponseData deleteFile(@RequestBody S_FileDTO container){
		/*logger.error("fileID=======>"+fileID);*/
		String r=null;
		int i=0;
/*		 S_FileDTO s_fileDTO=new S_FileDTO();
		 s_fileDTO.setSF_Code(fileID);*/
		 S_File s_file =service.getOne(container);
		// 判断文件是否为空 
		 File file = new File(s_file.getSF_Path());
	     if ((file).exists()) { 	  
	    	 	System.out.println("进入方法");
	    		file.delete();
	    		i=service.delete(container);
	      } 	       
		  if(i==1) {
			  r="Succees";
		  }else {
			  r="Fail ";
		  }
	    
	      Map<String, Object> map = new HashMap<>();
		  map.put("Succeed", r); 
	      return FormatUtil.success(map);
		}
	 	
	
	
//判断后缀名方法
public String puanduan(String str) {
	if(str.equalsIgnoreCase("BMP")||str.equalsIgnoreCase("jpg")||str.equalsIgnoreCase("png")||str.equalsIgnoreCase("tif")||str.equalsIgnoreCase("gif")||str.equalsIgnoreCase("JPEG")) {
		return "图片"+str;
	}else if(str.equalsIgnoreCase("DOC")||str.equalsIgnoreCase("PDF")||str.equalsIgnoreCase("HTL")||str.equalsIgnoreCase("TXT")||str.equalsIgnoreCase("HTML")){
		return "文本文档"+str;
	}
	return str + "文件";
}

@ApiOperation(value = "附件详细信息", notes = "根据编号查询详细信息")
@RequestMapping(value = "/file/info", method = { RequestMethod.POST })
public S_File getOne(@RequestBody S_FileDTO container) {  
    return service.getOne(container);
}
	
//文件下载相关代码
@RequestMapping("/file/download")
public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) 
		throws UnsupportedEncodingException{
	//前台参数测试
    String id= request.getParameter("id");
    
    // 此处加入根据参数获取文件路径的方法
    /*********************************************/
    S_FileDTO container=new S_FileDTO();
    container.setSF_Code(id);
    S_File s_file=service.getOne(container);
    //数据库中文件名
    String dataBaseFileName=s_file.getSF_FileName();
    System.out.println("----------------------------------"+dataBaseFileName);
    //数据库中文件路径
    String filePath=s_file.getSF_Path();
    System.out.println("----------------------------------"+filePath);
    /************************************************/
    
	//获得文件
    File file = new File(filePath);
    //下载后的文件名
    String fileName = dataBaseFileName;
    //拼接后 强制转为UTF-8否则文件名乱码
    fileName = URLEncoder.encode(fileName, "UTF-8");
    System.out.println(fileName);
    fileName=fileName.replace("+","%20");
    
    String sloadName=new String(fileName.getBytes("gb2312"),"iso8859-1");
	if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
		System.out.println("firefox浏览器");
		sloadName = new String(fileName.getBytes("gb2312"), "ISO8859-1"); // firefox浏览器
	} else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
		System.out.println("谷歌浏览器");
		sloadName = new String(fileName.getBytes("gb2312"), "ISO8859-1");// 谷歌
	} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
		System.out.println("IE浏览器");
		sloadName =  URLEncoder.encode(fileName, "UTF-8");// IE浏览器
	}
	System.out.println(sloadName);
    if (file.exists()) {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=\"" +  sloadName+"\"");// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
        	//new一个文件输入流，用于文件的读取
            fis = new FileInputStream(file);
            //包装成缓冲流
            bis = new BufferedInputStream(fis);
            //获得reponse的输出流
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            //开始传输
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("文件下载成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    return null;
}

//多文件上传
@RequestMapping(value = "/file/batch/upload", method = RequestMethod.POST)
@ResponseBody
public String handleFileUpload(@RequestParam(value="fileID",required=false) String fileID,HttpServletRequest request,HttpSession session) {
    List<MultipartFile> files = ((MultipartHttpServletRequest) request)
            .getFiles("file");
    MultipartFile file = null;
    BufferedOutputStream stream = null;
    for (int i = 0; i < files.size(); ++i) {
        file = files.get(i);
        if (!file.isEmpty()) {
        	//存入数据库
        	S_File s_file=new S_File();
        	String fileName = file.getOriginalFilename();
        	String houzhui=fileName.substring(fileName.indexOf(".")+1, fileName.length());	
  	      	String type= puanduan(houzhui);  
  	        //创建UUID成数据附件编号
  	        UUID uuid = UUID.randomUUID();
  	        String strID = uuid.toString().replaceAll("-", "");	  
  	        String filePath = sysFilePath; 
  	        //得到当前时间和当前登录人
  	        Date now = new Date(); 
  			String username=(String)session.getAttribute("user");//拿到session里存储的登录人名字
  			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//创建日期的格式
  			String hehe = dateFormat.format(now);
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");//编号
			String code = dateFormat1.format(now);
			code=code.replaceAll("/","");
			code=code.replaceAll(" ","");
			int shu=(int) ((Math.random()*9+1)*10000);
			String s=code+shu;
			String ming=fileName.substring(0,fileName.indexOf("."));
			String ming2=fileName.substring(fileName.indexOf("."), fileName.length());
			String newfileName=ming+username+s+ming2;
  			s_file.setSF_Code(strID);
  			System.out.println("-----------------------------"+s_file.getSF_Code());
  			s_file.setSF_CreateTime(hehe);
  			s_file.setSF_CreateUser(username);
  			s_file.setSF_State("1");
  			s_file.setSF_FileName(fileName);
  			s_file.setSF_FileType(type);
  			s_file.setSF_Path(filePath+newfileName);
  			s_file.setSF_TempPath(newfileName);            
  			s_file.setSF_BussinessCode(fileID);
            try {
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(new FileOutputStream(
                        new File(filePath + newfileName)));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                stream = null;
                return "You failed to upload " + i + " => "
                        + e.getMessage();
            }
        } else {
            return "You failed to upload " + i
                    + " because the file was empty.";
        }
    }
    return "upload successful";
}

//预览文件
@RequestMapping("/file/preview")
public void  previewFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 logger.info("previewFile=============>");
	//前台参数测试
    String id= request.getParameter("fileID");
    
    S_FileDTO container=new S_FileDTO();
    container.setSF_Code(id);
    S_File s_file=service.getOne(container);
    logger.info("id=============>"+id);
    //数据库中文件名
    String dataBaseFileName=s_file.getSF_FileName();
    //数据库中文件路径
    String filePath=s_file.getSF_Path();
    System.out.println("----------------------------文件路径:"+s_file.getSF_Path());
    System.out.println("----------------------------文件路径:"+s_file.getSF_Path());
    
    // 此处加入根据参数获取文件路径的方法
    /*********************************************/
    if("1".equalsIgnoreCase(id)) {
    	dataBaseFileName="谷歌开启flash.txt";
    }else if("2".equalsIgnoreCase(id)){
    	dataBaseFileName="招生就业问题.txt";
    }
    else if("3".equalsIgnoreCase(id)){
    	dataBaseFileName="Diagram 1.pdf";
    }
    else if("4".equalsIgnoreCase(id)){
    	dataBaseFileName="Diagram+1.png";
    }
    else if("5".equalsIgnoreCase(id)){
    	dataBaseFileName="luquxinxi.xlsx";
    }
    else if("6".equalsIgnoreCase(id)){
    	dataBaseFileName="机器人软件接口简介.doc";
    }
    else if("7".equalsIgnoreCase(id)){
    	dataBaseFileName="机器人软件接口简介.docx";
    }
    else if("8".equalsIgnoreCase(id)){
    	dataBaseFileName="松江政务云工作量统计.xls";
    }
    else if("9".equalsIgnoreCase(id)){
    	dataBaseFileName="松江政务云工作量统计.xlsx";
    }
    /************************************************/
    //获取pdf文件路径
    /*filePath=sysFilePath+dataBaseFileName;*/
    String pdfPath="";
    int idx=filePath.lastIndexOf(".");
    String fileType=filePath.substring(idx,filePath.length());
    //pdfPath=filePath.replace(fileType, ".pdf");
    if(".pdf".equalsIgnoreCase(fileType)) {
    	pdfPath=filePath;
    }else {
    	pdfPath=filePath.substring(0,idx)+".pdf";
    }
    logger.info("转换后的pdf路径=====>"+pdfPath);
    
    File file = new File(pdfPath);
    if(file.exists()) {
    	logger.info("frontUrl=====>"+frontUrl);
//    	response.setHeader("Access-Control-Allow-Origin", frontUrl); 
//    	//response.setContentLength((int) file.length());
//    	//response.setHeader( "Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));// 设置在下载框默认显示的文件名
//    	//response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
//    	response.setContentType("application/pdf");
//    	//response.setContentType("multipart/form-data");
//    	// 读出文件到response
//    	// 这里是先需要把要把文件内容先读到缓冲区
//    	// 再把缓冲区的内容写到response的输出流供用户下载
//    	FileInputStream fileInputStream = new FileInputStream(file);
//    	BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//    	OutputStream outputStream = response.getOutputStream();
//    	byte buffer[] = new byte[1024];
//    	int len = 0; 
//    	while ((len = bufferedInputStream.read(buffer)) > 0) {
//    	    outputStream.write(buffer, 0, len);
//    	}
//    	// 人走带门
//    	bufferedInputStream.close();
//    	outputStream.flush();
//    	outputStream.close();
    	
    	response.setStatus(HttpServletResponse.SC_OK);  
    	response.setContentType("application/pdf;charset=UTF-8");  
    	FileInputStream fileInputStream = new FileInputStream(file);
    	@SuppressWarnings("resource")
		BufferedInputStream input = new BufferedInputStream(fileInputStream);  
    	byte buffBytes[] = new byte[1024];  
    	OutputStream out = response.getOutputStream();  
    	int read = 0;    
    	while ((read = input.read(buffBytes)) != -1) {    
    	    out.write(buffBytes, 0, read);    
    	}  
    	out.flush();    
    	out.close(); 
    }else {
    	logger.info("file not exists=====");
    }
}

//文件下载相关代码
@RequestMapping("/template/download")
public String downloadFileTemplate(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) 
		throws UnsupportedEncodingException{
	//前台参数测试
	//拿到前台传过来的模块ID
    String id= request.getParameter("id");
    FileTemplateDTO container=new FileTemplateDTO();
    container.setSFT_ModelCode(id);
    //查询到那条详细信息
    FileTemplate filetemplate=ftservice.getOne(container);
    //拿到编号
    String SFT_Code=filetemplate.getSFT_Code();
    S_FileDTO container2=new S_FileDTO();
    //编号等于业务表编号查询出附件详细信息
    container2.setSF_BussinessCode(SFT_Code);
    S_File s_file= service.getOne2(container2);
    // 此处加入根据参数获取文件路径的方法
    /*********************************************/
    //数据库中文件名
    String dataBaseFileName=s_file.getSF_FileName();
    System.out.println("----------------------------------"+dataBaseFileName);
    //数据库中文件路径
    String filePath=s_file.getSF_Path();
    System.out.println("----------------------------------"+filePath);
  /************************************************/
  
	//获得文件
  File file = new File(filePath);
  //下载后的文件名
  String fileName = dataBaseFileName;
  //拼接后 强制转为UTF-8否则文件名乱码
  fileName = URLEncoder.encode(fileName, "UTF-8");
  System.out.println(fileName);
  fileName=fileName.replace("+","%20");
  
  String sloadName=new String(fileName.getBytes("gb2312"),"iso8859-1");
	if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
		System.out.println("firefox浏览器");
		sloadName = new String(fileName.getBytes("gb2312"), "ISO8859-1"); // firefox浏览器
	} else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
		System.out.println("谷歌浏览器");
		sloadName = new String(fileName.getBytes("gb2312"), "ISO8859-1");// 谷歌
	} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
		System.out.println("IE浏览器");
		sloadName =  URLEncoder.encode(fileName, "UTF-8");// IE浏览器
	}
	System.out.println(sloadName);
  if (file.exists()) {
      response.setContentType("application/force-download");// 设置强制下载不打开
      response.addHeader("Content-Disposition",
              "attachment;fileName=\"" +  sloadName+"\"");// 设置文件名
      byte[] buffer = new byte[1024];
      FileInputStream fis = null;
      BufferedInputStream bis = null;
      try {
      	//new一个文件输入流，用于文件的读取
          fis = new FileInputStream(file);
          //包装成缓冲流
          bis = new BufferedInputStream(fis);
          //获得reponse的输出流
          OutputStream os = response.getOutputStream();
          int i = bis.read(buffer);
          //开始传输
          while (i != -1) {
              os.write(buffer, 0, i);
              i = bis.read(buffer);
          }
          System.out.println("文件下载成功");
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          if (bis != null) {
              try {
                  bis.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (fis != null) {
              try {
                  fis.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  return null;
}

}