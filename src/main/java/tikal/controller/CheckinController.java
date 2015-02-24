package tikal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tikal.dao.Dao;
import tikal.model.Checkin;


@RestController
public class CheckinController {
	
	private final Dao dao;
	
	@Autowired
	public CheckinController(Dao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="/checkin",method=RequestMethod.POST)
	public Object checkin(@RequestBody final Checkin checkin) {
		checkin.setTimestamp(System.currentTimeMillis());
		System.out.println(checkin);
		dao.insertChecking(checkin);
		return true;
	}
}
