package xroigmartin.ecm.service.domain.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import xroigmartin.ecm.model.domain.DomainValue;
import xroigmartin.ecm.repository.domain.DomainValueRepository;
import xroigmartin.ecm.service.domain.DomainValueService;

@Service
public class DomainValueServiceImpl implements DomainValueService {

	@Autowired
	private DomainValueRepository domainValueRepository;

	@Override
	public List<DomainValue> valuesOfDomain(Long domainId) {
		return domainValueRepository.findAllValuesByDomainId(domainId);
	}

	@Override
	public Page<DomainValue> valuesOfDomain(Long domainId, Pageable pageable) {
		return domainValueRepository.findAllValuesByDomainId(domainId, pageable);
	}

	@Override
	public Optional<DomainValue> findValueOfDomain(String value, Long domainId) {
		return domainValueRepository.findValueByValueAndDomainId(value, domainId);
	}
}
