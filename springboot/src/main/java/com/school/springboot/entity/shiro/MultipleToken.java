package com.school.springboot.entity.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class MultipleToken implements Serializable, AuthenticationToken {
    
    private String username;

    /**
     * The password, in char[] format
     */
    private char[] password;

    /**
     * Whether or not 'rememberMe' should be enabled for the corresponding login attempt;
     * default is <code>false</code>
     */
    private boolean rememberMe = false;

    /**
     * The location from where the login attempt occurs, or <code>null</code> if not known or explicitly
     * omitted.
     */
    // private String host;

    private int loginType;

    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return this.getUsername();
    }

    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return this.getPassword();
    }
    
}
