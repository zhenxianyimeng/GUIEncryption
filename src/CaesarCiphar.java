
import java.util.HashMap;
import java.util.Map;

public class CaesarCiphar {

    private static final char MAGIC_CHAR = 'e';

    /**
     * crack method
     * @param str
     * @return
     */
    public static String crack(String str){
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : arr){
            if((ch>='a' && ch<='z')||(ch>='A' && ch<='Z')) {
                if (!map.containsKey(ch)) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }
        }
        char max = MAGIC_CHAR;
        int maxNum = 0;
        for(char temp : map.keySet()){
            if(map.get(temp) > maxNum){
                max = temp;
                maxNum = map.get(temp);
            }
        }
        max = Character.toLowerCase(max);
        int num = ((max - MAGIC_CHAR)+26)%26;
        return decode(str, num);
    }


    /**
     * encode method
     * @param str
     * @param num
     * @return
     */
    public static String encode(String str, int num){
        if(str == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for(char ch : arr){
            if(ch >= 'a' && ch<='z'){
                sb.append((char)(((ch - 'a') + num + 26)%26 + 'a'));
            }else if(ch >= 'A' && ch<='Z'){
                sb.append((char)(((ch - 'A') + num + 26)%26 + 'A'));
            }else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * decode method
     * @param str
     * @param num
     * @return
     */
    public static String decode(String str, int num){
        if(str == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for(char ch : arr){
            if(ch >= 'a' && ch<='z'){
                sb.append((char)(((ch - 'a') - num + 26)%26 + 'a'));
            }else if(ch >= 'A' && ch<='Z'){
                sb.append((char)(((ch - 'A') - num + 26)%26 + 'A'));
            }else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String encode = CaesarCiphar.encode("Hello here is yours",3);
        System.out.println(encode);
        System.out.println(CaesarCiphar.crack(encode));
    }
}
