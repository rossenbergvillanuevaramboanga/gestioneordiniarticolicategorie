package it.prova.gestioneordiniarticolicategorie.service.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.exception.OrdineConArticoliException;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineServiceImpl implements OrdineService {
	
	private OrdineDAO ordineDAO; 
	private ArticoloDAO articoloDAO;
	private CategoriaDAO categoriaDAO;
	

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAOInstance) {
		// TODO Auto-generated method stub
		this.ordineDAO = ordineDAOInstance;
		
	}

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAOInstance) {
		// TODO Auto-generated method stub
		this.articoloDAO = articoloDAOInstance;
		
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAOIstance) {
		// TODO Auto-generated method stub
		this.categoriaDAO = categoriaDAOIstance;
		
	}

	@Override
	public List<Ordine> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Ordine caricaSingoloElementoEager(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.getEager(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}


	@Override
	public void aggiorna(Ordine ordine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			ordineDAO.setEntityManager(entityManager);
			ordineDAO.update(ordine);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Ordine ordine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			ordineDAO.setEntityManager(entityManager);
			ordineDAO.insert(ordine);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			ordineDAO.setEntityManager(entityManager);
			
			if(!ordineDAO.get(id).getArticoli().isEmpty()) throw new OrdineConArticoliException("Rimozione di ordine con articoli.");
			ordineDAO.delete(ordineDAO.get(id));
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuoviArticoli(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			ordineDAO.setEntityManager(entityManager);
			articoloDAO.setEntityManager(entityManager);
	
			Ordine ordine = ordineDAO.getEager(idOrdine);
			ordine.getArticoli().clear();
			articoloDAO.deleteAllArticoliWithOrder(idOrdine);
			ordineDAO.update(ordine);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Ordine> cercaOrdiniDataCategoria(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.findAllByCategoria(categoria);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine ordinePiuRecenteDataCategoria(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.findMostRecentOrdineByCategoria(categoria);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public int sommaPrezziArticoliDatoNomeDestinatario(String nomeDestinatario) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.sumPrezziArticoliByNomeDestinatario(nomeDestinatario).intValue();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<String> listaIndirizziDatoNumeroSeriale(String numeroSeriale) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);
			return ordineDAO.findIndirizziByNumeroSeriale(numeroSeriale);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	
}
