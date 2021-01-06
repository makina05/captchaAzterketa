package ehu.isad.model;

import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class CustomImage {
    private Integer id;
    private String filename;
    private String value;
    private Date date;
    private Image irudia;

    public CustomImage(Integer id, String filename, String value, Date date) {
        this.id = id;
        this.filename = filename;
        this.value = value;
        this.date = date;

        String imagePath = Utils.lortuEzarpenak().getProperty("pathtoimages")+filename;
        try {
            this.irudia = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Image getIrudia() {
        return irudia;
    }

    public void setIrudia(Image irudia) {
        this.irudia = irudia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
