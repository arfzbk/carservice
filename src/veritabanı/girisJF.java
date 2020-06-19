
package veritabanı;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class girisJF extends javax.swing.JFrame {
public String kullanici="adfa";
    public girisJF() {
        initComponents();
        seticon();
        //Nimbus tasarımını ekleme
        try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Windows Classic".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameField = new javax.swing.JTextField();
        jl_kullanıcı = new javax.swing.JLabel();
        jl_sifre = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jb_giris = new javax.swing.JButton();
        jl_isim = new javax.swing.JLabel();
        jl_kucult = new javax.swing.JLabel();
        jl_cikis = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        getContentPane().setLayout(null);

        usernameField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usernameField);
        usernameField.setBounds(166, 53, 183, 30);

        jl_kullanıcı.setFont(new java.awt.Font("Calibri", 0, 13)); // NOI18N
        jl_kullanıcı.setText("Kullanıcı Adı");
        getContentPane().add(jl_kullanıcı);
        jl_kullanıcı.setBounds(170, 20, 120, 33);

        jl_sifre.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jl_sifre.setText("Şifre");
        getContentPane().add(jl_sifre);
        jl_sifre.setBounds(170, 110, 70, 33);

        passwordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        getContentPane().add(passwordField);
        passwordField.setBounds(170, 140, 180, 30);

        jb_giris.setBackground(new java.awt.Color(255, 255, 255));
        jb_giris.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jb_giris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veritabanı/kayıt.png"))); // NOI18N
        jb_giris.setText("Giriş");
        jb_giris.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_giris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_girisActionPerformed(evt);
            }
        });
        getContentPane().add(jb_giris);
        jb_giris.setBounds(200, 217, 99, 44);

        jl_isim.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jl_isim.setForeground(new java.awt.Color(102, 102, 255));
        jl_isim.setText("Araç Servis Otomasyonu");
        getContentPane().add(jl_isim);
        jl_isim.setBounds(20, 570, 460, 140);

        jl_kucult.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jl_kucult.setForeground(new java.awt.Color(255, 255, 255));
        jl_kucult.setText(" -");
        jl_kucult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_kucultMouseClicked(evt);
            }
        });
        getContentPane().add(jl_kucult);
        jl_kucult.setBounds(940, 0, 40, 40);

        jl_cikis.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jl_cikis.setForeground(new java.awt.Color(255, 255, 255));
        jl_cikis.setText("    X");
        jl_cikis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_cikisMouseClicked(evt);
            }
        });
        getContentPane().add(jl_cikis);
        jl_cikis.setBounds(980, 0, 40, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veritabanı/arac.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.setMaximumSize(new java.awt.Dimension(1024, 768));
        jLabel4.setMinimumSize(new java.awt.Dimension(1024, 768));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(-9, -7, 1040, 780);

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed

    }//GEN-LAST:event_usernameFieldActionPerformed

    private void jb_girisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_girisActionPerformed
   String kullanıcı=usernameField.getText();
   String sifre=passwordField.getText();
   Veritabanı db=new Veritabanı();
        if (db.girisYap(kullanıcı, sifre)) {
            JOptionPane.showMessageDialog(this, "Hoşgeldin:"+usernameField.getText());
            anaekranJF home=new anaekranJF();
            kullanici=usernameField.getText(); 
            home.setVisible(true);
            this.setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(this,"Giriş Başarısız");
        }
    }//GEN-LAST:event_jb_girisActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void jl_cikisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_cikisMouseClicked
       System.exit(0);
    }//GEN-LAST:event_jl_cikisMouseClicked

    private void jl_kucultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_kucultMouseClicked
        this.setState(girisJF.ICONIFIED);
    }//GEN-LAST:event_jl_kucultMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new girisJF().setVisible(true);
            }
        });
    }
    private void seticon(){
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ikon.png")));
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jb_giris;
    private javax.swing.JLabel jl_cikis;
    private javax.swing.JLabel jl_isim;
    private javax.swing.JLabel jl_kucult;
    private javax.swing.JLabel jl_kullanıcı;
    private javax.swing.JLabel jl_sifre;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
