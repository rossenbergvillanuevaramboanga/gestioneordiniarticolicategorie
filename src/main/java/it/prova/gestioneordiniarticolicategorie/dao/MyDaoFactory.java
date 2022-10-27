package it.prova.gestioneordiniarticolicategorie.dao;

import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAOImpl;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAOImpl;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAOImpl;

public class MyDaoFactory {
	
	private static OrdineDAO instanceOrdineDAO = null; 
	private static ArticoloDAO instanceArticoloDAO = null;
	private static CategoriaDAO instanceCategoriaDAO = null; 

	
	public static OrdineDAO getOrdineDAOInstance() {
		if(instanceOrdineDAO == null) instanceOrdineDAO = new OrdineDAOImpl();
		return instanceOrdineDAO;
	}
	
	public static ArticoloDAO getArticoloDAOInstance() {
		if(instanceArticoloDAO == null)instanceArticoloDAO = new ArticoloDAOImpl();
		return instanceArticoloDAO;
	}
	
	public static CategoriaDAO getCategoriaDAOIstance(){
		if(instanceCategoriaDAO == null) instanceCategoriaDAO = new CategoriaDAOImpl();
		return instanceCategoriaDAO;
	}
}
