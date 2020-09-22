import java.util.*;
import java.io.*;

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
			String str = " ";
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
			long n = s.nextLong();
			long []a = new long[1000000];
			int i, j;
			long p = 0, m = 0, c = n * (n + 1) / 2;
			for (i = 0; i < n; i++) {
				a[i] = s.nextLong();
				a[i] = Math.abs(a[i]);
				a[i] %= 4;
			}
			for (i = 0; i < n; i++) {
				if (a[(int)i] == 2) {
					p = 0; m = 0;
					for (j = i - 1; j >= 0; j--) {
						if (a[j] == 1 || a[j] == 3)
							++p;
						else break;
					}
					for (j = i + 1; j < n; j++) {
						if (a[j] == 1 || a[j] == 3)
							++m;
						else break;
					}
					c = c - (p + 1) * (m + 1);
				}
			}
			System.out.println(c);
		}
	}
}
