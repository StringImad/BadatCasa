package prueba;

import java.util.List;

import controladores.BD_ControladorPasajeroCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import entidades.Pasajero;
import entidades.Usuario;

public class PruebaPasajero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BD_ControladorPasajeroCRUD cp= new BD_ControladorPasajeroCRUD();
		BD_ControladorUsuarioCRUD cu= new BD_ControladorUsuarioCRUD();


		imprimirEntidades(cp);
		Pasajero p1 = new Pasajero();
		Usuario user = cu.findByPK(9);
		p1.setUsuario(user);
		p1.setDireccionRecogida("Calle Pasaj 1");
		
		

		cp.createPasajero(p1);
		
//		cp.borrarPasajero(p1);
		imprimirEntidades(cp);

	}
	private static void imprimirEntidades(BD_ControladorPasajeroCRUD cp) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
	
		for (Pasajero pasaj : cp.findAll()) {
			System.out.println(pasaj);
		}
	}
}
