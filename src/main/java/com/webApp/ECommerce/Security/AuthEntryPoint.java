package com.webApp.ECommerce.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import lombok.extern.java.Log;
import java.io.IOException;
import java.io.PrintWriter;




import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Log
public class AuthEntryPoint  extends BasicAuthenticationEntryPoint{
    private static String REALM = "REAME";

    @Bean
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException
    {
        String ErrMsg = "Userid e/o Password non corrette!";
        log.warning("Errore Sicurezza: " + authException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");


        PrintWriter writer = response.getWriter();
        writer.println(ErrMsg);
    }

    @Override
    public void afterPropertiesSet()
    {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }
}
