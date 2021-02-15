package xroigmartin.ecm.repository.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueRepository extends JpaRepository<DomainValue, Long>{
	
	List<DomainValue> findByDomainId(Long domainId);
	
	Optional<DomainValue> findByValueAndDomainId(String value, Long domainId);
}
