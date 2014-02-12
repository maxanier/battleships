package battleships.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.text.DateFormatter;

public class Logger {

	private static boolean debug = false;
	private static File logFile;
	private static String directory;
	private static JTextArea log_area;

	public static void init() {
		if (logFile == null) {
			directory = CONSTANTS.LOG_DIRECTORY;
			(new File(directory)).mkdir();
			logFile = new File(directory + CONSTANTS.LOG_FILE_NAME);
			try {
				logFile.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			log("\n Neustart \n");
		}
	}

	/**
	 * Sets extended Debugmode
	 * 
	 * @param mode
	 */
	public static void setDebugMode(boolean mode) {
		debug = mode;
	}

	public static boolean getDebugMode() {
		return debug;
	}

	/**
	 * Sets a JTextArea, in which all logs will appear
	 * 
	 * @param area
	 *            JTextArea
	 */
	public static void setOutput(JTextArea area) {
		log_area = area;
	}

	public static void i(String tag, String msg) {
		log("[INFO] [" + tag + "]: " + msg);
	}

	public static void w(String tag, String msg) {
		log("[WARNING] [" + tag + "]: " + msg);
	}

	public static void e(String tag, String msg) {
		log("[ERROR] [" + tag + "]: " + msg);
	}

	public static void e(String tag, String msg, Throwable t) {
		log("[ERROR] [" + tag + "]: " + msg + "\nERROR-MESSAGE: " + t.getMessage());
	}

	/**
	 * Writes the message to the log file and adds the current time
	 * 
	 * @param msg
	 *            Message
	 */
	private static void log(String msg) {
		if (debug && logFile != null) {
			try {

				BufferedWriter output = new BufferedWriter(new FileWriter(logFile, true));
				output.append(new SimpleDateFormat("MM-dd HH:mm:ss").format(new java.util.Date()) + " " + msg + "\n");
				output.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		System.out.println(msg);
		if (log_area != null) {
			log_area.append(msg + "\n");
			log_area.setCaretPosition(log_area.getDocument().getLength());
		}

	}
}
