package cn.coderap.edu.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String portrait;

    private String phone;

    private String password;

    private String regIp;

    private Boolean accountNonExpired;

    private Boolean credentialsNonExpired;

    private Boolean accountNonLocked;

    private String status;

    private Boolean isDel;

    private Date createTime;

    private Date updateTime;
}
