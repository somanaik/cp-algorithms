package algebra.lineardiophantineequation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheEquation {
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

    static long calculateLower(long n, long m) {
        if(m < 0) {
            n = -n;
            m = -m;
        }
        if(n%m == 0 || n < 0) return n/m;
        return n/m+1;
    }

    static long calculateUpper(long n, long m) {
        if(m < 0) {
            n = -n;
            m = -m;
        }
        if(n%m == 0 || n > 0) return n/m;
        return n/m-1;
    }

    static void solve() {
        int a = in.nextInt(), b = in.nextInt(), c = -in.nextInt();
        int xl = in.nextInt(), xr = in.nextInt(), yl = in.nextInt(), yr = in.nextInt();

        assert(xl <= xr);
        assert(yl <= yr);

        // de-generate case
        long res = 0;
        if(a == 0 && b == 0) {
            if(c == 0) {
                res = (xr-xl+1);
                res = res * (yr-yl+1);
            }
        } else if(a == 0) {
            if(c%b == 0) {
                int y = c/b;
                if(yl <= y && y <= yr) {
                    res = (xr-xl+1);
                }
            }
        } else if(b == 0) {
            if(c%a == 0) {
                int x = c/a;
                if(xl <= x && x <= xr) {
                    res = (yr-yl+1);
                }
            }
        } else {
            // iterative extended euclidian algorithm
            long a1 = Math.abs(a), b1 = Math.abs(b), x = 1, y = 0, x1 = 0, y1 = 1, r, q;
            while(b1 > 0) {
                q = a1/b1;

                r = a1;
                a1 = b1;
                b1 = r - q*b1;

                r = x;
                x = x1;
                x1 = r - q * x1;

                r = y;
                y = y1;
                y1 = r - q*y1;
            }

            if(c%a1 == 0) {
                q = c/a1;
                long x0 = x*q, y0 = y*q;
                if(a < 0) x0 = -x0;
                if(b < 0) y0 = -y0;

                long l1, r1, l2, r2;
                if(b < 0) {
                    l1 = calculateLower((x0-xr)*a1, -b);
                    r1 = calculateUpper((x0-xl)*a1, -b);
                } else {
                    l1 = calculateLower((xl-x0)*a1, b);
                    r1 = calculateUpper((xr-x0)*a1, b);
                }

                if(a < 0) {
                    l2 = calculateLower((yl-y0)*a1, -a);
                    r2 = calculateUpper((yr-y0)*a1, -a);
                } else {
                    l2 = calculateLower((y0-yr)*a1, a);
                    r2 = calculateUpper((y0-yl)*a1, a);
                }

                l1 = Math.max(l1, l2);
                r1 = Math.min(r1, r2);
                res = (l1 > r1 ? 0 : r1 - l1 + 1);
            }
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
