package com.zxhan.core.codeGenerate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "code")
public class Code {
    public String dirPath;
    public String author;
    public String driverClassName;
    public String url;
    public String username;
    public String password;
    public String includetable;
    public String parentpackage;
    public boolean openLombok;
}
