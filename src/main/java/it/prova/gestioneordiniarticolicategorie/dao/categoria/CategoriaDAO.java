package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import java.util.Date;
import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	void deleteArticoli(Long id);

	void deleteArticolo(Long idCategoria, Long idArticolo);

	Categoria getEager(Long idCategoria);

	List<Categoria> findAllByOrdine(Ordine ordine);

	List<String> findAllCodiceCategoriOfOrdiniByDataMeseAnno(Date data);

}
