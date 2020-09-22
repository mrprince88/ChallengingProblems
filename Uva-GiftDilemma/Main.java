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
	
	static class triplet {
		long gcd = 0;
		long x = 0;
		long y = 0;
	}

	static long gcd(long a, long b) {
		if (a < b)
			return gcd(b, a);
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static triplet gcdExtend(long a, long b) {

		if (b == 0) {
			triplet myAns = new triplet();
			myAns.gcd = a;
			myAns.x = 1;
			myAns.y = 0;
			return myAns;
		}
		triplet smallAns = gcdExtend(b, a % b);
		triplet myAns = new triplet();
		myAns.gcd = smallAns.gcd;
		myAns.x = smallAns.y;
		myAns.y = (smallAns.x - ((a / b) * smallAns.y));
		return myAns;
	}

	static long modInverse(long a, long b) {
		long ans = gcdExtend(a, b).x;
		return (ans % b + b) % b;
	}

	static long count(long a, long b, long p) {
		long x = gcd(a, b);
		if (p % x != 0) {
			return 0;
		}
		if (p == 0) {
			return 1;
		}
		a /= x;
		b /= x;
		p /= x;

		long y1 = ((p % a) * modInverse(b, a)) % a;
		long firstValue = p / b;
		if (p < y1 * b) {
			return 0;
		}

		long n = (firstValue - y1) / a;
		n = n + 1;
		return n;
	}

	public static void main(String[] args) {
	
		MyReader s = new MyReader();
		int t = s.nextInt();
		for (int w = 1; w <= t; w++) {
			long a = s.nextLong();
			long b = s.nextLong();
			long c = s.nextLong();
			long p = s.nextLong();
			long total = 0;
			for (long i = 0; p - (c * i) >= 0; i++) {
				total += count(a, b, p - c * i);
			}
			System.out.println("Case " + w + ": " + total);

		}

	}
}
