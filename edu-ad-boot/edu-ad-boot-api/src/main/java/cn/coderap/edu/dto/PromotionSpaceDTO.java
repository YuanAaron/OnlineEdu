package cn.coderap.edu.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PromotionSpaceDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String spaceKey;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;

    private List<PromotionAdDTO> adDTOList;
}
