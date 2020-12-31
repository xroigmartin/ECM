package xroigmartin.ecm.service.domain;

import java.util.List;

import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;
import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	
	List<Domain> findAllDomains();
	Domain findDomainById(Long domainId);
	Domain findDomainByCodeDomain(String codeDomain);
	Domain addDomain(Domain domain) throws CodeDomainExistsException;
	Domain saveDomain(Domain domain);
	Domain changeEnable(Long domainId);
 
}
