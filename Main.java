
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
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
        while(e > 0) {
            if((e&1) == 1) res = res * a % m;
            a = a * a % m;
            e >>= 1;
        }
        return  res;
    }

    static void solve(long n, long k, long mod) {
        long res = powMod(n, k, mod) + 2 * powMod(n-1, k, mod);
        res += powMod(n, n, mod) + 2 * powMod(n-1, n-1, mod);
        out.println(res%mod);
    }

    public static void main(String[] args) {
        while(true) {
            long n = in.nextInt(), k = in.nextInt(), mod = 10000007;
            if(n == 0) break;
            solve(n, k, mod);
        }
        out.close();
    }
}