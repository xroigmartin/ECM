package xroigmartin.ecm.service.domain;

import java.util.List;
import java.util.Optional;

import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;
import xroigmartin.ecm.model.domain.Domain;

public interface DomainService {
	
	List<Domain> findAllDomains();
	Optional<Domain> findDomainById(Long domainId);
	Optional<Domain> findDomainByCodeDomain(String codeDomain);
	Domain addDomain(Domain domain) throws CodeDomainExistsException;
	Domain saveDomain(Domain domain);
	Domain changeEnable(Long domainId);
 
}
