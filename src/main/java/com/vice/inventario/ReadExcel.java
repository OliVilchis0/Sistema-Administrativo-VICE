package com.vice.inventario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

@Component
public class ReadExcel {
	
	private FileInputStream filePath;
	private XSSFWorkbook book;
	private XSSFSheet sheet;
	private List<HashMap<String,Object>> listMap;
	
	public List<HashMap<String, Object>> read(){
		try {
			File file = getPath();
			if(file == null)
				return null;
			
			filePath = new FileInputStream(file);
			book = new XSSFWorkbook(filePath);
			sheet = book.getSheetAt(0);
			listMap = new ArrayList<>();
			
			HashMap<String, Object> map;
			for(Row row : sheet) {
				if(row.getRowNum() > 5) {
					map = new HashMap<>();
					for(Cell cell : row) {
						switch (cell.getColumnIndex()) {
							case 1:
								map.put("partida", cell.getStringCellValue());
								break;
							case 4:
								map.put("talla", cell.getStringCellValue());
								break;
							case 5:
								map.put("color", cell.getStringCellValue());
								break;
							case 6:
								map.put("cantidad",(int)cell.getNumericCellValue());
								break;
							case 7:
								map.put("pBuenas",(int)cell.getNumericCellValue());
								break;
							case 8:
								map.put("pMalas",(int)cell.getNumericCellValue());
								break;
							default:
								break;
							}
					}
					listMap.add(map);
				}
			}
			
			filePath.close();
			book.close();
			return listMap;
		} catch (FileNotFoundException e) {
			Validacion.getAlert(
					AlertType.ERROR, "Error", "", "Debio ocurrir un error al tratar de abrir el archivo :(");
			return null;
		}catch(IOException e) {
			Validacion.getAlert(
					AlertType.ERROR, "Error", "", "Debio ocurrir un error al tratar de leer el archivo :(");
			return null;
		}	
	}
		
	//GET PATH TO DOCUMENT
	public File getPath() {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Excel", "*.xls"));
		File file = fc.showOpenDialog(null);
		
		return file == null ? null : file;
	}
}
