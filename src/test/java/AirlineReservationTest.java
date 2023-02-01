import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class AirlineReservationTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/pelin/Downloads/chromedriver_mac64/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.aa.com/homePage.do?locale=en_US");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,300)");

        // Choose Departure Place
        driver.findElement(By.cssSelector("input[value='IST']")).clear();
        driver.findElement(By.name("originAirport")).sendKeys("Cana");
        driver.findElement(By.xpath("//a[text()='YVR - Vancouver Intl, Canada']")).click();

        // Choose Destination Place
        driver.findElement(By.cssSelector("a[data-for='reservationFlightSearchForm.destinationAirport']")).click();
        driver.findElement(By.id("countryCode")).click();
        driver.findElement(By.cssSelector("option[value='GB']")).click();
        driver.findElement(By.id("airport_LHR")).click();

        // Choose Number of Passenger
        driver.findElement(By.name("adultOrSeniorPassengerCount")).click();
        driver.findElement(By.cssSelector("option[value='2']")).click();

        // Departure Date
        driver.findElement(By.id("aa-leavingOn")).sendKeys("01/31/2023");

        // Choose Date of Return
        driver.findElement(By.xpath("(//button[@class='ui-datepicker-trigger'])[2]")).click();

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("a[aria-label='Next Month']")).click();
        }

        List<WebElement> date = driver.findElements(By.cssSelector("td[data-handler='selectDay']"));
        for (WebElement days : date) {
            String day = days.getText();
            if (day.equals("22")) {
                days.click();
                break;
            }
        }

        // Search Button
        driver.findElement(By.id("flightSearchFormSubmitButton")).click();
        Thread.sleep(30000);

        // Choose Flights
        js.executeScript("scrollBy(0,1400)");
        driver.findElement(By.id("slice0Flight5MainCabin")).click();

        js.executeScript("scrollBy(0,1000)");
        driver.findElement(By.id("slice1Flight1MainCabin")).click();
        driver.findElement(By.id("btn-main-cabin")).click();

        // Flights Information Summary
        driver.findElement(By.id("flightS7PjaEiPUDjgMXz3StopCountLink_desktop")).click();
        driver.findElement(By.id("closeTooltip")).click();
        driver.findElement(By.id("flight0SummaryDetailsLink")).click();
        driver.findElement(By.id("ui-id-7")).click();
        driver.findElement(By.id("flightDetailsDialogButton0")).click();
        driver.findElement(By.id("flight1SummaryDetailsLink")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick'])[4]")).click();
        driver.findElement(By.id("button_continue_guest")).click();

        // Information of Passengers
        driver.findElement(By.name("passengers[0].personalInformationForm.firstName")).sendKeys("Abc");
        driver.findElement(By.name("passengers[0].personalInformationForm.lastName")).sendKeys("ABCD");

        driver.findElement(By.id("passengers0.dateComponentForm.month")).click();
        driver.findElement(By.cssSelector("option[value='2']")).click();
        driver.findElement(By.cssSelector("select[name='passengers[0].dateComponentForm.day']")).click();
        driver.findElement(By.xpath("(//option[@value='5'])[2]")).click();
        driver.findElement(By.name("passengers[0].dateComponentForm.year")).click();
        driver.findElement(By.cssSelector("option[value='2000']")).click();
        driver.findElement(By.id("sfpdGender0")).click();
        driver.findElement(By.cssSelector("option[value='F']")).click();
        driver.findElement(By.id("passengers0.residencyInfo.country")).click();
        driver.findElement(By.cssSelector("option[value='BE']")).click();

        driver.findElement(By.name("passengers[1].personalInformationForm.firstName")).sendKeys("Xyz");
        driver.findElement(By.name("passengers[1].personalInformationForm.lastName")).sendKeys("WXYZ");

        WebElement month = driver.findElement(By.name("passengers[1].dateComponentForm.month"));
        Select select = new Select(month);
        select.selectByValue("8");

        WebElement day = driver.findElement(By.cssSelector("select[name='passengers[1].dateComponentForm.day']"));
        select = new Select(day);
        select.selectByValue("20");

        WebElement year = driver.findElement(By.name("passengers[1].dateComponentForm.year"));
        select = new Select(year);
        select.selectByValue("1995");

        driver.findElement(By.id("sfpdGender1")).click();
        driver.findElement(By.cssSelector("select[id='passengers1.personalInformationForm.gender'] option[value='M']")).click();
        driver.findElement(By.id("passengers1.residencyInfo.country")).click();
        driver.findElement(By.cssSelector("select[id='passengers1.residencyInfo.country'] option[value='CA']")).click();
        driver.findElement(By.id("passengers1.residencyInfo.province")).click();
        driver.findElement(By.cssSelector("select[id='passengers1.residencyInfo.province'] option[value='ON']")).click();

        driver.findElement(By.id("tripContact.email")).sendKeys("abc@gmail.com");
        driver.findElement(By.name("tripContact.confirmEmail")).sendKeys("abc@gmail.com");
        driver.findElement(By.id("tripContact.phones0.number")).sendKeys("5675675675");

        driver.findElement(By.name("_button_continue")).click();
        js.executeScript("scrollBy(0,1000)");

        driver.findElement(By.id("viewReservationDetailsForm_button_continue")).click();

        // Choose Seats of Passengers
        driver.findElement(By.id("seat32A")).click();
        driver.findElement(By.id("seat32B")).click();

        driver.findElement(By.id("nextFlightButton")).click();

        driver.findElement(By.id("seat40G")).click();
        driver.findElement(By.id("seat41H")).click();

        driver.findElement(By.id("nextFlightButton")).click();
        js.executeScript("scrollBy(0,800)");

    }
}
