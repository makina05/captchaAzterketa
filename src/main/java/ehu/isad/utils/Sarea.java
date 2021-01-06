package ehu.isad.utils;

import ehu.isad.db.SampleDBKud;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sarea {

    private static final Sarea instance = new Sarea();

    public static Sarea getInstance() {
        return instance;
    }

    private Sarea() { }

    public void irudiaGorde(String url, String hFitxategia){
        BufferedImage image;
        try{
            URL Url =new URL(url);
            image = ImageIO.read(Url);
            ImageIO.write(image, "png", new File(Utils.lortuEzarpenak().getProperty("pathtoimages")+hFitxategia+".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}