package xroigmartin.ecm.service.domain;

import java.util.List;

import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;
import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	
	List<Domain> findAllDomains();
	Domain findDomainById(Long domainId);
	Domain findDomainByCodeDomain(String codeDomain);
	Domain storeDomain(Domain domain) throws CodeDomainExistsException;
	Domain changeEnable(Long domainId);
 
}
