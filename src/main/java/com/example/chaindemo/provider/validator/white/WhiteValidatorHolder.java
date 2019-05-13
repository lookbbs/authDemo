package com.example.chaindemo.provider.validator.white;

import com.example.chaindemo.provider.validator.data.ValidData;
import com.example.chaindemo.provider.validator.exception.NotFoundValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 白名单校验工具类
 * @author yuandongfei
 * @date 2019/4/11
 */
@Component
public class WhiteValidatorHolder {

    @Autowired
    private List<WhiteValidator> validators;


    /**
     * 数据校验
     *
     * @param dataList 待校验的数据集合
     * @param isOr     是否or关系校验。true: 有一个校验通过，则全部通过，false：全部校验通过才算通过
     * @return
     */
    public <T> boolean valid(List<ValidData<T>> dataList, boolean isOr) {
        if (isOr) {
            return validOr(dataList);
        }
        return validAdd(dataList);
    }

    private <T> boolean validAdd(List<ValidData<T>> dataList) {
        boolean flag = true;
        for (ValidData data : dataList) {
            flag = flag && valid(data);
        }
        return flag;
    }

    private <T> boolean validOr(List<ValidData<T>> dataList) {
        for (ValidData data : dataList) {
            if (valid(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数据有效性验证
     *
     * @param data
     * @return
     */
    public <T> boolean valid(ValidData<T> data) {
        return findValidator(data.getClass()).isPermitted(data.getData());
    }

    private WhiteValidator findValidator(Class<? extends ValidData> clazz) {
        if (CollectionUtils.isEmpty(validators)) {
            throw new NotFoundValidator("系统未实现验证器");
        }
        Optional<WhiteValidator> first = validators.stream().filter(val -> val.supports(clazz)).findFirst();
        if (!first.isPresent()) {
            throw new NotFoundValidator("未找到合适的验证器实现类");
        }
        return first.get();
    }
}
