package com.webApp.ECommerce.Security;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service("customUserDetailService")
@Log
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserConfig userConfig;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String ErrMsg;
        if (username == null || username.length() < 2) {
            ErrMsg = "Nome utente assente o non valido";
            log.warning(ErrMsg);
            throw new UsernameNotFoundException(ErrMsg);
        }
        User user = this.GettHttpValue(username);
         if(user == null){
             String Err = String.format("Nome utente non trovato",username);
             log.warning(Err);
             throw  new UsernameNotFoundException(Err);
         }
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
         builder =org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
         builder.disabled(user.getAbilitato().equals("si")?false:true);
         builder.password(user.getPassword());

         String [] ruoli = user.getRuolo().stream().map(a -> a.getRole()).toArray(String[]::new);
         builder.authorities(ruoli);
         return builder.build();
    }


    private User GettHttpValue(String UserId) {
        URI url = null;
        try {
            String srvUrl = userConfig.getSrvUrl();
            url = new URI(srvUrl + UserId);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userConfig.getUserId(), userConfig.getPassword()));
        User user = null;
        try{
            user = restTemplate.getForObject(url,User.class);
        }catch (Exception e){
          String ErrMsg = String.format("Connessione al servizio di autenticazione non riuscita");
          log.warning(ErrMsg);
        }
        return user;
    }
}