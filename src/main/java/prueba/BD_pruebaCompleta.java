package prueba;

import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.List;

import controladores.BD_ControladorConductorCRUD;
import controladores.BD_ControladorPasajeroCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import controladores.BD_ControladorViajeCRUD;
import entidades.Conductor;
import entidades.Pasajero;
import entidades.Usuario;
import servicios.ServicioUsuario;

public class BD_pruebaCompleta {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		// TODO Auto-generated method stub
		BD_ControladorUsuarioCRUD cu = new BD_ControladorUsuarioCRUD();
		BD_ControladorViajeCRUD cv = new BD_ControladorViajeCRUD();
		BD_ControladorPasajeroCRUD cp = new BD_ControladorPasajeroCRUD();
		BD_ControladorConductorCRUD cc = new BD_ControladorConductorCRUD();
		List<Conductor> listaVehiculos = cc.findAll();
		List<Usuario> listaUsuarios = cu.findAll();
		boolean repetir = true;
		String codigo = "";
		String[] botones = { "Mostrar", "Modificar", "Crear", "Borrar", "salir" };
		String[] botonesEntidades = { "Usuarios", "Pasajeros", "Conductores", "Viajes", "salir" };
		do {
			int ventana = JOptionPane.showOptionDialog(null, "Bienvenido al sistema, ¿Qué desea realizar?", " ",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);
			switch (ventana) {
			case 0: {

				int ventanaMostrar = JOptionPane.showOptionDialog(null, "¿Qué desea mostrar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesEntidades,
						botonesEntidades[0]);
				switch (ventanaMostrar) {
				case 0:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes usuarios: \n" + cu.findAll());

					break;
				case 1:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes pasajeros: \n" + cp.findAll());
					break;
				case 2:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes conductores: \n" + cc.findAll());
					break;
				case 3:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes viajes: \n" + cv.findAll());
					break;
				case 4:

					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir a la pantalla de inicio.");
					// al pulsar en case 4 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;

				}
				break;
			}				

			case 1: {
				int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Qué desea modificar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesEntidades,
						botonesEntidades[0]);
				switch (ventanaModificar) {
				case 0:

					do {
						codigo = JOptionPane.showInputDialog(
								"Actualmente tenemos registrados los siguientes usuarios: \n" + cu.findAll()+
								"Escriba el codigo");

					} while (ServicioUsuario.comprobarExistenciaUsuario(Integer.parseInt(codigo)));
					ServicioUsuario.modificarUsuario(codigo);
					// ServicioUsuario.ModificarUsuario(Integer.parseInt(codigo));

					break;
				case 1:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes pasajeros: \n" + cp.findAll());
					break;
				case 2:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes conductores: \n" + cc.findAll());
					break;
				case 3:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes viajes: \n" + cv.findAll());
					break;
				case 4:

					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
					// al pulsar en case 4 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;

				}
				break;
			}
			case 2: {
				int ventanaCrear = JOptionPane.showOptionDialog(null, "¿Qué desea crear?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesEntidades,
						botonesEntidades[0]);
				switch (ventanaCrear) {
				case 0:
					JOptionPane.showMessageDialog(null,
							"Vamos a crear un usuario nuevo, actualmente tenemos registrados los siguientes: \n"
									+ cu.findAll());

					ServicioUsuario.InsertarDatosUsuario();

					break;
				case 1:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes pasajeros: \n" + cp.findAll());
					break;
				case 2:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes conductores: \n" + cc.findAll());
					break;
				case 3:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes viajes: \n" + cv.findAll());
					break;
				case 4:

					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
					// al pulsar en case 4 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;

				}
				break;
			}
			case 3: {
				int ventanaBorrar = JOptionPane.showOptionDialog(null, "¿Qué desea borrar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesEntidades,
						botonesEntidades[0]);
				switch (ventanaBorrar) {
				case 0:
					
						JOptionPane.showMessageDialog(null,
								"Actualmente tenemos registrados los siguientes usuarios: \n" + cu.findAll());

					ServicioUsuario.borrarUsuario();
					break;
				case 1:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes pasajeros: \n" + cp.findAll());
					break;
				case 2:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes conductores: \n" + cc.findAll());
					break;
				case 3:
					JOptionPane.showMessageDialog(null,
							"Actualmente tenemos registrados los siguientes viajes: \n" + cv.findAll());
					break;
				case 4:

					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
					// al pulsar en case 4 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;

				}
				break;
			}
			case 4:
				JOptionPane.showMessageDialog(null, "Pulse aceptar para salir del sistema.");
				// al pulsar en case 4 se pone reptir en false por lo tanto sale del programa y
				// del bucle
				repetir = false;
				break;
			}
		} while (repetir);
	}

	private static void imprimirConductoresDisponibles(List<Conductor> listaConductores) {
		for (Conductor conduct : listaConductores) {
			if (conduct.getDispo()) {
				System.out.println("\tNombre: " + conduct.getUsuario().getNombre() + "\tDireccion : "
						+ conduct.getUsuario().getDireccion());
			}

		}
	}

	private static void imprimirUsuarios(List<Usuario> listaUsuarios) {
		for (Usuario user : listaUsuarios) {
			System.out.println(user.toString());

		}
	}

	// Metodo que comprueba si la pk existe en la tabla Usuario

}
