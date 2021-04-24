package xroigmartin.ecm.repository.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueRepository extends JpaRepository<DomainValue, Long>{
	
	List<DomainValue> findByDomainId(Long domainId);
	Page<DomainValue> findByDomainId(Long domainId, Pageable pageable);
	List<DomainValue> findByDomainIdAndEnableTrue(Long domainId);
	Page<DomainValue> findByDomainIdAndEnableTrue(Long domainId, Pageable pageable);
	
	Optional<DomainValue> findByValueId(Long id);
	Optional<DomainValue> findByValueIdAndEnableTrue(Long id);
	
	List<DomainValue> findByValue(String value);
	Page<DomainValue> findByValue(String value, Pageable pageable);
	List<DomainValue> findByValueAndEnableTrue(String value);
	Page<DomainValue> findByValueAndEnableTrue(String value, Pageable pageable);
	
	List<DomainValue> findByValueContaining(String value);
	Page<DomainValue> findByValueContaining(String value, Pageable pageable);
	List<DomainValue> findByValueContainingAndEnableTrue(String value);
	Page<DomainValue> findByValueContainingAndEnableTrue(String value, Pageable pageable);
	
	Optional<DomainValue> findByValueAndDomainId(String value, Long domainId);
	Optional<DomainValue> findByValueAndDomainIdAndEnableTrue(String value, Long domainId);

	List<DomainValue> findByValueContainingAndDomainId(String value, Long domainId);
	Page<DomainValue> findByValueContainingAndDomainId(String value, Long domainId, Pageable pageable);
	List<DomainValue> findByValueContainingAndDomainIdAndEnableTrue(String value, Long domainId);
	Page<DomainValue> findByValueContainingAndDomainIdAndEnableTrue(String value, Long domainId, Pageable pageable);
	
}
