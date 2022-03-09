package cn.coderap.edu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PromotionAdDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer spaceId;

    private String keyword;

    private String htmlContent;

    private String text;

    private String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //调用方传过来的数据转换成这样的时间格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //将当前时间转换成这样的时间格式返回给调用方
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer priority;

    private String img;
}
