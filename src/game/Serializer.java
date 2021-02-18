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
            Util.printAndWait("File saved");
        } catch (Exception error) {
            Util.print("DID NOT WORK");
            System.out.println(error);
            Util.inputEnter();
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
            Util.print("Load fail");
            System.out.println(error);
            return false; // we couldn't complete deserialization
        }
    }

}
