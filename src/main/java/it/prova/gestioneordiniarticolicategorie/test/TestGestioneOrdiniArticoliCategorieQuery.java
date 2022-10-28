package it.prova.gestioneordiniarticolicategorie.test;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.articolo.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.categoria.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.ordine.OrdineService;

public class TestGestioneOrdiniArticoliCategorieQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceIstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		
		try {
			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
			System.out.println(
					"In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			
//			1. Voglio tutti gli ordini effettuati per una determinata categoria
//			2. Voglio tutte le categorie (distinte) degli articoli di un determinato ordine
//			3. Voglio la somma totale di tutti i prezzi degli articoli legati ad una categoria
//			4. Voglio il più recente ordine, in termini di spedizione, relativo ad una data categoria in input
//			5. Voglio la lista distinta di codici di categorie di ordini effettuati durante l'arco di un mese (es. febbraio 2022)
//			6. Voglio la somma totale dei prezzi di tutti gli articoli indirizzati ad un destinatario
//			7. Voglio la lista distinta di indirizzi di ordini che contengano una determinata stringa nel numero seriale dei relativi articoli
//			8. Voglio la lista di articoli in ‘situazioni strane’ vale a dire quelli in cui l’ordine di appartenenza sia stato spedito oltre la data scadenza

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");
			 
			System.out.println(
					"****************************** fine batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

}
