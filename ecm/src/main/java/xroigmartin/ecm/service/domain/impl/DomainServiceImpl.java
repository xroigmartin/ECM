package xroigmartin.ecm.service.domain.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain findDomainById(Long domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain findDomainByCodeDomain(String codeDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain storeDomain(Domain domain) throws CodeDomainExistsException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain changeEnable(Long domainId) {
		// TODO Auto-generated method stub
		return null;
	}

}
