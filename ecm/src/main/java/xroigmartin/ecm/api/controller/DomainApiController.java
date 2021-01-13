package xroigmartin.ecm.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xroigmartin.ecm.api.controller.dto.CreateDomainDto;
import xroigmartin.ecm.api.controller.dto.DomainDto;
import xroigmartin.ecm.api.controller.dto.converter.DomainDtoConverter;
import xroigmartin.ecm.exceptions.api.domain.DomainNotFoundException;
import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.service.domain.DomainService;

@RestController
@RequestMapping("/api/domain")
public class DomainApiController {

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private DomainDtoConverter domainDtoConverter;

	@GetMapping("/getAllDomains")
	public ResponseEntity<List<DomainDto>> getAllDomains() {
		
		List<Domain> listOfDomains = domainService.findAllDomains();
		
		if(listOfDomains.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {		
			List<DomainDto> dtoList = listOfDomains.stream()
													.map(domainDtoConverter::converToDto)
													.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	@GetMapping("/{id}")
	public Domain getDomainById(@PathVariable Long id){
		return domainService.findDomainById(id)
							.orElseThrow(() -> new DomainNotFoundException(id));
	}

	@PostMapping("/new")
	public ResponseEntity<DomainDto> insertDomain(@RequestBody CreateDomainDto newDomain){
		Domain addDomain = new Domain(newDomain.getCodeDomain(), newDomain.getCodeDomainText());
		Domain domainSaved = domainService.addDomain(addDomain);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(domainDtoConverter.converToDto(domainSaved));
	}

	@PutMapping("/{id}/edit")
	public DomainDto editDomain(@RequestBody Domain domainEdit, @PathVariable Long id){
		return domainService.findDomainById(id)
							.map(d -> {
								d.setCodeDomain(domainEdit.getCodeDomain());
								d.setCodeDomainText(domainEdit.getCodeDomainText());
								if(d.isEnable() != domainEdit.isEnable()) {
									d.changeEnable();
								}
								return domainDtoConverter.converToDto(domainService.saveDomain(d));
							}).orElseThrow(() -> new DomainNotFoundException(id));
	}

	@PutMapping("/{id}/enable")
	public DomainDto enableDomain(@PathVariable Long id){
		return changeStateDomain(id, true);
	}

	@PutMapping("/{id}/disable")
	public DomainDto disableDomain(@PathVariable Long id){
		return changeStateDomain(id, false);
	}

	private DomainDto changeStateDomain(long id, boolean enabled){
		return domainService.findDomainById(id)
				.map(d -> {
					if(d.isEnable() != enabled) {
						return domainDtoConverter.converToDto(domainService.changeEnable(id));
					}
					return domainDtoConverter.converToDto(d);
				}).orElseThrow(() -> new DomainNotFoundException(id));
	}
	
	
	
}
