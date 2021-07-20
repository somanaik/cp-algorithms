package algebra.euclidianextendedalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EuclidProblem {
    static final int INPUT_BUFF = (1<<15);
    static class InputReader {
        StringTokenizer tokenizer;
        BufferedReader reader;
        InputReader(InputStream is) {
            reader = new BufferedReader(new InputStreamReader(is), INPUT_BUFF);
            tokenizer = null;
        }

        String next() {
            while(tokenizer == null || !tokenizer.hasMoreTokens()) {
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

    static void solve() {
        int a = in.nextInt(), b = in.nextInt();
        int x = 1, y = 0, x1 = 0, y1 = 1;
        while(b > 0) {
            int q = a / b;

            int r = x;
            x = x1;
            x1 = r - q * x1;

            r = y;
            y = y1;
            y1 = r - q * y1;

            r = a;
            a = b;
            b = r - q*b;
        }
        out.println(x + " " + y + " " + a);
    }

    public static void main(String[] args) {
        while(true) {
            try {
                solve();
            } catch (Exception e) {
                break;
            }
        }
        out.close();
    }
}
