package xroigmartin.ecm.service.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueService {
	
	List<DomainValue> findAllDomainValueByDomainId(Long domainId);
	
	Page<DomainValue> findDomainValueByDomainIdPageable(Long domainId, Pageable pageable);
	
	Optional<DomainValue> findDomainValueById(Long id);
	Optional<DomainValue> findDomainValueByValueAndDomainId(Long value, Long DomainId);
	
	void addDomainValue(DomainValue domainValue);
	
	void saveDomainValue(DomainValue domainValue);
	
	void changeEnable(Long domainValueId);

}
