import java.util.*;

public class VigenereCiphar {

    /**
     * encode method
     * @param plainStr
     * @param key
     * @return
     */
    public static String encode(String plainStr, String key) {
        try {
            Map<Integer, Character> map = new LinkedHashMap<>();
            StringBuilder sb = new StringBuilder();
            char[] arr = plainStr.toCharArray();
            for (int i=0; i<arr.length; i++){
                char ch = arr[i];
                if((ch>='a' && ch<='z')||(ch>='A' && ch<='Z')){
                    sb.append(ch);
                }else {
                    map.put(i,ch);
                }
            }
            plainStr = sb.toString().toLowerCase();
            key = key.toLowerCase();
            StringBuilder cryptograph = new StringBuilder();
            String letters = "abcdefghijklmnopqrstuvwxyz";
            char[][] table = new char[26][26];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    int index = (i + j) % 26;
                    table[i][j] = letters.charAt(index);
                }
            }

            int len = plainStr.length();
            for (int i = 0; i < len; i++) {
                int row = letters.indexOf(key.charAt(i % (key.length())));
                int column = letters.indexOf(plainStr.charAt(i));

                if (column == -1 || row == -1) {
                    cryptograph.append(plainStr.charAt(i));
                } else {
                    cryptograph.append(table[row][column]);
                }
            }
            char[] temp = cryptograph.toString().toCharArray();
            List<Character> list = new LinkedList<>();
            for(char ch : temp){
                list.add(ch);
            }
            for(int index : map.keySet() ){
                list.add(index, map.get(index));
            }
            String result = "";
            for(char ch : list){
                result += ch;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * decode method
     * @param encodedStr
     * @param key
     * @return
     */
    public static String decode(String encodedStr, String key) {
        try {
            Map<Integer, Character> map = new LinkedHashMap<>();
            StringBuilder sb = new StringBuilder();
            char[] arr = encodedStr.toCharArray();
            for (int i=0; i<arr.length; i++){
                char ch = arr[i];
                if((ch>='a' && ch<='z')||(ch>='A' && ch<='Z')){
                    sb.append(ch);
                }else {
                    map.put(i,ch);
                }
            }
            encodedStr = sb.toString().toLowerCase();
            key = key.toLowerCase();
            StringBuilder plaintext = new StringBuilder();
            String letters = "abcdefghijklmnopqrstuvwxyz";
            char[][] table = new char[26][26];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    int index = (i + j) % 26;
                    table[i][j] = letters.charAt(index);
                }
            }

            int len = encodedStr.length();
            for (int i = 0; i < len; i++) {
                if (letters.indexOf((encodedStr.charAt(i))) == -1) {
                    plaintext.append(encodedStr.charAt(i));
                } else {
                    int row = letters.indexOf(key.charAt(i % (key.length())));
                    if (row == -1) {
                        plaintext.append(encodedStr.charAt(i));
                    } else {
                        String rowStr = new String(table[row]);
                        int column = rowStr.indexOf(encodedStr.charAt(i));
                        plaintext.append(letters.charAt(column));
                    }
                }
            }
            char[] temp = plaintext.toString().toCharArray();
            List<Character> list = new LinkedList<>();
            for(char ch : temp){
                list.add(ch);
            }
            for(int index : map.keySet() ){
                list.add(index, map.get(index));
            }
            String result = "";
            for(char ch : list){
                result += ch;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encode("i love youu","kiss"));
        System.out.println(decode("s tgno ggme","kiss"));
    }
}
