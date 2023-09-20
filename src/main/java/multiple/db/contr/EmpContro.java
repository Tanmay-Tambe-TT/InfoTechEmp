package multiple.db.contr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import multiple.db.h2.h2dbentity.EmpEntity;
import multiple.db.h2.h2dbrepo.EpmRepo;
import multiple.db.mysql.mysqlentity.AddEntity;
import multiple.db.mysql.mysqlrepo.AddRepo;


@RestController
@RequestMapping("/Employee")
public class EmpContro {

	@Autowired
	private AddRepo addRepo;
	
	@Autowired
	EpmRepo epmRepo;

	@PostMapping("/addEntity")
	public void add(@RequestBody AddEntity addEntity) {
		addRepo.save(addEntity);

	}

	@PostMapping("/empEntity")
	public void sa(@RequestBody EmpEntity empEntity) {
		epmRepo.save(empEntity);

	}
	
	

}
