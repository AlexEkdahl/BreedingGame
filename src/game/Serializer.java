package game;

//Thomas helpingclass

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    static public void serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            System.out.println("File saved");
            GameHelper.inputEnter();
        } catch (Exception ignored) {
            System.out.println("DID NOT WORK");
            System.out.println(ignored);
            GameHelper.inputEnter();
        }
    }

    static public Object deserialize(String filePath) {
        try {
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        } catch (Exception error) {
            System.out.println("Load fail");
            System.out.println(error);
            return false; // we couldn't complete deserialization
        }
    }

}
