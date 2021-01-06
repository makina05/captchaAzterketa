package ehu.isad.db;

import ehu.isad.model.CustomImage;
import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SampleDBKud {
    private Image logo;
    private static final SampleDBKud instance = new SampleDBKud();

    public static SampleDBKud getInstance(){
        return instance;
    }

    private SampleDBKud(){
    }

    public List<CustomImage> lortuEskaneatutakoak(){

        String query = "select id, filename, value, date from captchas";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<CustomImage> emaitza = new ArrayList<>();
        try{
            while (rs.next()){
                Integer id = rs.getInt("id");
                String filename = rs.getString("filename");
                String value = rs.getString("value");
                Date data = rs.getDate("date");
//                Date date = new Date(data);
//                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                format.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
//                String formatted = format.format(date);
                emaitza.add(new CustomImage(id,filename,value,data));

            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public void captchaGehitu(String filename) {
        String query = "Insert into captchas (filename, date) values ('"+filename+"',"+new Date().getTime()+" )";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }
    //private String query;
    public void dbEguneratu(Integer id, String value) {
//        query = "Update captchas set value=null where id="+id;
//        if (value !=null){
//            query = "Update captchas set value='"+value+"' where id="+id;
//        }
//
//        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
//        ResultSet rs = dbKudeatzaile.execSQL(query);
        String query = "UPDATE captchas SET value='"+value+"' WHERE id="+id;
        if(value == null){
            query = "UPDATE captchas SET value=null WHERE id="+id;
        }
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }
}