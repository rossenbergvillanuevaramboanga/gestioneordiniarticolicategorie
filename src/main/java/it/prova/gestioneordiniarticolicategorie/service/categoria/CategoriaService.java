package it.prova.gestioneordiniarticolicategorie.service.categoria;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaService {
	
	// DAO
	void setOrdineDAO(OrdineDAO ordineDAOInstance);

	void setCategoriaDAO(CategoriaDAO categoriaDAOIstance);

	void setArticoloDAO(ArticoloDAO articoloDAOInstance);

	// CRUD
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Categoria categoria) throws Exception;

	public void inserisciNuovo(Categoria categoria) throws Exception;

	public void rimuovi(Long id) throws Exception;

}
