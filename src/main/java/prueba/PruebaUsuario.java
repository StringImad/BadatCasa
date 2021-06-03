package prueba;

import java.util.List;

import controladores.BD_ControladorUsuarioCRUD;
import entidades.Usuario;

public class PruebaUsuario {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BD_ControladorUsuarioCRUD cu = new BD_ControladorUsuarioCRUD();

		// Se obtienen todas las instancias
		List<Usuario> listaUsuarios = cu.findAll();

		// Se imprime la lista
		System.out.println("\n\nTodas las entidades ------------ ");
		for (Usuario user : listaUsuarios) {
			System.out.println(user);
		}

//		// Se obtiene una entidad
		System.out.println("\n\nBuscar Usuario con apellido Lopez ------------ ");
		Usuario aux = cu.findByApellido("ape1"); 
		System.out.println(aux);
//
//		// Creación de una entidad
				Usuario user = new Usuario();
				Usuario userCond = new Usuario();

				Usuario userPasaj = new Usuario();

				user.setApellido("Jacinto");
				user.setNombre("Bernal");
				user.setDireccion("Calle nueva");
				user.setDni("12345678L");
				user.setTlf("612233444");
//			
//				userCond.setCodusuario(11);
//				userCond.setApellido("nombre Conductor1");
//				userCond.setNombre("apellido Conductor1");
//				userCond.setDireccion("Calle Conductor1");
//				userCond.setDni("Conduc1L");
//				userCond.setTlf("612233222");
//				
//				userPasaj.setCodusuario(12);
//				userPasaj.setApellido("nombre pasajero1");
//				userPasaj.setNombre("apellido pasajero1");
//				userPasaj.setDireccion("Calle pasajero1");
//				userPasaj.setDni("pasajL");
//				userPasaj.setTlf("612233444");
				cu.createUsuario(user); 
//				cu.createUsuario(userCond); 
//				cu.createUsuario(userPasaj); // Si está creada lanzará una excepción
//
//		// Creación de una entidad
////				Usuario user2 = new Usuario();
////				user2.setCodusuario(11);
////				user2.setApellido("pruebaApellidop82");
////				user2.setNombre("pruebaNombrep82");
////				user2.setDireccion("pruebaCallep82");
////				user2.setDni("pruep82");
////				user2.setTlf("pruebp82");
////				cu.createUsuario(user2); // Si está creada lanzará una excepción
//
//		// Se obtienen todas las instancias
		listaUsuarios = cu.findAll();
		System.out.println("\n\nTodas las entidades después de crear una nueva ------------ ");
		listaUsuarios.forEach(System.out::println);
//
//		// Se modifica la direccion del usuario con id = 1
		Usuario userModificar = cu.findByPK(1);
		if (userModificar != null) {
			userModificar.setDireccion("calle Mar Alboran");
			cu.modifyUsuario(userModificar);
		}
//
//		// Se obtienen todas las instancias
		listaUsuarios = cu.findAll();
		System.out.println("\n\nTodas las entidades después de modificar una ------------ ");
		listaUsuarios.forEach(System.out::println);

//		// Borrado del usaurio con apellido Jacinto
		Usuario userBorrar = cu.findByApellido("Jacinto");
		cu.borrarUsuario(userBorrar);

//			Usuario codUserBorrar = cu.findByPK(7);
//			cu.borrarUsuario(codUserBorrar);

		// Se obtienen todas las instancias
		listaUsuarios = cu.findAll();
		System.out.println("\n\nTodas las entidades después de borrar dos ------------ ");
		listaUsuarios.forEach(System.out::println);

	}
}
