package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BigMod {
    static final int INPUT_BUFF = (1 << 15);
    static class InputReader {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;
        public InputReader(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is), INPUT_BUFF);
            tokenizer = null;
        }
        public String next() {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static void solve(int b, int p, int m) {
        b%=m;
        int res = 1;
        while(p > 0) {
            if((p&1) == 1) res = res * b % m;
            b = b * b % m;
            p >>= 1;
        }
        out.println(res);
    }

    public static void main(String[] args) {
        while(true) {
            try {
                int b = in.nextInt(), p = in.nextInt(), m = in.nextInt();
                solve(b, p, m);
            } catch (Exception e) {
                break;
            }
        }
        out.close();
    }
}