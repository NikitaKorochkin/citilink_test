package pages;

import interfaces.Page;
import interfaces.fieldname.FieldName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CitilinkMainPage implements Page {

    @FindBy(xpath = "//div[@class='MainHeader__logo']//a[@class='Logo__link']")
    private WebElement logoLink;

    @FieldName("Каталог")
    @FindBy(xpath = "//button[@data-label='Каталог товаров']")
    private WebElement catalogButton;

    @FieldName("Ноутбуки и компьютеры")
    @FindBy(xpath = "//span[contains(text(), 'Ноутбуки и компьютеры')]//ancestor::a")
    private WebElement category;

    @FieldName("Видеокарты")
    @FindBy(xpath = "//div[@data-subcategory-id = '25']//following-sibling::div//a[@data-title='Видеокарты']")
    private WebElement subCategory;

    public CitilinkMainPage() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return logoLink.isDisplayed();
    }

    public void openCatalog()   {
        catalogButton.click();
    }
}
