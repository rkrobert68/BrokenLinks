package org.brokenLinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) {
		int brokenLInk=0;
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		int size = allLinks.size();
		System.out.println(size);
		for (WebElement webElement : allLinks) {
			String attribute = webElement.getAttribute("href");

			if(attribute==null||attribute.isEmpty()) {

				System.out.println("Url is empty");
				continue;
			}

			try {

				URL u=new URL(attribute);
				URLConnection openConnection = u.openConnection();
				HttpURLConnection http=(HttpURLConnection) openConnection;
				http.connect();
				if(http.getResponseCode()<200) {
					System.out.println(http.getResponseCode() + attribute + "is  "  + "brokenLink");
					brokenLInk++;

				}
			}catch(Exception e) {
				System.out.println("error");
			}

		}
		System.out.println(brokenLInk);
	}


	}


