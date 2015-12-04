/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studapp;

import client.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class MessageListener extends Thread{
    
    @Override
    public void run(){
        while(true){
            try {
                sleep(1000);
                MsgCount[] msgCount = StudApp.user.recvMessageCount(null, null);
                if(msgCount != null){
                    for(MsgCount curmsgc : msgCount){
                        int curOriginId = curmsgc.originId;
                        ChatFrame cf = StudApp.OpenChat(curOriginId);
                        Message[] msgs = StudApp.user.recvMessages(curOriginId, null, null);
                        for (Message msg : msgs) {
                            cf.RecieveMessage(msg);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
