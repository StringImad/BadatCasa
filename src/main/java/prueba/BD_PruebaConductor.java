package prueba;

import java.util.List;

import controladores.BD_ControladorConductorCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import entidades.Conductor;
import entidades.Usuario;

public class BD_PruebaConductor {
	public static void main(String[] args) {
		BD_ControladorConductorCRUD cc= new BD_ControladorConductorCRUD();
		BD_ControladorUsuarioCRUD cu = new BD_ControladorUsuarioCRUD();

		// Se obtienen todas las instancias
		List<Conductor> listaConductores = cc.findAll();
		Conductor nuevo = new Conductor();
		Usuario userNuevo = cu.findByPK(8);
		
//		nuevo.setDispo(true);
//		nuevo.setNumCuentBanc("nuevoCuentBanc");
//		nuevo.setNumSegSocial("numSegNuevo");
//		nuevo.setUsuario(userNuevo);
//cc.createUsuario(nuevo);
//Conductor borrar = cc.findByPK(4);
//cc.borrarConductor(borrar);
		
		
		// Se imprime la lista
		imprimirEntidades(cc);
	}
	private static void imprimirEntidades(BD_ControladorConductorCRUD cc) {
		System.out.println("ENTIDADES EN LA BASE DE DATOS ---------------");
	
		for (Conductor conduct : cc.findAll()) {
			System.out.println(conduct);
		}
	}
	

}
