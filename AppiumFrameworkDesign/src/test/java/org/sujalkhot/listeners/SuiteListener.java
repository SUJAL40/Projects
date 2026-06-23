package org.sujalkhot.listeners;

import org.sujalkhot.utils.EmailUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

	@Override

	public void onFinish(ISuite suite) {

		try {

			EmailUtils.sendEmail();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
