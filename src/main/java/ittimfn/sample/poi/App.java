package ittimfn.sample.poi;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;

import ittimfn.sample.poi.controller.ExcelReaderController;

/**
 * Poiで行数、列数を取得する。
 *
 */
public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public void exec(String[] args) throws EncryptedDocumentException, IOException {
        if (args.length < 2) {
            logger.error("Usage: java -jar getExcelLength.jar <Excel file path> <sheet name>");
            return;
        }

        int argsIndex = 0;

        Path filePath = Paths.get(args[argsIndex++]);
        ExcelReaderController reader = new ExcelReaderController(filePath);
        String sheetName = args[argsIndex++];

        logger.info("Row length: " + reader.getRowLength(sheetName));
        logger.info("Column length: " + reader.getColumnLength(sheetName));
    }

    public static void main( String[] args ) throws EncryptedDocumentException, IOException {
        logger.info("Get Excel length start.");
        new App().exec(args);
        logger.info("Get Excel length finish.");
    }
}
