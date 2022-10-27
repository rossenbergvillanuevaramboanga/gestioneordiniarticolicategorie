package it.prova.gestioneordiniarticolicategorie.service;

import it.prova.gestioneordiniarticolicategorie.dao.MyDaoFactory;
import it.prova.gestioneordiniarticolicategorie.service.articolo.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.articolo.ArticoloServiceImpl;
import it.prova.gestioneordiniarticolicategorie.service.categoria.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.categoria.CategoriaServiceImpl;
import it.prova.gestioneordiniarticolicategorie.service.ordine.OrdineService;
import it.prova.gestioneordiniarticolicategorie.service.ordine.OrdineServiceImpl;

public class MyServiceFactory {

	private static OrdineService instanceOrdineService = null;
	private static ArticoloService instanceArticoloService = null;
	private static CategoriaService instanceCategoriaService = null;

	public static OrdineService getOrdineServiceIstance() {

		if (instanceOrdineService == null)
			instanceOrdineService = new OrdineServiceImpl();

		instanceOrdineService.setOrdineDAO(MyDaoFactory.getOrdineDAOInstance());
		instanceOrdineService.setArticoloDAO(MyDaoFactory.getArticoloDAOInstance());
		instanceOrdineService.setCategoriaDAO(MyDaoFactory.getCategoriaDAOIstance());

		return instanceOrdineService;

	}

	public static ArticoloService getArticoloServiceInstance() {

		if (instanceArticoloService == null)
			instanceArticoloService = new ArticoloServiceImpl();

		instanceArticoloService.setOrdineDAO(MyDaoFactory.getOrdineDAOInstance());
		instanceArticoloService.setArticoloDAO(MyDaoFactory.getArticoloDAOInstance());
		instanceArticoloService.setCategoriaDAO(MyDaoFactory.getCategoriaDAOIstance());

		return instanceArticoloService;

	}

	public static CategoriaService getCategoriaServiceInstance() {
		
		if (instanceCategoriaService == null)
			instanceCategoriaService = new CategoriaServiceImpl();

		instanceCategoriaService.setOrdineDAO(MyDaoFactory.getOrdineDAOInstance());
		instanceCategoriaService.setArticoloDAO(MyDaoFactory.getArticoloDAOInstance());
		instanceCategoriaService.setCategoriaDAO(MyDaoFactory.getCategoriaDAOIstance());

		return instanceCategoriaService;

	}
}
