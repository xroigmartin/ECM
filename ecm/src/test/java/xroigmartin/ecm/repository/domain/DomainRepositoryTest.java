package xroigmartin.ecm.repository.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

@DataJpaTest
@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DomainRepositoryTest {

	@Autowired
	private DomainRepository domainRepository;
	
	private Domain domain1, domain2;
	
	@BeforeEach
	public void setUp() {
		domain1 = new Domain("test1", "test1");
		domain2 = new Domain("test2", "test2", false);
	}
	
	@DisplayName("Test: When find domains and not exists anyone return empty list")
	@Test
	public void WhenFindDomainsAndNotExistsAnyoneReturnEmptyList() {
		List<Domain> domainList = domainRepository.findAll();
		
		assertTrue(domainList.isEmpty());
	}
	
	@DisplayName("When find domains and exists in database then return list with them")
	@Test
	public void whenFindDomainAndExistsReturnList() {
		domainRepository.save(domain1);
		domainRepository.save(domain2);
		
		List<Domain> domainList = domainRepository.findAll();
		
		assertFalse(domainList.isEmpty());
	}
	
	@DisplayName("Store domain in database")
	@Test
	public void storeDomainInDatabase() {
		domainRepository.save(domain1);
	}
	
	@DisplayName("Test: Should find domain by id")
	@Test
	public void shouldFindDomainById() {
		domainRepository.save(domain1);
		
		Optional<Domain> domainOptional = domainRepository.findById(domain1.getDomainId());
		
		assertTrue(domainOptional.isPresent());
	}
	
	@DisplayName("Test: Shouldn't find domain by id")
	@Test
	public void shouldNotFindDomainById() {
		Optional<Domain> domainOptional = domainRepository.findById(1L);
		
		assertTrue(domainOptional.isEmpty());
	}
	
	@DisplayName("Test: Should find domain by code domain")
	@Test
	public void shouldFindDomainByCodeDomain() {
		domainRepository.save(domain1);
		
		Optional<Domain> domainOptional = domainRepository.findByCodeDomain(domain1.getCodeDomain());
		
		assertTrue(domainOptional.isPresent());
	}
	
	@DisplayName("Test: Shouldn't find domain by code domain")
	@Test
	public void shouldNotFindDomainByCodeDomain() {
		Optional<Domain> domainOptional = domainRepository.findByCodeDomain("Test1");
		
		assertTrue(domainOptional.isEmpty());
	}
}
