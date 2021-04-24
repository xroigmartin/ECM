package xroigmartin.ecm.repository.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueRepository extends JpaRepository<DomainValue, Long> {

	List<DomainValue> findAllValuesByDomainId(Long domainId);
	Page<DomainValue> findAllValuesByDomainId(Long domainId, Pageable pageable);
	
	List<DomainValue> findValuesByValue(String value);
	Page<DomainValue> findValuesByValue(String value, Pageable pageable);
	
	Optional<DomainValue> findValueByValueAndDomainId(String value, Long domainId);

	List<DomainValue> findValuesByValueContaining(String value);
	Page<DomainValue> findValuesByValueContaining(String value, Pageable pageable);
}
