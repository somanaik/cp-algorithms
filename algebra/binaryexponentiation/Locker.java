package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Locker {
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static long powMod(long a, long e, long m) {
        long res = 1;
        a %= m;
        while(e > 0) {
            if((e&1) == 1) res = res * a % m;
            a = a * a % m;
            e >>= 1;
        }
        return res;
    }

    static void solve() {
        long n = in.nextLong(), mod = (long)1e9+7, a = 3, r = n % a;
        long res = 1;
        if(n > 1) {
            if(r == 1) {
                res = powMod(a, n/a - 1, mod) * (a + 1) % mod;
            } else {
                res = powMod(a, n/a, mod);
                if(r != 0) res = res * r % mod;
            }
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = 1;
        tests = in.nextInt();
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}