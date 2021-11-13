package pages;

import interfaces.Page;
import interfaces.fieldname.FieldName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CitilinkGPUPage implements Page {


    @FindBy(xpath = "//h1")
    private WebElement title;

    @FieldName("Поиск по модели")
    @FindBy(xpath = "//input[@data-meta-name='FilterSearch__input' and @placeholder='Найти']")
    private WebElement searchField;

    @FieldName("Показать все")
    @FindBy(xpath = "//div[@id='app-filter']//span[contains(text(), 'Показать все')]//ancestor::button")
    private WebElement filterShowAll;

    @FieldName("Искомая модель")
    @FindBy(xpath = "//div[@data-meta-name='FilterSeoLabelLayout']//a")
    private WebElement currentModelLink;

    public CitilinkGPUPage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return title.isDisplayed() && title.getText().equals("Видеокарты") && filterShowAll.isDisplayed();
    }
}

