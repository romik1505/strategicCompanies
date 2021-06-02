package com.example.demo;

import com.example.demo.pojo.PurchasesRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<PurchasesRequest> parsePurchase(String inn, int count) throws MalformedURLException, InterruptedException, ParseException {
        URL local = new URL("http://localhost:9515");
        WebDriver driver = new RemoteWebDriver(local, DesiredCapabilities.chrome());
        String url;
        url = "https://zakupki.gov.ru/epz/contractfz223/search/results.html?searchString="+inn+"&morphology=on&search-filter=Дате+размещения&sortBy=BY_UPDATE_DATE&pageNumber=1&sortDirection=false&recordsPerPage=_50&showLotsInfoHidden=false&currencyId=-1";
        driver.get(url);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        List<PurchasesRequest> purchasesRequests = new ArrayList<>(20);

        while (true){
            List<WebElement> num = driver.findElements(By.className("registry-entry__body-value"));
            List<WebElement> status = driver.findElements(By.className("registry-entry__header-mid__title"));
            List<WebElement> price = driver.findElements(By.className("price-block__value"));
            List<WebElement> date = driver.findElements(By.className("/html/body/form/section[2]/div/div/div[1]/div[3]/div[48]/div/div[2]/div[2]/div[2]"));

            for (int i = 0; i < num.size(); i++) {
                purchasesRequests.add(new PurchasesRequest(
                        num.get(i).getText(),
                        status.get(i).getText(),
                        price.get(i).getText(),
                        formatter.parse(driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+ (i+1) +"]/div/div[2]/div[2]/div[2]")).getText()),
                        driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+(i+1)+"]/div/div[1]/div[1]/div/div[1]/a")).getAttribute("href")));
//                System.out.println(num.get(i).getText());
//                System.out.println(status.get(i).getText());
//                System.out.println(price.get(i).getText());
//                System.out.println(driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+ (i+1) +"]/div/div[2]/div[2]/div[2]")).getText());
//                System.out.println(driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+(i+1)+"]/div/div[1]/div[1]/div/div[1]/a")).getAttribute("href"));

                if (count-- < 0)
                    return purchasesRequests;
            }

            Thread.sleep(2000l);
            if (driver.findElement(By.className("paginator-button-next")) == null)
                break;
            Thread.sleep(1000l);
            driver.findElement(By.className("paginator-button-next")).click();
        }
        return purchasesRequests;
    }
}
