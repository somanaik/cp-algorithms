package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountKLengthPaths {
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

    static long[][] multiply(long[][] mat1, long[][] mat2, long mod) {
        int n = mat1.length, m = mat2.length, p = mat2[0].length;
        long[][] res = new long[n][p];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < p; j++) {
                for(int k = 0; k < m; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j] % mod;
                }
                res[i][j] %= mod;
            }
        }
        return res;
    }

    static void solve() {
        int n = in.nextInt(), m = in.nextInt(), k =  in.nextInt();
        long[][] adjMatrix = new long[n][n], path = new long[n][n];
        long mod = (long)1e9+7;
        while(m > 0) {
            int u = in.nextInt(), v = in.nextInt();
            adjMatrix[u-1][v-1] = 1;
            m--;
        }
        for(int i = 0; i < n; i++) path[i][i] = 1;
        while(k > 0) {
            if((k&1) == 1) path = multiply(path, adjMatrix, mod);
            adjMatrix = multiply(adjMatrix, adjMatrix, mod);
            k>>=1;
        }
        long res = 0;
        for(long[] row : path) {
            for(long val : row) {
                res+=val;
            }
            res%=mod;
        }
        out.println(res);
    }

    public static void main(String[] args) {
        int tests = 1;
        for(int i = 1; i <= tests; i++) {
            solve();
        }
        out.close();
    }

}
