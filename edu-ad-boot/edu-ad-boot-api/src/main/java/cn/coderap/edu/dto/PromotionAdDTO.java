package cn.coderap.edu.dto;

import lombok.Data;

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

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer priority;

    private String img;
}
