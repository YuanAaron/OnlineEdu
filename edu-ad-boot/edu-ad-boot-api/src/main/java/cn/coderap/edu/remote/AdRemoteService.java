package cn.coderap.edu.remote;

import cn.coderap.edu.dto.PromotionSpaceDTO;
import cn.coderap.edu.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "edu-ad-boot",path = "/ad")
public interface AdRemoteService {

    /*----------------------------管理后台接口--------------------*/
    /**
     * 获取所有的广告位
     * @return 不带广告信息的广告位
     */
    @GetMapping("/space/getAllSpace")
    List<PromotionSpaceDTO> getAllSpace();

    /**
     * 新增或编辑广告位
     * @param spaceDTO
     * @return
     */
    @PostMapping("/space/saveOrUpdateSpace")
    ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO spaceDTO);

    /**
     * 根据id获取单个广告位
     * @param id
     * @return
     */
    @GetMapping("/space/getSpaceById")
    PromotionSpaceDTO getSpaceById(@RequestParam("id") Integer id);


    /*-----------------------------门户后台接口--------------------*/
    /**
     * 通过广告位标识spaceKeys（顶部和侧边）获取所有的广告
     * @return 带有广告信息的广告位
     */
    @GetMapping("/getAdBySpaceKeys")
    List<PromotionSpaceDTO> getAdBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys);


}
