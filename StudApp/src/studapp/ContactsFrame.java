/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studapp;

import client.Contact;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;

/**
 *
 * @author Rafael
 */
public class ContactsFrame extends javax.swing.JFrame {
    
    private javax.swing.JPanel[] panels;
    private javax.swing.JButton[] botoes;
    private javax.swing.JLabel[] rotuloNome;
    private javax.swing.JLabel[] rotuloEmail;
    private javax.swing.JLabel[] rotuloNota;
    private javax.swing.JLabel[] rotuloAval;
    
    private Contact[] contacts;
    
    int numeroDeContatos = 7;

    /**
     * Creates new form ContactsFrame
     */
    public ContactsFrame() {
        initComponents();
        MenuPanel.setVisible(false);
        
        
        contacts = StudApp.user.getContacts();
        numeroDeContatos = contacts.length;
        
        panels = new JPanel[numeroDeContatos];
        
        javax.swing.GroupLayout PainelContatosLayout = new javax.swing.GroupLayout(PainelContatos);
        PainelContatos.setLayout(PainelContatosLayout);
        
        GroupLayout.ParallelGroup horizontalGroup = PainelContatosLayout.createParallelGroup();
        GroupLayout.ParallelGroup verticalGroup = PainelContatosLayout.createParallelGroup();
        
        verticalGroup.addGap(0, 15, Short.MAX_VALUE);
        horizontalGroup.addGap(0, 15, Short.MAX_VALUE);
        GroupLayout.SequentialGroup vgSeqGroup = PainelContatosLayout.createSequentialGroup();
        
        botoes = new JButton[numeroDeContatos];
        rotuloNome = new JLabel[numeroDeContatos];
        rotuloEmail = new JLabel[numeroDeContatos];
        rotuloNota = new JLabel[numeroDeContatos];
        rotuloAval = new JLabel[numeroDeContatos];
        
        for(int i=0; i<numeroDeContatos; i++){
            panels[i] = new JPanel();
            javax.swing.JPanel painel2 = new JPanel();
            javax.swing.JPanel painel3 = new JPanel();
            javax.swing.JPanel painel4 = new JPanel();
            javax.swing.JPanel painel5 = new JPanel();
            javax.swing.JPanel painel8 = new JPanel();
            javax.swing.JPanel painel9 = new JPanel();
            javax.swing.JLabel rotulo2 = new JLabel();
            javax.swing.JLabel rotulo4 = new JLabel();
            javax.swing.JLabel rotulo5 = new JLabel();
            javax.swing.JLabel rotulo6 = new JLabel();
            javax.swing.JLabel rotulo12 = new JLabel();
            rotuloNome[i] = new JLabel();
            rotuloEmail[i] = new JLabel();
            rotuloNota[i] = new JLabel();
            rotuloAval[i] = new JLabel();
            botoes[i] = new JButton();
            
            panels[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            
            
            painel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            rotulo2.setBackground(new java.awt.Color(255, 255, 255));
            rotulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(painel2);
            painel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rotulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(rotulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            rotulo4.setText("Nome:");

            rotuloNome[i].setText(contacts[i].getName());

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(painel5);
            painel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(rotulo4)
                    .addGap(18, 18, 18)
                    .addComponent(rotuloNome[i])
                    .addContainerGap(92, Short.MAX_VALUE))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo4)
                    .addComponent(rotuloNome[i]))
            );

            rotulo5.setText("Email:");

            rotuloEmail[i].setText("emailDoUsuário");

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(painel8);
            painel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(rotulo5)
                    .addGap(18, 18, 18)
                    .addComponent(rotuloEmail[i])
                    .addContainerGap(97, Short.MAX_VALUE))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo5)
                    .addComponent(rotuloEmail[i]))
            );

            rotulo6.setText("Nota:");

            rotuloNota[i].setText("3,00/5,00");

            rotuloAval[i].setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            rotuloAval[i].setText("5");

            rotulo12.setText("Avaliações");

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(painel9);
            painel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(rotulo6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(rotuloNota[i])
                    .addGap(18, 18, 18)
                    .addComponent(rotuloAval[i], javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(rotulo12)
                    .addContainerGap(34, Short.MAX_VALUE))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo6)
                    .addComponent(rotuloNota[i])
                    .addComponent(rotuloAval[i])
                    .addComponent(rotulo12))
            );

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(painel3);
            painel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(painel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(painel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(painel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(painel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(painel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(painel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            botoes[i].setText("Iniciar Conversa");
            botoes[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BotaoIniciarConversaApertado(e, 0);
                }
            });

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(painel4);
            painel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(botoes[i])
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(botoes[i], javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );


            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panels[i]);
            panels[i].setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(painel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(painel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(painel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(painel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            
        
            vgSeqGroup.addComponent(panels[i]);
            horizontalGroup.addGap(0, 5, Short.MAX_VALUE);
            horizontalGroup.addComponent(panels[i]);
            
        }
        verticalGroup.addGroup(vgSeqGroup);
        horizontalGroup.addGap(0, 15, Short.MAX_VALUE);
        verticalGroup.addGap(0, 15, Short.MAX_VALUE);
        
        PainelContatosLayout.setHorizontalGroup(horizontalGroup);
        PainelContatosLayout.setVerticalGroup(verticalGroup);
        
    }
    
    private void BotaoIniciarConversaApertado(java.awt.event.ActionEvent evt, int index){
        JButton buttonPressed = (JButton)evt.getSource();
        for(int i=0; i<numeroDeContatos; i++){
            if(buttonPressed == botoes[i]){
                IniciarConversa(i);
                break;
            }
        }
    }
    
    private void IniciarConversa(int contactIndex){
        StudApp.OpenChat(contactIndex);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PainelTopo = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        MenuButton = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        PainelPrincipal = new javax.swing.JLayeredPane();
        MenuPanel = new javax.swing.JPanel();
        ConfigButton = new javax.swing.JButton();
        PerfilButton = new javax.swing.JButton();
        ContatosButton = new javax.swing.JButton();
        NoticiasButton = new javax.swing.JButton();
        CreditosButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PainelContatos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Creditos");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        MenuButton.setText("Menu");
        MenuButton.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                MenuButtonCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });
        MenuButton.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                MenuButtonPropertyChange(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contatos");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout PainelTopoLayout = new javax.swing.GroupLayout(PainelTopo);
        PainelTopo.setLayout(PainelTopoLayout);
        PainelTopoLayout.setHorizontalGroup(
            PainelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTopoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(MenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addContainerGap())
        );
        PainelTopoLayout.setVerticalGroup(
            PainelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(MenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PainelPrincipal.setEnabled(false);

        MenuPanel.setBackground(new java.awt.Color(204, 204, 204));
        MenuPanel.setEnabled(false);

        ConfigButton.setText("Configurações");
        ConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfigButtonActionPerformed(evt);
            }
        });

        PerfilButton.setText("Perfil");
        PerfilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PerfilButtonActionPerformed(evt);
            }
        });

        ContatosButton.setText("Contatos");
        ContatosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContatosButtonActionPerformed(evt);
            }
        });

        NoticiasButton.setText("Noticias");
        NoticiasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoticiasButtonActionPerformed(evt);
            }
        });

        CreditosButton.setText("Creditos");
        CreditosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditosButtonActionPerformed(evt);
            }
        });

        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContatosButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PerfilButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConfigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogoutButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CreditosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NoticiasButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PerfilButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContatosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NoticiasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreditosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelContatosLayout = new javax.swing.GroupLayout(PainelContatos);
        PainelContatos.setLayout(PainelContatosLayout);
        PainelContatosLayout.setHorizontalGroup(
            PainelContatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        PainelContatosLayout.setVerticalGroup(
            PainelContatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(PainelContatos);

        javax.swing.GroupLayout PainelPrincipalLayout = new javax.swing.GroupLayout(PainelPrincipal);
        PainelPrincipal.setLayout(PainelPrincipalLayout);
        PainelPrincipalLayout.setHorizontalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPrincipalLayout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        PainelPrincipalLayout.setVerticalGroup(
            PainelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPrincipalLayout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
        PainelPrincipal.setLayer(MenuPanel, javax.swing.JLayeredPane.POPUP_LAYER);
        PainelPrincipal.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(PainelTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PainelPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelPrincipal)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuButtonCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_MenuButtonCaretPositionChanged

    }//GEN-LAST:event_MenuButtonCaretPositionChanged

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        ToggleEnabled(MenuPanel);
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void MenuButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_MenuButtonPropertyChange

    }//GEN-LAST:event_MenuButtonPropertyChange

    private void ConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigButtonActionPerformed
        StudApp.GoTo("SettingsFrame", this);
    }//GEN-LAST:event_ConfigButtonActionPerformed

    private void PerfilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PerfilButtonActionPerformed
        StudApp.GoTo("ProfileFrame", this);
    }//GEN-LAST:event_PerfilButtonActionPerformed

    private void ContatosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContatosButtonActionPerformed
        StudApp.GoTo("ContactsFrame", this);
    }//GEN-LAST:event_ContatosButtonActionPerformed

    private void NoticiasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoticiasButtonActionPerformed
        StudApp.GoTo("HomeFrame", this);
    }//GEN-LAST:event_NoticiasButtonActionPerformed

    private void CreditosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditosButtonActionPerformed
        StudApp.GoTo("CreditsFrame", this);
    }//GEN-LAST:event_CreditosButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        StudApp.GoTo("LoginFrame", this);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        StudApp.OpenCreditsFrame();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void ToggleEnabled(java.awt.Component component){
        component.setVisible(!component.isVisible());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfigButton;
    private javax.swing.JButton ContatosButton;
    private javax.swing.JButton CreditosButton;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JToggleButton MenuButton;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JButton NoticiasButton;
    private javax.swing.JPanel PainelContatos;
    private javax.swing.JLayeredPane PainelPrincipal;
    private javax.swing.JPanel PainelTopo;
    private javax.swing.JButton PerfilButton;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
