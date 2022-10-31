package it.prova.gestioneordiniarticolicategorie.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {
	
	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Ordine o) throws Exception {
		// TODO Auto-generated method stub
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Ordine o) throws Exception {
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
	public Ordine getEager(Long id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Ordine o left join fetch o.articoli where o.id = ?1", Ordine.class)
				.setParameter(1, id).getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Ordine> findAllByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select distinct o from Ordine o left join fetch o.articoli a left join fetch a.categorie c where c.id = ?1 ", Ordine.class).setParameter(1, categoria.getId()).getResultList();
	}

	@Override
	public Ordine findMostRecentOrdineByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT distinct o FROM Ordine o LEFT JOIN FETCH o.articoli a LEFT JOIN FETCH a.categorie c where c.id = ?1 ORDER BY o.dataSpedizione desc", Ordine.class)
				.setParameter(1, categoria.getId())
				.getResultStream().findFirst().orElse(null);
	}

	@Override
	public Long sumPrezziArticoliByNomeDestinatario(String nomeDestinatario) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT SUM(a.prezzoSingolo) from Articolo a JOIN a.ordine o where o.nomeDestinatario = ?1 ",Long.class).setParameter(1,nomeDestinatario).getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<String> findIndirizziByNumeroSeriale(String numeroSeriale) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT DISTINCT o.indirizzoSpedizione from Ordine o JOIN o.articoli a WHERE a.numeroSeriale like ?1", String.class)
				.setParameter(1, numeroSeriale+"%")
				.getResultList();
	}

}
