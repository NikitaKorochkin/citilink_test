
import org.junit.jupiter.api.Test;
import pages.CitilinkGPUPage;
import pages.CitilinkMainPage;
import pages.GPUModelPage;
import steps.Steps;

public class Tests extends BaseTests    {
    @Test
    public void gpuModelsPricesComparison()   {
        chromeDriver.get("https://www.citilink.ru/");

        CitilinkMainPage citilinkMainPage = new CitilinkMainPage();
        Steps.clickOnElement(citilinkMainPage, "Каталог");
        Steps.clickOnElementWithMoving(citilinkMainPage, "Ноутбуки и компьютеры", "Видеокарты");

        CitilinkGPUPage citilinkGPUPage = new CitilinkGPUPage();

        Steps.clickOnElement(citilinkGPUPage, "Показать все");
        Steps.sendModelToSearchField(citilinkGPUPage, "GTX 1660 Ti");
        Steps.chooseModel(citilinkGPUPage, "Выбор модели");
        GPUModelPage gpuModelPage = new GPUModelPage();
        int firstModelLowestPrice = Steps.getLowestPriceGPU(Steps.getRes(gpuModelPage)).getValue();

        Steps.clickOnElement(citilinkGPUPage, "Показать все");
        Steps.sendModelToSearchField(citilinkGPUPage, "RTX 3060");
        Steps.chooseModel(citilinkGPUPage, "Выбор модели");
        int secondModelLowestPrice = Steps.getLowestPriceGPU(Steps.getRes(gpuModelPage)).getValue();

        Steps.firstPriceShouldBeLower(firstModelLowestPrice, secondModelLowestPrice);
    }
}
