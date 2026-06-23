package org.sujalkhot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class VideoRecorder {

	public static void startRecording(AppiumDriver driver) {

		((CanRecordScreen) driver).startRecordingScreen();
	}

	public static String stopAndSaveVideo(AppiumDriver driver, String testName) throws Exception {

		String media = ((CanRecordScreen) driver).stopRecordingScreen();
		// Appium stops recording and returns video in Base64 format.

		String path = System.getProperty("user.dir") + "//videos//" + testName + ".mp4";

		byte[] decode = Base64.getDecoder().decode(media); // Converts encoded text into actual video bytes

		File file = new File(path); // create a new file

		file.getParentFile().mkdirs(); // is used to create all parent directories of a file if they don't already
										// exist.

		FileOutputStream fos = new FileOutputStream(file);
		// creates a FileOutputStream object that allows Java to write data into a file.

		fos.write(decode); // Writes video bytes into: test_Name.mp4

		fos.close();

		return path;
	}

}
