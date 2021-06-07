package algebra.binaryexponentiation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JewelEatingMonsters {
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

    static long powMod(long a, long e, long m) {
        long res = 1;
        while(e > 0) {
            if((e&1) == 1) res = res * a % m;
            a = a * a % m;
            e >>= 1;
        }
        return res;
    }

    // (1 + a^1 + a^2 + a^3 +...+ a^n) % m when m is not necessarily a prime
    static long powSeriesMod(long a, long e, long m) {
        if(e == 0) return 1;
        long res = powSeriesMod(a, e>>1, m);
        if((e&1) == 1) return (res + res * powMod(a, e - (e>>1), m)) % m;
        return (res + (res - 1 + m) * powMod(a, e - (e>>1), m)) % m;
    }

    static long solve(int x, int a, int n, int c) {
        x%=c;
        a%=c;
        long an = powMod(a, n, c) * x % c, ani = powSeriesMod(a, n-1, c) * a % c;
        return (an - ani + c) % c;
    }

    public static void main(String[] args) {
        while(true) {
            int x = in.nextInt(), a = in.nextInt();
            int n = in.nextInt(), c = in.nextInt();
            if(x == 0) break;
            out.println(solve(x, a, n, c));
        }
        out.close();
    }
}