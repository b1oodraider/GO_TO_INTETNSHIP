import java.util.HashSet;
import java.util.Scanner;

public class D_Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        String commands = sc.next();
        int counter = 0;
        char vector = 'R';
        HashSet<Integer> numberF = new HashSet<>();
        for(int i = 0; i < n - 1; ++i) {
            if(commands.charAt(i) == 'L'){
                int fnum = getF(commands, i +1);
                if(fnum > 0) {
                    if(!numberF.contains(getF(commands, i+ 1)) && vector == 'R') {
                        numberF.add(getF(commands, i+ 1));
                        vector = 'L';
                        counter+= 2;
                    } else if(numberF.contains(getF(commands, i+ 1))) {
                        counter++;
                    }
                } else {
                    counter++;
                    vector = 'L';
                }
            } else if(commands.charAt(i) == 'R'){
                int fnum = getF(commands, i +1);
                if(fnum > 0) {
                    if(!numberF.contains(getF(commands, i+ 1)) && vector == 'L') {
                        numberF.add(getF(commands, i+ 1));
                        vector = 'R';
                        counter+= 2;
                    }else if(numberF.contains(getF(commands, i+ 1))) {
                        counter++;
                    }
                } else {
                    counter++;
                }
            } else {
                int fnum = getF(commands, i +1);
                if(fnum > 0) {
                    if(!numberF.contains(fnum)) {
                        counter++;
                    }
                }
            }
        }
        System.out.println(counter+1);
    }

    public static int getF(String str, int pos) {
        int num = 0;
        for(int i = pos; i < str.length() && str.charAt(i) == 'F'; ++i) {
            num++;
        }
        return num;
    }
}
