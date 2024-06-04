import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class E_Autofill_max {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node();
            arr[i].word = sc.next();
            arr[i].popul = sc.nextInt();
            arr[i].number = i;
        }

        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return E_Autofill_max.compare(o1.word,o2.word);
            }
        });
        String[][] qs = new String[q][2];
        for(int i = 0; i < q; i++){
            qs[i][0] = sc.next();
            if(qs[i][0].charAt(0) == '+') {
                qs[i][1] = sc.next();
            } else {
                qs[i][1] = " ";
            }
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < q; ++i) {
            if(qs[i][0].equals("-")) {
                str.deleteCharAt(str.length()-1);
            } else {
                str.append(qs[i][1]);
            }
            int index = getMax(arr, str.toString());
            if(index == -1) {
                System.out.println(-1);
            } else {
                System.out.println(index);
            }
        }
        sc.close();
    }
    public static int getMax(Node[] arr, String pat) {
        int l = binS(arr, pat);
        int r = binS_r(arr, pat);
        if(l >= arr.length || r >= arr.length) {
            return - 1;
        }
        if(pat.length() > arr[l].word.length() || pat.length() > arr[r].word.length()) {
            return -1;
        }
        if (!(arr[l].word.substring(0, pat.length()).contains(pat) || arr[r].word.substring(0, pat.length()).contains(pat))) {
            return -1;
        }

        int max = 0;
        int index = 0;

        for(int i = l; i <= r; ++i) {
            if(max < arr[i].popul) {
                max = arr[i].popul;
                index = arr[i].number;
            }
        }
        return index;
    }

    public static int binS(Node[] str, String pat) {
        int l = 0;
        int r = str.length;
        while (l < r) {
            int m = (l + r) / 2;
            if(pat.length() > str[m].word.length()) {
                if (compare(str[m].word, pat) < 0) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else {
                if (compare(str[m].word.substring(0, pat.length()), pat) < 0) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

        }
        return r;
    }

    public static int binS_r(Node[] str, String pat) {
        int l = 0;
        int r = str.length;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if(pat.length() > str[m].word.length()) {
                if (compare(str[m].word, pat) > 0) {
                    r = m;
                } else {
                    l = m;
                }
            } else {
                if (compare(str[m].word.substring(0, pat.length()), pat) > 0) {
                    r = m;
                } else {
                    l = m;
                }
            }

        }
        return l;
    }

    public static int compare(String str1, String str2) {
        for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.charAt(i) - str2.charAt(i);
            }
        }
        return str1.length() - str2.length();
    }

}
class Node  {
    public String word = "";
    public int number = 0;
    public int popul = 0;
}

