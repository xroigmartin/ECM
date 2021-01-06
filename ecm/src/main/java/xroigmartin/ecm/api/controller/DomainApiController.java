package xroigmartin.ecm.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Domain> getAllDomains() {
		return domainService.findAllDomains();
	}

	@GetMapping("/{id}")
	public Domain getDomainById(@PathVariable Long id){
		return domainService.findDomainById(id);
	}

	@PostMapping("/new")
	public Domain insertDomain(@RequestBody Domain newDomain){
		return domainService.addDomain(newDomain);
	}

	@PutMapping("/{id}/edit")
	public Domain editDomain(@RequestBody Domain domainEdit, @PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if (domainStore != null){
			domainStore.setCodeDomain(domainEdit.getCodeDomain());
			domainStore.setCodeDomainText(domainEdit.getCodeDomainText());
			if (domainStore.isEnable() != domainEdit.isEnable()){
				domainStore.changeEnable();
			}
			return domainService.saveDomain(domainStore);
		}
		return null;
	}

	@PutMapping("/{id}/enable")
	public Domain enableDomain(@PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if (domainStore != null && !domainStore.isEnable()){
			return domainService.changeEnable(id);
		}

		return domainStore;
	}

	@PutMapping("/{id}/disable")
	public Domain disableDomain(@PathVariable Long id){
		Domain domainStore = domainService.findDomainById(id);
		if (domainStore != null && domainStore.isEnable()){
			return domainService.changeEnable(id);
		}

		return domainStore;
	}
}
