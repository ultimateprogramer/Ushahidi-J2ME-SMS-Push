/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.smspush.util;

/**
 *
 * @author Admin
 */
public class ServerCommunicationHandler {
    private String serverURL;

    public ServerCommunicationHandler(String url) {
        // Construct the object to send to specific URL
        this.serverURL = url;
    }

    public boolean pushTextToServer(String msgData) {
        // TODO: Communicate to server
        return true;
    }

    public boolean pushBinaryToServer(byte[] msgData) {
        // TODO: Communicate to server
        return true;
    }
}
