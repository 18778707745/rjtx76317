package com.mvn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mvn.entity.User2;

@Controller
public class FileUploadController {

    @RequestMapping(value="/{formName}")
    public String loginForm(@PathVariable String formName){

        return formName;

    }

    @RequestMapping(value="/gotoAction",method=RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,
            HttpServletRequest request){

        if (!file.isEmpty()) {

            String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
            String servletPath = request.getServletPath();//"/gotoAction"
            String scheme = request.getScheme();//"http"
       

            String storePath= "C:\\project\\SpringMvcFileUpload\\WebRoot\\images";//存放我们上传的文件路径

            String fileName = file.getOriginalFilename();

            File filepath = new File(storePath, fileName);

            if (!filepath.getParentFile().exists()) {

                filepath.getParentFile().mkdirs();//如果目录不存在，创建目录

            }

            try {
                file.transferTo(new File(storePath+File.separator+fileName));//把文件写入目标文件地址

            } catch (Exception e) {

                e.printStackTrace();

                return "error";

            }

            return "success";//返回到成功页面

        }else {

            return "error";//返回到失败的页面
        }

    }
    
    
    
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String reg(@ModelAttribute User2 user,HttpServletRequest request,Map<String,Object> map){

        final String wrong="error";

        final String good="success";

        MultipartFile headimage = user.getHeadimage();

        boolean empty = headimage.isEmpty();

        if (!empty) {

            String realPath = request.getServletContext().getRealPath("/images");

            String uploadPath="C:\\project\\SpringMvcFileUpload\\WebRoot\\images";

            String headimageName = headimage.getOriginalFilename();

            File imageFile = new File(uploadPath,headimageName);

            
            try {

                headimage.transferTo(new File(uploadPath+File.separator+headimageName));

            } catch (Exception e) {

                e.printStackTrace();

                return wrong;

            }
            map.put("user", user);

            return "userinfo";

        }else {
            return wrong;
        }
    }
    
    
    @RequestMapping(value="/download",method=RequestMethod.GET) //匹配的是href中的download请求
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename,
            Model model) throws IOException{
        
        String downloadFilePath="C:\\project\\SpringMvcFileUpload\\WebRoot\\images";//从我们的上传文件夹中去取
        
        File file = new File(downloadFilePath+File.separator+filename);//新建一个文件
        
        HttpHeaders headers = new HttpHeaders();//http头信息
        
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//设置编码
        
        headers.setContentDispositionFormData("attachment", downloadFileName);
        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        
        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息
        
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
        
    }
}
