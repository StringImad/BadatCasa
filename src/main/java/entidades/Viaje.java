package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the viaje database table.
 * 
 */
@Entity
@NamedQuery(name="Viaje.findAll", query="SELECT v FROM Viaje v")
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codviaje;

	
	@Column(name = "precio")
	private Double precio;
	@Column(name = "puntorecogida")

	private String puntorecogida;
	@Column(name = "tiempo")

	private int tiempo;
	@Column(name = "tipopago")

	private String tipopago;

	//bi-directional many-to-one association to Pasajero
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name="codpasajero")
	private Pasajero pasajero;

	//bi-directional many-to-one association to Conductor
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codconductor")
	private Conductor conductor;

	public Viaje() {
	}

	public int getCodviaje() {
		return this.codviaje;
	}

	public void setCodviaje(int codviaje) {
		this.codviaje = codviaje;
	}


	

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getPuntorecogida() {
		return this.puntorecogida;
	}

	public void setPuntorecogida(String puntorecogida) {
		this.puntorecogida = puntorecogida;
	}




	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getTipopago() {
		return this.tipopago;
	}

	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}

	public Pasajero getPasajero() {
		return this.pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Conductor getConductor() {
		return this.conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
//
//	@Override
//	public String toString() {
//		return "Viaje [codviaje=" + codviaje + ", precio=" + precio + ", puntorecogida=" + puntorecogida + ", tiempo="
//				+ tiempo + ", tipopago=" + tipopago + ", pasajero=" + pasajero + ", conductor=" + conductor + "]";
//	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Viaje [codviaje=");
		builder.append(codviaje);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", puntorecogida=");
		builder.append(puntorecogida);
		builder.append(", tiempo=");
		builder.append(tiempo);
		builder.append(", tipopago=");
		builder.append(tipopago);
		builder.append(", pasajero=");
		builder.append(pasajero.getCodpasajero());
		builder.append(", conductor=");
		builder.append(conductor.getCodconductor());
		builder.append("]");
		return builder.toString();
	}

}