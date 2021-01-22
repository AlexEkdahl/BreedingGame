package Game;

//Thomas helpingclass

import java.io.*;

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
         return false; // we couldn't complete deserialization
      }
   }
}
