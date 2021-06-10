package algebra.euclidianalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GcdAndLcm {
    static final int INPUT_BUFF = (1<<15);
    static class InputReader {
        final BufferedReader reader;
        StringTokenizer tokenizer;
        InputReader(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is), INPUT_BUFF);
            tokenizer = null;
        }

        String next() {
            while(tokenizer != null || !tokenizer.hasMoreTokens()) {
                try {
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

    static int gcd(int a, int b) {
        while(b > 0) {
            int r = a % b;
            a=b;
            b=r;
        }
        return a;
    }

    static void solve() {
        int a = in.nextInt(), b = in.nextInt();
        int g = gcd(a,b);
        out.println(g + " " + (long)a * b / g);
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
