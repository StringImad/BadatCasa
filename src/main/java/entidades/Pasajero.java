package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the pasajero database table.
 * 
 */
@Entity
@NamedQuery(name = "Pasajero.findAll", query = "SELECT p FROM Pasajero p")
public class Pasajero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codpasajero;

	private String direccionRecogida;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "codusuario")
	private Usuario usuario;

	// bi-directional many-to-one association to Viaje
	// Asociación bidireccional entre pasajero y Viaje
	// Un pasajero puede realizar muchos viajes
	// Con esta anotación y este atributo se pueden recuperar los viajes
	// de este pasajeros
	// mappedBy indica el atributo asociado en la clase Viaje

	@OneToMany(mappedBy = "pasajero")
	private List<Viaje> viajes;

	public Pasajero() {
	}

	public int getCodpasajero() {
		return this.codpasajero;
	}

	public void setCodpasajero(int codpasajero) {
		this.codpasajero = codpasajero;
	}

	public String getDireccionRecogida() {
		return this.direccionRecogida;
	}

	public void setDireccionRecogida(String direccionRecogida) {
		this.direccionRecogida = direccionRecogida;
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
		viaje.setPasajero(this);

		return viaje;
	}

	public Viaje removeViaje(Viaje viaje) {
		getViajes().remove(viaje);
		viaje.setPasajero(null);

		return viaje;
	}

	@Override
	public String toString() {
		return "Pasajero codpasajero=" + codpasajero + ", direccionRecogida=" + direccionRecogida+  "\n";
//				+ ", Codusuario="
//				+ usuario.getCodusuario() +  "\n";
	}

}