package algebra.lineardiophantineequation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GetAcInOneGo {
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

    static int gcd(int a, int b) {
        int r;
        while(b > 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static void solve() {
        int a = in.nextInt(), b = in.nextInt();
        int g = gcd(a, b);
        long res = -1;
        if(g == 1) {
            res = a;
            res = res * b - a - b + 1;
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = in.nextInt();
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}
