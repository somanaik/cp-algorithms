package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LeadingAndTrailing {
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

    static long getTrailing(long a, long e, long m) {
        long res = 1;
        a %= m;
        while(e > 0) {
            if((e&1) == 1) res = res * a % m;
            a = a * a % m;
            e >>= 1;
        }
        return res;
    }

    static long getLeading(int a, int e) {
        double expo = e * Math.log10(a);
        return (long)(Math.pow(10, expo - (int)expo) * 100);
    }

    static String formatTrailing(long trail) {
        return new String(new char[3-String.valueOf(trail).length()]).replace('\0', '0')
                + String.valueOf(trail);
    }

    static void solve() {
        int n = in.nextInt(), k = in.nextInt();
        long trail = getTrailing(n, k, 1000);
        long lead = getLeading(n, k);
        out.println(String.valueOf(lead) + "..." + formatTrailing(trail));
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