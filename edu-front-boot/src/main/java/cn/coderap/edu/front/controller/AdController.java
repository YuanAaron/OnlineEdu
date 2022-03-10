package cn.coderap.edu.front.controller;

import cn.coderap.edu.dto.PromotionSpaceDTO;
import cn.coderap.edu.remote.AdRemoteService;
import cn.coderap.edu.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdRemoteService adRemoteService;

    @GetMapping("/getAdBySpaceKeys")
    public ResponseDTO getAdBySpaceKeys(@RequestParam("spaceKeys") String[] spaceKeys) {
        List<PromotionSpaceDTO> spaceDTOList = adRemoteService.getAdBySpaceKeys(spaceKeys);
        return ResponseDTO.success(spaceDTOList);
    }
}
