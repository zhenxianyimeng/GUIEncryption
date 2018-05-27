/**
 * @author zjb
 * @date 2018/5/28.
 */
public class VigenereCiphar {

    public static String encode(String plainStr, String key) {
        try {
            plainStr = plainStr.toLowerCase();
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

            return cryptograph.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String encodedStr, String key) {
        try {
            encodedStr = encodedStr.toLowerCase();
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
            return plaintext.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encode("iloveyouu","kiss"));
        System.out.println(decode("s tgno ggme","kiss"));
//        System.out.println(decrypt("stgfmqycj", "kiss"));
    }
}
