package Controller;

import com.badlogic.gdx.Gdx;

import java.io.*;

public class Save {

    private static Data data;

    public static void init(){
        data = new Data();
        data.init();
        save();
    }

    public static Data getData() {
        return data;
    }

    public static void save() {
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream(System.getProperty("user.dir")+"scores.sv"));
            outputStream.writeObject(data);
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static void load() {
        try{
            if(!saveFileExists()) {
                init();
            }
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(System.getProperty("user.dir")+"scores.sv"));
            data = (Data) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public  static boolean saveFileExists(){
        File file = new File(System.getProperty("user.dir")+"scores.sv");
        return file.exists();
    }
}