package algebra.binaryexponentiation;

import java.io.PrintWriter;
import java.util.Scanner;

public class LastDig {
    static Scanner in = new Scanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static void solve() {
        int res = 1, m = 10, val = in.nextInt(), exp = in.nextInt();
        val %= m;
        while(exp > 0) {
            if((exp&1) == 1) res = res * val % m;
            val = val * val % m;
            exp >>= 1;
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int ts = in.nextInt();
        for(int i = 1;  i <= ts; i++) {
            solve();
        }
        out.close();
    }
}