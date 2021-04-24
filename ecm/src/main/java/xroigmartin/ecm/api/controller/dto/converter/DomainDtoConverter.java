package xroigmartin.ecm.api.controller.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xroigmartin.ecm.api.controller.dto.domain.DomainDto;
import xroigmartin.ecm.model.domain.Domain;

@Component
public class DomainDtoConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public DomainDto converToDto(Domain domain) {
		return modelMapper.map(domain, DomainDto.class);
	}
}
