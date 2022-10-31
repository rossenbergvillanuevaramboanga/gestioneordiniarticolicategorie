package it.prova.gestioneordiniarticolicategorie.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {
	
	//Ordine (id, nomeDestinatario, indirizzoSpedizione, dataSpedizione, dataScadenza, articoli)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nomedestinatario")
	private String nomeDestinatario;
	@Column(name = "indirizzospedizione")
	private String indirizzoSpedizione;
	@Column(name = "dataspedizione")
	private Date dataSpedizione;
	@Column(name = "datascadenza")
	private Date dataScadenza; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordine")
	private Set<Articolo> articoli;
	
	public Ordine() {
		// TODO Auto-generated constructor stub
	}
	public Ordine(String nomeDestinatario, String indirizzoSpedizione, Date dataSpedizione, Date dataScadenza) {
		super();
		this.nomeDestinatario = nomeDestinatario;
		this.indirizzoSpedizione = indirizzoSpedizione;
		this.dataSpedizione = dataSpedizione;
		this.dataScadenza = dataScadenza;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}
	
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	
	public Date getDataSpedizione() {
		return dataSpedizione;
	}
	
	public void setDataSpedizione(Date dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}
	
	public Date getDataScadenza() {
		return dataScadenza;
	}
	
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}
	@Override
	public String toString() {
		return "Ordine [id=" + id + ", nomeDestinatario=" + nomeDestinatario + ", indirizzoSpedizione="
				+ indirizzoSpedizione + ", dataSpedizione=" + dataSpedizione + ", dataScadenza=" + dataScadenza + "]";
	} 
	
	
	
	
	
	

}
