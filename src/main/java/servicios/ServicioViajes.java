package servicios;

import javax.swing.JOptionPane;

import controladores.BD_ControladorConductorCRUD;
import controladores.BD_ControladorPasajeroCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import controladores.BD_ControladorViajeCRUD;
import entidades.Conductor;
import entidades.Pasajero;
import entidades.Usuario;
import entidades.Viaje;

public class ServicioViajes {
	private static BD_ControladorPasajeroCRUD controladorPasajero = new BD_ControladorPasajeroCRUD();
	private static BD_ControladorUsuarioCRUD controladorUsuario = new BD_ControladorUsuarioCRUD();
	private static BD_ControladorConductorCRUD controladorConductor = new BD_ControladorConductorCRUD();
	private static BD_ControladorViajeCRUD controladorViaje = new BD_ControladorViajeCRUD();

	public static Viaje modificarViaje(String codViaje) {
		boolean repetir = true;
		do {

			// Creamos un objeto de tipo conductor
			Viaje viaje = new Viaje();
			String codUsuario = "";
			String precio = "";
			boolean comprobacion = true;

			// declaracion de las variables que contendran los valores que van dentro de
			// cada campo
			String direccionRecogida = "";
			String tiempo ="";
			String tipoPago="";
			String[] botonesDatos = { "codigo conductor","codigo pasajero","precio","Destino","tiempo","tipo pago", "salir" };
			Conductor conductorAsociado = new Conductor();
			Pasajero pasajeroAsociado = new Pasajero();
			Viaje viajeModificar = controladorViaje.findByPK(Integer.parseInt(codViaje));
			if (viajeModificar != null) {
				int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Qué desea modificar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
				switch (ventanaModificar) {
				case 0:

					do {
						codUsuario = JOptionPane
								.showInputDialog("Introduce el codigo de conductor al que vas a asociar el viaje" + "\n"
										+ controladorConductor.findAll());
						if (comprobarExistenciaConductor(Integer.parseInt(codUsuario))) {
							JOptionPane.showMessageDialog(null, "El conductor introducido no existe.");

						} else {

							conductorAsociado = controladorConductor.findByPK(Integer.parseInt(codUsuario));
							if (conductorAsociado.getUsuario().getCodusuario()==pasajeroAsociado.getUsuario().getCodusuario()) {
								JOptionPane.showMessageDialog(null,
										"El conductor y pasajero no  pueden ser el mismo usuario.");

							} else {
								viajeModificar.setConductor(conductorAsociado);
								controladorViaje.modifyViaje(viajeModificar);

								comprobacion = false;
							}
							

						}
					} while (comprobacion);
					break;
				case 1:

					do {
						codUsuario = JOptionPane
								.showInputDialog("Introduce el codigo de pasajero al que vas a asociar el viaje" + "\n"
										+ controladorPasajero.findAll());
						if (comprobarExistenciaPasajero(Integer.parseInt(codUsuario))) {
							JOptionPane.showMessageDialog(null, "El pasajero introducido no existe.");

						} else {
							 pasajeroAsociado = controladorPasajero.findByPK(Integer.parseInt(codUsuario));

							if (pasajeroAsociado.getUsuario().equals(conductorAsociado.getUsuario())) {
								JOptionPane.showMessageDialog(null,
										"El conductor y pasajero no  pueden ser el mismo usuario.");

							} else {
								//viaje.setPasajero(pasajeroAsociado);
								viajeModificar.setPasajero(pasajeroAsociado);
								comprobacion = false;
								controladorViaje.modifyViaje(viajeModificar);

							}

						}
					} while (comprobacion);
					break;
				case 2:
					precio = JOptionPane.showInputDialog("Introduce el precio del viaje");
					viajeModificar.setPrecio(Double.parseDouble(precio));
					controladorViaje.modifyViaje(viajeModificar);

					break;
				case 3:
					direccionRecogida = JOptionPane.showInputDialog("Introduce el destino del viaje");
					viajeModificar.setPuntorecogida((precio));
					controladorViaje.modifyViaje(viajeModificar);

					break;
				case 4:
				tiempo = JOptionPane.showInputDialog("Introduce el tiempo estimado del viaje");
				viajeModificar.setTiempo(Integer.parseInt(tiempo));
					controladorViaje.modifyViaje(viajeModificar);

					break;
				case 5:
					tipoPago = JOptionPane.showInputDialog("Introduce el tipo de pago del viaje");
					viajeModificar.setTipopago((tipoPago));
					controladorViaje.modifyViaje(viajeModificar);

					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir a la pantalla de inicio.");
					// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;
				}
			}
			return viaje;
		} while (repetir);
	}

	public static Viaje InsertarDatosViaje() {

		// Creamos un objeto de tipo usuario
		Pasajero pasajeroAsociado = new Pasajero();
		Conductor conductorAsociado = new Conductor();
		Viaje viaje = new Viaje();
		// declaracion de las variables que contendran los valores que van dentro de
		// cada campo
		String codUsuario = "";
		String precio = "";
		String direccionRecogida = "";
		boolean comprobacion = true;

		do {
			codUsuario = JOptionPane.showInputDialog("Introduce el codigo de pasajero al que vas a asociar el viaje"
					+ "\n" + controladorPasajero.findAll());
			if (comprobarExistenciaPasajero(Integer.parseInt(codUsuario))) {
				JOptionPane.showMessageDialog(null, "El pasajero introducido no existe.");

			} else {
				pasajeroAsociado = controladorPasajero.findByPK(Integer.parseInt(codUsuario));

				if (pasajeroAsociado.getUsuario().equals(conductorAsociado.getUsuario())) {
					JOptionPane.showMessageDialog(null, "El conductor y pasajero no  pueden ser el mismo usuario.");

				} else {
					viaje.setPasajero(pasajeroAsociado);
					comprobacion = false;
				}

			}
		} while (comprobacion);

		do {
			codUsuario = JOptionPane.showInputDialog("Introduce el codigo de conductor al que vas a asociar el viaje"
					+ "\n" + controladorConductor.findAll());
			if (comprobarExistenciaConductor(Integer.parseInt(codUsuario))) {
				JOptionPane.showMessageDialog(null, "El conductor introducido no existe.");

			} else {

				conductorAsociado = controladorConductor.findByPK(Integer.parseInt(codUsuario));
				viaje.setConductor(conductorAsociado);
				comprobacion = false;

			}
		} while (comprobacion);

		precio = JOptionPane.showInputDialog("Introduce el precio del viaje");
		viaje.setPrecio(Double.parseDouble(precio));

		controladorViaje.createViaje(viaje);
		return viaje;
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
					if (comprobarExistenciaPasajero(Integer.parseInt(codigo))) {
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

	public static boolean comprobarExistenciaViaje(int codviaje) {

		try {
			controladorViaje.findByPK(codviaje);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean comprobarExistenciaConductor(int codusuario) {

		try {
			controladorConductor.findByPK(codusuario);
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
