/*
 * Copyright (c) 2022 SAM
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.generic.CrossBrowserCheck;
import com.generic.DataPropertyReader;
import com.generic.MasterPageFactory;

public class BaseLogin {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		WebDriver driver = CrossBrowserCheck.crossBrowserCheck("chrome");
		MasterPageFactory MPF = new MasterPageFactory(driver);
		DataPropertyReader DPR = new DataPropertyReader();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		driver.get(DPR.getValue("url"));
		MPF.getSignIn().click();
		MPF.getUsername().sendKeys(DPR.getValue("email"));
		MPF.getPassword().sendKeys(DPR.getValue("password"));
		MPF.getSubmitLogin().click();
		if(MPF.getSignOut().isDisplayed())
		{
			((JavascriptExecutor)driver).executeScript("alert('Login Successfully');");
		}
		Thread.sleep(5000);
		driver.switchTo().alert().dismiss();
	}

}
