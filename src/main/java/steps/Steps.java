package steps;

import drivers.WebDriverManager;
import interfaces.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CitilinkGPUPage;
import pages.GPUModelPage;

import java.util.Comparator;
import java.util.Map;

public class Steps {

    @Step("Нажать на элемент {elementName}, а затем на элемент {subElement}")
    public static void clickOnElementWithMoving(Page page, String elementName, String subElement) {
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        WebElement element = page.getElement(elementName);
        actions.moveToElement(element).build().perform();

        WebElement subEl = page.getElement(subElement);
        actions.moveToElement(subEl);
        actions.click().build().perform();

    }

    @Step("Нажать на элемент {elementName}")
    public static void clickOnElement(Page page, String elementName) {
        WebElement element = page.getElement(elementName);
        element.click();
    }

    @Step("Ввести модель {model} видеокарты в строку поиска")
    public static void sendModelToSearchField(Page page, String model) {
        WebElement searchField = page.getElement("Поиск по модели");
        searchField.click();
        searchField.sendKeys(model);
    }

    @Step("Выбрать модель по названию {elementName} ")
    public static void chooseModel(CitilinkGPUPage page, String elementName)   {
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        WebElement element = page.getElement(elementName);
        actions.moveToElement(element).click().build().perform();

    }

    @Step("Получить все видеокарты")
    public static Map<String, Integer> getRes(GPUModelPage page)    {
       return page.getResults();
    }

    @Step("Выбор самой дешевой видеокарты из {gpus}")
    public static Map.Entry<String, Integer> getLowestPriceGPU(Map<String, Integer> gpus)    {
        return gpus.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
    }

    @Step("Сравнение моделей по ценам {firstPrice} и {secondPrice}")
    public static void firstPriceShouldBeLower(int firstPrice, int secondPrice) {
        System.out.println(firstPrice);
        System.out.println(secondPrice);
        Assertions.assertTrue(firstPrice < secondPrice);
    }

}
