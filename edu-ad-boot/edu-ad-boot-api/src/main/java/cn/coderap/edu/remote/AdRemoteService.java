package cn.coderap.edu.remote;

import cn.coderap.edu.dto.PromotionSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "edu-ad-boot",path = "/ad")
public interface AdRemoteService {

    /**
     * 获取所有的广告位
     * @return 不带广告信息的广告位
     */
    @GetMapping("/space/getAllSpace")
    List<PromotionSpaceDTO> getAllSpace();

    /**
     * 通过广告位标识spaceKeys（顶部和侧边）获取所有的广告
     * @return 带有广告信息的广告位
     */
    @GetMapping("/getAdBySpaceKeys")
    List<PromotionSpaceDTO> getAdBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys);
}
