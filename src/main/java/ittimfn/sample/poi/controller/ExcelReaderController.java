package ittimfn.sample.poi.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelReaderController {
    
    private static Logger logger = LogManager.getLogger(ExcelReaderController.class);

    private static final List<Path> usingExcelPath = new ArrayList<>();
    private Path excelPath;
    private FileInputStream fis;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReaderController(Path excelPath) throws EncryptedDocumentException, IOException {
        this.excelPath = excelPath;
        usingExcelPath.add(excelPath);
        try {
            this.fis = new FileInputStream(this.excelPath.toString());
            this.workbook = WorkbookFactory.create(this.fis);
        } catch (Exception e) {
            usingExcelPath.remove(excelPath);
            throw e;
        }
    }

    public int getRowLength(String sheetName) {
        this.sheetOpen(sheetName);
        return this.sheet.getPhysicalNumberOfRows();
    }

    public int getColumnLength(String sheetName) {
        this.sheetOpen(sheetName);
        return this.sheet.getRow(0).getPhysicalNumberOfCells();
    }

    private void sheetOpen(String sheetName) {
        logger.info("Open Excel file: {} , sheet : {}", this.excelPath, sheetName);
        this.sheet = this.workbook.getSheet(sheetName);
    }

    public void close() {
        logger.info("Close Excel file: " + this.excelPath);
        try {
            this.workbook.close();
            this.fis.close();
        } catch (Exception e) {
            logger.error("Excel file close error.", e);
        } finally {
            usingExcelPath.remove(this.excelPath);}
    }
}
