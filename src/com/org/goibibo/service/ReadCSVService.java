package com.org.goibibo.service;

import java.io.File;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component
public interface ReadCSVService {
	
	
	public JsonObject readExcel(String name,File path,String file);
	public JsonObject excelType(String name);

}
