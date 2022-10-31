package it.prova.gestioneordiniarticolicategorie.test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
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

			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			Ordine ordine01 = new Ordine("Rossenberg Ramboanga", "via roma 1",
					new SimpleDateFormat("dd-mm-yyyy").parse("28-10-2022"),
					new SimpleDateFormat("dd-mm-yyyy").parse("30-10-2022"));
			Ordine ordine02 = new Ordine("Rene Neumann", "via arezzo 1",
					new SimpleDateFormat("dd-mm-yyyy").parse("29-10-2022"),
					new SimpleDateFormat("dd-mm-yyyy").parse("31-10-2022"));

			ordineServiceInstance.inserisciNuovo(ordine01);
			ordineServiceInstance.inserisciNuovo(ordine02);

			Articolo articolo01ordine01 = new Articolo("iPhone 14 Pro Max 256 Viola", "IP14PROMAX256V", 1599);
			Articolo articolo02ordine01 = new Articolo("Logitech Folio Touch Combo", "LTFTC", 159);
			Articolo articolo01ordine02 = new Articolo("iPhone 13 Pro Max 256 Blue", "IP13PROMAX256B", 1599);
			Articolo articolo02ordine02 = new Articolo("Logitech Smart Keyboard Touch Combo", "LTSKTC", 129);

			articolo01ordine01.setOrdine(ordine01);
			articolo02ordine01.setOrdine(ordine01);
			articolo01ordine02.setOrdine(ordine02);
			articolo02ordine02.setOrdine(ordine02);

			articoloServiceInstance.inserisciNuovo(articolo01ordine01);
			articoloServiceInstance.inserisciNuovo(articolo02ordine01);
			articoloServiceInstance.inserisciNuovo(articolo01ordine02);
			articoloServiceInstance.inserisciNuovo(articolo02ordine02);

			Categoria categoriaApple = new Categoria("Apple Product", "APPLEINC");
			Categoria categoriaLogitech = new Categoria("Logitech Product", "LOGITECHINC");
			Categoria categoriaTecnologia = new Categoria("Tecnologia Product", "TEC");

			categoriaServiceInstance.inserisciNuovo(categoriaApple);
			categoriaServiceInstance.inserisciNuovo(categoriaLogitech);
			categoriaServiceInstance.inserisciNuovo(categoriaTecnologia);

			categoriaApple.setArticoli(new HashSet<Articolo>(Arrays.asList(articolo01ordine01, articolo01ordine02)));
			categoriaLogitech.setArticoli(new HashSet<Articolo>(Arrays.asList(articolo02ordine01, articolo02ordine02)));
			categoriaTecnologia.setArticoli(new HashSet<Articolo>(
					Arrays.asList(articolo01ordine01, articolo02ordine01, articolo01ordine02, articolo02ordine02)));

			articolo01ordine01.setCategorie(new HashSet<Categoria>(Arrays.asList(categoriaApple, categoriaTecnologia)));
			articolo02ordine01
					.setCategorie(new HashSet<Categoria>(Arrays.asList(categoriaLogitech, categoriaTecnologia)));
			articolo01ordine02.setCategorie(new HashSet<Categoria>(Arrays.asList(categoriaApple, categoriaTecnologia)));
			articolo02ordine02
					.setCategorie(new HashSet<Categoria>(Arrays.asList(categoriaLogitech, categoriaTecnologia)));

			articoloServiceInstance.aggiorna(articolo01ordine01);
			articoloServiceInstance.aggiorna(articolo02ordine01);
			articoloServiceInstance.aggiorna(articolo01ordine02);
			articoloServiceInstance.aggiorna(articolo02ordine02);

			ordineServiceInstance.aggiorna(ordine01);
			ordineServiceInstance.aggiorna(ordine02);

			categoriaServiceInstance.aggiorna(categoriaTecnologia);
			categoriaServiceInstance.aggiorna(categoriaApple);
			categoriaServiceInstance.aggiorna(categoriaLogitech);

			// 1. Voglio tutti gli ordini effettuati per una determinata categoria
			testCercaOrdiniDataCategoria(ordineServiceInstance, categoriaTecnologia);

			// 2. Voglio tutte le categorie (distinte) degli articoli di un determinato
			// ordine
			testCercaCategorieDateUnOrdine(categoriaServiceInstance, ordine01);

			// 3 . Voglio la somma totale di tutti i prezzi degli articoli legati ad una
			// categoria
			testSommaPrezziArticoliDataCategoria(articoloServiceInstance, categoriaApple);

//			4. Voglio il più recente ordine, in termini di spedizione, relativo ad una data categoria in input
			// SELECT o FROM Ordine o LEFT JOIN FETCH o.articoli a LEFT JOIN FETCH
			// a.categorie c where c.id = ?1 ORDER BY o.dataSpedizione DESC
			testOrdinePiuRecenteDataCategoria(ordineServiceInstance, categoriaApple, ordine02);

//			5. Voglio la lista distinta di codici di categorie di ordini effettuati durante l'arco di un mese (es. febbraio 2022)
			// SELECT DISTINCT c.codice from Categoria c JOIN c.articoli a JOIN a.ordine o
			// WHERE MONTH(o.dataSpedizione) = MONTH(?1) and YEAR(o.dataSpedizione) =
			// YEAR(?1)
			testCercaCodiciCategorieOrdiniDataUnaDataMeseAnno(categoriaServiceInstance);

//			6. Voglio la somma totale dei prezzi di tutti gli articoli indirizzati ad un destinatario
			// SELECT SUM(a.prezzoSingolo) from Articolo a JOIN a.ordine o where
			// o.nomeDestinatario = ?1
			testSommaPrezziArticoliDatoNomeDestinatario(ordineServiceInstance, ordine01);

//			7. Voglio la lista distinta di indirizzi di ordini che contengano una determinata stringa nel numero seriale dei relativi articoli
			// SELECT DISTINCT o.indirizzoSpedizione from Ordine o LEFT JOIN FETCH
			// o.articoli a WHERE a.numeroSeriale like ?1
			testListaIndirizziDatoNumeroSeriale(ordineServiceInstance);

//			8. Voglio la lista di articoli in ‘situazioni strane’ vale a dire quelli in cui l’ordine di appartenenza sia stato spedito oltre la data scadenza
			// SELECT DISTINCT a FROM Articolo a LEFT JOIN FETCH a.ordine o WHERE o.dataSpedizone > o.dataScadenza
			testListaArticoliConErrori(articoloServiceInstance, ordineServiceInstance);

			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
			System.out.println(
					"In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");

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

	private static void testListaArticoliConErrori(ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testListaArticoliConErrori inizio.............");

		Ordine ordineErrore = new Ordine("Topolino", "via milano 1",
				new SimpleDateFormat("dd-mm-yyyy").parse("30-10-2022"),
				new SimpleDateFormat("dd-mm-yyyy").parse("28-10-2022"));
		
		Articolo articolo01ordine01 = new Articolo("iPhone 13 Pro Max 256 Nero", "IP14PROMAX256N", 1599);
		Articolo articolo02ordine01 = new Articolo("Logitech MX Keys", "LTFMXK", 150);
		
		ordineServiceInstance.inserisciNuovo(ordineErrore);
		
		articolo01ordine01.setOrdine(ordineErrore);
		articolo02ordine01.setOrdine(ordineErrore);
		
		articoloServiceInstance.inserisciNuovo(articolo01ordine01);
		articoloServiceInstance.inserisciNuovo(articolo02ordine01);

		List<Articolo> listaArticoloErrori = articoloServiceInstance.listaArticoliConErrori();

		if (listaArticoloErrori.size() != 2)
			throw new RuntimeException("testListaArticoliConErrori FAILED");

		System.out.println(".......testListaArticoliConErrori fine.............\n");

	}

	private static void testListaIndirizziDatoNumeroSeriale(OrdineService ordineServiceInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testListaIndirizziDatoNumeroSeriale inizio.............");

		String numeroSeriale = "IP";

		List<String> listaIndirizzi = ordineServiceInstance.listaIndirizziDatoNumeroSeriale(numeroSeriale);

		if (listaIndirizzi.size() != 2)
			throw new RuntimeException("testListaIndirizziDatoNumeroSeriale FAILED");

		System.out.println(".......testListaIndirizziDatoNumeroSeriale fine.............\n");
	}

	private static void testSommaPrezziArticoliDatoNomeDestinatario(OrdineService ordineServiceInstance, Ordine ordine)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testSommaPrezziArticoliDatoNomeDestinatario inizio.............");

		String nomeDestinatario = ordine.getNomeDestinatario();

		int somma = ordineServiceInstance.sommaPrezziArticoliDatoNomeDestinatario(nomeDestinatario);

		if (somma != 1758)
			throw new RuntimeException("testSommaPrezziArticoliGivenIndrizzo FAILED");

		System.out.println(".......testSommaPrezziArticoliDatoNomeDestinatario fine.............\n");

	}

	private static void testCercaCodiciCategorieOrdiniDataUnaDataMeseAnno(CategoriaService categoriaServiceInstance)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testCercaCodiciCategorieOrdiniDataUnaDataMeseAnno inizio.............");

		Date dataMeseAnno = new SimpleDateFormat("dd-mm-yyyy").parse("10-10-2022");

		List<String> listaCodiciCategorie = categoriaServiceInstance
				.cercaCodiciCategorieOrdiniDataUnaDataMeseAnno(dataMeseAnno);

		if (listaCodiciCategorie.size() != 3)
			throw new RuntimeException("testCercaCodiciCategorieOrdiniDataUnaDataMeseAnno FAILED");

		System.out.println(".......testCercaCodiciCategorieOrdiniDataUnaDataMeseAnno fine.............\n");

	}

	private static void testOrdinePiuRecenteDataCategoria(OrdineService ordineServiceInstance, Categoria categoria,
			Ordine ordine) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testOrdinePiuRecenteDataCategoria inizio.............");

		Ordine ordinePiuRecente = ordineServiceInstance.ordinePiuRecenteDataCategoria(categoria);

		if (!ordinePiuRecente.getId().equals(ordine.getId()))
			throw new RuntimeException("testOrdinePiuRecenteDataCategoria FAILED");

		System.out.println(".......testOrdinePiuRecenteDataCategoria fine.............\n");

	}

	private static void testSommaPrezziArticoliDataCategoria(ArticoloService articoloServiceInstance,
			Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testSommaPrezziArticoliDataCategoria inizio.............");

		int somma = articoloServiceInstance.sommaPrezziArticoliDataCategoria(categoria);

		if (somma != 3198)
			throw new RuntimeException("testCercaCategorieDateUnOrdine FAILED");

		System.out.println(".......testSommaPrezziArticoliDataCategoria fine.............\n");
	}

	private static void testCercaCategorieDateUnOrdine(CategoriaService categoriaServiceInstance, Ordine ordine01)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n.......testCercaCategorieDateUnOrdine inizio.............");

		List<Categoria> categorieDatoOrdineList = categoriaServiceInstance.cercaCategorieDateUnOrdine(ordine01);
		if (categorieDatoOrdineList.size() != 3)
			throw new RuntimeException("testCercaCategorieDateUnOrdine FAILED");

		System.out.println(".......testCercaCategorieDateUnOrdine fine.............\n");

	}

	private static void testCercaOrdiniDataCategoria(OrdineService ordineServiceInstance, Categoria categoria)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("\n.......testCercaOrdiniDataCategoria inizio.............");

		List<Ordine> ordiniDataCategoriaList = ordineServiceInstance.cercaOrdiniDataCategoria(categoria);
		if (ordiniDataCategoriaList.size() != 2)
			throw new RuntimeException("testCercaOrdiniDataCategoria FAILED");

		System.out.println(".......testCercaOrdiniDataCategoria fine.............\n");

	}

}
