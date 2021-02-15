package xroigmartin.ecm.service.domain.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.model.domain.DomainValue;
import xroigmartin.ecm.service.domain.DomainValueService;

public class DomainValueServiceImpl implements DomainValueService {

	@Override
	public List<DomainValue> findAllDomainValueByDomainId(Long domainId) {
		return null;
	}

	@Override
	public Page<DomainValue> findDomainValueByDomainIdPageable(Long domainId, Pageable pageable) {
		return null;
	}

	@Override
	public Optional<DomainValue> findDomainValueById(Long id) {
		return null;
	}

	@Override
	public Optional<DomainValue> findDomainValueByValueAndDomainId(Long value, Long DomainId) {
		return null;
	}

	@Override
	public void addDomainValue(DomainValue domainValue) {

	}

	@Override
	public void saveDomainValue(DomainValue domainValue) {

	}

	@Override
	public void changeEnable(Long domainValueId) {
		// TODO Auto-generated method stub

	}

}
