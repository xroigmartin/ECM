package xroigmartin.ecm.service.domain;

import java.util.List;

import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	
	List<Domain> findAllDomains();
	Domain findDomainById(Long domainId);
	Domain findDomainByCodeDomain(String codeDomain);
	Domain storeDomain(Domain domain);
	Domain changeEnable(Long domainId);
 
}
