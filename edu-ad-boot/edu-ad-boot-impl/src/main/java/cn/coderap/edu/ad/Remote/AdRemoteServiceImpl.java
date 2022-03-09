package cn.coderap.edu.ad.Remote;

import cn.coderap.edu.ad.entity.PromotionAd;
import cn.coderap.edu.ad.entity.PromotionSpace;
import cn.coderap.edu.ad.service.IPromotionAdService;
import cn.coderap.edu.ad.service.IPromotionSpaceService;
import cn.coderap.edu.dto.PromotionAdDTO;
import cn.coderap.edu.dto.PromotionSpaceDTO;
import cn.coderap.edu.remote.AdRemoteService;
import cn.coderap.edu.enums.YesOrNoEnum;
import cn.coderap.edu.util.ConvertUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 替代原来的Controller
 */
@RestController
@RequestMapping("/ad")
public class AdRemoteServiceImpl implements AdRemoteService {

    @Autowired
    private IPromotionSpaceService promotionSpaceService;
    @Autowired
    private IPromotionAdService promotionAdService;

    @RequestMapping("/space/getAllSpace")
    public List<PromotionSpaceDTO> getAllSpace() {
        List<PromotionSpace> list = promotionSpaceService.list();
        return ConvertUtil.convertList(list, PromotionSpaceDTO.class);
    }

    @RequestMapping("/getAdBySpaceKeys")
    public List<PromotionSpaceDTO> getAdBySpaceKeys(String[] spaceKeys) {
        List<PromotionSpaceDTO> spaceDTOList = new ArrayList<>();
        for (String spaceKey : spaceKeys) {
            QueryWrapper<PromotionSpace> spaceQueryWrapper = new QueryWrapper<>();
            spaceQueryWrapper.eq("spaceKey", spaceKey);
            // 获取广告位对象
            PromotionSpace promotionSpace = promotionSpaceService.getOne(spaceQueryWrapper);

            // 根据广告位对象获取其所有广告
            QueryWrapper<PromotionAd>  adQueryWrapper = new QueryWrapper<>();
            adQueryWrapper.eq("spaceId", promotionSpace.getId());
            // 上架状态：1上架、0下架
            adQueryWrapper.eq("status", YesOrNoEnum.YES.getType());
            // 在有效期内
            Date now = new Date();
            adQueryWrapper.lt("startTime", now);
            adQueryWrapper.gt("endTime", now);
            List<PromotionAd> promotionAdList = promotionAdService.list(adQueryWrapper);
            List<PromotionAdDTO> promotionAdDTOList = ConvertUtil.convertList(promotionAdList, PromotionAdDTO.class);

            PromotionSpaceDTO promotionSpaceDTO = ConvertUtil.convert(promotionSpace, PromotionSpaceDTO.class);
            promotionSpaceDTO.setAdDTOList(promotionAdDTOList);
            spaceDTOList.add(promotionSpaceDTO);
        }
        return spaceDTOList;
    }
}
