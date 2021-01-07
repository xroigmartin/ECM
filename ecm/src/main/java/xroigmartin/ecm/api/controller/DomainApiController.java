package xroigmartin.ecm.api.controller;

import java.util.List;

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

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.service.domain.DomainService;

@RestController
@RequestMapping("/api/domain")
public class DomainApiController {

	@Autowired
	private DomainService domainService;

	@GetMapping("/getAllDomains")
	public ResponseEntity<List<Domain>> getAllDomains() {
		
		List<Domain> listOfDomains = domainService.findAllDomains();
		
		if(listOfDomains.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(listOfDomains);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domain> getDomainById(@PathVariable Long id){
		Domain domain = domainService.findDomainById(id);
		
		if(domain == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(domain);
		}
	}

	@PostMapping("/new")
	public ResponseEntity<Domain> insertDomain(@RequestBody Domain newDomain){
		Domain domainSaved = domainService.addDomain(newDomain);
		return ResponseEntity.status(HttpStatus.CREATED).body(domainSaved);
	}

	@PutMapping("/{id}/edit")
	public ResponseEntity<Domain> editDomain(@RequestBody Domain domainEdit, @PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if (domainStore != null){
			domainStore.setCodeDomain(domainEdit.getCodeDomain());
			domainStore.setCodeDomainText(domainEdit.getCodeDomainText());
			if (domainStore.isEnable() != domainEdit.isEnable()){
				domainStore.changeEnable();
			}
			return ResponseEntity.ok(domainService.saveDomain(domainStore));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}/enable")
	public ResponseEntity<Domain> enableDomain(@PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if(domainStore != null) {
			if(!domainStore.isEnable()) {
				return ResponseEntity.ok(domainService.changeEnable(id));
			}
			else {
				return ResponseEntity.ok(domainStore);
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/disable")
	public ResponseEntity<Domain> disableDomain(@PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if(domainStore != null) {
			if(domainStore.isEnable()) {
				return ResponseEntity.ok(domainService.changeEnable(id));
			}
			else {
				return ResponseEntity.ok(domainStore);
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
