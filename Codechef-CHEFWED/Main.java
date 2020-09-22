import java.io.*;
import java.util.*;

class Main {

	static class MyReader {
		BufferedReader br;
		StringTokenizer st;

		public MyReader() {
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

	public static void main(String[] args) {

		MyReader s = new MyReader();
		int t = s.nextInt();
		while (t-- > 0) {
			int n = s.nextInt();
			int k = s.nextInt();
			int []a = new int[n];
			int i, j, m;
			for (i = 0; i < n; i++) {
				a[i] = s.nextInt();
			}
			HashMap<Integer, Integer> map = new HashMap<>();
			int [][]col = new int[n + 1][n + 1];
			for (i = 0; i < n; i++) {
				map.clear();
				for (j = i; j < n; j++) {
					col[i][j] = (j == 0) ? 0 : col[i][j - 1];
					int c = map.containsKey(a[j]) ? map.get(a[j]) : 0;
					if (map.containsKey(a[j])) {
						if (c == 1)
							col[i][j]++;
						col[i][j]++;
					}
					if (c == 0)
						map.put(a[j], 1);
					else
						map.put(a[j], c + 1);
				}
			}
			int [][]dp = new int[1001][1001];
			for (i = 1; i < n + 1; i++)
				dp[1][i] = col[0][i - 1];
			for (i = 2; i <= 100; i++) {
				for (j = 2; j <= n; j++) {
					int best = Integer.MAX_VALUE;
					for (m = 1; m < j; m++)
						best = Math.min(best, dp[i - 1][m] + col[m][j - 1]);
					dp[i][j] = best;
				}
			}
			int ans = Integer.MAX_VALUE;
			for (i = 1; i <= 100; i++) {
				ans = Math.min(ans, i * k + dp[i][n]);
			}
			System.out.println(ans);


		}
	}
}