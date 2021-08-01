package algebra.lineardiophantineequation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EbonyAndIvory {
    static int INPUT_BUFF = (1<<15);
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

    static void solve() {
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        long x = 1, y = 0, x1 = 0, y1 = 1, r, q, a1 = a, b1 = b;
        while(b1 > 0) {
            q = a1 / b1;

            r = a1;
            a1 = b1;
            b1 = r - q * b1;

            r = x;
            x = x1;
            x1 = r - q * x1;

            r = y;
            y = y1;
            y1 = r - q * y1;
        }

        String res = "No";
        if(c%a1 == 0) {
            q = c/a1;
            long x0 = -x*q*a1, y0 = y*q*a1;
            long l1 = (x0 < 0 || x0%b == 0) ? x0 / b : x0/b+1;
            long r1 = (y0 > 0 || y0%a == 0) ? y0 / a : y0/a-1;

            if(l1 <= r1) {
                x0 = x0 + l1*b/a1;
                y0 = y0 - l1*a/a1;
                assert(x0 >= 0 && x0 <= c/a);
                assert(y0 >= 0 && y0 <= c/b);
                res = "Yes";
            }
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = 1;
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}
