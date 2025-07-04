package abhik26.java_programs.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ParallelNetworkCall {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			Runnable task = () -> {
				HttpURLConnection httpConnection = null;
				BufferedReader br = null;

				try {
					URL url = new URL("http://localhost:8000/sample-api");
					httpConnection = (HttpURLConnection) url.openConnection();

					try {
						InputStream is = httpConnection.getInputStream();
						br = new BufferedReader(new InputStreamReader(is));
						System.out.println(br.readLine());
					} catch (Exception e) {
						InputStream is = httpConnection.getErrorStream();
						br = new BufferedReader(new InputStreamReader(is));
						System.out.println(br.readLine());
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (httpConnection != null) {
						httpConnection.disconnect();
					}

					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {

						}
					}
				}
			};

			new Thread(task).start();
		}
	}
}