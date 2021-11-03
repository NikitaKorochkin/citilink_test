package pages;

import interfaces.Page;
import interfaces.fieldname.FieldName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CitilinkMainPage implements Page {

    @FindBy(xpath = "//div[@class='MainHeader__logo']//a[@class='Logo__link']")
    private WebElement logoLink;

    @FieldName("Каталог")
    @FindBy(xpath = "//button[@data-label='Каталог товаров']")
    private WebElement catalogButton;

    @FieldName("Все категории")
    @FindAll(@FindBy (xpath = "//div[@class=\"CatalogMenu__category-items js--CatalogMenu__category-items\"]//a"))
    private List<WebElement> allCats;

    @FieldName("Ноутбуки и компьютеры")
    @FindBy(xpath = "//span[contains(text(), 'Ноутбуки и компьютеры')]//ancestor::a")
    private WebElement category;

    @FieldName("Видеокарты")
    @FindBy(xpath = "//div[@data-subcategory-id = '25']//following-sibling::div//a[@data-title='Видеокарты']")
    private WebElement subCategory;

    public CitilinkMainPage() {
        initPage();
        allCats = new ArrayList<>();
    }

    @Override
    public boolean isPageLoaded() {
        return logoLink.isDisplayed();
    }

    public void openCatalog()   {
        catalogButton.click();
    }
}
