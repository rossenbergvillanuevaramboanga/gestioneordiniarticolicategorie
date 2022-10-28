package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import it.prova.gestioneordiniarticolicategorie.dao.IBaseDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	void deleteArticoli(Long id);

	void deleteArticolo(Long idCategoria, Long idArticolo);

	Categoria getEager(Long idCategoria);

}
