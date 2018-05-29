import javafx.scene.control.TextArea;

import java.io.*;

/**
 * @author zjb
 * @date 2018/5/27.
 */
public class FileUtils {
    public static String in = "";
    public static String out = "";

    public static String fileReader(File file, TextArea input){
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
            String line = bf.readLine();
            while(line != null){
                sb.append(line+"\n");
                line = bf.readLine();
            }
            sb = new StringBuilder(sb.substring(0,sb.length()-1));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bf!=null){
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        in = sb.toString();
        input.setText(sb.toString());
        return sb.toString();
    }

    public static void fileWriter(TextArea output, File file){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(output.getText());
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
