/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studapp;

import javax.swing.JFrame;

/**
 *
 * @author Rafael
 */
public class StudApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
    
    public static void GoTo(String frameClass, JFrame frame){
        if(frame != null){
            frame.setVisible(false);
        }
        try{
            Class theClass = Class.forName("studapp." + frameClass);
            JFrame newFrame = (JFrame) theClass.newInstance();
            newFrame.setVisible(true);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println(e);
        }
    }
    
    public static void GoToSettings(javax.swing.JFrame frame){
        if(frame != null){
            frame.setVisible(false);
        }
        SettingsFrame newFrame = new SettingsFrame();
        newFrame.setVisible(true);
    }
    
    public static void GoToProfile(javax.swing.JFrame frame){
        if(frame != null){
            frame.setVisible(false);
        }
        ProfileFrame newFrame = new ProfileFrame();
        newFrame.setVisible(true);
    }
    
    public static void GoToContacts(javax.swing.JFrame frame){
        if(frame != null){
            frame.setVisible(false);
        }
        ContactsFrame newFrame = new ContactsFrame();
        newFrame.setVisible(true);
    }
    
}
