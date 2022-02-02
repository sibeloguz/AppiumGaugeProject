import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest {

    Logger logger = LogManager.getLogger(LogMethod.class);

    @Step("<time> saniye kadar bekle")
    public void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    @Step("id <id> li elemente tıkla")
    public void clickByid (String id) {
        appiumDriver.findElement(By.id(id)).click();
        logger.info("id li elemente tıkklandı");
    }

    @Step("xpath <xpath> li elemente tıkla")
    public void clickByxpath (String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
        logger.info("xpath li elemente tıklandı");
    }

    @Step("id <id> li elementi bul ve <text> degerini yaz")
    public void sendkeysByid(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info("id li elementi buldu ve <text> degerini yazdı");
    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreacontrol(String id, String text) {
        Assert.assertTrue("Element text degerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));
        logger.info("id li elementi buldu ve <text> alanını kontrol etti");
    }

    @Step("Elementler <xpath> arasından rasgele bir tanesini seç ve tıkla")
    public void clickRandomelement(String xpath) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
        logger.info("xpath li elementi seçti ve tıklandı");
    }

    @Step("Sayfanın en aşağı kısmına kadar  iki kez scroll et")
    public void scrollWithAction() {
        Actions actions = new Actions(appiumDriver);
        ((Actions) actions).build().perform();

        final int ANIMATION_TIME = 150;
        final int PRESS_TIME = 150;
        int edgeBorder = 30;
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

}


