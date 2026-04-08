package utils;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider
	private Object[][] getExcelData() throws IOException {
	    String path = ConfigReader.getPropertyAsString("testdata.path");
	    ExcelUtils xl = new ExcelUtils(path, "Sheet1");
	    int rownum = xl.getRowCount();
	    int colcount = xl.getCellCount(1);
	    Object[][] dataArray = new Object[rownum][colcount];
	    for(int i = 1; i <= rownum; i++) {
	        for(int j = 0; j < colcount; j++) {
	            dataArray[i-1][j] = xl.getCellData(i, j);
	        }
	    }
	    return dataArray;
	}
}
