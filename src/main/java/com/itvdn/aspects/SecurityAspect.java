package com.itvdn.aspects;

import com.itvdn.exceptions.NotAuthorizedException;
import com.itvdn.model.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SecurityAspect {
    private ApplicationContext applicationContext;


    public void checkAuthorize(){
        Authorization authorization = applicationContext.getBean("userAuthorized", Authorization.class);
        if (!authorization.getAuthorized()) {
            throw new NotAuthorizedException("User is not authorized!");
        }
    }
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
