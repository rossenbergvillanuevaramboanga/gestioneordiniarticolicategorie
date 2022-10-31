package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	Ordine getEager(Long id);

	List<Ordine> findAllByCategoria(Categoria categoria);

	Ordine findMostRecentOrdineByCategoria(Categoria categoria);

	Long sumPrezziArticoliByNomeDestinatario(String indirizzo);

	List<String> findIndirizziByNumeroSeriale(String numeroSeriale);



}
