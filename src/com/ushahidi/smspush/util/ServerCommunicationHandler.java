/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.smspush.util;

import org.mobiweb.mobile.Message;
import org.mobiweb.mobile.ServerCommunication;
import org.mobiweb.mobile.exceptions.ConnectionException;
import org.mobiweb.mobile.exceptions.MessageException;

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
        try {
            // Construct the message
            Message message = new Message();
            message.addParameter("data", msgData);
            message.addParameter("type", "Text");

            // Send to server
            ServerCommunication serverCommunication = new ServerCommunication(this.serverURL, true);
            serverCommunication.sendPostMessage(message);

            // Close connection
            serverCommunication.closeConnection();

            // Sent the message
            return true;
        } catch (ConnectionException ex) {
            return false;
        } catch (MessageException ex) {
            return false;
        }
    }

    public boolean pushBinaryToServer(byte[] msgData) {
        try {
            // Construct the message
            Message message = new Message();
            message.addPhoto("data", msgData);
            message.addParameter("type", "Binary");

            // Send to server
            ServerCommunication serverCommunication = new ServerCommunication(this.serverURL, true);
            serverCommunication.sendPostMessage(message);

            // Close server connection
            serverCommunication.closeConnection();

            // Sent the message
            return true;
        } catch (ConnectionException ex) {
            return false;
        } catch (MessageException ex) {
            return false;
        }
    }
}