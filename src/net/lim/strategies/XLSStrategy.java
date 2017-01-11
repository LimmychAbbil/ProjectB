package net.lim.strategies;


import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * Created by Limmy on 09.01.2017.
 */
public class XLSStrategy implements Strategy{

    /*
        First try of POI
        NOW
        Create .xls file with only 2 fields (in the different columns)
     */

    private static final String PATHTOFILE = "C:/test.xlsx";
    public XLSStrategy() throws IOException {
        File sheetFile = new File(PATHTOFILE);
        if (sheetFile.exists() && sheetFile.isFile()) {
            //TODO READ FROM FILE USING INPUT STREAM
        }
        else {
            XSSFWorkbook workbook = new XSSFWorkbook();
            OutputStream os = new FileOutputStream(sheetFile);
            XSSFSheet sheet = workbook.createSheet("Users");

            XSSFRow title = sheet.createRow(0);
            XSSFCell userTitleCell = title.createCell(0);
            userTitleCell.setCellValue("Test");
            XSSFCell numberTitleCell = title.createCell(1);
            numberTitleCell.setCellValue("Numbers of attempts");
            //TODO set title style

            workbook.write(os);
            os.flush();
            os.close();
            workbook.close();
        }

    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        new XLSStrategy();
        System.out.println("DONE");

    }
    public void create() {

    }

    public int read(String name) {
        return 0;
    }

    public int write(String name) {
        return 0;
    }
}
