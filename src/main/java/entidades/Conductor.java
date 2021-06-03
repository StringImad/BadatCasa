package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conductor database table.
 * 
 */
@Entity
@NamedQuery(name="Conductor.findAll", query="SELECT c FROM Conductor c")
public class Conductor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codconductor;
	private boolean dispo;
	private String numCuentBanc;
	private String numSegSocial;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="codusuario")
	private Usuario usuario;

	// Asociación bidireccional entre conductor y Viaje
			// Un conductor puede realizar muchos viajes
			// Con esta anotación y este atributo se pueden recuperar los viajes
			// de este usuarip
			// mappedBy indica el atributo asociado en la clase Viaje	
	@OneToMany(mappedBy="conductor")
	private List<Viaje> viajes;

	public Conductor() {
	}

	public int getCodconductor() {
		return this.codconductor;
	}

	public void setCodconductor(int codconductor) {
		this.codconductor = codconductor;
	}

	public boolean getDispo() {
		return this.dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}


	public String getNumCuentBanc() {
		return this.numCuentBanc;
	}

	public void setNumCuentBanc(String numCuentBanc) {
		this.numCuentBanc = numCuentBanc;
	}


	public String getNumSegSocial() {
		return this.numSegSocial;
	}

	public void setNumSegSocial(String numSegSocial) {
		this.numSegSocial = numSegSocial;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Viaje> getViajes() {
		return this.viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

	public Viaje addViaje(Viaje viaje) {
		getViajes().add(viaje);
		viaje.setConductor(this);

		return viaje;
	}

	public Viaje removeViaje(Viaje viaje) {
		getViajes().remove(viaje);
		viaje.setConductor(null);

		return viaje;
	}

	@Override
	public String toString() {
		return "Conductor codconductor=" + codconductor + ", dispo=" + dispo + ", numCuentBanc=" + numCuentBanc
				+ ", numSegSocial=" + numSegSocial + ", Codusuario=" + usuario.getCodusuario() + "\n";
	}

}