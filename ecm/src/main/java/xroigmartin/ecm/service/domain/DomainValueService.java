package xroigmartin.ecm.service.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.exceptions.domain.DomainValueExistsException;
import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueService {
	
	List<DomainValue> findAllDomainValueByDomainId(Long domainId);
	
	Page<DomainValue> findDomainValueByDomainIdPageable(Long domainId, Pageable pageable);
	
	Optional<DomainValue> findDomainValueById(Long id);
	Optional<DomainValue> findDomainValueByValueAndDomainId(String value, Long domainId);
	
	void addDomainValue(DomainValue domainValue) throws DomainValueExistsException;
	
	void saveDomainValue(DomainValue domainValue);
	
	void changeEnable(Long domainValueId);

}
