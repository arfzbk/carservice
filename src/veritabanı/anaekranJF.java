
package veritabanı;

import java.awt.Toolkit;
import java.util.ArrayList;

public class anaekranJF extends javax.swing.JFrame {
    
   
    public anaekranJF() {
        initComponents();
       
       seticon();
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jl_isim = new javax.swing.JLabel();
        jb_fatura = new javax.swing.JButton();
        jb_cikis = new javax.swing.JButton();
        jb_servis_tuketilen = new javax.swing.JButton();
        jb_malzeme = new javax.swing.JButton();
        jb_musteri_ekle = new javax.swing.JButton();
        jb_bakım = new javax.swing.JButton();
        jb_calisanListele = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setLayout(null);

        jl_isim.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jl_isim.setForeground(new java.awt.Color(255, 0, 51));
        jl_isim.setText("Araç Servisi Ana Menü");
        jPanel2.add(jl_isim);
        jl_isim.setBounds(430, 30, 471, 58);

        jb_fatura.setBackground(new java.awt.Color(204, 255, 204));
        jb_fatura.setText("Fatura Görüntüle");
        jb_fatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_fatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_faturaActionPerformed(evt);
            }
        });
        jPanel2.add(jb_fatura);
        jb_fatura.setBounds(650, 310, 200, 150);

        jb_cikis.setBackground(new java.awt.Color(204, 255, 102));
        jb_cikis.setText("Çıkış");
        jb_cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cikisActionPerformed(evt);
            }
        });
        jPanel2.add(jb_cikis);
        jb_cikis.setBounds(920, 720, 90, 40);

        jb_servis_tuketilen.setBackground(new java.awt.Color(255, 204, 204));
        jb_servis_tuketilen.setText("Servisin Tükettiği Malzeme");
        jb_servis_tuketilen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_servis_tuketilen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_servis_tuketilenActionPerformed(evt);
            }
        });
        jPanel2.add(jb_servis_tuketilen);
        jb_servis_tuketilen.setBounds(160, 510, 200, 150);

        jb_malzeme.setBackground(new java.awt.Color(0, 102, 102));
        jb_malzeme.setForeground(new java.awt.Color(255, 255, 255));
        jb_malzeme.setText("Servisteki Malzeme");
        jb_malzeme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_malzeme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_malzemeActionPerformed(evt);
            }
        });
        jPanel2.add(jb_malzeme);
        jb_malzeme.setBounds(160, 310, 200, 160);

        jb_musteri_ekle.setBackground(new java.awt.Color(153, 204, 255));
        jb_musteri_ekle.setText("Müşteri Ekleme");
        jb_musteri_ekle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_musteri_ekle.setMaximumSize(new java.awt.Dimension(120, 20));
        jb_musteri_ekle.setMinimumSize(new java.awt.Dimension(120, 20));
        jb_musteri_ekle.setPreferredSize(new java.awt.Dimension(120, 30));
        jb_musteri_ekle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_musteri_ekleActionPerformed(evt);
            }
        });
        jPanel2.add(jb_musteri_ekle);
        jb_musteri_ekle.setBounds(160, 130, 200, 150);

        jb_bakım.setBackground(new java.awt.Color(255, 255, 204));
        jb_bakım.setText("Araç Bakım");
        jb_bakım.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_bakım.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_bakımActionPerformed(evt);
            }
        });
        jPanel2.add(jb_bakım);
        jb_bakım.setBounds(650, 140, 200, 150);

        jb_calisanListele.setBackground(new java.awt.Color(204, 153, 255));
        jb_calisanListele.setText("Çalışan Listele");
        jb_calisanListele.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jb_calisanListele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_calisanListeleActionPerformed(evt);
            }
        });
        jPanel2.add(jb_calisanListele);
        jb_calisanListele.setBounds(650, 510, 200, 150);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" -");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2);
        jLabel2.setBounds(940, 0, 30, 30);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("    X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1);
        jLabel1.setBounds(980, 0, 40, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/veritabanı/anaekran.jpg"))); // NOI18N
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 1030, 770);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1024, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jb_bakımActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_bakımActionPerformed
       aracBakımJF bakım=new aracBakımJF();
       bakım.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jb_bakımActionPerformed

    private void jb_musteri_ekleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_musteri_ekleActionPerformed
       musteriEklemeJF musteri_ekle=new musteriEklemeJF();
       musteri_ekle.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jb_musteri_ekleActionPerformed

    private void jb_malzemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_malzemeActionPerformed
        servisteki_malzemelerJF serJF=new servisteki_malzemelerJF();
        serJF.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jb_malzemeActionPerformed

    private void jb_servis_tuketilenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_servis_tuketilenActionPerformed
        tuketilenMalzemeJF tmalzeme=new tuketilenMalzemeJF();
        tmalzeme.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jb_servis_tuketilenActionPerformed

    private void jb_faturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_faturaActionPerformed
       faturaGoruntuleJF fg=new faturaGoruntuleJF();
       fg.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jb_faturaActionPerformed

    private void jb_cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cikisActionPerformed
        girisJF giris=new girisJF();
        giris.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jb_cikisActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setState(anaekranJF.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jb_calisanListeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_calisanListeleActionPerformed
        calisanListeleJF calisan=new calisanListeleJF();
        calisan.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jb_calisanListeleActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new anaekranJF().setVisible(true);
            }
        });
    }
private void seticon(){
    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ikon.png")));
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jb_bakım;
    private javax.swing.JButton jb_calisanListele;
    private javax.swing.JButton jb_cikis;
    private javax.swing.JButton jb_fatura;
    private javax.swing.JButton jb_malzeme;
    private javax.swing.JButton jb_musteri_ekle;
    private javax.swing.JButton jb_servis_tuketilen;
    private javax.swing.JLabel jl_isim;
    // End of variables declaration//GEN-END:variables
}
