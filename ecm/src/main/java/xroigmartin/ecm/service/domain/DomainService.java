package xroigmartin.ecm.service.domain;

import java.util.List;

import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	
	List<Domain> findAllDomains();
	Domain findDomainById();
	Domain findDomainByCodeDomain();
	Domain storeDomain();
	Domain changeEnable();
 
}
