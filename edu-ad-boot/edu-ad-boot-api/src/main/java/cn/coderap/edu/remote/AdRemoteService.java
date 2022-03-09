package cn.coderap.edu.remote;

import cn.coderap.edu.dto.PromotionSpaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "edu-ad-boot",path = "/ad")
public interface AdRemoteService {

    @GetMapping("/space/getAllSpace")
    List<PromotionSpaceDTO> getAllSpace();
}
