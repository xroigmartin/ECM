package xroigmartin.ecm.service.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xroigmartin.ecm.repository.domain.DomainValueRepository;
import xroigmartin.ecm.service.domain.DomainValueService;

@Service
public class DomainValueServiceImpl implements DomainValueService {

	@Autowired
	private DomainValueRepository domainValueRepository;
}
