package com.atguigu.controller;

import com.atguigu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;

/**
 * @PACKAGE_NAME: com.atguigu.controller
 * @CLASSNAME: UserController
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/12 12:24
 * @SINCE 17.0.7
 * @DESCRIPTION: UserController 控制器
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private ServletContext servletContext;

    @ModelAttribute
    public void doSthAtFirst(HttpServletRequest request, Model model) {
        StringBuffer requestURL = request.getRequestURL();
        model.addAttribute("first", "ModelAttribute");
        log.debug("requestURL = {}", requestURL.toString());
    }

    @RequestMapping("/register2")
    public String register2(User user, MultipartFile photo1, Model model) {
        try {
            log.debug("user=" + user);
            log.debug("photo = " + photo1);
            log.debug("文件名 = " + photo1.getOriginalFilename());
            user.setPhoto(photo1.getOriginalFilename());
            //1 判断 文件夹,文件夹不存在就创建
            File dir = new File("d:/upload");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdirs();
            }
            log.debug("上传文件的额名称: " + photo1.getOriginalFilename());
            log.debug("上传文件的大小: " + photo1.getSize());
            //Tomcat的web.xml中所有文件类型MIME类型列表
            log.debug("上传文件的MIME类型: " + photo1.getContentType());
            log.debug("上传文件的表单项的name属性: " + photo1.getName());

            //2 避免同名文件覆盖
            String fileName = UUID.randomUUID().toString() + "_" + photo1.getOriginalFilename();

            //3 限制上传文件类型
            //方法1: 按照扩展名来限制
            //方法2: 按照MIME类型来限制tomcat的web.xml中有多有的MIME类型和扩展名对应关系
            String contentType = photo1.getContentType().toLowerCase(Locale.ROOT);
            if (!"image/jpeg".equals(contentType) && !"image/gif".equals(contentType)) {
                model.addAttribute("error", "上传类型只能是jpg和gif类型");
                return "portal";
            }

            //4 限制上传文件大小
            long size = photo1.getSize();
            if (size > 1024 * 100) {
                model.addAttribute("error", "上传大小不能超过100k");
                return "portal";
            }

            //创建文件
            File file = new File(dir, fileName);

            photo1.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @RequestMapping("/register")
    public String register(User user, MultipartFile photo1, Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        user.setPhoto(photo1.getOriginalFilename());
        String realPath = servletContext.getRealPath("/upload");
        File dir = new File(realPath);
        if (!(dir.exists() && dir.isDirectory())) {
            dir.mkdirs();
        }
        //System.out.println("realPath = " + realPath);
        //URL resource = servletContext.getResource("/");
        //System.out.println("resource = " + resource);

        // 1 防止文件重名覆盖
        String fileName = UUID.randomUUID() + "_" + photo1.getOriginalFilename();
        File file = new File(dir, fileName);

        //3 限制上传文件类型
        //方法1: 按照扩展名来限制
        //方法2: 按照MIME类型来限制
        String contentType = photo1.getContentType().toLowerCase(Locale.ROOT);
        if (!"image/jpeg".equals(contentType) && !"image/gif".equals(contentType)) {
            model.addAttribute("error", "上传类型只能是jpg和gif类型");
            return "portal";
        }
        photo1.transferTo(file);
        return "result";
    }

    @RequestMapping("/register3")
    public String register3(User user, MultipartFile photo1, Model model) {
        try {
            log.debug("user=" + user);
            log.debug("photo = " + photo1);
            log.debug("文件名=" + photo1.getOriginalFilename());
            user.setPhoto(photo1.getOriginalFilename());
            //1 判断文件夹,是否存在,不存在就创建
            File dir = new File("d:/upload");
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdirs();
            }
            log.debug("上传文件的名称: " + photo1.getOriginalFilename());
            log.debug("上传文件的大小: " + photo1.getSize());
            log.debug("上传文件的MIME类型: " + photo1.getContentType());
            log.debug("上传文件的表单项的name属性: " + photo1.getName());

            //2 避免同名文件覆盖,修改文件名
            String fileName = UUID.randomUUID().toString() + "_" + photo1.getOriginalFilename();

            //3 限制上传文件类型
            //方法1: 按照扩展名来限制
            //方法2:按照MIME类型来限制tomcat的web.xml有MIME类型和具体文件类型的对应表
            String contentType = photo1.getContentType().toLowerCase(Locale.ROOT);

            if (!("image/jpeg".equals(contentType) || "image/gif".equals(contentType))) {
                model.addAttribute("error", "上传类型只能是jpg或者gif类型");
                return "portal";
            }

            //4 限制上传文件的大小
            long size = photo1.getSize();
            if (size > 1024 * 200) {
                model.addAttribute("error", "上传文件大小不能超过100k");
                return "portal";
            }

            //5 上传文件
            File file = new File(dir, fileName);
            photo1.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    @RequestMapping(value = "/register4",params = {"userId={张三}","realName={张三}"})
    public String register4(User user, MultipartFile photo1, Model model) {
        try {
            log.debug("user = " + user);
            log.debug("photo1 = " + photo1);
            user.setPhoto(photo1.getOriginalFilename());
            //开发阶段 打war:exploded 发布到当前项目编译后的路径下
            //上线阶段 打war包 发布到tomcat服务区目录的webapps下
            String realPath = servletContext.getRealPath("/upload");
            File dir = new File(realPath);
            //1 验证该文件夹是否存在
            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdirs();
            }

            //2 防止文件重名
            String fileName = UUID.randomUUID() + "_" + photo1.getOriginalFilename();
            File file = new File(dir, fileName);

            //3 限制文件上传类型
            //方法1:按照扩展名来限制
            //方法2:按照MIME类型来限制
            String contentType = photo1.getContentType().toLowerCase(Locale.ROOT);
            if (!("image/jpeg".equals(contentType) || "image/gif".equals(contentType))) {
                model.addAttribute("error", "上传类型只能是jpg或者gif类型");
                return "portal";
            }
            //上传文件
            photo1.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "result";
    }

    /**
     * 下载图片,直接传递输出流到客户端,不需要页面跳转
     * 上传:就是把客户端的文件复制到服务器端
     * 下载:就是把服务器的文件复制到客户端
     * <p>
     * 控制浏览器的行为:不是直接打开,而是下载
     * 通过三个响应头来控制,关键是其中一个
     *
     * @param photoName
     * @param response
     */
    @RequestMapping("/download")
    public void download(String photoName, HttpServletResponse response) {
        //创建已给输入流和输出流
        try {
            String realPath = servletContext.getRealPath("/upload");
            File file = new File(realPath, photoName);
            InputStream is = new FileInputStream(file);
            OutputStream os = response.getOutputStream();

            //指定响应信息
            response.setContentLength((int)file.length());
            response.setContentType("image/gif");
            response.setHeader("Content-Disposition", "attachment;filename=zwj.gif");
            IOUtils.copy(is, os);
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载硬盘上的文件,直接打开
     * @param photoName
     * @param response
     */
    @RequestMapping("/download2")
    public void download2(@RequestParam("photoName") String photoName,
                          HttpServletResponse response) {
        InputStream fis = null;
        OutputStream os = null;
        try {
            File file = new File("d:/upload", photoName);
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "inline");
            os = response.getOutputStream();

            IOUtils.copy(fis, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/download3/{photoName}")
    public void download3(@PathVariable("photoName") String photoName, HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;

        try {
            File file = new File("d:/upload", photoName);
            is = new FileInputStream(file);
            response.setContentLength((int)file.length());
            response.setContentType("image/jpg");
            String filename = "沙发.jpg";
            response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(StandardCharsets.UTF_8),"ISO8859-1"));
            os = response.getOutputStream();
            IOUtils.copy(is, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
