import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    private Brain_Forside class_Brain_Forside;
    Brain_analyze class_Brain_analyze;

    // public config config;
    public String workingDir = System.getProperty("user.dir") + "\\";
    public String newline = System.getProperty("line.separator");

    public Controller() {

    }

    void Startup_1_GUI() throws Exception {
	// class_Brain_Forside = new Brain_Forside(this);
	// class_Brain_Forside.SettOppGUI();
    }

    private void Startup_6_EnableSearch() { // enabler normale søk
	System.out.println("Startup_6_EnableSearch");
	try {
	    System.out.println("Startup_6_EnableSearch 1a");
	    class_Brain_analyze = new Brain_analyze(this);
	    System.out.println("Startup_6_EnableSearch 1b");
	} catch (Throwable e) {
	    System.out.println("Startup_6_EnableSearch 2a");
	    String filename = "POS_analyze_ERROR.log";
	    FileWriter fw;
	    try {
		fw = new FileWriter(filename, true);

		for (StackTraceElement ste : e.getStackTrace()) {
		    System.out.println(ste);

		    fw.write(ste + System.getProperty("line.separator"));
		}
		fw.close();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}
    }

    public void SettingsComplete() {
	// class_Brain_Forside.SettingsComplete();

	// PrintAction(this.getClass().toString() + " Settings adjusted");

	try {

	    Startup_6_EnableSearch();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public boolean PrintAction(String actionMessage) {
	this.class_Brain_Forside.AddProgressMessage(actionMessage);

	return false;
    }

}
