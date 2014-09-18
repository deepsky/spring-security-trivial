package com.deepsky.spring_security.dynamic;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;


/**
 * More details http://stackoverflow.com/a/10451102
 */
public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

    public CustomWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
        super(a, fi);
    }

    public boolean yourCustomMethod() {
        boolean calculatedValue = true; // FIXME...;
        return calculatedValue;

    }
}