package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT user FROM Usuario user"),
		@NamedQuery(name = "Usuario.findApellido", query = "SELECT user FROM Usuario user WHERE user.apellido = :apellido"), })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codusuario;

	private String apellido;


	private String direccion;

	private String dni;

	

	private String nombre;

	private String tlf;

	

	// Asociación bidireccional entre Usuario y Viaje
		// Un usuario puede realizar muchos viajes
		// Con esta anotación y este atributo se pueden recuperar los viajes
		// de este usuarip
		// mappedBy indica el atributo asociado en la clase Viaje	
	@OneToMany(mappedBy="usuario")
	private List<Conductor> conductors;

	//bi-directional many-to-one association to Pasajero
	@OneToMany(mappedBy="usuario")
	private List<Pasajero> pasajeros;

	public Usuario() {
	}

	public int getCodusuario() {
		return this.codusuario;
	}

	public void setCodusuario(int codusuario) {
		this.codusuario = codusuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public List<Conductor> getConductors() {
		return this.conductors;
	}

	public void setConductors(List<Conductor> conductors) {
		this.conductors = conductors;
	}

	public Conductor addConductor(Conductor conductor) {
		getConductors().add(conductor);
		conductor.setUsuario(this);

		return conductor;
	}

	public Conductor removeConductor(Conductor conductor) {
		getConductors().remove(conductor);
		conductor.setUsuario(null);

		return conductor;
	}

	public List<Pasajero> getPasajeros() {
		return this.pasajeros;
	}

	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Pasajero addPasajero(Pasajero pasajero) {
		getPasajeros().add(pasajero);
		pasajero.setUsuario(this);

		return pasajero; 
	}

	public Pasajero removePasajero(Pasajero pasajero) {
		getPasajeros().remove(pasajero);
		pasajero.setUsuario(null);

		return pasajero;
	}

	@Override
	public String toString() {
		return "Usuario codusuario=" + codusuario + ", nombre=" + nombre+ ", apellido=" + apellido + ", direccion=" + direccion + ", dni="
				+ dni  + ", tlf=" + tlf + "\n";
	}

}