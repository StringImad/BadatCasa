package servicios;

import javax.swing.JOptionPane;

import controladores.BD_ControladorPasajeroCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import entidades.Pasajero;
import entidades.Usuario;

public class ServicioPasajero {
	private static BD_ControladorPasajeroCRUD controladorPasajero = new BD_ControladorPasajeroCRUD();
	private static BD_ControladorUsuarioCRUD controladorUsuario = new BD_ControladorUsuarioCRUD();

	public static Pasajero modificarPasajero(String codPasajero) {
		boolean repetir = true;
		do {


			// Creamos un objeto de tipo conductor
			Pasajero pasajero = new Pasajero();

			// declaracion de las variables que contendran los valores que van dentro de
			// cada campo
			String direccionRecogida = "";
						String[] botonesDatos = { "Cambiar direccion", "salir" };

			Pasajero userModificar = controladorPasajero.findByPK(Integer.parseInt(codPasajero));
			if (userModificar != null) {
				int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Qué desea modificar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
				switch (ventanaModificar) {
				case 0:
					direccionRecogida = JOptionPane.showInputDialog("Ingrese un nuevo nombre");
					userModificar.setDireccionRecogida(direccionRecogida);
					controladorPasajero.modifyPasajero(userModificar);
					break;
				
				case 1:
					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir a la pantalla de inicio.");
					// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;
				}
			}
			return pasajero;
		} while (repetir);
	}


	public static Pasajero InsertarDatosPasajero() {

		// Creamos un objeto de tipo usuario
		Pasajero pasajero = new Pasajero();

		// declaracion de las variables que contendran los valores que van dentro de
		// cada campo
		String codUsuario = "";

		String direccionRecogida = "";
		boolean comprobacion = true;

		do {
			codUsuario = JOptionPane.showInputDialog("Introduce el codigo de usuario al que vas a asociar el Pasajero"+"\n"+controladorUsuario.findAll());
			if (comprobarExistenciaUsuario(Integer.parseInt(codUsuario))) {
				JOptionPane.showMessageDialog(null, "El usuario introducido no existe.");

			} else {

				Usuario usuarioAsociado = controladorUsuario.findByPK(Integer.parseInt(codUsuario));
				pasajero.setUsuario(usuarioAsociado);
				comprobacion = false;

			}
		} while (comprobacion);
		
		direccionRecogida = JOptionPane.showInputDialog("Introduce la direccion de recogida");
		pasajero.setDireccionRecogida(direccionRecogida);
	
		controladorPasajero.createPasajero(pasajero);
		return pasajero;
	}

	public static Pasajero borrarConductor() {
		boolean repetir = false;
		Pasajero pasajero = new Pasajero();
		String codigo = "";

		// Creamos un objeto de tipo usuario
		do {
			String[] botonesDatos = { "Borrar por codigo", "salir" };

			// controladorUsuario.borrarUsuario(usuario);

			int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Cómo desea borrar?", " ",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
			switch (ventanaModificar) {
			case 0:
				boolean comprobacion = true;
				do {
					codigo = JOptionPane.showInputDialog("Introduce  el codigo del pasajero a borrar");
					if (comprobarExistenciaUsuario(Integer.parseInt(codigo))) {
						JOptionPane.showMessageDialog(null, "El pasajero buscado no existe.");

					} else {

						Pasajero codUserBorrar = controladorPasajero.findByPK(Integer.parseInt(codigo));
						controladorPasajero.borrarPasajero(codUserBorrar);
						comprobacion = false;

					}
				} while (comprobacion);
				break;
		

			case 2:
				JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
				// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
				// del bucle
				repetir = false;

				break;
			}

			return pasajero;
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
	
	public static boolean comprobarExistenciaPasajero(int codusuario) {

		try {
			controladorPasajero.findByPK(codusuario);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

}
