import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.Map;

import java.io.File;
import java.util.Iterator;

import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RootKey;

/**
 * @author bls
 *
 */

public class Brain_analyze {

    String outputname = "SystemAnalyzer_";

    public Brain_analyze(Controller controller) throws Throwable {
	System.out.println(this.getClass().toString() + " construct");
	// File name __________________________________________________________________________________________________
	this.makeName();

	try {
	    File ResultsFile = new File(outputname);
	    if (ResultsFile.exists()) {
		ResultsFile.delete();
		ResultsFile.createNewFile();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// Nettverk __________________________________________________________________________________________________
	try {
	    InetAddress ip;

	    ip = InetAddress.getLocalHost();
	    System.out.println("Current IP address=" + ip.getHostAddress());
	    writeLine("Current IP address=" + ip.getHostAddress());

	    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	    final byte[] mac = network.getHardwareAddress();
	    final StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < mac.length; i++) {
		sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	    }
	    writeLine("Mac addr=" + sb.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// enviromental variables __________________________________________________________________________________________________
	/*
	String varsStr = "";
	Map<String, String> env = System.getenv();
	for (String envName : env.keySet()) {
	    varsStr = varsStr + envName+"="+env.get(envName) + "\n";
	}
	writeLine("Environmental variables=" + varsStr);
	
	String propStr = "";
	Map<Object, Object> prop = System.getProperties();
	for (Object propName : prop.keySet()) {
	    propStr = propStr + propName+"="+env.get(propName) + "\n";
	}
	writeLine("Environmental properties=" + propStr);
*/
	// JAVA __________________________________________________________________________________________________
	// System.out.println(System.getProperties());
	String javav = System.getProperties().getProperty("java.specification.version");
	writeLine("java.specification.version (1.8)  " + javav);

	String JAVA_HOME = System.getenv("JAVA_HOME");
	if (JAVA_HOME == null) {
	    writeLine("--JAVA_HOME (i.e C:\\Program Files\\Java\\jdk1.8.0_66)=" + JAVA_HOME);
	} else {
	    writeLine("JAVA_HOME=" + JAVA_HOME);
	}

	// Eclipse __________________________________________________________________________________________________
	System.out.println("Registry check 2");
	System.out.println( getClass().getResource(getClass().getSimpleName() + ".class") );
	System.out.println("jRegistryKey.dll="+Brain_analyze.class.getResource("jRegistryKey.dll"));
	System.out.println("jRegistryKey.dll.path="+Brain_analyze.class.getResource("jRegistryKey.dll").getPath());
	System.out.println("jRegistryKey.dll.getFile()="+Brain_analyze.class.getResource("jRegistryKey.dll").getFile());
        RegistryKey.initialize(Brain_analyze.class.getResource("jRegistryKey.dll").getFile());
        RegistryKey key = new RegistryKey(RootKey.HKLM, "Software\\Eclipse");
        for (Iterator<RegistryKey> subkeys = key.subkeys(); subkeys.hasNext();) {
            RegistryKey subkey = subkeys.next();
            System.out.println("subkey.getName()="+subkey.getName()); // You need to check here if there's anything which matches "Mozilla FireFox".
        }
	
	// GitHub __________________________________________________________________________________________________
	// Skype __________________________________________________________________________________________________
	// FireFox __________________________________________________________________________________________________
	// MS Office __________________________________________________________________________________________________
	//Notepad++ __________________________________________________________________________________________________
	
	/*
	 * //PayEx PosPay Client  __________________________________________________________________________________________________
	 * final String PPP_HOME = System.getenv("PPP_HOME"); writeLine("PPP_HOME " + PPP_HOME);
	 * 
	 * final String SSH_AUTH_SOCK = System.getenv("SSH_AUTH_SOCK"); // writeLine("SSH_AUTH_SOCK " + SSH_AUTH_SOCK);
	 * 
	 * String ipv6 = WindowsRegistry.readRegistry("HKLM\\SYSTEM\\CurrentControlSet\\services\\RemoteAccess\\RouterManagers\\Ipv6", "ProtocolId");
	 * if (ipv6 != null) { writeLine("--ipv6 (null) " + ipv6); } else { writeLine("ipv6 (null) " + ipv6); }
	 * 
	 * writeLine(" "); String PosPayService_config = PPP_HOME + "\\config\\config.properties"; File readFile = new File(PosPayService_config); if
	 * (!readFile.exists()) { return; } try { BufferedReader reader = new BufferedReader(new FileReader(readFile)); String line = null; while
	 * ((line = reader.readLine()) != null) { String inn = line; if (!line.equals("")) { if (!line.startsWith("#")) { writeLine(line); } } }
	 * reader.close(); } catch (Exception e) { e.printStackTrace(); } writeLine(" ");
	 * 
	 * String[] command1 = { "cmd.exe", "/c", "sc", "stop", "RemoteAccess" }; startCommand(command1); String[] command2 = { "cmd.exe", "/c", "sc",
	 * "start", "RemoteAccess" }; startCommand(command2);
	 * 
	 * String[] command3 = { "cmd.exe", "/c", "sc", "stop", "PosPayService" }; startCommand(command3); String[] command4 = { "cmd.exe", "/c",
	 * "sc", "start", "PosPayService" }; startCommand(command4);
	 * 
	 * writeLine(" "); InetAddress inet; try { pingAdress(InetAddress.getByAddress(new byte[] { (byte) 1, (byte) 1, (byte) 1, 1 }),
	 * "relay.SRC.addr"); pingAdress(InetAddress.getByAddress(new byte[] { (byte) 1, (byte) 1, 1, 2 }), "PINPAD.lan.ip");
	 * pingAdress(InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }), "Self");
	 * 
	 * // vil ikke være tilgjengelig, ikke et problem // inet = InetAddress.getByAddress(new byte[] { (byte) 91, (byte) 208, (byte) 214, 34 }); //
	 * PingAdress(inet, "relay.tms.DST.addr");
	 * 
	 * // vil ikke være tilgjengelig, ikke et problem // inet = InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 243, 103 });
	 * // PingAdress(inet, "relay.nfc.DST.addr"); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * netstatPort(new InetSocketAddress("82.115.146.56", 9010), "PayEx TMS, normal host address"); netstatPort(new
	 * InetSocketAddress("82.115.146.183", 443), "evc.payex.com, Prod externt WAN"); netstatPort(new InetSocketAddress("91.208.214.34", 7003),
	 * "Ingenico TMS DST, Prod externt internett"); netstatPort(new InetSocketAddress("91.208.214.34", 7005),
	 * "TCP Relay service TMS dst, Prod externt internett"); netstatPort(new InetSocketAddress("192.168.243.103", 9039), "DST nfc");
	 * netstatPort(new InetSocketAddress("195.225.0.42", 9010), "pospaywan.payex.com, Prod externt WAN"); netstatPort(new
	 * InetSocketAddress("195.225.0.42", 9034), "pospaywan.payex.com, Prod externt WAN"); netstatPort(new InetSocketAddress("195.225.0.45", 9046),
	 * "TMS IP"); netstatPort(new InetSocketAddress("195.225.0.45", 9048), "TMS IP"); netstatPort(new InetSocketAddress("195.225.28.96", 443),
	 * "pospay.payex.com, Prod externt"); netstatPort(new InetSocketAddress("195.225.28.97", 443), ""); netstatPort(new
	 * InetSocketAddress("1.1.1.2", 5188), "Terminal"); writeLine(" "); netstatPort(new InetSocketAddress("1.1.1.1", 7003),
	 * "TCP Relay service SRC tms"); netstatPort(new InetSocketAddress("1.1.1.1", 9034), "TCP Relay service SRC host"); netstatPort(new
	 * InetSocketAddress("1.1.1.1", 9039), "TCP Relay service SRC nfc"); netstatPort(new InetSocketAddress("172.25.129.20", 8080), "PayEx TMS");
	 * netstatPort(new InetSocketAddress("195.225.28.98", 2121), "pospayssh.payex.com, Prod externt internett"); netstatPort(new
	 * InetSocketAddress("195.225.28.104", 9010), "PayEx TMS");
	 */
	writeLine("Finished");
    }

    void pingAdress(InetAddress adress, String note) {
	System.out.println("Sending Ping Request to " + adress);
	try {
	    if (adress.isReachable(5000)) {
		writeLine(adress + " is reachable (" + note + ")");
	    } else {
		writeLine("--" + adress + " is NOT reachable (" + note + ")");
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    void netstatPort(SocketAddress sockaddr, String note) {
	Socket socket = new Socket();
	System.out.println("Sending netstat Request to " + sockaddr);
	try {
	    socket.connect(sockaddr, 10000);
	    writeLine(sockaddr + " is reachable (" + note + ")");
	} catch (Throwable e) {
	    writeLine("--" + sockaddr + " is NOT reachable (" + note + ")");
	} finally {
	    try {
		socket.close();
	    } catch (IOException ex) {
	    }

	}
    }

    void startCommand(String[] command) {
	System.out.println("startCommand");
	try {
	    Process process = new ProcessBuilder(command).start();
	    InputStream inputStream = process.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	    String line;
	    boolean success = true;
	    String retur = "";
	    while ((line = bufferedReader.readLine()) != null) {
		if (line.equals("")) {

		} else if (line.contains("FAILED")) {
		    System.out.println("line "+line);
		    retur = "-- Command unable to run (" + command[3] + " " + command[4] + "): " + line;
		    success = false;
		} else {
		    retur = retur + line;
		    System.out.println("line "+line);
		}
		// writeLine("-- Command successful (" +command[3] + command[4] + ")");
	    }
	    if (success) {
		writeLine("Command successful (" + command[3] + " " + command[4] + ")");
	    } else {
		writeLine(retur);
	    }
	} catch (Throwable ex) {
	    writeLine("-- Command unable to run (" + command[3] + " " + command[4] + ")");
	    // System.out.println("Exception : "+ex);
	}
    }

    void writeLine(String line) {
	System.out.println("writeLine "+line);

	if (line == null) {
	    try {
		throw new Exception("Null line to write");
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	try {
	    String filename = outputname;
	    FileWriter fw = new FileWriter(filename, true); // the true will append the new data
	    fw.write(line + System.getProperty("line.separator"));// appends the string to the file
	    fw.close();
	} catch (IOException ioe) {
	    System.err.println("IOException: " + ioe.getMessage());
	}

    }

    private void makeName() {
	Date naa = new Date();
	String dato = (naa.getYear() + 1900) + "." + (naa.getMonth() + 1) + "." + naa.getDate();
	String tid = naa.getHours() + "." + naa.getMinutes() + "." + naa.getSeconds();
	System.out.println("dato=" + dato);
	System.out.println("tid=" + tid);
	outputname = outputname + dato + "_" + tid;
	try {
	    // maskinnavn
	    InetAddress ip;
	    ip = InetAddress.getLocalHost();
	    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	    final byte[] mac = network.getHardwareAddress();
	    final StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < mac.length; i++) {
		sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	    }
	    outputname = outputname + "_" + sb.toString() + ".log";
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
