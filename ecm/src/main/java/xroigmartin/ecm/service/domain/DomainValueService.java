package xroigmartin.ecm.service.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueService {

	List<DomainValue> valuesOfDomain(Long domainId);
	Page<DomainValue> valuesOfDomain(Long domainId, Pageable pageable);
	
	Optional<DomainValue> findById(Long domainValueId);
	
	List<DomainValue> findValuesByValue(String value);
	Page<DomainValue> findValuesByValue(String value, Pageable pageable);
	
	Optional<DomainValue> findValueOfDomain(String value, Long domainId);
	
	List<DomainValue> findValuesByValueContaining(String value);
	Page<DomainValue> findValuesByValueContaining(String value, Pageable pageable);
	
}

