import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class excelData {
    public String getString(int sh,int row, int column) throws IOException {
        String excelPath = "C:\\Users\\prhegde\\IdeaProjects\\HU_API_TRACK\\Rest_Assured_Main\\src\\resource\\mainAPI.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sh);
        XSSFRow r = null;
        XSSFCell cell = null;
        String st= null;
        r = sheet.getRow(row);
        cell = r.getCell(column);
        if(column == 2){
            int a =(int) cell.getNumericCellValue();
            st=Integer.toString(a);
            return st;
        }
        st = cell.getStringCellValue();
        return st;
    }
    public int getAge(int sh, int row, int column) throws IOException {
        String excelPath = "C:\\Users\\prhegde\\IdeaProjects\\HU_API_TRACK\\Rest_Assured_Main\\src\\resource\\mainAPI.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sh);
        XSSFRow r = null;
        XSSFCell cell = null;
        r = sheet.getRow(row);
        cell = r.getCell(column);
        int ag = (int) cell.getNumericCellValue();
        return ag;
    }
    public void writeToken(Object ObjToken,int row, int column) throws IOException {
        String excelPath = "C:\\Users\\prhegde\\IdeaProjects\\HU_API_TRACK\\Rest_Assured_Main\\src\\resource\\mainAPI.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow r = null;
        XSSFCell cell = null;
        r= sheet.getRow(row);
        cell = r.createCell(column);
        cell.setCellType(CellType.STRING);
        String token =(String)ObjToken;
        cell.setCellValue(token);
        FileOutputStream fos = new FileOutputStream(excelPath);
        wb.write(fos);
        fos.close();
    }
    public String getToken(int sht,int row,int col) throws IOException {
        String excelPath = "C:\\Users\\prhegde\\IdeaProjects\\HU_API_TRACK\\Rest_Assured_Main\\src\\resource\\mainAPI.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(sht);
        XSSFRow r = null;
        XSSFCell cell = null;
        String st= null;
        r = sheet.getRow(row);
        cell = r.getCell(col);
        st = cell.getStringCellValue();
        return st;
    }
    public String getString1(int row, int column) throws IOException {
        String excelPath = "C:\\Users\\prhegde\\IdeaProjects\\HU_API_TRACK\\Rest_Assured_Main\\src\\resource\\mainAPI.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow r = null;
        XSSFCell cell = null;
        String st= null;
        r = sheet.getRow(row);
        cell = r.getCell(column);
        if(column == 2){
            int a =(int) cell.getNumericCellValue();
            st=Integer.toString(a);
            return st;
        }
        st = cell.getStringCellValue();
        return st;
    }
}