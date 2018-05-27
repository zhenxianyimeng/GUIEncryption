import com.sun.deploy.util.StringUtils;

/**
 * @author zjb
 * @date 2018/5/26.
 */
public class CaesarCiphar {
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
//        CaesarCiphar caesarCiphar = new CaesarCiphar();
        String encode = CaesarCiphar.encode("JavaFx*^&#",3);
        System.out.println(encode);
        System.out.println(CaesarCiphar.decode(encode,3));
    }
}
