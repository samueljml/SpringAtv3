package br.com.samuel.fatec_ipi_paoo_cidades_rest_json.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.samuel.fatec_ipi_paoo_cidades_rest_json.model.beans.Cidade;
import br.com.samuel.fatec_ipi_paoo_cidades_rest_json.model.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {

	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping ("/lista")
	public List<Cidade> obterCidade(){
		return cidadeRepo.findAll();
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Cidade> cadastrar (@RequestBody Cidade cidade, HttpServletResponse htResponse) {
		Cidade c = cidadeRepo.save(cidade);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(c.getId()).toUri();
		
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/{latitude}/{longitude}")
	public Cidade buscarLL (@PathVariable String latitude, @PathVariable String longitude) {
	return cidadeRepo.findOneByLatitudeAndLongitude(latitude, longitude);
	}
	
	@GetMapping ("/{inicial}")
	public List<Cidade> buscarEspecifico (@PathVariable String inicial) {
		return cidadeRepo.findAllByNomeStartingWith(inicial);
	}
	
}
