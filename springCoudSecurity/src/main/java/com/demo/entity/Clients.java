package com.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import com.alibaba.fastjson.JSON;
import java.util.*;

/**
 * @author 35086
 */
public class Clients implements ClientDetails {
    private String clientId;
    @Override
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }



    private String clientSecret;
    @Override
    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    @Override
    public boolean isSecretRequired() {
        return clientSecret != null;
    }



    private String resourceIds;
    @Override
    public Set<String> getResourceIds() {
        if (resourceIds==null||resourceIds.trim().length()<=0){
            return Collections.emptySet();
        }
        Set<String> resourceSet = new LinkedHashSet<>();
        String[] resources = resourceIds.split(",");
        for (String resource : resources) {
            resourceSet.add(resource);
        }
        return resourceSet;
    }
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }






    private String scope;
    @Override
    public Set<String> getScope() {
        if (scope==null||scope.trim().length()<=0){
            return Collections.emptySet();
        }
        Set<String> scopeSet = new LinkedHashSet<>();
        String[] scopes = scope.split(",");
        for (String scope : scopes) {
            scopeSet.add(scope);
        }
        return scopeSet;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    @Override
    public boolean isScoped() {
        return scope!=null&&scope.trim().length()>0;
    }



    private String authorizedGrantTypes;
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (authorizedGrantTypes==null||authorizedGrantTypes.trim().length()<=0){
            return Collections.emptySet();
        }
        Set<String> authorizedGrantTypeSet = new LinkedHashSet<>();
        String[] types = authorizedGrantTypes.split(",");
        for (String type : types) {
            authorizedGrantTypeSet.add(type);
        }
        return authorizedGrantTypeSet;
    }
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }



    private String registeredRedirectUris;
    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (registeredRedirectUris==null||registeredRedirectUris.trim().length()<=0){
            return Collections.emptySet();
        }
        Set<String> redirectUriSet = new LinkedHashSet<>();
        String[] uris = registeredRedirectUris.split(",");
        for (String uri : uris) {
            redirectUriSet.add(uri);
        }
        return redirectUriSet;
    }
    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUris = registeredRedirectUris;
    }




    private String authorities;
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (authorities==null||authorities.trim().length()<=0){
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        String[] authoritiesArray = authorities.split(",");
        for (String authorities : authoritiesArray) {
            authorityList.add((GrantedAuthority) () -> authorities);
        }
        return authorityList;
    }
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }




    private Integer accessTokenValiditySeconds;
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }
    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }



    private Integer refreshTokenValiditySeconds;
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }
    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }



    private String additionalInformation;
    @Override
    public Map<String, Object> getAdditionalInformation() {
        if (additionalInformation==null||additionalInformation.trim().length()<=0){
            return Collections.emptyMap();
        }
        return JSON.parseObject(additionalInformation);
    }
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    private String autoApproveScopes;
    public Set<String> getAutoApproveScopes() {
        if (autoApproveScopes==null||autoApproveScopes.trim().length()<=0){
            return Collections.emptySet();
        }
        Set<String> approveSet = new HashSet<>();
        String[] approves = autoApproveScopes.split(",");
        for (String approve : approves) {
            approveSet.add(approve);
        }
        return approveSet;
    }
    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }


    @Override
    public boolean isAutoApprove(String scope) {
        if(getAutoApproveScopes() == null||getAutoApproveScopes().isEmpty()) {
            return false;
        } else {
            Iterator var2 = getAutoApproveScopes().iterator();
            String auto;
            do {
                if(!var2.hasNext()) {
                    return false;
                }
                auto = (String)var2.next();
            } while(!auto.equals("true") && !scope.matches(auto));
            return true;
        }
    }

}