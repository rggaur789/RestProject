package com.org.goibibo.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.org.goibibo.service.ReadCSVService;




@Controller
public class RestApiController {


@Autowired
ReadCSVService rcs;
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody  String one(@PathVariable("name") String name) {

		return  "Response:"+rcs.excelType(name).toString();
		
		
	}


	
	

}
