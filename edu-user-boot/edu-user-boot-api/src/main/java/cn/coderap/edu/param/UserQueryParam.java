package cn.coderap.edu.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryParam {
    private Integer currentPage;
    private Integer pageSize;
    private Integer userId;
    private String phone;
    private Date startCreateTime;
    private Date endCreateTime;

}
