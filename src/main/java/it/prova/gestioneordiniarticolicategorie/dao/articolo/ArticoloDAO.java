package it.prova.gestioneordiniarticolicategorie.dao.articolo;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo>{

	public void deleteCategorie(Long idArticolo);

	public void deleteCategoria(Long idArticolo, Long idCategoria);

	public Articolo getEager(Long idArticolo);

	public void deleteAllArticoliWithOrder(Long idOrdine) throws Exception;

	public Long sumPrezziArticoliGivenCategoria(Categoria categoria) throws Exception;

	public List<Articolo> findArticoliWithErrors() throws Exception;

}
