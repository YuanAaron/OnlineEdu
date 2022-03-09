package cn.coderap.edu.ad.controller;

import cn.coderap.edu.dto.PromotionSpaceDTO;
import cn.coderap.edu.remote.AdRemoteService;
import cn.coderap.edu.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdRemoteService adRemoteService;

    @GetMapping("/space/getAllSpace")
    public ResponseDTO getAllSpace() {
        List<PromotionSpaceDTO> spaceDTOList = adRemoteService.getAllSpace();
        return ResponseDTO.success(spaceDTOList);
    }

    @PostMapping("/space/saveOrUpdateSpace")
    public ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO spaceDTO) {
        return adRemoteService.saveOrUpdateSpace(spaceDTO);
    }

    @GetMapping("/space/getSpaceById")
    public ResponseDTO getSpaceById(@RequestParam("id") Integer id) {
        PromotionSpaceDTO spaceDTO = adRemoteService.getSpaceById(id);
        return ResponseDTO.success(spaceDTO);
    }
}
