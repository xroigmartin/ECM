package xroigmartin.ecm.repository.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xroigmartin.ecm.model.domain.Domain;

public interface DomainRepository extends JpaRepository<Domain, Long>{

	Optional<Domain> findByCodeDomain(String codeDomain);
}
