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
       

            String storePath= "C:\\project\\SpringMvcFileUpload\\WebRoot\\images";//��������ϴ����ļ�·��

            String fileName = file.getOriginalFilename();

            File filepath = new File(storePath, fileName);

            if (!filepath.getParentFile().exists()) {

                filepath.getParentFile().mkdirs();//���Ŀ¼�����ڣ�����Ŀ¼

            }

            try {
                file.transferTo(new File(storePath+File.separator+fileName));//���ļ�д��Ŀ���ļ���ַ

            } catch (Exception e) {

                e.printStackTrace();

                return "error";

            }

            return "success";//���ص��ɹ�ҳ��

        }else {

            return "error";//���ص�ʧ�ܵ�ҳ��
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
    
    
    @RequestMapping(value="/download",method=RequestMethod.GET) //ƥ�����href�е�download����
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename,
            Model model) throws IOException{
        
        String downloadFilePath="C:\\project\\SpringMvcFileUpload\\WebRoot\\images";//�����ǵ��ϴ��ļ�����ȥȡ
        
        File file = new File(downloadFilePath+File.separator+filename);//�½�һ���ļ�
        
        HttpHeaders headers = new HttpHeaders();//httpͷ��Ϣ
        
        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//���ñ���
        
        headers.setContentDispositionFormData("attachment", downloadFileName);
        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        
        //MediaType:������ý������  contentType�����������е�ý��������Ϣ
        
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
        
    }
}