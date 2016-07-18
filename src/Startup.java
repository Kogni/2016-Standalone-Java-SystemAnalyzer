
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("ucd")
public class Startup {

    public static void main(String[] args) throws IOException {
	System.out.println("Starting parser");

	try {
	    Controller ny = new Controller();
	    ny.Startup_1_GUI();
	    ny.SettingsComplete();
	} catch (Throwable e) {
	    System.out.println("a");
	    String filename = "POS_analyze_ERROR";
	    FileWriter fw = new FileWriter(filename, true); // the true will append the new data
	    for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
		fw.write(ste + System.getProperty("line.separator"));// appends the string to the file
	    }
	    fw.close();

	}

    }
}
