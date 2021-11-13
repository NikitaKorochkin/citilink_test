package steps;

import drivers.WebDriverManager;
import interfaces.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CitilinkGPUPage;
import pages.GPUModelPage;
import utils.CustomUtils;
import utils.PageUtils;

import java.util.Comparator;
import java.util.Map;

public class Steps {

    @Step("Навестись на элемент каталога {elementName}")
    public static void clickOnCatalogElement(Page page, String elementName) {
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        WebElement element = page.getElement(elementName);
        actions.moveToElement(element).build().perform();
    }

    @Step("Нажать на дочерний элемент каталога {elementName}")
    public static void clickOnCatalogSubElement(Page page, String elementName) {
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        WebElement element = page.getElement(elementName);
        actions.moveToElement(element).click().build().perform();
    }

    @Step("Нажать на элемент {elementName}")
    public static void clickOnElement(Page page, String elementName) {
        WebElement element = page.getElement(elementName);
        PageUtils.waitUntilElementBeVisible(element);
        element.click();
    }

    @Step("Ввести модель {model} видеокарты в строку поиска")
    public static void sendModelToSearchField(Page page, String model) {
        PageUtils.waitUntilElementBeVisible(page.getElement("Поиск по модели"));
        WebElement searchField = page.getElement("Поиск по модели");
        searchField.click();
        searchField.sendKeys(model);
    }

    @Step("Выбрать модель {model} из элемента {elementName} ")
    public static void chooseModel(CitilinkGPUPage page, String model, String elementName) {
        try {
            sendModelToSearchField(page, model);
            WebElement element = page.getElement(elementName);
            Actions actions = new Actions(WebDriverManager.getCurrentDriver());
            actions.moveToElement(element).click().build().perform();
            Assertions.assertEquals(element.getText(), model, "Уточните название модели");
        } catch (NoSuchElementException e) {
            System.out.println("Модели " + model + " нет в списке");
        }
    }


    @Step("Вернуться к предыдущей странице")
    public static void goBack(WebDriver driver) {
        driver.navigate().back();
    }

    @Step("Получить все видеокарты одной модели {model}, которые есть в наличии")
    public static Map<String, Integer> getRes(GPUModelPage page, String model) {
        clickOnElement(page, "Сортировать по цене");
        PageUtils.waitUntilElementBeVisible(page.getElement("Блок с результатами"));
        CustomUtils.getScreen(WebDriverManager.getCurrentDriver());
        return page.getResults(model);
    }

    @Step("Выбор самой дешевой видеокарты из {gpus}")
    public static Map.Entry<String, Integer> getLowestPriceGPU(Map<String, Integer> gpus) {
        Assertions.assertFalse(gpus.isEmpty(), "Модели нет в наличии");
        return gpus.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
    }


    @Step("Сравнение моделей по ценам {firstPrice} и {secondPrice}")
    public static void firstPriceShouldBeLower(int firstPrice, int secondPrice) {
        System.out.println(firstPrice);
        System.out.println(secondPrice);
        Assertions.assertTrue(firstPrice < secondPrice);
    }

}
