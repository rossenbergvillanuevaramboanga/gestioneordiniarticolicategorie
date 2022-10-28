package it.prova.gestioneordiniarticolicategorie.service.ordine;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineService {

	void setOrdineDAO(OrdineDAO ordineDAOInstance);

	void setArticoloDAO(ArticoloDAO articoloDAOInstance);

	void setCategoriaDAO(CategoriaDAO categoriaDAOIstance);

	// CRUD
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;
	public Ordine caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Ordine ordine) throws Exception;

	public void inserisciNuovo(Ordine ordine) throws Exception;

	public void rimuovi(Long id) throws Exception;
	
	public void rimuoviArticoli(Long id) throws Exception;

}
