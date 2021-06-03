package prueba;

import java.util.List;

import controladores.BD_ControladorConductorCRUD;
import controladores.BD_ControladorPasajeroCRUD;
import controladores.BD_ControladorViajeCRUD;
import entidades.Conductor;
import entidades.Pasajero;
import entidades.Viaje;

public class BD_PruebaViaje {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		BD_ControladorViajeCRUD cv= new BD_ControladorViajeCRUD();
		BD_ControladorPasajeroCRUD cp= new BD_ControladorPasajeroCRUD();
		BD_ControladorConductorCRUD cc= new BD_ControladorConductorCRUD();

		// Se obtienen todas las instancias
		List<Viaje> listaViajes = cv.findAll();

		
		imprimirEntidades(cv);
		Viaje v1 = new Viaje();
		Pasajero pasaj = cp.findByPK(5);
		Conductor conduct = cc.findByPK(5);
		v1.setConductor(conduct);
		v1.setPasajero(pasaj);
		v1.setPrecio(15.0);
		v1.setPuntorecogida("Calle del pasajero 1");
		
		cv.createViaje(v1);
		//p1.setUsuario(user);
		imprimirEntidades(cv);

	}
	private static void imprimirEntidades(BD_ControladorViajeCRUD cv) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
	
		for (Viaje viaj : cv.findAll()) {
			System.out.println(viaj);
		}
	}

}
