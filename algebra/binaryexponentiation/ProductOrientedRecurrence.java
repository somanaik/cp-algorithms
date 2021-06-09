package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProductOrientedRecurrence {
    static final int INPUT_BUFF = (1<<15);
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

    // just 3X3 square matrix
    static long[][] multiplyMat(long[][] mat1, long[][] mat2, long mod) {
        int n = 3;
        long[][] res = new long[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                res[i][j] = 0;
                for(int k = 0; k < n; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j];
                }
                res[i][j] %= mod;
            }
        }
        return res;
    }

    static long powMod(long a, long e, long mod) {
        long res = 1;
        while(e > 0) {
            if((e&1) == 1) res = res * a % mod;
            a = a * a % mod;
            e >>= 1;
        }
        return  res;
    }

    static void solve() {
        long n = in.nextLong(), mod = (long)(1e9+7);
        long[] f = {in.nextLong(), in.nextLong(),in.nextLong()};
        long c = in.nextLong();
        if(n < 4) out.println(f[(int)n-1]);
        else {
            // g(n) = c^n * f(n)
            for(int i = 0; i < 3; i++) {
                for(int j = 0;  j <= i; j++) {
                    f[i] = f[i] * c % mod;
                }
            }

            // inverse mod of c^n
            long cn = powMod(c, n, mod), ic = powMod(cn, mod-2, mod);

            n-=3;
            long[][] mat = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
            long[][] nmat = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
            // matrix exponentiation + fermat's little theorem
            while(n > 0) {
                if((n&1) == 1) {
                    nmat = multiplyMat(nmat, mat, mod-1);
                }
                mat = multiplyMat(mat, mat, mod-1);
                n >>= 1;
            }

            // g(n) = f3^e3 * f2^e2 * f1^e1
            long res = ic;
            for(int i = 0; i < 3; i++) {
                res = res * powMod(f[2-i], nmat[0][i], mod) % mod;
            }
            out.println(res);
        }
    }

    public static void main(String[] args) {
        int tests = 1;
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}
