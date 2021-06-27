package com.example.demo.Parser;

import com.example.demo.models.Purchases;
import com.example.demo.pojo.PurchasesRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    public static List<Purchases> parsePurchase(String inn, int count) throws IOException, InterruptedException {

        URL local = new URL("http://localhost:9515");
        WebDriver driver = new RemoteWebDriver(local, DesiredCapabilities.chrome());
        String url;
        url = "https://zakupki.gov.ru/epz/contractfz223/search/results.html?searchString="+inn+"&morphology=on&search-filter=Дате+размещения&sortBy=BY_UPDATE_DATE&pageNumber=1&sortDirection=false&recordsPerPage=_50&showLotsInfoHidden=false&currencyId=-1";
        driver.get(url);
        WebDriver driver1 = new RemoteWebDriver(local, DesiredCapabilities.chrome());
        List<Purchases> result = new ArrayList<>(20);

        while (count >= 0){
            List<WebElement> num = driver.findElements(By.className("registry-entry__body-value"));
            List<WebElement> status = driver.findElements(By.className("registry-entry__header-mid__title"));
            List<WebElement> price = driver.findElements(By.className("price-block__value"));

            for (int i = 0; (i < num.size())&&(count >= 0); i++, count--) {
                driver1.get(driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+(i+1)+"]/div/div[1]/div[1]/div/div[1]/a")).getAttribute("href"));

                try {
                    WebElement iz = driver1.findElement(By.xpath(".//*[text()='Извещение о закупке']/../td[2]"));
                    WebElement lot = driver1.findElement(By.xpath(".//*[text()='Лот']/../td[2]"));
                    WebElement sub = driver1.findElement(By.xpath(".//*[text()='Предмет договора']/../td[2]"));
                    result.add(
                            new Purchases(
                                    num.get(i).getText(),
                                    status.get(i).getText(),
                                    price.get(i).getText(),
                                    new SimpleDateFormat("dd.MM.yyyy").parse(driver.findElement(By.xpath("/html/body/form/section[2]/div/div/div[1]/div[3]/div["+ (i+1) +"]/div/div[2]/div[2]/div[2]")).getText()),
                                    iz.getText(),
                                    lot.getText(),
                                    sub.getText()
                            )
                    );
                } catch (Exception e) {
                }
            }

            Thread.sleep(2000l);
            if (driver.findElement(By.className("paginator-button-next")) == null)
                break;
            Thread.sleep(1000l);
            driver.findElement(By.className("paginator-button-next")).click();
        }

        driver1.quit();
        driver.quit();
        return result;
    }
}
