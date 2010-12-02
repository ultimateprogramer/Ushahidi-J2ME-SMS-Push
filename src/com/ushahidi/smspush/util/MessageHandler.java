/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ushahidi.smspush.util;

import gov.nist.siplite.message.Message;
import java.io.IOException;
import java.io.InterruptedIOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author Admin
 */
public class MessageHandler implements MessageListener{
    MessageConnection messageConnection;
    boolean connectionSuccessful;

    public MessageHandler() {
        try {
            // Register the message server
            this.messageConnection = (MessageConnection) Connector.open("sms://:5000");
            this.messageConnection.setMessageListener((MessageListener) this);
            // Managed to connect
            connectionSuccessful = true;
        } catch (IOException ex) {
            connectionSuccessful = false;
        }
    }

    public void notifyIncomingMessage(MessageConnection conn) {
        boolean processSuccess = false;
        boolean readSuccess = false;

        ServerCommunicationHandler serverCommunicationHandler = new ServerCommunicationHandler(ApplicationConfig.appUrl);

        try {
            Message msg = (Message) conn.receive();
            processSuccess = true;

            if(msg instanceof TextMessage) {
                TextMessage tmsg = (TextMessage)msg;
    		String data = tmsg.getPayloadText();
                // Process the text message - push to server

                serverCommunicationHandler.pushTextToServer(data);
            }
            else if(msg instanceof BinaryMessage) {
                BinaryMessage bmsg = (BinaryMessage)msg;
                byte[] data = bmsg.getPayloadData();
                // Process the binary message - push to server

                serverCommunicationHandler.pushBinaryToServer(data);
            }
        } catch (InterruptedIOException ex) {
            // TODO: Handle this error
        } catch (IOException ex)  {
            // TODO: Handle this error
        }
    }

    public void closeInstance() {
        try {
            this.messageConnection.setMessageListener(null);
            this.messageConnection.close();
        } catch (IOException ex) {
            // TODO: Handle this exception
        }
    }
}