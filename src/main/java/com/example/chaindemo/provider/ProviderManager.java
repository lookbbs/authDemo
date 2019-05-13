package com.example.chaindemo.provider;

import com.example.chaindemo.exception.AuthenticationException;
import com.example.chaindemo.exception.UserNotExistException;
import com.example.chaindemo.handler.AuthenticationFailureHandler;
import com.example.chaindemo.handler.AuthenticationSuccessHandler;
import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.ServletHeader;
import com.example.chaindemo.pojo.UserDetail;
import com.example.chaindemo.provider.sms.SmsRegisterProvider;
import com.example.chaindemo.provider.validator.LoginRequestValidator;
import com.example.chaindemo.provider.validator.RechargeValidator;
import com.example.chaindemo.provider.validator.black.DeviceBlackValidator;
import com.example.chaindemo.provider.validator.black.IpBlackValidator;
import com.example.chaindemo.provider.validator.data.DeviceValidData;
import com.example.chaindemo.provider.validator.data.IpValidData;
import com.example.chaindemo.provider.validator.data.UsernameValidData;
import com.example.chaindemo.provider.validator.white.WhiteValidatorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 认证管理类
 *
 * @author yuandongfei
 * @date 2019/4/10
 */
@Slf4j
@Component
public class ProviderManager {

    @Autowired
    private ProviderHolder providerHolder;

    @Autowired
    private WhiteValidatorHolder whiteValidatorHolder;

    @Autowired
    private DeviceBlackValidator deviceBlackValidator;

    @Autowired
    private RechargeValidator rechargeValidator;

    @Autowired
    private IpBlackValidator ipBlackValidator;

    @Autowired
    private SmsRegisterProvider smsRegisterProvider;

    @Autowired
    private LoginRequestValidator loginRequestValidator;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 登录认证
     *
     * @param request
     * @param header
     * @return
     */
    public UserDetail authenticate(LoginRequest request, ServletHeader header) {
        try {
            // 登录请求参数校验
            loginRequestValidator.isValid(request);
            // 检验IP白名单
            whiteValidatorHolder.valid(Stream.of(
                    // 检验IP白名单
                    new IpValidData(header.getIp()),
                    // 检验设备号白名单
                    new DeviceValidData(request.getDeviceNo()),
                    // 检验用户名白名单
                    new UsernameValidData(request.getUserName())).collect(Collectors.toList()), false);
            log.info(">>> IP，设备号，账户白名单验证通过");

            // 检验设备黑名单, 校验不通过则抛出ValidatorException异常
            deviceBlackValidator.isValid(request.getDeviceNo());

            // 充值金额判断
            boolean checkRecharge = rechargeValidator.isValid(request.getUserName());
            if (!checkRecharge) {
                // 检验IP黑名单, 校验不通过则抛出ValidatorException异常
                ipBlackValidator.isValid(header.getIp());
            }

            // 读取用户信息
            UserDetail authenticate = providerHolder.find(request.getLoginType()).authenticate(request);

            // 记录用户登录行为
            log.info(">>> 记录用户登录行为。。。。。。");
            authenticationSuccessHandler.onSuccess();

            return authenticate;
        } catch (UserNotExistException e) {
            if (3 == request.getLoginType()) {
                // 当未找到用户时，用户的登录类型为手机号登陆，则进行新用户注册流程
                UserDetail register = smsRegisterProvider.register(request);
                // 记录用户注册行为
                log.info(">>> 记录用户注册行为。。。。。。");
                return register;
            }
            authenticationFailureHandler.onFailure(request, e);
        } catch (AuthenticationException e) {
            log.error(">>> 用户登录认证发生异常。", e);
            authenticationFailureHandler.onFailure(request, e);
        }
        return null;
    }
}
