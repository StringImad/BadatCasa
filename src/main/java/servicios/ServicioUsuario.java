package servicios;

import javax.swing.JOptionPane;

import controladores.BD_ControladorUsuarioCRUD;
import entidades.Usuario;

public class ServicioUsuario {
	private static BD_ControladorUsuarioCRUD controladorUsuario = new BD_ControladorUsuarioCRUD();

	public static Usuario modificarUsuario(String codusuario) {
		boolean repetir = true;
		do {
			// BD_ControladorUsuarioCRUD controladorUsuario = new
			// BD_ControladorUsuarioCRUD();

			// Creamos un objeto de tipo usuario
			Usuario usuario = new Usuario();

			// declaracion de las variables que contendran los valores que van dentro de
			// cada campo
			String apellido = "";
			String dni = "";
			String tlf = "";
			String nombre = "";
			String direccion = "";
			String[] botonesDatos = { "Nombre", "Apellido", "Direccion", "Dni", "telefono", "salir" };

			Usuario userModificar = controladorUsuario.findByPK(Integer.parseInt(codusuario));
			if (userModificar != null) {
				int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Qué desea modificar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
				switch (ventanaModificar) {
				case 0:
					nombre = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
					userModificar.setNombre(nombre);
					controladorUsuario.modifyUsuario(userModificar);
					break;
				case 1:
					apellido = JOptionPane.showInputDialog("Ingrese un  apellido  nuevo");
					userModificar.setApellido(apellido);
					controladorUsuario.modifyUsuario(userModificar);
					break;
				case 2:
					direccion = JOptionPane.showInputDialog("Ingrese una nueva direccion");
					userModificar.setDireccion(direccion);
					controladorUsuario.modifyUsuario(userModificar);
					break;
				case 3:
					dni = controladorDniCorrecto();
					userModificar.setDni(dni);
					controladorUsuario.modifyUsuario(userModificar);
					break;
				case 4:
					tlf = controladorTlfCorrecto();
					userModificar.setTlf(tlf);
					controladorUsuario.modifyUsuario(userModificar);
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir a la pantalla de inicio.");
					// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;
				}
			}
			return usuario;
		} while (repetir);
	}

	// metodo que devuelve un string que corresponde a un dni correcto 12345678M
	private static String controladorDniCorrecto() {
		boolean comprobacion = false;
		String dni = "";
		do {
			dni = JOptionPane.showInputDialog("Ingrese un nuevo dni");
//Condicion que nos controla que el numero de caracteres no sea distinto a nueve 
			// y que el caracter en la posicion 8 es decir el noveno sea una letra
			if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
				JOptionPane.showMessageDialog(null, "Dni incorrecto, el formato correcto es : \n" + "99999999L");
				comprobacion = true;
			} else {
				comprobacion = false;
			}
		} while (comprobacion);

		return dni;
	}

//metod que devuleve un numero de 9 cifras
	private static String controladorTlfCorrecto() {
		boolean comprobacion = false;

		String tlf = "";
		do {
			tlf = JOptionPane.showInputDialog("Ingrese numero de telefono nuevo");
			// String dniIncorrecto = "Dni incorrecto introduce uno valido";
			if (tlf.length() > 9 || tlf.length() < 9) {
				JOptionPane.showMessageDialog(null, "telefono incorrecto, el formato correcto es : \n" + "666555222");
				comprobacion = true;
			} else {
				comprobacion = false;
			}
		} while (comprobacion);

		return tlf;
	}

	public static Usuario InsertarDatosUsuario() {

		// BD_ControladorUsuarioCRUD controladorUsuario = new
		// BD_ControladorUsuarioCRUD();

		// Creamos un objeto de tipo usuario
		Usuario usuario = new Usuario();

		// declaracion de las variables que contendran los valores que van dentro de
		// cada campo
		String apellido = "";
		// String dni = "";
		// String tlf = "";
		String nombre = "";
		String direccion = "";

		nombre = JOptionPane.showInputDialog("Introduce  nombre");
		apellido = JOptionPane.showInputDialog("Introduce apellido");
		// controladorDniCorrecto();
		direccion = JOptionPane.showInputDialog("Ingrese una nueva direccion");
		// controladorTlfCorrecto();
		usuario.setApellido(apellido);

		usuario.setDni(controladorDniCorrecto());

		usuario.setDireccion(direccion);

		usuario.setNombre(nombre);

		usuario.setTlf(controladorTlfCorrecto());

		controladorUsuario.createUsuario(usuario);
		return usuario;
	}

	public static Usuario borrarUsuario() {
		boolean repetir = false;
		Usuario usuario = new Usuario();
		String apellido = "";
		String codigo = "";

		// Creamos un objeto de tipo usuario
		do {
			String[] botonesDatos = { "Borrar por codigo", "Borrar por apellido", "salir" };

			// controladorUsuario.borrarUsuario(usuario);

			int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Cómo desea borrar?", " ",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
			switch (ventanaModificar) {
			case 0:
				boolean comprobacion = true;
				do {
					codigo = JOptionPane.showInputDialog("Introduce  el codigo del usuario a borrar");
					if (comprobarExistenciaUsuario(Integer.parseInt(codigo))) {
						JOptionPane.showMessageDialog(null, "El usuario buscado no existe.");

					} else {

						Usuario codUserBorrar = controladorUsuario.findByPK(Integer.parseInt(codigo));
						controladorUsuario.borrarUsuario(codUserBorrar);
						comprobacion = false;

					}
				} while (comprobacion);
				break;
			case 1:
				apellido = JOptionPane.showInputDialog("Introduce el apellido del usuario o usuarios a borrar\n"
						+ "!Cuidado se borraran todos los que tengan el mismo apellido¡");
				if (!apellido.equalsIgnoreCase("")) {
					Usuario userBorrarApellido = controladorUsuario.borrarByApellido((apellido));
					controladorUsuario.borrarUsuario(userBorrarApellido);
				}

				break;

			case 2:
				JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
				// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
				// del bucle
				repetir = false;

				break;
			}

			return usuario;
		} while (repetir);

	}

	public static boolean comprobarExistenciaUsuario(int codusuario) {

		try {
			controladorUsuario.findByPK(codusuario);
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}
