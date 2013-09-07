/**
 * Xi Gong
 * Sep 6, 2013
 */
import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
	public static void log(String message) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
			PrintWriter out = new PrintWriter(new FileWriter(prop.getProperty("logger"), true));
			out.println(message);
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
