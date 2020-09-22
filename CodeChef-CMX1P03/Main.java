import java.io.*;
import java.util.*;

class Main {

	static long []fact = new long[500005];
	static int m = 998244353;

	static long recpow(long n, long p , long m) {
		long ans = 1;
		n = n % m;
		if (n == 0)
			return 0;

		while (p > 0) {
			if ((p & 1) == 1)
				ans = (ans * n) % m;
			n = (n * n) % m;
			p >>= 1;
		}

		return ans % m;

	}

	static long mod_inv(long n, int m) {
		return recpow(n, m - 2, m);
	}

	static long nCr(int n, int r) {
		if (r == 0)
			return 1;
		return (((fact[n] * mod_inv(fact[n - r], m)) % m) * mod_inv(fact[r], m)) % m;
	}


	public static void main(String[] args)throws IOException {

		fact[0] = fact[1] = 1L;
		for (int i = 2; i < 500005; i++) {
			fact[i] = (fact[i - 1] * i) % m;
		}

		InputReader in = new InputReader();
		int t = in.nextInt();
		while (t-- > 0) {
			String s = in.nextLine();
			int []freq = new int[27];
			long l = 0;
			int pos = 0;

			for (int i = 0; i < s.length(); i++)
				freq[(int)s.charAt(i) - 'a']++;

			int n = in.nextInt();

			String []str = new String[n];

			for (int i = 0; i < n; i++) {
				str[i] = in.nextLine();
				long score = 1;
				int []freq1 = new int[27];
				for (int j = 0; j < str[i].length(); j++)
					freq1[(int)str[i].charAt(j) - 'a']++;

				for (int j = 0; j < 26; j++) {
					if (freq[j] < freq1[j]) {
						score = 0;
						break;
					}
					if (freq1[j] == 0)
						continue;
					if (freq[j] == 0)
						continue;
					score = (score * nCr(freq[j], freq1[j])) % m;
				}
				System.out.println(score);
				if (l < score) {
					l = score;
					pos = i;
				}
			}

			if (l == 0)
				System.out.println("No Research This Month");
			else
				System.out.println(str[pos]);
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
