package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomUtils {
    public static void print(String text) {
        Allure.step(text);
        System.out.println(text);
    }

    public static void printError(String text) {
        Allure.step(text, Status.FAILED);
        System.out.println(text);
    }

    @Attachment
    public static byte[] getScreen(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screenshots/" + screenshot.getName()));
            return Files.readAllBytes(Paths.get("src/main/resources/screenshots", screenshot.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
