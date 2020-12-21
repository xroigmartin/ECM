package xroigmartin.ecm.controller.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.service.domain.DomainService;

@Controller
@RequestMapping("/domain")
public class DomainController {

	@Autowired
	private DomainService domainService;
	
	
	@GetMapping("/")
	public String getAllDomains(Model model) {
		List<Domain> domainList = domainService.findAllDomains();
		model.addAttribute("domainList", domainList);
		return "domain/domain_list";
	}
}
