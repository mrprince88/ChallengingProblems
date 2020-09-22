import java.io.*;
import java.util.*;

class Main {
    static long ans;

    static void solve(ArrayList<Long>a, long tempAns, int pointer, int count, long mod) {
        if (pointer == a.size()) {
            if (count == 0)
                tempAns = 0;
            ans = Math.max(ans, tempAns);
            return;
        }
        solve(a, (a.get(pointer) * tempAns) % mod, pointer + 1, count + 1, mod);
        solve(a, tempAns, pointer + 1, count, mod);
    }

    public static void main(String[] args) {
        InputReader in = new InputReader();
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int i, j;
            ArrayList<Long>[] a = new ArrayList[m + 1];
            for (i = 1; i <= m; i++)
                a[i] = new ArrayList<>();

            int[] g = new int[n + 1];

            for (i = 1; i <= n; i++)
                g[i] = in.nextInt();

            for (i = 1; i <= n; i++)
                a[g[i]].add(in.nextLong());

            long[] lg = new long[m + 1];
            for (i = 1; i <= m; i++)
                lg[i] = in.nextLong();

            for (i = 1; i <= m; i++) {
                ans = 0;
                solve(a[i], 1, 0, 0, lg[i]);
                System.out.print(ans + " ");
            }
            System.out.println();
        }

    }


    static class InputReader {
        BufferedReader br;
        StringTokenizer st;

        public InputReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}