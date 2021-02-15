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
	public void whenFindValuesOfDomainWithDomainObjectReturnList() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);

		List<DomainValue> domainValueList = domainValueRepository.findAllDomainValueByDomain(domain);

		assertTrue(domainValueList.size() == 2);
	}

	@DisplayName("Test: When find value of domain with domain object returns empty list because not exists values for it domain")
	@Test
	public void whenFindValuesOfDomainWithDomainObjectReturnEmptyList() {
		List<DomainValue> domainValueList = domainValueRepository.findAllDomainValueByDomain(domain);

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

	@DisplayName("Test: Should save new domain value")
	@Test
	public void shouldSaveNewDomainValue() {
		domainValueRepository.save(domainValue1);
	}

}
