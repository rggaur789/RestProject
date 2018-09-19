package com.org.goibibo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServlet;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.JsonObject;

@Service
public class ReadCSVServiceImpl implements ReadCSVService {

	public JsonObject excelType(String name) {
		JsonObject json = new JsonObject();// Creating the Json object
		String path = this.getClass().getClassLoader().getResource("")
				.getPath();// Getting the current path

		String extension = "";
		String filename = "";

		URL resource = ReadCSVServiceImpl.class.getClassLoader().getResource(
				"//Excel//Corpus.CSV.xlsx");
		
		File file = new File(resource.getFile());
		if (file.canRead()) {
			filename = file.getName();// Storing filename
		
   
			extension = filename.substring(filename.lastIndexOf("."));//getting file extension
			
			json = readExcel(name, file, extension); //Calling method readexcel which computes and generate json

		} else {
                       
                        json.addProperty("error", "File Not Found");
		}

		return json;
	}

	public JsonObject readExcel(String key, File file, String fileextensiontype) {
		JsonObject innerjson = new JsonObject();
		// If excel file extension is csv
		if (fileextensiontype.equalsIgnoreCase("csv")) {
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			try {

				br = new BufferedReader(new FileReader(file));
				br.readLine();
				while ((line = br.readLine()) != null) {

					// use comma as separator
					String[] data = line.split(cvsSplitBy);

					if (data[0].equals(key)) {

						innerjson.addProperty("key", data[0]);
						innerjson.addProperty("value", data[1]);
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// uf excel file extension is other then cxv
		else {
			try {

				FileInputStream inputStream = new FileInputStream(file);// Reading
																		// file
																		// using
																		// file
																		// inputstream
				// object

				Workbook wrk = WorkbookFactory.create(inputStream);
				DataFormatter df = new DataFormatter();

				// Iteration for number of sheets in excel
				for (int i = 0; i < wrk.getNumberOfSheets(); i++) {
					Sheet sheet = wrk.getSheetAt(i);
					// Iteration for number of rows in excel
					for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

						Row row = sheet.getRow(rowIndex);// creating row object

						// Checking whether cell value is not empty and matching
						// the key value in the excel
						if (df.formatCellValue(row.getCell(0))
								.equalsIgnoreCase(key)
								&& df.formatCellValue(row.getCell(0)) != "") {

							// Creating json in the format
							// ex:{"key":"one",value:"1"}
							innerjson.addProperty("key",
									df.formatCellValue(row.getCell(0)));
							innerjson.addProperty("value",
									df.formatCellValue(row.getCell(1)));

							break;

						}
					}
				}
				// checking if key was not found in the excel i.e. json size is
				// zero
				if (innerjson.size() == 0) {
					innerjson.addProperty("error", "key Not Found");// creating
																	// error
																	// json

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return innerjson;
	}

}
