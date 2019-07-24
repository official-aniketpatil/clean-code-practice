package com.epam.engx.cleancode.functions.task2;


import java.util.Map;


public class SitePage {

    private static final String HTTP = "http://";
    private static final String EDITABLE = "/?edit=true";
    private static final String DOMAIN = "mysite.com";


    private String siteGroup;
    private String userGroup;

    public SitePage(String siteGroup, String userGroup) {
        this.siteGroup = siteGroup;
        this.userGroup = userGroup;
    }
    
    private String compileFullPageUrl(String paramsString) {
    	return HTTP + DOMAIN + EDITABLE + paramsString + getGroupAttributesParams();
    }
    
    public String getEditablePageUrl(Map<String, String> params) {
        String paramsString = "";
        for (Map.Entry<String, String> param : params.entrySet())
            paramsString += "&" + convertEntryToGetParams(param);
        return compileFullPageUrl(paramsString);
    }
    
    private String convertEntryToGetParams(Map.Entry<String, String> param) {
    	return param.getKey() + "=" + param.getValue();
    }   
    private String getGroupAttributesParams() {
        return "&siteGrp=" + getSiteGroup() + "&userGrp=" + getUserGroup();
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getSiteGroup() {
        return siteGroup;
    }




}
