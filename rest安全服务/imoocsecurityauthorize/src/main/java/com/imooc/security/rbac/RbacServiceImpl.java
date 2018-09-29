package com.imooc.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FangYan on 2017/10/29 0029.
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    //因为配置的url可能是带*号的，所以需要用AntPathMatcher比较。

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principle=authentication.getPrincipal();
        boolean hasPermission = false;
        //principle可能是一个匿名的字符串 "anonymousUser"
        if(principle instanceof UserDetails) { //principle可能是字符串，说明没有通过之前的验证
            String username = ((UserDetails) principle).getUsername();
            //读取用户所拥有的权限的所有URL
            Set<String> urls = new HashSet<>(); //它不允许集合中有重复的值
            for (String url : urls) {
                if(antPathMatcher.match(url,request.getRequestURI()));
                hasPermission=true;
                break;
            }
        }
        return hasPermission;
    }
}
