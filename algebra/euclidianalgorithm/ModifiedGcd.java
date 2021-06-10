package algebra.euclidianalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ModifiedGcd {
    static final int INPUT_BUFF = (1<<15);
    static class InputReader {
        final BufferedReader reader;
        StringTokenizer tokenizer;
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

    static int gcd(int a, int b) {
        while(b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static void solve() {
        int a = in.nextInt(), b = in.nextInt();
        List<Integer> arr = new ArrayList<>();
        int g = gcd(a, b);
        for(int i = 1; i*i <= g; i++) {
            if(g%i == 0) {
                int j = g / i;
                arr.add(i);
                if(j != i) arr.add(j);
            }
        }
        Collections.sort(arr);
        int n = in.nextInt();
        while(n > 0) {
            int low = in.nextInt(), high = in.nextInt(), res = -1;
            int l = 0, h = arr.size() - 1;
            while(l < h) {
                int m = (l + h) >> 1;
                if(arr.get(m) <= high) {
                    l = m + 1;
                } else h = m;
            }
            if(arr.get(l) > high) l--;
            if(l >= 0) {
                if(arr.get(l) >= low) {
                    res = arr.get(l);
                }
            }
            out.println(res);
            n--;
        }
    }
    public static void main(String[] args) {
        int tests = 1;
//        tests = in.nextInt();
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}
