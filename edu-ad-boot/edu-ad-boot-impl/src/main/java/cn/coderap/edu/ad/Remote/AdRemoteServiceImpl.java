package cn.coderap.edu.ad.Remote;

import ch.qos.logback.core.pattern.ConverterUtil;
import cn.coderap.edu.ad.entity.PromotionSpace;
import cn.coderap.edu.ad.service.IPromotionSpaceService;
import cn.coderap.edu.dto.PromotionSpaceDTO;
import cn.coderap.edu.remote.AdRemoteService;
import cn.coderap.edu.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 替代原来的Controller
 */
@RestController
@RequestMapping("/ad")
public class AdRemoteServiceImpl implements AdRemoteService {

    @Autowired
    private IPromotionSpaceService promotionSpaceService;

    @RequestMapping("/space/getAllSpace")
    public List<PromotionSpaceDTO> getAllSpace() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return ConvertUtil.convertList(list, PromotionSpaceDTO.class);
    }
}
