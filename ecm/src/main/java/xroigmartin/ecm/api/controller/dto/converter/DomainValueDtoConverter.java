package xroigmartin.ecm.api.controller.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xroigmartin.ecm.api.controller.dto.domain.value.DomainValueDto;
import xroigmartin.ecm.model.domain.DomainValue;

@Component
public class DomainValueDtoConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public DomainValueDto converToDto(DomainValue domainValue) {
		return modelMapper.map(domainValue, DomainValueDto.class);
	}
}
