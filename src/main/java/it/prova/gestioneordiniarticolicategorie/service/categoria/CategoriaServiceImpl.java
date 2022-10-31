package it.prova.gestioneordiniarticolicategorie.service.categoria;

import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.dao.articolo.ArticoloDAO;
import it.prova.gestioneordiniarticolicategorie.dao.categoria.CategoriaDAO;
import it.prova.gestioneordiniarticolicategorie.dao.ordine.OrdineDAO;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService {
	
	private OrdineDAO ordineDAO; 
	private ArticoloDAO articoloDAO;
	private CategoriaDAO categoriaDAO;

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAOInstance) {
		// TODO Auto-generated method stub
		this.ordineDAO = ordineDAOInstance;
		
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAOIstance) {
		// TODO Auto-generated method stub
		this.categoriaDAO = categoriaDAOIstance;
	}

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAOInstance) {
		// TODO Auto-generated method stub
		this.articoloDAO = articoloDAOInstance;
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Categoria caricaSingoloElementoEager(Long idCategoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.getEager(idCategoria);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}


	@Override
	public void aggiorna(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			categoriaDAO.update(categoria);
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
	public void inserisciNuovo(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			categoriaDAO.insert(categoria);
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
			categoriaDAO.setEntityManager(entityManager);
			
			categoriaDAO.deleteArticoli(id);
			categoriaDAO.delete(categoriaDAO.get(id));
			
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
	public void rimuoviArticoli(Long idCategoria) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			
			categoriaDAO.deleteArticoli(idCategoria);
			
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
	public void rimuoviArticolo(Long idCategoria, Long idArticolo) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			categoriaDAO.setEntityManager(entityManager);
			
			categoriaDAO.deleteArticolo(idCategoria, idArticolo);
			
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
	public void aggiungiArticolo(Categoria categoria, Articolo articolo) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			entityManager.getTransaction().begin();
			//categoriaDAO.setEntityManager(entityManager);
			//categoria.getArticoli().add(articolo);
			//categoriaDAO.update(categoria);
			
			//Try Double Linking
			articoloDAO.setEntityManager(entityManager);
			if(categoria.getArticoli().contains(articolo)) throw new RuntimeException("Categoria possiede Articolo");
			categoria.getArticoli().add(articolo);
			if(articolo.getCategorie().contains(categoria)) throw new RuntimeException("Articolo possiede Categoria");
			articolo.getCategorie().add(categoria);
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
	public List<Categoria> cercaCategorieDateUnOrdine(Ordine ordine) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.findAllByOrdine(ordine);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<String> cercaCodiciCategorieOrdiniDataUnaDataMeseAnno(Date data) throws Exception {
		// TODO Auto-generated method stub
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			categoriaDAO.setEntityManager(entityManager);
			return categoriaDAO.findAllCodiceCategoriOfOrdiniByDataMeseAnno(data);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	
}
