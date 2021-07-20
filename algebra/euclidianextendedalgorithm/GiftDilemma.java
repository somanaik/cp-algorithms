package algebra.euclidianextendedalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GiftDilemma {
    static final int INPUT_BUFF = (1<<15);
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        InputReader(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is), INPUT_BUFF);
            tokenizer = null;
        }

        String next() {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static long calculateLower(long n, long m) {
        if(n < 0 || n%m == 0) return n/m;
        return n/m+1;
    }

    static long calculateUpper(long n, long m) {
        if(n%m == 0 || n > 0) return n/m;
        return n/m - 1;
    }

    // 0 <= x0 + k * b/g <= c/a, 0 <= y0 - k * a/g <= c/b
    static long countSolution(long a, long b, long x0, long y0, long c, long g) {
        long l1 = calculateLower(-x0*g, b), r1 = calculateUpper((c/a-x0)*g, b);
        long l2 = calculateLower((y0-c/b)*g, a), r2 = calculateUpper(y0*g, a);
        long l = Math.max(l1, l2), r = Math.min(r1, r2);
        return l > r ? 0 : r-l+1;
    }

    static void solve() {
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt(), p = in.nextInt();

        // iterative extended euclid algorithm
        long x = 1, y = 0, x1 = 0, y1 = 1, a1 = a, b1 = b, r;
        while(b1 > 0) {
            long q = a1/b1;

            r = x;
            x = x1;
            x1 = r - q*x1;

            r = y;
            y = y1;
            y1 = r - q*y1;

            r = a1;
            a1 = b1;
            b1 = r - q*b1;
        }

        // find valid values (x, y, z) s.t a*x + b*y + c*z = p
        long res = 0;
        for(int z = 0; z <= p/c; z++) {
            int new_p = p - c * z;
            if(new_p == 0) res++;
            else if(new_p % a1 == 0) {
                long q = new_p / a1;
                long x0 = x*q, y0 = y*q;     // base solution
                res+= countSolution(a, b, x0, y0, new_p, a1);
            }
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = in.nextInt();
        for(int i = 1; i <= tests; i++) {
            out.print("Case " + i + ": ");
            solve();
        }
        out.close();
    }
}