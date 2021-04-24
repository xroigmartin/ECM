package xroigmartin.ecm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xroigmartin.ecm.service.domain.DomainValueService;

@RestController
@RequestMapping("/api/domain/value")
public class DomainValueApiController {

	@Autowired
	private DomainValueService domainValueService;
}
