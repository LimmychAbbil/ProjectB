package net.lim.strategies;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Limmy on 09.01.2017.
 */
public class XLSStrategy implements Strategy{

    private static final String PATHTOFILE = "./res/UsersSheet.xlsx";
    private static final Logger XLSLogger = Logger.getLogger(XLSStrategy.class.getName());
    private XSSFWorkbook workbook;
    private static XLSStrategy instance;

    public static synchronized XLSStrategy getInstance() throws IOException {
        if (instance == null) instance = new XLSStrategy();
        return instance;
    }

    private XLSStrategy() throws IOException {
        File sheetFile = new File(PATHTOFILE);
        if (!sheetFile.getParentFile().exists()) new File(sheetFile.getParent()).mkdir();
        if (sheetFile.exists() && sheetFile.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(sheetFile);
            workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();

            if (workbook.getNumberOfSheets() != 0) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                XLSLogger.log(Level.INFO, "Sheet loads successfully, number of rows is " + sheet.getPhysicalNumberOfRows());
            }
            else {
                XSSFSheet sheet = workbook.createSheet("Users");
            }
        }
        else {
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Users");
            sheet.setColumnWidth(0,15220);
            sheet.setColumnWidth(1, 15220);

            XSSFRow title = sheet.createRow(0);
            XSSFCell userTitleCell = title.createCell(0);
            userTitleCell.setCellValue("Test");
            XSSFCell numberTitleCell = title.createCell(1);
            numberTitleCell.setCellValue("Numbers of attempts");

            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setBorderBottom(BorderStyle.MEDIUM);
            titleStyle.setFillForegroundColor(new XSSFColor(java.awt.Color.GREEN));
            titleStyle.setFillPattern(FillPatternType.ALT_BARS);


            userTitleCell.setCellStyle(titleStyle);
            numberTitleCell.setCellStyle(titleStyle);

            this.writeFile();

            XLSLogger.log(Level.INFO, ".xlsx file created successfully");
        }

    }

    public void create(String name) {
        XSSFSheet sheet = workbook.getSheet("Users");
        XSSFRow row = sheet.createRow(sheet.getPhysicalNumberOfRows());

        XSSFCell userCell = row.createCell(0);
        userCell.setCellType(CellType.STRING);
        userCell.setCellValue(name);

        XSSFCell numberCell = row.createCell(1);
        numberCell.setCellType(CellType.NUMERIC);
        numberCell.setCellValue(1.0);

        this.writeFile();
    }

    public int read(String name) {
        XSSFSheet users = workbook.getSheetAt(0);
        if ((users != null) && users.getSheetName().equals("Users")) {
            for (int i = 1; i < users.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = users.getRow(i);
                if (row.getCell(0).getStringCellValue().equals(name)) {
                    return (int)row.getCell(1).getNumericCellValue();
                }
            }
        }
        else {
            XLSLogger.severe("Can not read the name from file, return 0");
        }

        return 0;
    }

    public void update(String name) {
        XSSFSheet users = workbook.getSheetAt(0);
        if ((users != null) && users.getSheetName().equals("Users")) {
            for (int i = 1; i < users.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = users.getRow(i);
                if (row.getCell(0).getStringCellValue().equals(name)) {
                    row.getCell(1).setCellValue(row.getCell(1).getNumericCellValue() + 1);
                    break;
                }
            }
            writeFile();
        }
        else {
            XLSLogger.severe("Can not read the name from file, return 0");
        }
    }

    private void writeFile() {
        try (OutputStream os = new FileOutputStream(PATHTOFILE);) {
            workbook.write(os);
            os.flush();
            os.close();
        }
        catch (IOException e) {
            XLSLogger.log(Level.WARNING, "Sheet File wasn't written.");
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        workbook.close();
    }
}
