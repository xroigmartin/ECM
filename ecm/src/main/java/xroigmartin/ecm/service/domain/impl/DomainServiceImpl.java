package xroigmartin.ecm.service.domain.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;
import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.repository.domain.DomainRepository;
import xroigmartin.ecm.service.domain.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepository;

	@Override
	public List<Domain> findAllDomains() {
		return domainRepository.findAll();
	}

	@Override
	public Optional<Domain> findDomainById(Long domainId) {
		return domainRepository.findById(domainId);
	}

	@Override
	public Optional<Domain> findDomainByCodeDomain(String codeDomain) {
		return domainRepository.findByCodeDomain(codeDomain);
	}		
	
	@Override
	public Domain addDomain(Domain domain) throws CodeDomainExistsException {
		Domain findDomain = domainRepository.findByCodeDomain(domain.getCodeDomain()).orElse(null);
		
		if(findDomain != null) {
			throw new CodeDomainExistsException("Exists domain with same code domain");
		}
		
		return this.saveDomain(domain);
	}

	@Override
	public Domain saveDomain(Domain domain) {
		return domainRepository.save(domain);
	}

	@Override
	public Domain changeEnable(Long domainId) {
		Domain domain = domainRepository.findById(domainId).orElse(null);

		if (domain != null) {
			domain.changeEnable();
			domainRepository.save(domain);
		}

		return domain;
	}

}
