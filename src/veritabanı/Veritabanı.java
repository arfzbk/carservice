
package veritabanı;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Veritabanı {

    private String kullanıcıad="root";
private  String sifre="1234";
private String host="localhost";
private int port=3306;
private String databasename="arabaservisi";
private static Connection con=null;
private static Statement statement=null;
//Constructor tanımladım ve veritabanına bağlandım.
public Veritabanı(){
    String url="jdbc:mysql://"+host+":"+port+"/"+databasename+"?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı");
        }
        try {
            con=DriverManager.getConnection(url,kullanıcıad,sifre);
            orneksorgu();
        } catch (SQLException ex) {
            System.out.println("Bağlantı Sağlanamadı.");
        }
    
}
//Verilen müşteri bilgilerini kaydeder.
public boolean musteri_kaydet(ArrayList alist){
    String musAd=alist.get(0).toString();
   String musEmail=alist.get(1).toString();
    String musKimlik=alist.get(2).toString();
    String musSoyad=alist.get(3).toString();
    String musTelefon=alist.get(4).toString();
    String aracPlaka=alist.get(5).toString();
    String aracMarka=alist.get(6).toString();
    String aracModel=alist.get(7).toString();
    String aracYıl=alist.get(8).toString();
    String aracKm=alist.get(9).toString();
    String sorgu1="select*from musteri where tc_no='"+musKimlik+"'";
    System.out.println(sorgu1);
    String sorgu4 ="select mod_id from model where mar_id=(select mar_id from marka where mar_ad='"+aracMarka+"')"+"and mod_ad='"+aracModel+"'";
    String sorgu2="insert into musteri(tc_no,mus_ad,mus_soyad,telefon,email) values("+"'"+musKimlik+"','"+musAd+"','"+musSoyad+"','"+musTelefon+"','"+musEmail+"')";
   
    System.out.println(sorgu4);
    System.out.println(sorgu2);
        try {
            ResultSet rs1 =statement.executeQuery(sorgu1);

            while (rs1.next()) {
                String kimlik=rs1.getString("tc_no");
                if (musKimlik.equals(kimlik)) {
                    System.out.println("Kimlik zaten kayıtlı");
                    return false;
                }   
            } 
            int s=statement.executeUpdate(sorgu2);
            
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
        ResultSet rs3=statement.executeQuery(sorgu4);
        int modId=-1;
        while (rs3.next()) {
                 modId=rs3.getInt("mod_id");
            }
            if (modId==-1) {
                return false;
            }
            String sorgu5="insert into arac values('"+aracPlaka+"','"+modId+"','"+aracYıl+"','"+aracKm+"')";
            int s2=statement.executeUpdate(sorgu5);
            this.aracıOlanMusteriEkle(alist);
    } catch (Exception e) {
            System.out.println("rs3 hatası");
    }
      
    return true;
}
//Aracı olan müşteriler tablosuna ekleme yapar
public  boolean aracıOlanMusteriEkle(ArrayList alist){
    String tcno=alist.get(2).toString();
    String aracplaka=alist.get(5).toString();
    String musteriIdveAracPlakaSorgula="select mus_id,arac_plaka from musteri,arac where tc_no='"+tcno+"'"+"and arac_plaka='"+aracplaka+"'";
    int eklenme=-99;
    int musId=0;
    String aracPlaka="";
    ResultSet sorgu;
        try {
            sorgu = statement.executeQuery(musteriIdveAracPlakaSorgula);
            while (sorgu.next()) {
                musId=sorgu.getInt("mus_id");
                aracPlaka=sorgu.getString("arac_plaka");   
            }
            String ekleme="insert into arac_olan_musteri values("+musId+",'"+aracPlaka+"')";
           eklenme=statement.executeUpdate(ekleme);
            if (eklenme==-99) {
                System.out.println("Aracı olan müşteri hatası");
                return false;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
    return true;
}
//Giriş kullanıcı ad sorgulatır
   public static boolean girisYap(String username,String password){
       String sorgu="select*from kullanici";
       System.out.println(username);
       System.out.println(password);
        try {        
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                String kullanıcı=rs.getString("kullanici_ad");
                String sifre=rs.getString("sifre");
                if (username.equals(kullanıcı)&&password.equals(sifre)) {
                    return true;
                }
        } 
        return false;
        }
            catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       return false;
   }
   //Bakımda olan araçları listeler
   public JTable bakımdakiAracListele(JTable table){
       DefaultTableModel model=(DefaultTableModel) table.getModel();
       String sorgu="select bg.arac_plaka,b.bak_id,b.bak_ad,bg.giris_tarih,bg.bakim_neden,bg.bakim_durum,bg.mus_id from(bakim_giren_arac bg inner join bakim b on bg.bak_id=b.bak_id) where not bakim_durum='yapıldı'";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String aracPlaka=rs.getString("arac_plaka");
                int bakId=rs.getInt("bak_id");
                String bakAd=rs.getString("bak_ad");
                String tarih=rs.getString("giris_tarih");
                String bakNeden=rs.getString("bakim_neden");
                String bakim_durum=rs.getString("bakim_durum");
                int musteriNo=rs.getInt("mus_id");
                Object[] dizi={aracPlaka,bakId,bakAd,tarih,bakNeden,bakim_durum,musteriNo};
                model.addRow(dizi);
            }
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return table;
   }
   //Malzemeleri listeler
 public JTable malzemeListele(String servisAdı,JTable table){
       DefaultTableModel model=(DefaultTableModel)table.getModel();
       model.getDataVector().clear();
      String sorgu2="select s.ser_ad,m.mal_ad,bm.miktar,b.birim_ad from(((bulunan_malzeme bm inner join servis s on s.ser_id=bm.ser_id) inner join malzeme m on bm.mal_id=m.mal_id)inner join birim b on m.birim_id=b.birim_id)";
       String sorgu="select s.ser_ad,m.mal_ad,bm.miktar,b.birim_ad from(((bulunan_malzeme bm inner join servis s on s.ser_id=bm.ser_id) inner join malzeme m on bm.mal_id=m.mal_id)inner join birim b on m.birim_id=b.birim_id) where s.ser_ad='"+servisAdı+"'";
        try {
            if (servisAdı.equals("")) {
                sorgu=sorgu2;
            }
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {
                String serad=rs.getString("ser_ad");
                String malad=rs.getString("mal_ad");
                int miktar=rs.getInt("miktar");
                String birim=rs.getString("birim_ad");
                Object[] dizi={serad,malad,miktar,birim};
                model.addRow(dizi);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println("Malzemelistelemehatası");
        }
    
       
       return table;
   }
 //Yeni bakım eklemek için gerekli olan bakımları döndürür
 public  ArrayList yeniBakımEkleBakımDöndür(){
     ArrayList alist=new ArrayList();
     String sorgu="select*from bakim";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String bak_ad=rs.getString("bak_ad");
                if (!alist.contains(bak_ad)) {
                    alist.add(bak_ad);
                }
                
            }
            return alist;
        } catch (SQLException ex) {
            System.out.println("Hata");
        }
        return alist;
 }
 //Servislerin adlarını döndürür
 public  ArrayList servisDondur(){
          ArrayList alist=new ArrayList();
     String sorgu="select*from servis";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String bak_ad=rs.getString("ser_ad");
                if (!alist.contains(bak_ad)) {
                    alist.add(bak_ad);
                }
                
            }
            return alist;
        } catch (SQLException ex) {
            System.out.println("Hata");
        }
        return alist;
 }
 //Verilen servis numarasından servisin adını döndürür
 public String servisIdtoservisAd(int serID){
     String sorgu="select ser_ad from servis where ser_id="+serID;
     String servis="";
        try {
            ResultSet rs =statement.executeQuery(sorgu);
            while (rs.next()) {
                servis=rs.getString("ser_ad");
                
            }
            System.out.println(servis);
            return servis;
        } catch (SQLException ex) {
            System.out.println("Servis ID to Servis Adı Hatası");
        }
        return servis;
     
 }
 //Bakımda olan aracları döndürür
 public JTable yeniBakımEkleBakımListele(JTable table){
     DefaultTableModel model=(DefaultTableModel)table.getModel();
     String sorgu="select ao.arac_plaka,m.mus_id ,m.tc_no,m.mus_ad,m.mus_soyad from(arac_olan_musteri ao inner join musteri m on m.mus_id=ao.mus_id) ";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {
            String aracPlaka=rs.getString("arac_plaka");
            int musteriNo=rs.getInt("mus_id");
            String tcNo=rs.getString("tc_no");
            String musteriAd=rs.getString("mus_ad");
            String musteriSoyad=rs.getString("mus_soyad");
            Object[] dizi={aracPlaka,musteriNo,tcNo,musteriAd,musteriSoyad};
            model.addRow(dizi);
                
            }
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     return table;
 }
 //Plakaya göre arama gerçekleşir
 public  JTable yeniBakımEkleArama(JTable tablo,String plaka){
      try {
        String sorgu="select ao.arac_plaka,m.mus_id ,m.tc_no,m.mus_ad,m.mus_soyad from(arac_olan_musteri ao inner join musteri m on m.mus_id=ao.mus_id) where m.tc_no like '"+plaka+"%'";
        
         DefaultTableModel model=(DefaultTableModel) tablo.getModel();
          model.getDataVector().clear();
        statement=con.createStatement();
        ResultSet rs=statement.executeQuery(sorgu);
        while (rs.next()) {
    
            String aracPlaka=rs.getString("arac_plaka");
            int musteriNo=rs.getInt("mus_id");
            String tcNo=rs.getString("tc_no");
            String musteriAd=rs.getString("mus_ad");
            String musteriSoyad=rs.getString("mus_soyad");
            Object[] dizi={aracPlaka,musteriNo,tcNo,musteriAd,musteriSoyad};
            model.addRow(dizi);

        }
        return tablo;
    } catch (SQLException ex) {
        System.out.println("Listelemede sıkıntı");
    }
    return tablo;
     
     
 }
 //Bakımdaki aracın durumunu günceller
public boolean aracBakımDurumGuncelle(String aracPlaka,int musteriNo,String mesaj){
    String sorgu="update bakim_giren_arac set bakim_durum='"+mesaj+"' where arac_plaka='"+aracPlaka+"' and mus_id='"+musteriNo+"'";
        try {
            int guncelleme=statement.executeUpdate(sorgu);
            return true;
        } catch (SQLException ex) {
            System.out.println("Aracın durumunu güncelleme hatası");
        }

 return false;
}
//Bakım eklenirken aynı zamanda fatura kesilir
public void yeniBakımEkleFaturaKes(int musteriNo,String servisAd,int adet,String malzeme_ad,int bakımNo){
    String serIdSorgula="select ser_id from servis where ser_ad='"+servisAd+"'";
    int serId=0;
    int malzemeFiyat=0;
    String malzemeFiyatGetir="select satis_fiyat from malzeme where mal_id=(select mal_id from malzeme where mal_ad='"+malzeme_ad+"')";
    try {
        ResultSet rs1=statement.executeQuery(malzemeFiyatGetir);
        while (rs1.next()) {            
            malzemeFiyat=rs1.getInt("satis_fiyat");
        }
    } catch (Exception e) {
        System.out.println("Malzeme Fiyat Getirme Hatası");
    }
        try {
            ResultSet rs=statement.executeQuery(serIdSorgula);
            while (rs.next()) {                
                serId=rs.getInt("ser_id");
            }
        } catch (SQLException ex) {
            System.out.println("YeniBakımEkleFaturaKes hatası");
        }
        int tutar=malzemeFiyat*adet;
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        String zaman=timestamp.toString();
    String sorgu="insert into faturalar(mus_id,ser_id,tutar,odeme_durum,bak_id,kesilme_tarih) values("+musteriNo+","+serId+","+tutar+",'"+"odenmedi',"+bakımNo+",'"+zaman+"')";
        try {
            int a=statement.executeUpdate(sorgu);
        } catch (SQLException ex) {
            System.out.println("Fatura ekleme hatası");
        }
   
}
//Kimliğe göre müşterilerin faturaları listelenir
public JTable faturaListeleArama(JTable table,String tcNo){
    DefaultTableModel model=(DefaultTableModel)table.getModel();
    String sorgu="select s.ser_ad,m.tc_no,tutar,odeme_durum,kesilme_tarih from ((faturalar inner join servis s on s.ser_id=faturalar.ser_id) inner join musteri m on m.mus_id=faturalar.mus_id) where m.tc_no like '"+tcNo+"%' and not odeme_durum='odendi'";
    ResultSet rs;
        try {
            rs = statement.executeQuery(sorgu); 
            while (rs.next()){
                String serAd=rs.getString("ser_ad");
                String tcno=rs.getString("tc_no");
                int tutar=rs.getInt("tutar");
                String odemeDurum=rs.getString("odeme_durum");
                String kesilmeTarih=rs.getString("kesilme_tarih");
                Object[] dizi={serAd,tcno,tutar,odemeDurum,kesilmeTarih};
                model.addRow(dizi);
            }        
        return table;
    
        } catch (SQLException ex) {
            System.out.println("Fatura listele arama hatası");
        }
   return table;
}
//Hangi serviste olan bakımları döndürür
 public int yeniBakımEkleBakımNoReturn(String servis,String bakımturu){
     String sorgu="select bak_id from bakim  where ser_id=(select ser_id from servis where ser_ad='"+servis+"')"+"and bak_ad='"+bakımturu+"'";
     int musterino=0;
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                musterino=rs.getInt("bak_id");
                System.out.println(musterino);
                return musterino;
            }
            return musterino;
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return musterino;
 }
 //Hangi serviste olan malzemeleri döndürür eğer malzeme yoksa null döndürür
 public ArrayList yeniBakımMalzemeDöndür(String servis){
   ArrayList list=new ArrayList();
     String sorgu="select m.mal_ad,bm.miktar from(bulunan_malzeme bm inner join malzeme m on bm.mal_id=m.mal_id) where ser_id=(select ser_id from servis where ser_ad='"+servis+"')";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {     
                int bulunan_malzeme=rs.getInt("miktar");
                if (bulunan_malzeme!=0) {
                    list.add(rs.getString("mal_ad"));
                }
                
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Malzeme Getirme Hatası");
        }
     return list;
 }
//Harcanan malzeme miktarı güncellenir
 public boolean yeniBakımEkleMalzemeGuncelle(String servis,String malzeme,int miktar,int musteriNo,int bakımNo){
     String sorgu1="select miktar,mal_id from bulunan_malzeme where ser_id=(select ser_id from servis where ser_ad='"+servis+"') and mal_id=(select mal_id from malzeme where mal_ad='"+malzeme+"')";
     
     int bulunanMiktar=0;
     int mal_id=0;
        try {
            ResultSet rs1=statement.executeQuery(sorgu1);
            while (rs1.next()) {                
                bulunanMiktar=rs1.getInt("miktar");
                mal_id=rs1.getInt("mal_id");
            }
            
        } catch (SQLException ex) {
            System.out.println("rs1 malzemegüncelleme hatası");   
        }
        int total=bulunanMiktar-miktar;
        String sorgu2="update bulunan_malzeme set miktar="+total+" where ser_id=(select ser_id from servis where ser_ad='"+servis+"') and mal_id=(select mal_id from malzeme where mal_ad='"+malzeme+"')";
     try {
         
         int s=statement.executeUpdate(sorgu2);
         
     } catch (Exception e) {
         System.out.println("malzeme güncelleme hatası");
     }
     String sorgu3="insert into tuketilen_malzeme(mal_id,bak_id,mus_id,miktar) values("+mal_id+","+bakımNo+","+musteriNo+","+miktar+")";
     try {
         int a=statement.executeUpdate(sorgu3);
         return true;
     } catch (Exception e) {
         System.out.println("tüketilen malzeme ekleme hatası");
     }
     return false;
 }
 //Yeni bakım eklenir
 public boolean yeniBakımEkle(String aracPlaka,int bakımNo,String bakımNeden,int musteriNo){
 Timestamp timestamp=new Timestamp(System.currentTimeMillis());
     String zaman=timestamp.toString();
     String sorgu="insert into bakim_giren_arac values('"+aracPlaka+"',"+bakımNo+",'"+zaman+"','"+bakımNeden+"','tamirde',"+musteriNo+")";
        try {
           statement.executeUpdate(sorgu);
           return true;
        } catch (SQLException ex) {
            System.out.println("Bakım Ekleme Sorunu");
        }
     return false;
 }
 //Araçların markası döndürülür
 public ArrayList musteriEklemeAracMarkaDondur(){
     ArrayList alist=new ArrayList();
     String sorgu="select mar_ad from marka";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String mar_ad=rs.getString("mar_ad");
                if (!alist.contains(mar_ad)) {
                    alist.add(mar_ad);
                }
                
            }
            return alist;
        } catch (SQLException ex) {
            System.out.println("Marka Döndürme Hatası");
        }
        return alist;
     
 }
 //Bakımdan silme işlemi yapılır
 public void bakımdanCıkar(int musteriNo,int bakımId,String plaka){
     bakımdanCıkarTuketilenMalzemeSil(bakımId, musteriNo);
     String serIdSorgu="select ser_id from bakim where bak_id="+bakımId;
     int serId=0;
        try {
            ResultSet rs=statement.executeQuery(serIdSorgu);
            while (rs.next()) {                
                serId=rs.getInt("ser_id");
            }
        } catch (SQLException ex) {
            System.out.println("Servis İd getirme sorunu");
        }
       String faturaSil="delete from faturalar where mus_id="+musteriNo+" and ser_id="+serId+" and bak_id="+bakımId;
       System.out.println(faturaSil);
        try {
            int a=statement.executeUpdate(faturaSil);
        } catch (SQLException ex) {
            System.out.println("Fatura silme hatası");
        }
     String bakimSil="delete from bakim_giren_arac where mus_id="+musteriNo+" and arac_plaka='"+plaka+"'and bak_id="+bakımId;
        try {
            int b=statement.executeUpdate(bakimSil);
        } catch (SQLException ex) {
            System.out.println("Bakım silme hatası");
        }
 }
 //Verilen markadaki araçların modelini döndürür
 public ArrayList musteriEklemeAracModelDondur(String marka){
      ArrayList alist=new ArrayList();
     String sorgu="select mod_ad from model where mar_id=(select mar_id from marka where mar_ad='"+marka+"')";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String mod_ad=rs.getString("mod_ad");
                if (!alist.contains(mod_ad)) {
                    alist.add(mod_ad);
                }
                
            }
            return alist;
        } catch (SQLException ex) {
            System.out.println("Model Döndürme Hatası");
        }
        return alist;
     
     
 }
 //Faturalar listelenir
 public JTable faturaListele(JTable table){
     DefaultTableModel model=(DefaultTableModel) table.getModel();
     String sorgu="select s.ser_ad,m.tc_no,sum(tutar),odeme_durum,kesilme_tarih from ((faturalar inner join servis s on s.ser_id=faturalar.ser_id) inner join musteri m on m.mus_id=faturalar.mus_id ) where not odeme_durum='odendi' group by tc_no,ser_ad ";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String serAd=rs.getString("ser_ad");
                String tcNo=rs.getString("tc_no");
                int tutar=rs.getInt("sum(tutar)");
                String odemeDurum=rs.getString("odeme_durum");
                String kesilmeTarih=rs.getString("kesilme_tarih");
                Object [] dizi={serAd,tcNo,tutar,odemeDurum,kesilmeTarih};
                model.addRow(dizi);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println("Fatura listeleme hatası");
        }
     
     return table;
     
 }
 //Fatura ödemesi yapılır 
 public boolean faturaOdeme(String serAd,String tcNo,int tutar){
     String sorgu="select f.bak_id,m.mus_id,s.ser_id from(( faturalar f inner join musteri m on m.mus_id=f.mus_id)inner join servis s on s.ser_id=f.ser_id) where s.ser_ad='"+serAd+"' and m.tc_no='"+tcNo+"'";
     int serId=0;
     int musId=0;
     int bakımNo=0;
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                serId=rs.getInt("ser_id");
                musId=rs.getInt("mus_id");
                bakımNo=rs.getInt("bak_id");
            }
        } catch (SQLException ex) {
            System.out.println("fatura ödeme müşteri ve servis sorgulama hatası");
        }
        String guncellemeSorgusu="update faturalar set odeme_durum='odendi'"+" where mus_id="+musId+" and ser_id="+serId+" and tutar="+tutar;
        try {
            int a=statement.executeUpdate(guncellemeSorgusu);
            //Faturası kapatılan müşteri bakımdan çıkarılır
            faturasıodenenbakımdancıkar(musId, bakımNo);
            return true;
        } catch (SQLException ex) {
            System.out.println("fatura odeme güncelleme hatası");
        }
        return false;
 }
 //Silinen fatura bakımda varsa çıkarılır
 public void faturasıodenenbakımdancıkar(int musteriNo,int bakımNo){
     String sorgu="delete from bakim_giren_arac where mus_id="+musteriNo+" and bak_id="+bakımNo;
        try {
            int a=statement.executeUpdate(sorgu);
        } catch (SQLException ex) {
            System.out.println("Faturası ödenenen bakımdan çıkarma hatası");
        }
 }
 //Bakımdan çıkarılan aracın kaydedilen tükettiği malzeme silinir
 public void bakımdanCıkarTuketilenMalzemeSil(int bakId,int musId){
     String sorgu="select tuk_id from tuketilen_malzeme where bak_id="+bakId+" and "+"mus_id="+musId;
     int tID=0;
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                tID=rs.getInt("tuk_id");
            }
        } catch (SQLException ex) {
            System.out.println("Tüketilen malzeme silme sorgulama hatası");
        }
        String sorgu2="delete from tuketilen_malzeme where tuk_id="+tID;
        try {
            int a=statement.executeUpdate(sorgu2);
        } catch (SQLException ex) {
            System.out.println("Tüketilen malzeme silme hatası");
        }
 }
 //Tüketilen malzemeler listelenir
public JTable tuketilenMalzemeListele(JTable table){
    DefaultTableModel model=(DefaultTableModel) table.getModel();
    String sorgu="select b.ser_id,m.mal_ad,sum(t.miktar) from ((tuketilen_malzeme t inner join bakim b  on t.bak_id=b.bak_id)\n" +
"inner join malzeme m on t.mal_id=m.mal_id) group by mal_ad,ser_id";
    System.out.println(sorgu);
          
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {   
                int serId=rs.getInt("ser_id");
                String malAd=rs.getString("mal_ad");
                int miktar=rs.getInt("sum(t.miktar)");
                Object [] dizi={serId,malAd,miktar};
                model.addRow(dizi);
               
            }
        } catch (SQLException ex) {
            System.out.println("Tuketilen Malzemeyi Listeleme Hatası");
        }
    
    return table;
}
//Servise göre tğketilen malzeme listelenir
public JTable tuketilenMalzemeListeleServiseGore(JTable table,String serAd){
    DefaultTableModel model=(DefaultTableModel) table.getModel();
    String sorgu="select b.ser_id,m.mal_ad,sum(t.miktar) from ((tuketilen_malzeme t inner join bakim b  on t.bak_id=b.bak_id)\n" +
"inner join malzeme m on t.mal_id=m.mal_id) where ser_id=(select ser_id from servis where ser_ad='"+serAd+"')"+" group by mal_ad,ser_id ";
    System.out.println(sorgu);
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {   
                int serId=rs.getInt("ser_id");
                String malAd=rs.getString("mal_ad");
                int miktar=rs.getInt("sum(t.miktar)");
                Object [] dizi={serId,malAd,miktar};
                model.addRow(dizi);
               
            }
        } catch (SQLException ex) {
            System.out.println("Tuketilen Malzemeyi Servise Göre Listeleme Hatası");
        }
    
    return table;
}
//Çalışan tablosunun hepsini döndürme
public JTable calisanTablosuDondur(JTable table){
    DefaultTableModel model=(DefaultTableModel)table.getModel();
    String sorgu="select*from calisan";
    
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String ad=rs.getString("cal_ad");
                String soyad=rs.getString("cal_soyad");
                int maas=rs.getInt("cal_maas");
                String telefon=rs.getString("telefon");
                int kıdem=rs.getInt("kidem");
                Object[] dizi ={ad,soyad,maas,telefon,kıdem};
                model.addRow(dizi);
            }
   return table;
        } catch (SQLException ex) {
            System.out.println("Çalışan tablosunu döndürme hatası");
        }
    return table;
}
//Servise göre çalışan listeleme
public JTable calisanServiseGöreListele(JTable table,String servis){
    DefaultTableModel model=(DefaultTableModel) table.getModel();
    String sorgu="select * from calisan where ser_id=(select ser_id from servis where ser_ad='"+servis+"')";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String ad=rs.getString("cal_ad");
                String soyad=rs.getString("cal_soyad");
                int maas=rs.getInt("cal_maas");
                String telefon=rs.getString("telefon");
                int kıdem=rs.getInt("kidem");
                Object[] dizi ={ad,soyad,maas,telefon,kıdem};
                model.addRow(dizi);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println("Servise göre çalışan listeleme hatası");
        }
        return table;
}
//Verilen servisin yöneticisini getirme
public JTable yoneticiListele(JTable table,String servis) {
    DefaultTableModel model=(DefaultTableModel) table.getModel();
    String sorgu="select*from calisan where cal_id=(select yonetici_no from servis where ser_ad='"+servis+"')";
        try {
            ResultSet rs=statement.executeQuery(sorgu);
            while (rs.next()) {                
                String ad=rs.getString("cal_ad");
                String soyad=rs.getString("cal_soyad");
                int maas=rs.getInt("cal_maas");
                String telefon=rs.getString("telefon");
                int kıdem=rs.getInt("kidem");
                Object[] dizi ={ad,soyad,maas,telefon,kıdem};
                model.addRow(dizi);
            }
            return table;
        } catch (SQLException ex) {
            System.out.println("Yönetici listeleme hatası");
        }
        return table;
}
//Örnek kullanıcı ve şifreleri yazırılır
 public void orneksorgu(){
   
        try {
            String sorgu="Select*From kullanici";
            statement = con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                String derskodu=rs.getString("kullanici_ad");
                String ders=rs.getString("sifre");
                System.out.println(derskodu);
                System.out.println(ders);
                System.out.println("********");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Veritabanı.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
   }
   
    
}
