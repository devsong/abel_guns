package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

import java.io.File;

import static com.stylefeng.guns.core.util.ToolUtil.getTempPath;
import static com.stylefeng.guns.core.util.ToolUtil.isEmpty;

/**
 * guns项目配置
 */
@Component
@ConfigurationProperties(prefix = GunsProperties.PREFIX)
@Data
public class GunsProperties {
    public static final String PREFIX = "guns";

    private Boolean kaptchaOpen = false;

    private Boolean swaggerOpen = false;

    private String fileUploadPath;

    private String filePrefix;

    private Boolean haveCreatePath = false;

    private Boolean springSessionOpen = false;

    private Integer sessionInvalidateTime = 30 * 60; // session 失效时间（默认为30分钟 单位：秒）

    private Integer sessionValidationInterval = 15 * 60; // session 验证失效时间（默认为15分钟 单位：秒）

    public String getFileUploadPath() {
        // 如果没有写文件上传路径,保存到临时目录
        if (isEmpty(fileUploadPath)) {
            return getTempPath();
        }
        // 判断有没有结尾符,没有得加上
        if (!fileUploadPath.endsWith(File.separator)) {
            fileUploadPath = fileUploadPath + File.separator;
        }
        // 判断目录存不存在,不存在得加上
        if (haveCreatePath == false) {
            File file = new File(fileUploadPath);
            file.mkdirs();
            haveCreatePath = true;
        }
        return fileUploadPath;
    }

}
