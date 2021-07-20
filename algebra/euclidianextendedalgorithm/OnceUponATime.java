package algebra.euclidianextendedalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OnceUponATime {
    static final int INPUT_BUFF = (1 << 15);
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        InputReader(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is), INPUT_BUFF);
            tokenizer = null;
        }

        String next() {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                try{
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch(IOException e) {
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

    static long calculateUpper(long n, long m) {
        if(m < 0) {
            n = -n;
            m = -m;
        }
        if(n%m == 0 || n >= 0) return n/m;
        return n/m-1;
    }

    static void solve(long n, long m, long a, long k) {
        // iterative extended euclid algorithm
        long x = 1, y = 0, x1 = 0, y1 = 1, a1 = m, b1 = a, r;
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
        y=-y;

        if((k - n) % a1 == 0) {
            long q = (k-n)/a1, res;
            if(q == 0) {
                res = a*m/a1 + n;
            } else {
                long x0 = x*q, y0 = y*q;
                long k1 = Math.min(calculateUpper(x0*a1, a), calculateUpper((y0-1)*a1, m));
                res = (x0 - a*k1/a1) * m + n;
            }
            out.println(res);
        } else out.println("Impossible");
    }

    public static void main(String[] args) {
        while(true) {
            int n = in.nextInt(), m = in.nextInt(), a = in.nextInt(), k = in.nextInt();
            if(n == 0 && m == 0 && a == 0 && k == 0) break;
            solve(n, m, a, k);
        }
        out.close();
    }
}
