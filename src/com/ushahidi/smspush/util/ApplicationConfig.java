/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.smspush.util;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;

/**
 *
 * @author Admin
 */
public class ApplicationConfig {

    public static String appUrl = "http://www.ushahidi.com/";

    public ApplicationConfig() {
        
    }

    public static boolean loadApplicationConfig() {
        RecordStore rs;

        try {
            // TODO: Check if values exist in persistent storage
            rs = RecordStore.openRecordStore("ApplicationSettings", false);
            byte[] bUrl = rs.getRecord(0);
            rs.closeRecordStore();
            
            appUrl = bUrl.toString();
        } catch (RecordStoreFullException ex) {
            // Cant do anything here, just load defaults
        } catch (RecordStoreNotFoundException ex) {
            // Create it and save default values
            saveApplicationConfig(appUrl);
            return true;
        } catch (RecordStoreException ex) {
            return false;
        }

        // TODO: Load default values and save them to persistent storage if not

        return true;
    }

    public static boolean saveApplicationConfig(String url) {
        RecordStore rs;
        try {
            rs = RecordStore.openRecordStore("ApplicationSettings", true);
            rs.addRecord(url.getBytes(), 0, (url.getBytes()).length);
            rs.closeRecordStore();
        } catch (RecordStoreFullException ex) {
            // Cant do anything here
            return false;
        } catch (RecordStoreNotFoundException ex) {
            // We cant hit this, gets created automatically
        } catch (RecordStoreException ex) {
           return false;
        }

        return true;
    }
}
