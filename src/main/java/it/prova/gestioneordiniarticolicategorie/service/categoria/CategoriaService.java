package it.prova.gestioneordiniarticolicategorie.service.categoria;

import java.util.Date;
import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

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
	
	public void rimuoviArticoli(Long idCategoria) throws Exception;
	public void rimuoviArticolo(Long idCategoria, Long idArticolo) throws Exception;
	
	public void aggiungiArticolo(Categoria categoria, Articolo articolo) throws Exception;

	Categoria caricaSingoloElementoEager(Long idCategoria) throws Exception;

	List<Categoria> cercaCategorieDateUnOrdine(Ordine ordine01) throws Exception;

	List<String> cercaCodiciCategorieOrdiniDataUnaDataMeseAnno(Date dataMeseAnno)throws Exception;

}
