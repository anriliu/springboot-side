package com.github.yingzhuo.springboot.side.restsec.advice;

import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresAuthentication;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresGuest;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresPermissions;
import com.github.yingzhuo.springboot.side.restsec.annotation.RequiresRoles;
import com.github.yingzhuo.springboot.side.restsec.core.AssertUtils;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class MethodInterceptor implements org.aopalliance.intercept.MethodInterceptor, org.aopalliance.aop.Advice {

    private static final String NULL_ERROR_MESSAGE = ":::<NO MESSAGE>:::";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        handleRequiresAuthentication(method.getAnnotation(RequiresAuthentication.class));
        handleRequiresGuest(method.getAnnotation(RequiresGuest.class));
        handleRequiresPermissions(method.getAnnotation(RequiresPermissions.class));
        handleRequiresRoles(method.getAnnotation(RequiresRoles.class));
        return invocation.proceed();
    }

    private void handleRequiresAuthentication(RequiresAuthentication annotation) {
        if (annotation != null) {
            AssertUtils.assertAuthentication(getErrorMessage(annotation.errorMessage()));
        }
    }

    private void handleRequiresGuest(RequiresGuest annotation) {
        if (annotation != null) {
            AssertUtils.assertGuest(getErrorMessage(annotation.errorMessage()));
        }
    }

    private void handleRequiresPermissions(RequiresPermissions annotation) {
        if (annotation != null) {
            AssertUtils.assertPermissions(Arrays.asList(annotation.value()), annotation.logical(), getErrorMessage(annotation.errorMessage()));
        }
    }

    private void handleRequiresRoles(RequiresRoles annotation) {
        if (annotation != null) {
            AssertUtils.assertRoles(Arrays.asList(annotation.value()), annotation.logical(), getErrorMessage(annotation.errorMessage()));
        }
    }

    private String getErrorMessage(String message) {
        if (NULL_ERROR_MESSAGE.equals(message)) {
            return null;
        }
        return message.trim();
    }

}
