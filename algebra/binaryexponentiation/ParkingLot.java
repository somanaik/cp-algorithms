package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ParkingLot {
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
        int n = in.nextInt();
        long res = 0;
        if(n >= 4) {
            res = 12 * ((1L<<(2*n-5)) + 3 * (n-3) * (1L<<(2*n-8)));
        } else res = 12 * (1<<(2*n-5));
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = 1;
        for(int i = 1;  i <= tests; i++) {
            solve();
        }
        out.close();
    }
}