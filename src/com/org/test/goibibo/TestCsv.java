package com.org.test.goibibo;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.JsonObject;
import com.org.goibibo.service.ReadCSVService;


@ContextConfiguration(locations = "classpath:RestProject-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCsv {
	
	@Autowired
	ReadCSVService rsc;
	

	@Test
   public void testjson()
    {
		JsonObject json=new JsonObject();
		JsonObject expectedjson=new JsonObject();
		String name="one";
       
		
			   json=rsc.excelType(name);
				
			   expectedjson.addProperty("key","one");
				expectedjson.addProperty("value","1");
			
			
		
		Assert.assertEquals(expectedjson,json);
	
    }
	

}
