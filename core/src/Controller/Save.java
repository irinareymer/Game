package Controller;

import com.badlogic.gdx.Gdx;

import java.io.*;

public class Save {

    public static Data data;

    public static void save() {
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("scores.save"));
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
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("scores.save"));
            data = (Data) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }
    public  static boolean saveFileExists(){
        File file = new File("scores.save");
        return file.exists();
    }

    public static void init(){
        data = new Data();
        data.init();
        save();
    }

}
