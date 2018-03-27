package com.imooc.security.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FangYan on 2017/10/29 0029.
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
