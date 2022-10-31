package it.prova.gestioneordiniarticolicategorie.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class ArticoloDAOImpl implements ArticoloDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Articolo o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public void deleteCategorie(Long idArticolo) {
		// TODO Auto-generated method stub
		entityManager.createNativeQuery("delete from articolo_categoria where articolo_id=?1").setParameter(1, idArticolo).executeUpdate();
		
	}

	@Override
	public void deleteCategoria(Long idArticolo, Long idCategoria) {
		// TODO Auto-generated method stub
		entityManager.createNativeQuery("delete from articolo_categoria where articolo_id=?1 and categoria_id=?2 ").setParameter(1, idArticolo).setParameter(2, idCategoria).executeUpdate();
		
	}

	@Override
	public Articolo getEager(Long idArticolo) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Articolo a left join fetch a.categorie left join fetch a.ordine where a.id = ?1", Articolo.class)
				.setParameter(1, idArticolo).getResultStream().findFirst().orElse(null);
	}

	@Override
	public void deleteAllArticoliWithOrder(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub
		if (idOrdine < 0) {
			throw new Exception("Problema valore in input");
		}
		entityManager.createNativeQuery("delete from Articolo where ordine_id=?1").setParameter(1, idOrdine).executeUpdate();
		
	}

	@Override
	public Long sumPrezziArticoliGivenCategoria(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select SUM(a.prezzoSingolo) from Articolo a join a.categorie c where c.id = ?1 ",Long.class).setParameter(1,categoria.getId()).getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Articolo> findArticoliWithErrors() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT DISTINCT a FROM Articolo a LEFT JOIN FETCH a.ordine o WHERE o.dataSpedizione > o.dataScadenza", Articolo.class)
				.getResultList();
	}


}
