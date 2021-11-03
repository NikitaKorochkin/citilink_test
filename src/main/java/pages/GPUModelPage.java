package pages;

import interfaces.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPUModelPage implements Page {

    @FindBy(xpath = "//h1")
    private WebElement title;


    @FindAll(@FindBy(xpath = "//div[@class='ProductCardHorizontal__header-block']//a[contains(text(), 'Видеокарта')]"))
    private List<WebElement> gpuTitles;

    @FindAll(@FindBy(xpath = "//div[@class='ProductCardHorizontal__price-block']//span[@class='ProductCardHorizontal__price_current-price js--ProductCardHorizontal__price_current-price ']"))
    private List<WebElement> gpuPrices;


    private Map<String, Integer> results;

    public GPUModelPage() {
        initPage();
    }

    public Map<String, Integer> getResults() {
        results = new HashMap<>();
        int count = 0;
        for (WebElement title: gpuTitles) {
            results.put(title.getText(), convertToInteger(gpuPrices.get(count).getText()));
            count++;
        }
        return results;
    }

    public Integer convertToInteger(String price)   {
        return Integer.parseInt(price.replaceAll("\\s+", ""));
    }

    @Override
    public boolean isPageLoaded() {
        return title.getText() != null;
    }

}
