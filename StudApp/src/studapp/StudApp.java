/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studapp;

import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Rafael
 */
public class StudApp {

    private final static ArrayList<Integer> chatList = new ArrayList();
    private final static ArrayList<ChatFrame> chatFrames = new ArrayList();
    private static CreditsFrame creditsFrame = null;
    
    public static ArrayList<Integer> contactIDs = new ArrayList();
    
    public static client.User user = null;
    public static Socket socket = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
    
    public static void GoTo(String frameClass, JFrame frame){
        int posX = 0;
        int posY = 0;
        if(frame != null){
            posX = frame.getX();
            posY = frame.getY();
            frame.dispose();
        }
        try{
            Class theClass = Class.forName("studapp." + frameClass);
            JFrame newFrame = (JFrame) theClass.newInstance();
            newFrame.setBounds(posX, posY, newFrame.getWidth(), newFrame.getHeight());
            newFrame.setVisible(true);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println(e);
        }
    }
    
    public static ChatFrame OpenChat(Integer contactID){
        ChatFrame chatFrame;
        if(!chatList.contains(contactID)){
            chatFrame = new ChatFrame(contactID);
            chatList.add(contactID);
            chatFrames.add(chatFrame);
            chatFrame.setVisible(true);
        }
        else{
            chatFrame = chatFrames.get(chatList.indexOf(contactID));
            chatFrame.requestFocus();
        }
        return chatFrame;
    }
    
    public static void CloseChat(Integer contactID){
        chatFrames.remove(chatList.indexOf(contactID));
        chatList.remove(contactID);
    }

    public static void OpenCreditsFrame() {
        if(creditsFrame == null){
            creditsFrame = new CreditsFrame();
            creditsFrame.setVisible(true);
        }
    }

    public static void CloseCreditsFrame() {
        if(creditsFrame != null){
            creditsFrame.setVisible(false);
            creditsFrame = null;
        }
    }
    
    public static int GetContactID(int index){
        return contactIDs.get(index);
    }
    
    public static int GetContactIndex(int contactID){
        for(Integer curCon : contactIDs){
            if (curCon == contactID){
                return curCon;
            }
        }
        contactIDs.add(contactID);
        return contactIDs.indexOf(contactID);
    }
    
}
