package xroigmartin.ecm.repository.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.DomainValue;

public interface DomainValueRepository extends JpaRepository<DomainValue, Long> {

}
