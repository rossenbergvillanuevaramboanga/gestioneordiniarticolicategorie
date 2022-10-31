package it.prova.gestioneordiniarticolicategorie.test;

import java.text.SimpleDateFormat;
import java.util.List;


import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.exception.OrdineConArticoliException;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.articolo.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.categoria.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.ordine.OrdineService;

public class TestGestioneOrdiniArticoliCategorie {

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

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			// 1. Inserimento di un nuovo ordine
			testInserimentoNuovoOrdine(ordineServiceInstance);

			// 2. Aggiornamento ordine esistente
			testAggiornamentoOrdine(ordineServiceInstance);

			// 3. Inserimento nuovo articolo
			testInserimentoNuovoArticolo(articoloServiceInstance, ordineServiceInstance);

			// 4. Aggiornamento articolo esistente
			testAggiornamentoArticolo(articoloServiceInstance, ordineServiceInstance);

			// 5. Rimozione di un articolo legato ad un ordine
			testRimozioneArticoloDaOrdine(articoloServiceInstance, ordineServiceInstance);
			
			// 6. Inserimento nuova categoria
			testInserimentoNuovaCategoria(articoloServiceInstance, categoriaServiceInstance);
			
			// 7. Aggiornamento categoria esistente
			testAggiornamentoCategoria(articoloServiceInstance, categoriaServiceInstance);
			
			// 8. Aggiungi articolo a categoria
			testAggiungiArticoloACategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// 9. Aggiungi categoria a articolo
			testAggiungiCategoriaAdArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			// 10. Rimozione articolo previo scollegamento categorie
			testRimozioneArticolo(articoloServiceInstance);
			
			// 11. Rimozione categoria previo scollegamento articoli
			testRimozioneCategoria(categoriaServiceInstance);
			
			// 12. Rimozione ordine
			testRimozioneOrdine(ordineServiceInstance, articoloServiceInstance);
			

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
	
	private static void testRimozioneOrdine(OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testRimozioneOrdine inizio.............");
		
		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		Long idOrdine = listaOrdini.get(listaOrdini.size() - 1).getId();
		Ordine ordine = ordineServiceInstance.caricaSingoloElementoEager(idOrdine);
		
		Articolo articolo = new Articolo("iPhone 14 Pro Max", "ASDF1234", 1399);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		articolo = articoloServiceInstance.caricaSingoloElementoEager(articolo.getId());
		
		try {
			ordineServiceInstance.rimuovi(idOrdine);
		} catch (OrdineConArticoliException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ordineServiceInstance.rimuoviArticoli(idOrdine);
		
		try {
			ordineServiceInstance.rimuovi(idOrdine);
		} catch (OrdineConArticoliException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println(".......testRimozioneOrdine fine.............\n");
	}

	private static void testRimozioneCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testRimozioneCategoria inizio.............");

		List<Categoria> listaCategoria = categoriaServiceInstance.listAll();
		Categoria categoria = listaCategoria.get(listaCategoria.size() - 1);
		
		categoriaServiceInstance.rimuovi(categoria.getId());
		
		System.out.println(".......testRimozioneCategoria fine.............\n");
		
	}
	
	private static void testRimozioneArticolo(ArticoloService articoloServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testRimozioneArticolo inizio.............");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Long idArticolo = listaArticoli.get(listaArticoli.size() - 1).getId();
		Articolo articolo = articoloServiceInstance.caricaSingoloElementoEager(idArticolo);
		
		articoloServiceInstance.rimuovi(articolo.getId());

		System.out.println(".......testRimozioneArticolo fine.............\n");
		
	}

	private static void testAggiungiCategoriaAdArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(".......testAggiungiArticoloACategoria inizio.............");
		
		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Long idArticolo = listaArticoli.get(listaArticoli.size() - 1).getId();
		Articolo articolo = articoloServiceInstance.caricaSingoloElementoEager(idArticolo);

		List<Categoria> listaCategoria = categoriaServiceInstance.listAll();
		Long idCategoria = listaCategoria.get(listaCategoria.size() - 1).getId();
		Categoria categoria = categoriaServiceInstance.caricaSingoloElementoEager(idCategoria);
		
		try {
			articoloServiceInstance.aggiungiCategoria(articolo, categoria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		if(articolo.getCategorie().isEmpty())
			throw new RuntimeException("testAggiungiArticoloACategoria FAILED");
		
		System.out.println(".......testAggiungiArticoloACategoria fine.............\n");
		
	}

	private static void testAggiungiArticoloACategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println(".......testAggiungiArticoloACategoria inizio.............");
		
		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		Long idOrdine = listaOrdini.get(listaOrdini.size() - 1).getId();
		Ordine ordine = ordineServiceInstance.caricaSingoloElementoEager(idOrdine);

		Articolo articolo = new Articolo("iPhone 14 Pro Max", "ASDF1234", 1399);
		articolo.setOrdine(ordine);
		articoloServiceInstance.inserisciNuovo(articolo);
		articolo = articoloServiceInstance.caricaSingoloElementoEager(articolo.getId());

		List<Categoria> listaCategoria = categoriaServiceInstance.listAll();
		Long idCategoria = listaCategoria.get(listaCategoria.size() - 1).getId();
		Categoria categoria = categoriaServiceInstance.caricaSingoloElementoEager(idCategoria);
		
		try {
			categoriaServiceInstance.aggiungiArticolo(categoria, articolo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(categoria.getArticoli().isEmpty())
			throw new RuntimeException("testAggiungiArticoloACategoria FAILED");
		
		System.out.println(".......testAggiungiArticoloACategoria fine.............\n");
		
	}

	

	private static void testAggiornamentoCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testAggiornamentoCategoria inizio.............");

		List<Categoria> listaCategoria = categoriaServiceInstance.listAll();
		Categoria categoria = listaCategoria.get(listaCategoria.size() - 1);
		
		String nuovoCodice = "APPLE_02";
		categoria.setCodice(nuovoCodice);
		categoriaServiceInstance.aggiorna(categoria);
		
		if (!categoria.getCodice().equals(nuovoCodice))
			throw new RuntimeException("testAggiornamentoCategoria: FAILED");

		System.out.println(".......testAggiornamentoCategoria fine.............\n");
		
	}

	private static void testInserimentoNuovaCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testInserimentoNuovaCategoria inizio.............");

//		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
//		Articolo articolo = listaArticoli.get(listaArticoli.size() - 1);

		Categoria categoria = new Categoria("Apple Inc","APPLE_01" );
		categoriaServiceInstance.inserisciNuovo(categoria);
		
		if (categoria.getId() == null)
			throw new RuntimeException("testInserimentoNuovaCategoria: FAILED");

		System.out.println(".......testInserimentoNuovaCategoria fine.............\n");
		
	}

	private static void testRimozioneArticoloDaOrdine(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testRimozioneArticoloDaOrdine inizio.............");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo articolo = listaArticoli.get(listaArticoli.size() - 1);

		articoloServiceInstance.rimuovi(articolo.getId());
		
		System.out.println(".......testRimozioneArticoloDaOrdine fine.............\n");

	}

	private static void testAggiornamentoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testAggiornamentoArticolo inizio.............");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		Articolo articolo = listaArticoli.get(listaArticoli.size() - 1);

		String nuovaDescrizione = "iPhone 14 Pro Max";
		articolo.setDescrizione(nuovaDescrizione);
		articoloServiceInstance.aggiorna(articolo);

		if (!articolo.getDescrizione().equals(nuovaDescrizione))
			throw new RuntimeException("testAggiornamentoOrdine: FAILED");

		System.out.println(".......testAggiornamentoArticolo fine.............\n");

	}

	private static void testInserimentoNuovoArticolo(ArticoloService articoloServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testInserimentoNuovoArticolo inizio.............");

		// Creo un nuovo articolo
		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		Long idOrdine = listaOrdini.get(listaOrdini.size() - 1).getId();
		Ordine ordine = ordineServiceInstance.caricaSingoloElemento(idOrdine);

		Articolo articolo = new Articolo("iPhone 13 Pro Max", "ASDF1234", 1399);
		articolo.setOrdine(ordine);

		articoloServiceInstance.inserisciNuovo(articolo);
		if (articolo.getId() == null)
			throw new RuntimeException("testInserimentoNuovoArticolo: FAILED");

		System.out.println(".......testInserimentoNuovoArticolo fine.............\n");
	}

	private static void testAggiornamentoOrdine(OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testAggiornamentoOrdine inizio.............");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		Long idOrdine = listaOrdini.get(listaOrdini.size() - 1).getId();

		Ordine ordineModificato = ordineServiceInstance.caricaSingoloElemento(idOrdine);
		String nuovoIndirizzo = "via montopoli 4";
		ordineModificato.setIndirizzoSpedizione(nuovoIndirizzo);
		ordineServiceInstance.aggiorna(ordineModificato);

		if (!ordineModificato.getIndirizzoSpedizione().equals(nuovoIndirizzo))
			throw new RuntimeException("testAggiornamentoOrdine: FAILED");

		System.out.println(".......testAggiornamentoOrdine fine.............\n");

	}

	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testInserimentoNuovoOrdine inizio.............");

		Ordine ordine = new Ordine("Rossenberg Ramboanga", "via cagliari 38",
				new SimpleDateFormat("dd-mm-yy").parse("27-10-2022"),
				new SimpleDateFormat("dd-mm-yy").parse("29-10-2022"));
		ordineServiceInstance.inserisciNuovo(ordine);

		if (ordine.getId() == null)
			throw new RuntimeException("testInserimentoNuovoOrdine: FAILED");

		System.out.println(".......testInserimentoNuovoOrdine fine.............\n");
	}

}
