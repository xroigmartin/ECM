package xroigmartin.ecm.api.controller;

import java.util.List;
import java.util.Optional;
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
	public ResponseEntity<DomainDto> getDomainById(@PathVariable Long id){
		Optional<Domain> domain = domainService.findDomainById(id);
		
		if(domain.isPresent()) {
			return ResponseEntity.ok(domainDtoConverter.converToDto(domain.get()));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/new")
	public ResponseEntity<DomainDto> insertDomain(@RequestBody CreateDomainDto newDomain){
		Domain addDomain = new Domain(newDomain.getCodeDomain(), newDomain.getCodeDomainText());
		Domain domainSaved = domainService.addDomain(addDomain);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(domainDtoConverter.converToDto(domainSaved));
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<DomainDto> editDomain(@RequestBody Domain domainEdit, @PathVariable Long id){
		Optional<Domain> domainStore = domainService.findDomainById(id);
		if (domainStore.isPresent()){
			Domain domainChanged = domainStore.get();
			domainChanged.setCodeDomain(domainEdit.getCodeDomain());
			domainChanged.setCodeDomainText(domainEdit.getCodeDomainText());
			if (domainChanged.isEnable() != domainEdit.isEnable()){
				domainChanged.changeEnable();
			}
			
			Domain domainSaved = domainService.saveDomain(domainChanged);
			
			return ResponseEntity.ok(domainDtoConverter.converToDto(domainSaved));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}/enable")
	public ResponseEntity<DomainDto> enableDomain(@PathVariable Long id){
		Optional<Domain> domainStore = domainService.findDomainById(id);
		if(domainStore.isPresent()) {
			if(!domainStore.get().isEnable()) {
				Domain domainChanged = domainService.changeEnable(id);
				return ResponseEntity.ok(domainDtoConverter.converToDto(domainChanged));
			}
			else {
				return ResponseEntity.ok(domainDtoConverter.converToDto(domainStore.get()));
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/disable")
	public ResponseEntity<DomainDto> disableDomain(@PathVariable Long id){
		Optional<Domain> domainStore = domainService.findDomainById(id);
		if(domainStore.isPresent()) {
			if(domainStore.get().isEnable()) {
				Domain domainChanged = domainService.changeEnable(id);
				return ResponseEntity.ok(domainDtoConverter.converToDto(domainChanged));
			}
			else {
				return ResponseEntity.ok(domainDtoConverter.converToDto(domainStore.get()));
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
