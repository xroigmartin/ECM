package xroigmartin.ecm.repository.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.model.domain.DomainValue;

@DataJpaTest
@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DomainValueRepositoryTest {

	@Autowired
	private DomainRepository domainRepository;
	@Autowired
	private DomainValueRepository domainValueRepository;

	private Domain domain;
	private DomainValue domainValue1, domainValue2;

	@BeforeEach
	public void setUp() {
		domain = new Domain("Test", "Domain for testing");
		domainRepository.save(domain);
		domainValue1 = new DomainValue("test", domain);
		domainValue2 = new DomainValue("test2", domain);
	}
	
	@DisplayName("Test: When find values of domain with domain object returns a list with its domain values")
	@Test
	public void whenFindValuesOfDomainWithItIdReturnList() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);

		List<DomainValue> domainValueList = domainValueRepository.findByDomainId(domain.getId());

		assertTrue(domainValueList.size() == 2);
	}

	@DisplayName("Test: When find value of domain with domain object returns empty list because not exists values for it domain")
	@Test
	public void whenFindValuesOfDomainWithItIdReturnEmptyList() {
		List<DomainValue> domainValueList = domainValueRepository.findByDomainId(domain.getId());

		assertTrue(domainValueList.isEmpty());
	}

	@DisplayName("Test: When find value of domain with it id return info for domain value")
	@Test
	public void whenFindValueOfDomainWithId() {
		domainValueRepository.save(domainValue1);

		Optional<DomainValue> domainValue = domainValueRepository.findById(domainValue1.getId());

		assertTrue(domainValue.isPresent());
	}

	@DisplayName("Test: When find value of domain with it id no return anything")
	@Test
	public void whenFindValueOfDomainWithIdNoReturnAnything() {
		Optional<DomainValue> domainValue = domainValueRepository.findById(1L);

		assertTrue(domainValue.isEmpty());
	}
	
	@DisplayName("Test: It should find domain value with it value and domain id")
	@Test
	public void shouldFindDomainValueWithItValueAndDomainID() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		Optional<DomainValue> domainValue = domainValueRepository.findByValueAndDomainId("test", domain.getId());
		
		assertTrue(domainValue.isPresent());
	}
	
	@DisplayName("Test: It shouldn't find domain value with it value and domain id")
	@Test
	public void shouldNotFindDomainValueWithItValueAndDomainID() {
		Optional<DomainValue> domainValue = domainValueRepository.findByValueAndDomainId("test", domain.getId());
		
		assertTrue(domainValue.isEmpty());
	}
	
	@DisplayName("Test: It should find any domain value that contains the value text and domain id")
	@Test
	public void shouldFindDomainValueContainsValueTextAndDomainID() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByValueContainingAndDomainId("test", domain.getId());
		
		assertTrue(!domainValueList.isEmpty() && domainValueList.size() == 2);
	}
	
	@DisplayName("Test: It shouldn't find any domain value that contains the value text and domain id")
	@Test
	public void shouldNotFindDomainValueContainsValueTextAndDomainID() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByValueContainingAndDomainId("fail", domain.getId());
		
		assertTrue(domainValueList.isEmpty());
	}

	@DisplayName("Test: Should save new domain value")
	@Test
	public void shouldSaveNewDomainValue() {
		domainValueRepository.save(domainValue1);
	}

}
