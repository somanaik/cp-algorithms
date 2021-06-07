package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Modex {
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

    static void solve() {
        int x = in.nextInt(), y = in.nextInt(), n = in.nextInt();
        int res = 1;
        while(y > 0) {
            if((y&1) == 1) res = res * x % n;
            x = x * x % n;
            y >>= 1;
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = 1;
        tests = in.nextInt();
        for(int i = 1;  i <= tests; i++) {
            solve();
        }
        out.close();
    }
}