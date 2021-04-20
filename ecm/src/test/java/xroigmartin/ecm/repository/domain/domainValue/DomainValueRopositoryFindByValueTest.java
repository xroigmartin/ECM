package xroigmartin.ecm.repository.domain.domainValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.model.domain.DomainValue;

public class DomainValueRopositoryFindByValueTest extends DomainValueRepositoryTest{

	@Test
	public void shouldFindValueOfDomainWithValueAndReturnListOfValues() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByValue("test");
		
		assertFalse(domainValueList.isEmpty());
		assertTrue(domainValueList.size() == 1);
	}
	
	@Test
	public void shouldNotFindValueOfDomainWithValueAndReturnEmptyList() {
		List<DomainValue> domainValueList = domainValueRepository.findByValue("test");
		
		assertTrue(domainValueList.isEmpty());
	}
	
	@Test
	public void shouldFindValueOfDomainWithValueAndReturnPageableListOfValues() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValueListPageable = domainValueRepository.findByValue("test", pageable);
		
		assertFalse(domainValueListPageable.isEmpty());
		assertEquals(domainValueListPageable.getContent().size(),1);
	}
	
	@Test
	public void shouldNotFindValueOfDomainWithValueAndReturnEmptyPageableList() {
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValueListPageable = domainValueRepository.findByValue("test", pageable);
		
		assertTrue(domainValueListPageable.isEmpty());
	}
}
