package multiple.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multiple.db.h2.h2dbrepo.EpmRepo;
import multiple.db.mysql.mysqlrepo.AddRepo;

@Service
public class Services {
	
	@Autowired
	private AddRepo addRepo;
	
	@Autowired
	private EpmRepo epmRepo;
	
	public Services(AddRepo addRepo,EpmRepo epmRepo) {
		this.addRepo=addRepo;
		this.epmRepo=epmRepo;
	}
	

}
