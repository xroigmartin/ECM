package xroigmartin.ecm.service.domain.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.exceptions.domain.DomainValueExistsException;
import xroigmartin.ecm.model.domain.DomainValue;
import xroigmartin.ecm.repository.domain.DomainValueRepository;
import xroigmartin.ecm.service.domain.DomainValueService;

public class DomainValueServiceImpl implements DomainValueService {

	@Autowired
	private DomainValueRepository domainValueRepository;
	
	@Override
	public List<DomainValue> findAllDomainValueByDomainId(Long domainId) {
		return domainValueRepository.findByDomainId(domainId);
	}

	@Override
	public Page<DomainValue> findDomainValueByDomainIdPageable(Long domainId, Pageable pageable) {
		return domainValueRepository.findByDomainId(domainId, pageable);
	}

	@Override
	public Optional<DomainValue> findDomainValueById(Long id) {
		return domainValueRepository.findByValueId(id);
	}

	@Override
	public Optional<DomainValue> findDomainValueByValueAndDomainId(String value, Long domainId) {
		return domainValueRepository.findByValueAndDomainId(value, domainId);
	}

	@Override
	public void addDomainValue(DomainValue domainValue) throws DomainValueExistsException {
		
		Optional<DomainValue> existsValue = this.findDomainValueByValueAndDomainId(domainValue.getValue(), domainValue.getDomain().getId());
		
		if(existsValue.isPresent()) {
			throw new DomainValueExistsException("Value of domain exists in domain");
		}
		
		this.saveDomainValue(domainValue);
	}

	@Override
	public void saveDomainValue(DomainValue domainValue) {
		domainValueRepository.save(domainValue);
	}

	@Override
	public void changeEnable(Long domainValueId) {
		
		Optional<DomainValue> value = this.findDomainValueById(domainValueId);
		
		if(value.isPresent()) {
			value.get().changeEnable();
		}
		
		this.saveDomainValue(value.get());
		
		
	}

}
