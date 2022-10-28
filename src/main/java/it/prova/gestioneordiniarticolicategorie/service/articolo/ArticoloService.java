package it.prova.gestioneordiniarticolicategorie.service.articolo;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface ArticoloService {

	// DAO
	void setOrdineDAO(OrdineDAO ordineDAOInstance);

	void setArticoloDAO(ArticoloDAO articoloDAOInstance);

	void setCategoriaDAO(CategoriaDAO categoriaDAOIstance);

	// CRUD
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articolo) throws Exception;

	public void inserisciNuovo(Articolo articolo) throws Exception;

	public void rimuovi(Long id) throws Exception;
	
	// Rimuovi Categoria da Articolo
	
	public void rimuoviCategorie(Long idArticolo) throws Exception; 
	public void rimuoviCategoria(Long idArticolo, Long idCategoria) throws Exception;
	
	public void aggiungiCategoria(Articolo articolo, Categoria categoria) throws Exception;

	Articolo caricaSingoloElementoEager(Long idArticolo) throws Exception;

}
