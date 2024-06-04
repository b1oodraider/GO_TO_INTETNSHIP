
import java.util.Scanner;

public class A_Text {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder temp = new StringBuilder(sc.nextLine());
        for(int i = 0; i < temp.length() - 1; i++){
            if(temp.charAt(i) == ',' && temp.charAt(i + 1) != ' '){
                temp.insert(i+1, ' ');
            }
            if (i > 0 && temp.charAt(i) == ',' && temp.charAt(i - 1) != ' '){
                temp.insert(i, ' ');
            }
        }
        int len = getMax(temp.toString().split(" ")) * 3;
        for(int i = 0; i < temp.length() - 1; i++){
            if(i > 0 && temp.charAt(i) == ',' && temp.charAt(i - 1) == ' '){
                temp.deleteCharAt(i - 1);
            }
        }

        String[] str = temp.toString().split(" ");
        temp.delete(0,temp.length());
        StringBuilder text = new StringBuilder();
        text.append(str[0]);
        int strlen = str[0].length();
        for(int i = 1; i < str.length; i++){
            if(str[i].equals(",")) {
                if(str[i - 1].equals(",")) {
                    text.append(' ');
                    strlen++;
                }
                text.append(str[i]);
                strlen++;
            } else {
                if(strlen + str[i].length() > len - 1) {
                    text.append("\n").append(str[i]);
                    strlen = str[i].length();
                } else {
                    text.append(" ").append(str[i]);
                    strlen += str[i].length() + 1;
                }
            }
        }
        System.out.println(text.toString());
    }

    public static int getMax( String[] str) {
        int max = 0;
        for(String str1 : str) {
            if(str1.length() > max) {
                max = str1.length();
            }
        }
        return max;
    }
}