package com.vice.inventario;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import com.vice.inventario.model.Corte;

import javafx.stage.FileChooser;

@Component
public class CreateExcel {
	
	private XSSFSheet sheet;
	private XSSFWorkbook book;
	private List<Corte> listCorte;
	private String workshop;
	
	//METHOD FOR CREATE DOCUMENT
	public void createDoc(List<Corte> listCorte,String workshop) {
		book = new XSSFWorkbook();
		sheet = book.createSheet("Reporte");
		this.listCorte = listCorte;
		this.workshop = workshop;
		//CREATE A HEADER WITH INFORMATION OF DATE AND NAME OF WORKSHOP
		headerInfo();
		//CREATE A HEADER OF DOCUMENTO
		headerTitle();
		//CREATE IN EACH ROW A CORTE OBJECT
		createData();
		//SAVE DOCUMENT
		saveDocument();
		
	}
	
	//HEADER OF DOCUMENT WITH NEED INFORMATION
	private void headerInfo(){
		Row row = sheet.createRow(0);
		
		CellStyle cellStyle = createCellStyle(
				createFont((byte)13, IndexedColors.BLACK.getIndex(), false, true),
				IndexedColors.WHITE.getIndex(),
				FillPatternType.NO_FILL,
				HorizontalAlignment.CENTER_SELECTION,
				VerticalAlignment.CENTER,
				true);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("");
		cell.setCellStyle(cellStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(0,4,0,0));
		//INSERT IMAGET OF FACTORY
		try {
			//AGREGAR IMAGEN.
	        //InputStream is = new FileInputStream("/Archivos/vice.png");
			InputStream is = new FileInputStream(
					"/home/oliver/Documentos/WorkSpaceSTS /inventario/src/main/resources/imagenes/vice.png");
	        byte[] bytes = IOUtils.toByteArray(is);
	        int pictureIdx = book.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
	        //is.close();
	        CreationHelper helper = book.getCreationHelper();
	        // Create the drawing patriarch.  This is the top level container for all shapes. 
	        Drawing<?> drawing = sheet.createDrawingPatriarch();
	        //add a picture shape
	        ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner of the picture,
	        //subsequent call of Picture#resize() will operate relative to it
	        anchor.setCol1(0);
	        anchor.setRow1(0);
	        Picture pict = drawing.createPicture(anchor, pictureIdx);
	        //auto-size picture relative to its top-left corner
	        pict.resize(3.0, 4.0);
	        
	    } catch (Exception e) {e.printStackTrace();}
		
		long totalBolsas = listCorte.stream().count(); 
		final AtomicInteger totalPzs = new AtomicInteger();
		listCorte.stream().forEach(x->{
			totalPzs.getAndAdd(x.getNumPlayeras());
		});
		LocalDate date = LocalDate.now();
		//NEXT TO IMAGE, INSERT INRMATION OF DATA AND WORKSHOP NAME
		String inf = "LISTA DE PLAYERAS PARA CONFECCIONAR\n"
				+ "FECHA: "+date+" - "+totalPzs+" PZS  - "+
				totalBolsas+" BOLSAS\n ENVIADO A: "+workshop.toUpperCase();
		
		cell = row.createCell(1);
		cell.setCellValue(inf);
		cell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0,4,1,8));
		sheet.autoSizeColumn(0);
	}
	
	//HEADER OF DOCUMENT WITH TITLES
	private void headerTitle() {
		// Create a row and put some cells in it. Rows are 0 based.
		Row row = sheet.createRow(5);
		// Create a cell and put a value in it.
		//CREATE CUSTOME STYLE CELL
		CellStyle style = createCellStyle(
				createFont((byte)12,IndexedColors.WHITE.getIndex(),false,true),
				IndexedColors.DARK_BLUE.getIndex(),
				FillPatternType.FINE_DOTS, 
				HorizontalAlignment.CENTER,
				VerticalAlignment.CENTER,
				false);
		
		//CREATE LIST TO TITLES
		List<String> listTitle = Arrays.asList(
				"ID","PARTIDA","FECHA","DIAMETRO","TALLA","COLOR","CANTIDAD","PLAYERAS BUENAS","PLAYERAS MALAS");

		final AtomicInteger count0 = new AtomicInteger(0);
		listTitle.stream().forEach(x->{
			Cell cell = row.createCell(count0.get());
			cell.setCellValue(x);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(count0.getAndIncrement());
		});
	}
	
	//CREATE LIST OF OBJECT IN EACH ROW
	private void createData() {
		//CREATE LIST OF OBJECTS CORTE AND HIS STYLE OF CELL		
		CellStyle styleCorte = createCellStyle(
				createFont((byte)11, IndexedColors.BLACK.getIndex(), true, false), 
						IndexedColors.WHITE.getIndex(), 
						FillPatternType.NO_FILL, 
						HorizontalAlignment.JUSTIFY, 
						VerticalAlignment.TOP, 
						false);
		
		final AtomicInteger count = new AtomicInteger(6);
		this.listCorte.stream().forEach(x->{
			Row newRow = sheet.createRow(count.getAndIncrement());
			
			Cell cell = newRow.createCell(0);
			cell.setCellValue(x.getId());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(0);
			
			cell = newRow.createCell(1);
			cell.setCellValue(x.getProducto().getPartida());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(1);
			
			cell = newRow.createCell(2);
			cell.setCellValue(x.getFecha().toString());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(2);
			
			cell = newRow.createCell(3);
			cell.setCellValue(x.getFecha().toString());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(3);
			
			cell = newRow.createCell(4);
			cell.setCellValue(x.getTalla().getNombre());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(4);
			
			cell =newRow.createCell(5);
			cell.setCellValue(x.getProducto().getColor().getNombre());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(5);
			
			cell = newRow.createCell(6);
			cell.setCellValue(x.getNumPlayeras());
			cell.setCellStyle(styleCorte);
			sheet.autoSizeColumn(6);
		});
	}
	
	//SAVE DOCUMENT 
	private void saveDocument() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName("Reporte_"+(new Date())+".xls");
		File file = fileChooser.showSaveDialog(null);
		if(file != null) {
			// Write the output to a file
			try (OutputStream fileOut = new FileOutputStream(file)) {
			    book.write(fileOut);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	//CREATE A CUSTOME STYLE FONT
	private Font createFont(byte tam,short color,boolean italic,boolean bold){
        Font font = book.createFont();
        font.setFontHeightInPoints((short)tam);
        font.setFontName("Dialog");
        font.setItalic(italic);
        font.setColor(color);
        font.setBold(bold);
        return font;
    }
	
	//CREATE A CUSTOME SLYTE CELL OF THE DOCUMENT
    private CellStyle createCellStyle(Font font,short borde,FillPatternType type,
            HorizontalAlignment halign,VerticalAlignment valign,boolean wrap
    ){
        CellStyle estilo = book.createCellStyle();
        estilo.setFont(font);
        estilo.setAlignment(halign);
        estilo.setVerticalAlignment(valign);
        estilo.setFillBackgroundColor(borde);
        estilo.setFillPattern(type);
        estilo.setWrapText(wrap);
        
        return estilo;
    }


}
