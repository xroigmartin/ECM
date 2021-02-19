package xroigmartin.ecm.repository.domain.domainValue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import xroigmartin.ecm.model.domain.DomainValue;

public class DomainValueRepositorFindByDomainId extends DomainValueRepositoryTest{
	
	@Test
	public void whenFindValuesOfDomainWithItIdReturnList() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);

		List<DomainValue> domainValueList = domainValueRepository.findByDomainId(domain.getId());

		assertTrue(domainValueList.size() == 2);
	}

	@Test
	public void whenFindValuesOfDomainWithItIdReturnEmptyList() {
		List<DomainValue> domainValueList = domainValueRepository.findByDomainId(domain.getId());

		assertTrue(domainValueList.isEmpty());
	}
	
	@Test
	public void whenFindValuesOfDomainWithItIdAndValuesAreEnabledReturnListOfValues() {
		domainValue1.changeEnable(false);
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByDomainIdAndEnableTrue(domain.getId());
		
		assertTrue(!domainValueList.isEmpty() && domainValueList.size() == 1);
	}
	
	@Test
	public void shouldReturnEmptyListWhenNotExistsValueForDomainEnabled() {
		domainValue1.changeEnable(false);
		domainValue2.changeEnable(false);
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByDomainIdAndEnableTrue(domain.getId());
		
		assertTrue(domainValueList.isEmpty());
	}
	
	@Test
	public void shouldReturnPageableListWhenFindValuesWithDomainId() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValuePageable = domainValueRepository.findByDomainId(domain.getId(), pageable);
		
		assertThat(domainValuePageable.getContent().size()).isEqualTo(1);
		assertThat(domainValuePageable.getTotalPages() == 2);
	}
	
	@Test
	public void shouldReturnEmptyPageableListWhenNotExistsValuesForDomainId() {
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValuePageable = domainValueRepository.findByDomainId(domain.getId(), pageable);
		
		assertThat(domainValuePageable.isEmpty());
	}
	
	@Test
	public void shouldReturnPageableListWhenFindEnabledValuesWithDomainId() {
		domainValue1.changeEnable(false);
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValuePageable = domainValueRepository.findByDomainIdAndEnableTrue(domain.getId(), pageable);
		
		assertThat(domainValuePageable.getContent().size()).isEqualTo(1);
		assertThat(domainValuePageable.getTotalPages() == 1);
		
	}
	
	@Test
	public void shouldReturnEmptyPageableListWhenNotExistsEnableValuesWithDomainId() {
		domainValue1.changeEnable(false);
		domainValue2.changeEnable(false);
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		Pageable pageable = PageRequest.of(0,1);
		
		Page<DomainValue> domainValuePageable = domainValueRepository.findByDomainId(domain.getId(), pageable);
		
		assertThat(domainValuePageable.isEmpty());
	}

}
