import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sendOTP {
	

	public sendOTP(String name,String number,int otp) {
		try {
			
			String apiKey = "apikey=" + "rmRKGpUpmEc-P5bEqrxb4lWrLLf1WhWAoO8mz5mfNy";
			String message = "&message=" + "Hi "+name+ ", Your OTP is "+otp;
			String sender = "&sender=" + "YourDost";
			String numbers = "&numbers=" +number;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
		} 
		
		catch (Exception e) {
			System.out.println("Error SMS "+e);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
