package it.prova.gestioneordiniarticolicategorie.dao.categoria;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Categoria> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Categoria o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Categoria o) throws Exception {
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
	public void deleteArticoli(Long id) {
		// TODO Auto-generated method stub
		entityManager.createNativeQuery("delete from articolo_categoria where categoria_id=?1").setParameter(1, id).executeUpdate();
		
	}

	@Override
	public void deleteArticolo(Long idCategoria, Long idArticolo) {
		// TODO Auto-generated method stub
		entityManager.createNativeQuery("delete from articolo_categoria where articolo_id=?1 and categoria_id=?2 ").setParameter(2, idArticolo).setParameter(1, idCategoria).executeUpdate();
		
	}

	@Override
	public Categoria getEager(Long idCategoria) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Categoria c left join fetch c.articoli where c.id = ?1", Categoria.class)
				.setParameter(1, idCategoria).getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Categoria> findAllByOrdine(Ordine ordine) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select distinct c from Categoria c left join fetch c.articoli a left join fetch a.ordine o where o.id = ?1 ", Categoria.class).setParameter(1, ordine.getId()).getResultList();
	}

	@Override
	public List<String> findAllCodiceCategoriOfOrdiniByDataMeseAnno(Date data) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select distinct c.codice from Categoria c join c.articoli a join a.ordine o where month(o.dataSpedizione) = month(:data) and year(o.dataSpedizione) = year(:data) ", String.class).setParameter("data", data).getResultList();
	}

}
