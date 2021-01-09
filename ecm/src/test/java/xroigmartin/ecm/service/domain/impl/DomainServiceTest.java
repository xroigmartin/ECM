package xroigmartin.ecm.service.domain.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;
import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.repository.domain.DomainRepository;

@ExtendWith(MockitoExtension.class)
class DomainServiceTest {

	@Mock
	private DomainRepository domainRepository;
	
	@InjectMocks
	private DomainServiceImpl domainService;
	
	private Domain domain1, domain2;
	
	@BeforeEach
	public void setUp() {
		domain1 = new Domain("test1", "test1");
		domain2 = new Domain("test2", "test2", false);
	}
	
	@DisplayName("Test: When not exists domains return empty list")
	@Test
	public void whenNotExistsDomainsReturnEmptyList() {
		when(domainRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<Domain> domainList = domainService.findAllDomains();
		
		assertTrue(domainList.isEmpty());
		
	}
	
	@DisplayName("Test: When exists domains return list with them")
	@Test
	public void whenExistsDomainReturnListWithThem() {
		List<Domain> domainListMock = new ArrayList<>();
		domainListMock.add(domain1);
		domainListMock.add(domain2);
		
		when(domainRepository.findAll()).thenReturn(domainListMock);
		
		List<Domain> domainList = domainService.findAllDomains();
		
		assertTrue(domainList.size() > 0);
	}
	
	@DisplayName("Test: When search domain by id and this not exists return null")
	@Test
	public void whenSearchDomainByIdAndThisNotExistsReturnNull() {
		when(domainRepository.findById(1L)).thenReturn(Optional.empty());
		
		Optional<Domain> domain = domainService.findDomainById(1L);
		
		assertTrue(domain.isEmpty());
		
	}
	
	@DisplayName("Test: When search domain by id and this exists,it is returned")
	@Test
	public void whenSearchDomainByIdAndThisExistsItIsReturned() {
		when(domainRepository.findById(1L)).thenReturn(Optional.of(domain1));
		
		Optional<Domain> domain = domainService.findDomainById(1L);
		
		assertTrue(domain.isPresent());
		assertEquals("test1", domain.get().getCodeDomain());
	}
	
	@DisplayName("Test: When search domain by code domain and not exists then return null")
	@Test
	public void whenSearchDomainByCodeDomainAndNotExistsThenReturnNull() {
		when(domainRepository.findByCodeDomain("test")).thenReturn(Optional.empty());
		
		Optional<Domain> domain = domainService.findDomainByCodeDomain("test");
		
		assertTrue(domain.isEmpty());
	}
	
	@DisplayName("Test: When search domain by code domain and exists then return domain info")
	@Test
	public void whenSearchDomainByCodeDomainAndExistsThenReturnDomainInfo() {
		when(domainRepository.findByCodeDomain("test1")).thenReturn(Optional.of(domain1));
		
		Optional<Domain> domain = domainService.findDomainByCodeDomain("test1");
		
		assertTrue(domain.isPresent());
		assertEquals("test1", domain.get().getCodeDomain());
		
	}
	
	@DisplayName("Test: Should save the domain")
	@Test
	public void shouldSaveDomain() {
		when(domainRepository.save(domain1)).thenReturn(domain1);
			
		domainService.saveDomain(domain1);
		
		verify(domainRepository, times(1)).save(domain1);
	}
	
	@DisplayName("Test: Should add new domain")
	@Test
	public void shouldAddNewDomain() {
		when(domainService.saveDomain(domain1)).thenReturn(domain1);
		when(domainRepository.findByCodeDomain(domain1.getCodeDomain())).thenReturn(Optional.empty());
		
		domainService.addDomain(domain1);
		
		verify(domainRepository, times(1)).save(domain1);
	}
	
	@DisplayName("Test: Not should save the domain when exists other domain with same code domain")
	@Test
	public void notShouldSaveTheDomainWhenExistsOtherDomainWithSameCodeDomain() {
		when(domainRepository.findByCodeDomain("test1")).thenReturn(Optional.of(domain1));
		
		assertThrows(CodeDomainExistsException.class, () -> {
			domainService.addDomain(domain1);
		});
	}
	
	@DisplayName("Test: Should change state of doamin to enable when this domain is disable")
	@Test
	public void shouldChangeStateOfDomainToEnableWhenThisDomainIsDisable() {
		when(domainRepository.findById(2L)).thenReturn(Optional.of(domain2));
		when(domainRepository.save(domain2)).thenReturn(domain2);
		
		Domain domain = domainService.changeEnable(2L);
		
		assertTrue(domain.isEnable());
		
	}
	
	@DisplayName("Test: Should change state of doamin to disable when this domain is enable")
	@Test
	public void shouldChangeStateOfDomainToDisableWhenThisDomainIsEnable() {
		when(domainRepository.findById(1L)).thenReturn(Optional.of(domain1));
		when(domainRepository.save(domain1)).thenReturn(domain1);
		
		Domain domain = domainService.changeEnable(1L);
		
		assertFalse(domain.isEnable());
		
	}
	
}
