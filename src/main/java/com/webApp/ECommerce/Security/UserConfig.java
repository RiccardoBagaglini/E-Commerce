package com.webApp.ECommerce.Security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("autenticazione")
@Data
public class UserConfig {
    private String srvUrl;
    private String userId;
    private String password;

}
