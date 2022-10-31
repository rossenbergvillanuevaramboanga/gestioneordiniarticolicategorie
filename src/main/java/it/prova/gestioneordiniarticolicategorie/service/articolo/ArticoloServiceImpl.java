package it.prova.gestioneordiniarticolicategorie.service.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService{
	
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
	public List<Articolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Articolo caricaSingoloElementoEager(Long idArticolo) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.getEager(idArticolo);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Articolo articolo) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.update(articolo);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
		
	}

	@Override
	public void inserisciNuovo(Articolo articolo) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.insert(articolo);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
			
		
		
	}

	@Override
	public void rimuovi(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.deleteCategorie(id);
			articoloDAO.delete(articoloDAO.get(id));
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
		
	}

	@Override
	public void rimuoviCategorie(Long idArticolo) throws Exception{
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.deleteCategorie(idArticolo);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
	}

	@Override
	public void rimuoviCategoria(Long idArticolo, Long idCategoria) throws Exception{
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
			articoloDAO.setEntityManager(entityManager);
			
			articoloDAO.deleteCategoria(idArticolo, idCategoria);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
	}

	@Override
	public void aggiungiCategoria(Articolo articolo, Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			
//			articoloDAO.setEntityManager(entityManager);
//			articolo.getCategorie().add(categoria);
//			articoloDAO.update(articolo);
			
			articoloDAO.setEntityManager(entityManager);
			if(articolo.getCategorie().contains(categoria)) throw new RuntimeException("Articolo possiede Categoria");
			articolo.getCategorie().add(categoria);
			if(categoria.getArticoli().contains(articolo)) throw new RuntimeException("Categoria possiede Articolo");
			categoria.getArticoli().add(articolo);
			articoloDAO.update(articolo);
			
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally {
			EntityManagerUtil.closeEntityManager(entityManager);
			
		}
	}

	@Override
	public int sommaPrezziArticoliDataCategoria(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.sumPrezziArticoliGivenCategoria(categoria).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Articolo> listaArticoliConErrori() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			articoloDAO.setEntityManager(entityManager);
			return articoloDAO.findArticoliWithErrors();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	
	

}
