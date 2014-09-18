package com.deepsky.spring_security.dynamic;

import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "customExpressionHandler")
public class CustomWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

    private AuthenticationTrustResolver trustResolver1 = new AuthenticationTrustResolverImpl();

    protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        WebSecurityExpressionRoot root = new CustomWebSecurityExpressionRoot(authentication, fi);
        root.setPermissionEvaluator(this.getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver1);
        root.setRoleHierarchy(this.getRoleHierarchy());
        return root;
    }

    public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
        Assert.notNull(trustResolver, "trustResolver cannot be null");
        this.trustResolver1 = trustResolver;
    }


//    @Override
//    protected SecurityExpressionRoot createSecurityExpressionRoot(
//            Authentication authentication, FilterInvocation fi) {
//
//        WebSecurityExpressionRoot expressionRoot = new CustomWebSecurityExpressionRoot(authentication, fi);
//
//        return expressionRoot;
//    }
}