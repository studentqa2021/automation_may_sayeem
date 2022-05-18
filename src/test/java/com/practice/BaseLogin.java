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
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.generic.DataPropertyReader;
import com.generic.MasterPageFactory;
import com.utils.Highlighter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLogin {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Logger log = Logger.getLogger(BaseLogin.class.getName());
		PropertyConfigurator.configure("./Log4j.properties");
		WebDriver driver = WebDriverManager.chromedriver().create();
		log.info("WebDriver Loaded");
		MasterPageFactory MPF = new MasterPageFactory(driver);
		DataPropertyReader DPR = new DataPropertyReader();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		log.info("Loading supplied web address");
		driver.get(DPR.getValue("url"));
		log.info("Click SignIn to go to SignIn page");
		MPF.getSignIn().click();
		log.info("fill up username");
		MPF.getUsername().sendKeys(DPR.getValue("email"));
		log.info("fill up password");
		MPF.getPassword().sendKeys(DPR.getValue("password"));
		Highlighter.highlighter(driver,MPF.getSubmitLogin());
		Thread.sleep(7000);
		log.info("click SignIn button");
		MPF.getSubmitLogin().click();
		if(MPF.getSignOut().isDisplayed())
		{
			((JavascriptExecutor)driver).executeScript("alert('Login Successfully');");
		}
		Thread.sleep(5000);
		driver.switchTo().alert().dismiss();
	}

}
