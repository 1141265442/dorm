package com.zxhan.core.constant;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Data
@Component
public class PathUtil {
    private String projectDir;
    private String classDir1;
    private String classDir2;
    private String uploadDir;
    PathUtil(){
        this.projectDir=System.getProperty("user.dir");
        this.classDir1=ClassUtils.getDefaultClassLoader().getResource("").getPath();
        try {
            this.classDir2=ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            System.err.println("获取路径失败，");
        }
        File uploadDir = new File(classDir1, "src/main/webapp/static/images/upload/");//classpath下的static,重启tomcat后消失
        //项目路径下新建static，和src同级，可永久保存。拒绝访问
        //File uploadDir = new File("static/images/upload/");
        if(!uploadDir.exists()) uploadDir.mkdirs();
        this.uploadDir=uploadDir.getAbsolutePath();
    }
}
