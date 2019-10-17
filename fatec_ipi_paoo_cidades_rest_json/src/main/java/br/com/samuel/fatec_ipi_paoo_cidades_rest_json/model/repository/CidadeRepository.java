package br.com.samuel.fatec_ipi_paoo_cidades_rest_json.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.samuel.fatec_ipi_paoo_cidades_rest_json.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	public Cidade findOneByLatitudeAndLongitude(String latitude, String Longitude);
	
	public List<Cidade> findAllByNomeStartingWith(String letra);

}
