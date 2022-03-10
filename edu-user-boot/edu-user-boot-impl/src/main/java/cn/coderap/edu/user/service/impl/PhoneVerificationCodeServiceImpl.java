package cn.coderap.edu.user.service.impl;

import cn.coderap.edu.user.entity.PhoneVerificationCode;
import cn.coderap.edu.user.mapper.PhoneVerificationCodeMapper;
import cn.coderap.edu.user.service.IPhoneVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oshacker
 * @since 2022-03-10
 */
@Service
public class PhoneVerificationCodeServiceImpl extends ServiceImpl<PhoneVerificationCodeMapper, PhoneVerificationCode> implements IPhoneVerificationCodeService {

}
