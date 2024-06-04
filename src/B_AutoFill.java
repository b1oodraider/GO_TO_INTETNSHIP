import java.util.Scanner;

public class B_AutoFill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        String[] diary = new String[n];
        for (int i = 0; i < n; i++) {
            diary[i] = sc.next();
        }
        int[] ks = new int[q];
        String[] pats = new String[q];
        for(int i = 0; i < q; i++){
            ks[i] = sc.nextInt();
            pats[i] = sc.next();
        }
        for (int i = 0; i < q; i++) {
            System.out.println(getAnswer(diary, ks[i], pats[i]));
        }
        sc.close();
    }

    public static int getAnswer(String[] diary, int k, String pat) {
        int l = binS(diary, pat);
        int r = binS_r(diary, pat);
        if (!(diary[l].substring(0, pat.length()).contains(pat) || diary[r].substring(0, pat.length()).contains(pat))
                || k > diary.length || k > (r - l + 1)) {
            return -1;
        }
        return l + k;
    }

    public static int binS(String[] str, String pat) {
        int l = 0;
        int r = str.length;
        while (l < r) {
            int m = (l + r) / 2;
            if(pat.length() > str[m].length()) {
                if (compare(str[m], pat) < 0) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else {
                if (compare(str[m].substring(0, pat.length()), pat) < 0) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

        }
        return r;
    }

    public static int binS_r(String[] str, String pat) {
        int l = 0;
        int r = str.length;
        while (l < r - 1) {
            int m = (l + r) / 2;
            if(pat.length() > str[m].length()) {
                if (compare(str[m], pat) > 0) {
                    r = m;
                } else {
                    l = m;
                }
            } else {
                if (compare(str[m].substring(0, pat.length()), pat) > 0) {
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
