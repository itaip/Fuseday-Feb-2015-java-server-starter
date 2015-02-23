package tikal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tikal.model.Checkin;


@RestController
public class CheckinController {
	
	@RequestMapping(value="/checkin",method=RequestMethod.POST)
	public Object checkin(@RequestBody final Checkin checkin) {
		checkin.setTimestamp(System.currentTimeMillis());
		System.out.println(checkin);
		return true;
	}
}
