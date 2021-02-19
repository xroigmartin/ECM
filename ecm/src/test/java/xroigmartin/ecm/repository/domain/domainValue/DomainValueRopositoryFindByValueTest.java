package xroigmartin.ecm.repository.domain.domainValue;

import org.junit.jupiter.api.Test;

public class DomainValueRopositoryFindByValueTest extends DomainValueRepositoryTest{

	@Test
	public void shouldFindValueOfDomainWithValueAndReturnListOfValues() {
		domainValueRepository.save(domainValue1);
		domainValueRepository.save(domainValue2);
		
		List<DomainValue> domainValueList = domainValueRepository.findByValue
	}
	
}
