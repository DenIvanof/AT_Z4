package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

//java -jar artifacts/app-card-delivery.jar
public class OrderCardTest {
    private String generateData(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void fillingInAllFieldsTest() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Самара");
        String planningDate = generateData(365, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        //Thread.sleep(1000);
        $("[data-test-id='name'] input").setValue("Иванов Денис");
        //Thread.sleep(500);
        $("[data-test-id='phone'] input").setValue("+71112223344");
        //Thread.sleep(500);
        $("[data-test-id='agreement']").click();
        //$(".checkbox__box").click();
        //Thread.sleep(500);
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
        //Thread.sleep(500);

    }

    //Необязательная часть
    @Test
    void searchCityTest() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Са");
        //Thread.sleep(1000);
        $(byText("Самара")).click();
        String planningDate = generateData(365, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        //Thread.sleep(1000);
        $("[data-test-id='name'] input").setValue("Иванов Денис");
        //Thread.sleep(500);
        $("[data-test-id='phone'] input").setValue("+71112223344");
        //Thread.sleep(500);
        $("[data-test-id='agreement']").click();
        //$(".checkbox__box").click();
        //Thread.sleep(500);
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
        //Thread.sleep(500);

    }

    @Test
    void selectionCalendarTest() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Са");
        //Thread.sleep(1000);
        $(byText("Самара")).click();
        $("button").click();
        Thread.sleep(1000);
        $("[data-test-id='name'] input").setValue("Иванов Денис");
        //Thread.sleep(500);
        $("[data-test-id='phone'] input").setValue("+71112223344");
        //Thread.sleep(500);
        $("[data-test-id='agreement']").click();
        //$(".checkbox__box").click();
        //Thread.sleep(500);
        $(".button.button").click();
        //$(".notification__content")
        // .shouldBe(Condition.visible, Duration.ofSeconds(15))
        // .shouldHave(Condition.exactText("Встреча успешно забронирована на " + ));
        //Thread.sleep(500);

    }


}
