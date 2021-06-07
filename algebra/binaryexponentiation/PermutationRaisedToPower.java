package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PermutationRaisedToPower {
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

    static void applyPermutation(int[] target, int[] perm, int n) {
        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            res[i] = target[perm[i]];
        }
        for(int i = 0; i < n; i++) target[i] = res[i];
    }

    static void permutationExponent(int[] perm, int k, int n) {
        int[] res = new int[n];
        for(int i = 0; i < n; i++) res[i] = i;
        // use binary exponentiation technique
        while(k > 0) {
            if((k&1) == 1) {
                applyPermutation(res, perm, n);
            }
            applyPermutation(perm, perm, n);
            k >>= 1;
        }
        for(int i = 0; i < n; i++) perm[i] = res[i];
    }

    static void solve() {
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        int[] permutation = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++) {
            permutation[i] = in.nextInt();  // 1-index
            permutation[i]--;
        }
        permutationExponent(permutation, k, n);
        for(int i = 0; i < n; i++) {
            out.print(arr[permutation[i]] + " ");
        }
        out.println();
    }

    public static void main(String[] args) {
        int tests = 1;
        tests = in.nextInt();
        for(int i = 1 ; i <= tests; i++) {
            solve();
        }
        out.close();
    }
}