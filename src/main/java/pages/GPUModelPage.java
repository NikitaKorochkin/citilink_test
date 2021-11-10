package pages;

import interfaces.Page;
import interfaces.fieldname.FieldName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPUModelPage extends CitilinkGPUPage implements Page {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FieldName("Сортировать по цене")
    @FindBy(xpath = "//div[contains(text(), 'по цене')]")
    private WebElement byPrice;

    @FieldName("Блок с результатами")
    @FindBy(xpath = "//div[@class='ProductCardCategoryList__list']")
    private WebElement productCardListElement;

    @FindAll(@FindBy(xpath = "//div[@class='ProductCardHorizontal__header-block']//a[contains(text(), 'Видеокарта')]"))
    private List<WebElement> gpuTitles;

    @FindAll(@FindBy(xpath = "//div[@class='ProductCardHorizontal__price-block']//span[@class='ProductCardHorizontal__price_current-price js--ProductCardHorizontal__price_current-price ']"))
    private List<WebElement> gpuPrices;


    private Map<String, Integer> results;

    public GPUModelPage() {
        initPage();
    }

    public Map<String, Integer> getResults(String model) {
        String[] modelParts = model.split(" ");
        results = new HashMap<>();
        int count = 0;
        for (WebElement title : gpuTitles) {
            if (modelParts.length > 2 && title.getText().contains(modelParts[1] + modelParts[2].toUpperCase())) {
                results.put(title.getText(), convertToInteger(gpuPrices.get(count).getText()));
                count++;
            } else if (title.getText().contains(model)) {
                results.put(title.getText(), convertToInteger(gpuPrices.get(count).getText()));
            }

        }
        return results;
    }

    public Integer convertToInteger(String price) {
        return Integer.parseInt(price.replaceAll("\\s+", ""));
    }

    @Override
    public boolean isPageLoaded() {
        return title.getText() != null;
    }

}
