package xroigmartin.ecm.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import xroigmartin.ecm.api.controller.dto.CreateDomainDto;
import xroigmartin.ecm.api.controller.dto.DomainDto;
import xroigmartin.ecm.api.controller.dto.converter.DomainDtoConverter;
import xroigmartin.ecm.exceptions.api.ApiError;
import xroigmartin.ecm.exceptions.api.domain.DomainNotFoundException;
import xroigmartin.ecm.model.domain.Domain;
import xroigmartin.ecm.service.domain.DomainService;
import xroigmartin.ecm.utils.pagination.PaginationLinkUtils;

@RestController
@RequestMapping("/api/domain")
public class DomainApiController {

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private DomainDtoConverter domainDtoConverter;
	
	@Autowired
	private PaginationLinkUtils paginationLinkUtils;

	@Operation(summary = "Listar todos los dominios")
	@ApiResponse(responseCode = "200", description="Listado de dominios",
				content = @Content(mediaType = "application/json", 
									array = @ArraySchema(schema = @Schema(implementation = Domain.class),
									minItems = 0)
				)
	)
	@GetMapping("/getAllDomains")
	public ResponseEntity<Page<DomainDto>> getAllDomains(
			@PageableDefault(size=10, page=0, sort = {"domainId"}, direction = Direction.ASC)
			Pageable pageable,
			HttpServletRequest request) {
		
		Page<Domain> listOfDomains = domainService.findAllDomains(pageable);
		
		if(listOfDomains.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {		
			Page<DomainDto> dtoList = listOfDomains.map(domainDtoConverter::converToDto);
			
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
			
			return ResponseEntity.ok()
								.header("link", paginationLinkUtils.createLinkHeader(dtoList, uriBuilder))
								.body(dtoList);
		}
	}

	@Operation(summary = "Obtener un dominio a partir de su id", responses = {
			@ApiResponse(responseCode = "200", description="Dominio encontrado",
						content = @Content(mediaType = "application/json", 
											schema = @Schema(implementation = Domain.class))
						),
			@ApiResponse(responseCode = "404", description="Dominio no encontrado",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ApiError.class)))
		}
	)
	@GetMapping("/{id}")
	public Domain getDomainById(@Parameter(description = "id del dominio a buscar")@PathVariable Long id){
		return domainService.findDomainById(id)
							.orElseThrow(() -> new DomainNotFoundException(id));
	}

	@Operation(summary = "Añadir un dominio nuevo", responses = {
			@ApiResponse(responseCode = "201", description="Dominio creado",
						content = @Content(mediaType = "application/json", 
											schema = @Schema(implementation = Domain.class))
						),
			@ApiResponse(responseCode = "500", description="Ya existe un dominio con ese código de dominio",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ApiError.class)))
		}
	)
	@PostMapping("/new")
	public ResponseEntity<DomainDto> insertDomain(@Parameter(description = "Datos del nuevo dominio", schema = @Schema(implementation = CreateDomainDto.class))
													@RequestBody CreateDomainDto newDomain){
		Domain addDomain = new Domain(newDomain.getCodeDomain(), newDomain.getCodeDomainText());
		Domain domainSaved = domainService.addDomain(addDomain);
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(domainDtoConverter.converToDto(domainSaved));
	}

	@Operation(summary = "Editar un dominio", responses = {
			@ApiResponse(responseCode = "200", description="Dominio modificado",
						content = @Content(mediaType = "application/json", 
											schema = @Schema(implementation = Domain.class))),
			@ApiResponse(responseCode = "404", description="Dominio no encontrado",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ApiError.class))),
			@ApiResponse(responseCode = "500", description="Ya existe un dominio con ese código de dominio",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ApiError.class)))
		}
	)
	@PutMapping("/{id}/edit")
	public DomainDto editDomain(@Parameter(description = "Datos a modificar del dominio", schema = @Schema(implementation = CreateDomainDto.class))
								@RequestBody Domain domainEdit, 
								@Parameter(description = "id del dominio a buscar")
								@PathVariable Long id){
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

	@Operation(summary = "Reactiva un dominio", responses = {
			@ApiResponse(responseCode = "200", description="Dominio reactivado",
						content = @Content(mediaType = "application/json", 
											schema = @Schema(implementation = Domain.class))
						),
			@ApiResponse(responseCode = "404", description="Dominio no encontrado",
					content = @Content(mediaType = "application/json", 
					schema = @Schema(implementation = ApiError.class)))
		}
	)
	@PutMapping("/{id}/enable")
	public DomainDto enableDomain(@Parameter(description = "id del dominio a buscar")@PathVariable Long id){
		return changeStateDomain(id, true);
	}
	
	@Operation(summary = "Desactiva un dominio", responses = {
			@ApiResponse(responseCode = "200", description="Dominio desactivado",
						content = @Content(mediaType = "application/json", 
											examples = {
													@ExampleObject(value="{\n"
															+ "  \"domainId\": 0,\n"
															+ "  \"codeDomain\": \"string\",\n"
															+ "  \"codeDomainText\": \"string\",\n"
															+ "  \"enable\": false\n"
															+ "}")
											},
											schema = @Schema(implementation = Domain.class))
						),
			@ApiResponse(responseCode = "404", description="Dominio no encontrado",
					content = @Content(mediaType = "application/json",
									schema = @Schema(implementation = ApiError.class)))
		}
	)
	@PutMapping("/{id}/disable")
	public DomainDto disableDomain(@Parameter(description = "id del dominio a buscar")@PathVariable Long id){
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
