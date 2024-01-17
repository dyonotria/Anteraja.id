import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



WebUI.openBrowser('anteraja.id')
task_1()
task_2()
WebUI.closeBrowser()

private task_1() {
		
	WebUI.waitForElementPresent(findTestObject('Object Repository/AnterAja/Page_Anteraja Jasa Pengiriman Barang Online Terbaik/Button_Layanan'), 2)
	WebUI.click(findTestObject('Object Repository/AnterAja/Page_Anteraja Jasa Pengiriman Barang Online Terbaik/Button_Layanan'))
	WebUI.click(findTestObject('Object Repository/AnterAja/Page_Anteraja Jasa Pengiriman Barang Online Terbaik/DropDown_Option_CekOngkir'))

	WebUI.waitForElementPresent(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/Button_CekOngkir'), 2)
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Origin'), 'setiabudi')
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Origin'), Keys.chord(Keys.DOWN))
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Origin'), Keys.chord(Keys.ENTER))
	
	
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Destination'), 'pasar minggu')
	WebUI.delay(1)
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Destination'), Keys.chord(Keys.DOWN))
	WebUI.sendKeys(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/InputField_Destination'), Keys.chord(Keys.ENTER))
		
	WebUI.click(findTestObject('Object Repository/AnterAja/Page_Informasi Ongkos Kirim - Anteraja/Button_CekOngkir'))
	WebUI.takeScreenshot()
}

private task_2() {	
	WebDriver driver = DriverFactory.getWebDriver()
	String ExpectedValue1 = "Regular"
	String ExpectedValue2 = "Frozen"
	WebElement Table = driver.findElement(By.xpath("//table/tbody"))
	
	List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
	int rows_count = rows_table.size()
	
	Loop:
	for (int row = 0; row < rows_count; row++) {
		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))
		int columns_count = Columns_row.size()
			
			for (int column = 0; column < columns_count; column++) {
				String celltext = Columns_row.get(column).getText()
				
					//Layanan Regular
					if (celltext == ExpectedValue1) {
						WebUI.verifyEqual(Columns_row.get(1).getText(), 'Rp 10.000', FailureHandling.STOP_ON_FAILURE)
						WebUI.verifyEqual(Columns_row.get(2).getText(), '1-2 Day', FailureHandling.STOP_ON_FAILURE)
						WebUI.comment ((('Tipe Layanan: ' + Columns_row.get(0).getText()) + ', Estimasi Harga/KG: ' + Columns_row.get(1).getText()) + ', Estimasi Kedatangan: ' + Columns_row.get(2).getText())
						}
					//Layanan Frozen		
					if (celltext == ExpectedValue2) {
						WebUI.verifyEqual(Columns_row.get(1).getText(), 'Rp 22.000', FailureHandling.STOP_ON_FAILURE)
						WebUI.verifyEqual(Columns_row.get(2).getText(), '0 Day', FailureHandling.STOP_ON_FAILURE)
						WebUI.comment ((('Tipe Layanan: ' + Columns_row.get(0).getText()) + ', Estimasi Harga/KG: ' + Columns_row.get(1).getText()) + ', Estimasi Kedatangan: ' + Columns_row.get(2).getText())
						break Loop
						}
				}
		}
	WebUI.takeScreenshot()
}	
