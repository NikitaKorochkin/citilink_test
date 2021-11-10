
import org.junit.jupiter.api.Test;
import pages.CitilinkGPUPage;
import pages.CitilinkMainPage;
import pages.GPUModelPage;
import steps.Steps;

public class Tests extends BaseTests    {

    private final String FIRST_MODEL = "GTX 1050 Ti";
    private final String SECOND_MODEL = "RTX 3080";

    @Test
    public void gpuModelsPricesComparison()   {
        chromeDriver.get("https://www.citilink.ru/");

        CitilinkMainPage citilinkMainPage = new CitilinkMainPage();
        Steps.clickOnElement(citilinkMainPage, "Каталог");
        Steps.clickOnCatalogElement(citilinkMainPage, "Ноутбуки и компьютеры");
        Steps.clickOnCatalogSubElement(citilinkMainPage, "Видеокарты");
        CitilinkGPUPage citilinkGPUPage = new CitilinkGPUPage();

        Steps.clickOnElement(citilinkGPUPage, "Показать все");
        Steps.chooseModel(citilinkGPUPage, FIRST_MODEL, "Выбор модели");
        GPUModelPage firstGpuModelPage = new GPUModelPage();
        int firstModelLowestPrice = Steps.getLowestPriceGPU(Steps.getRes(firstGpuModelPage, FIRST_MODEL)).getValue();
        Steps.goBack(chromeDriver);
        Steps.goBack(chromeDriver);

        GPUModelPage secondGpuModelPage = new GPUModelPage();
        Steps.clickOnElement(citilinkGPUPage, "Показать все");
        Steps.chooseModel(citilinkGPUPage, SECOND_MODEL, "Выбор модели");
        int secondModelLowestPrice = Steps.getLowestPriceGPU(Steps.getRes(secondGpuModelPage, SECOND_MODEL)).getValue();

        Steps.firstPriceShouldBeLower(firstModelLowestPrice, secondModelLowestPrice);
    }
}
