package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by FangYan on 2017/12/26 0026.
 */
public interface ValidateCodeRepository {

    void save(ServletWebRequest request,ValidateCode code,ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request,ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request,ValidateCodeType validateCodeType);
}
