/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.smspush.util;

/**
 *
 * @author Admin
 */
public class ApplicationConfig {

    private String appUrl;

    public ApplicationConfig() {
        this.appUrl = "";

        if(!loadApplicationConfig()) {
            // TODO: Load default values and save them to persistent storage
        }
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public boolean loadApplicationConfig() {
        return true;
    }

    public boolean saveApplicationConfig() {
        return true;
    }
}
