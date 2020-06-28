package com.demo.entity;

import com.alibaba.fastjson.JSON;
import io.netty.util.internal.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * @author 35086
 */
public class Clients implements ClientDetails {
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private Date createTime;

    private Byte archived;

    private Byte trusted;

    private String autoapprove;

    private String additionalInformation;

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }


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

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    @Override
    public Set<String> getScope() {
        if (StringUtil.isNullOrEmpty(scope)){
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
        this.scope = scope == null ? null : scope.trim();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtil.isNullOrEmpty(authorizedGrantTypes)){
            return Collections.emptySet();
        }
        Set<String> authorizedGrantTypeSet = new LinkedHashSet<>();
        String[] types = authorizedGrantTypes.split(",");
        for (String type : types) {
            authorizedGrantTypeSet.add(type);
        }
        return authorizedGrantTypeSet;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (StringUtil.isNullOrEmpty(authorities)){
            return Collections.emptyList();
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        String[] authoritiesArray = authorities.split(",");
        for (String authorities : authoritiesArray) {
            authorityList.add((GrantedAuthority) () -> authorities);
        }
        return authorityList;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getArchived() {
        return archived;
    }

    public void setArchived(Byte archived) {
        this.archived = archived;
    }

    public Byte getTrusted() {
        return trusted;
    }

    public void setTrusted(Byte trusted) {
        this.trusted = trusted;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove == null ? null : autoapprove.trim();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        if (StringUtil.isNullOrEmpty(additionalInformation)){
            return Collections.emptyMap();
        }
        return JSON.parseObject(additionalInformation);
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation == null ? null : additionalInformation.trim();
    }
}