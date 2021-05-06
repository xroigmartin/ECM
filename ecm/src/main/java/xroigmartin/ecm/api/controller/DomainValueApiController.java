package xroigmartin.ecm.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import xroigmartin.ecm.api.controller.dto.converter.DomainValueDtoConverter;
import xroigmartin.ecm.api.controller.dto.domain.value.AddDomainValueDto;
import xroigmartin.ecm.api.controller.dto.domain.value.DomainValueDto;
import xroigmartin.ecm.api.controller.dto.domain.value.EditDomainValueDto;
import xroigmartin.ecm.exceptions.api.domain.DomainNotFoundException;
import xroigmartin.ecm.exceptions.api.domain.value.DomainValueExistsApiException;
import xroigmartin.ecm.exceptions.api.domain.value.DomainValueNotFoundException;
import xroigmartin.ecm.exceptions.domain.DomainValueExistsException;
import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.model.domain.DomainValue;
import xroigmartin.ecm.service.domain.DomainService;
import xroigmartin.ecm.service.domain.DomainValueService;
import xroigmartin.ecm.utils.pagination.PaginationLinkUtils;

@RestController
@RequestMapping("/api/domain/value")
public class DomainValueApiController {

	@Autowired
	private DomainValueService domainValueService;
	
	@Autowired
	private DomainService domainService;
	
	@Autowired
	private DomainValueDtoConverter domainValueDtoConverter;

	@Autowired
	private PaginationLinkUtils paginationLinkUtils;

	@GetMapping("/domainId/{domainId}")
	public ResponseEntity<Page<DomainValueDto>> valuesOfDomain(@PathVariable Long domainId,
															Pageable pageable,
															HttpServletRequest request) {

		Page<DomainValue> valuesOfDomainList = domainValueService.valuesOfDomain(domainId, pageable);

		if (valuesOfDomainList.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Page<DomainValueDto> valuesOfDomainDto = valuesOfDomainList.map(domainValueDtoConverter::converToDto);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok()
					.header("link", paginationLinkUtils.createLinkHeader(valuesOfDomainDto, uriBuilder))
					.body(valuesOfDomainDto);
		}
	}

	@GetMapping("/{value}/domainId/{domainId}")
	public DomainValueDto findValuesByValueAndDomain(@PathVariable String value, @PathVariable Long domainId) {

		Optional<DomainValue> domainValue = domainValueService.findValueOfDomain(value, domainId);

		if (domainValue.isEmpty()) {
			throw new DomainValueNotFoundException(value, domainId);
		}

		return domainValueDtoConverter.converToDto(domainValue.get());

	}

	@GetMapping("/{value}")
	public ResponseEntity<Page<DomainValueDto>> findValuesByValue(@PathVariable String value, 
																	Pageable pageable,
																	HttpServletRequest request) {

		Page<DomainValue> valuesPages = domainValueService.findValuesByValue(value, pageable);

		if (valuesPages.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Page<DomainValueDto> valuesDto = valuesPages.map(domainValueDtoConverter::converToDto);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok()
					.header("link", paginationLinkUtils.createLinkHeader(valuesDto, uriBuilder))
					.body(valuesDto);
		}
	}

	@GetMapping("/{value}/contain")
	public ResponseEntity<Page<DomainValueDto>> findValuesByValueContaining(@PathVariable String value, 
																		Pageable pageable,
																		HttpServletRequest request) {

		Page<DomainValue> valuesPages = domainValueService.findValuesByValueContaining(value, pageable);

		if (valuesPages.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Page<DomainValueDto> valuesDto = valuesPages.map(domainValueDtoConverter::converToDto);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok()
					.header("link", paginationLinkUtils.createLinkHeader(valuesDto, uriBuilder))
					.body(valuesDto);
		}
	}
	
	@GetMapping("/id/{domainValueId}")
	public DomainValueDto findById(@PathVariable Long domainValueId) {
		Optional<DomainValue> domainValue = domainValueService.findById(domainValueId);
		
		if(domainValue.isEmpty()) {
			throw new DomainValueNotFoundException(domainValueId);
		}
		else {
			return domainValueDtoConverter.converToDto(domainValue.get());
		}
	}
	
	@PostMapping("/new")
	public ResponseEntity<DomainValueDto> addDomainValue(@RequestBody AddDomainValueDto addDomainValueDto){
		Optional<Domain> domainInfo = domainService.findDomainById(addDomainValueDto.getDomainId());
		
		if(domainInfo.isEmpty()) {
			throw new DomainNotFoundException(addDomainValueDto.getDomainId());
		}
		
		DomainValue newDomainValue = new DomainValue(addDomainValueDto.getValue(), domainInfo.get());
		
		try {
			DomainValue storeDomainValue = domainValueService.addDomainValue(newDomainValue);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(domainValueDtoConverter.converToDto(storeDomainValue));
		}
		catch(DomainValueExistsException ex) {
			throw new DomainValueExistsApiException(addDomainValueDto.getValue(), addDomainValueDto.getDomainId());
		}
	
	}
	
	@PutMapping("/{domainValueId}/edit")
	public DomainValueDto editDomainValue(@PathVariable Long domainValueId, @RequestBody EditDomainValueDto editDomainValueDto){
		
		return domainValueService.findById(domainValueId)
						.map(dv -> {
							dv.changeValue(editDomainValueDto.getValue());
							if(dv.isEnable() != editDomainValueDto.isEnable()) {
								dv.changeEnable();
							}
							return domainValueDtoConverter.converToDto(domainValueService.saveDomainValue(dv));
						}).orElseThrow(() -> new DomainValueNotFoundException(domainValueId)); 
	}
}
