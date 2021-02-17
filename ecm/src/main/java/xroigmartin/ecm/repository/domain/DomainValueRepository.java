package xroigmartin.ecm.repository.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueRepository extends JpaRepository<DomainValue, Long>{
	
	List<DomainValue> findByDomainId(Long domainId);
	List<DomainValue> findByDomainId(Long domainId, Pageable pageable);
	List<DomainValue> findByDomainIdAndIsEnable(Long domainId);
	List<DomainValue> findByDomainIdAndIsEnable(Long domainId, Pageable pageable);
	
	Optional<DomainValue> findByValueAndDomainId(String value, Long domainId);
	Optional<DomainValue> findByValueAndDomainId(String value, Long domainId, Pageable pageable);
	Optional<DomainValue> findByValueAndDomainIdAndIsEnable(String value, Long domainId);
	Optional<DomainValue> findByValueAndDomainIdAndIsEnable(String value, Long domainId, Pageable pageable);

	List<DomainValue> findByValueContainingAndDomainId(String value, Long domainId);
	List<DomainValue> findByValueContainingAndDomainId(String value, Long domainId, Pageable pageable);
	List<DomainValue> findByValueContainingAndDomainIdAndIsEnable(String value, Long domainId);
	List<DomainValue> findByValueContainingAndDomainIdAndIsEnable(String value, Long domainId, Pageable pageable);
	
	List<DomainValue> findByValueContaining(String value);
	List<DomainValue> findByValueContaining(String value, Pageable pageable);
	List<DomainValue> findByValueContainingIsEnable(String value);
	List<DomainValue> findByValueContainingIsEnable(String value, Pageable pageable);
}
