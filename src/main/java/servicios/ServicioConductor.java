package servicios;

import javax.swing.JOptionPane;

import controladores.BD_ControladorConductorCRUD;
import controladores.BD_ControladorUsuarioCRUD;
import entidades.Conductor;
import entidades.Usuario;

public class ServicioConductor {
	private static BD_ControladorConductorCRUD controladorConductor = new BD_ControladorConductorCRUD();
	private static BD_ControladorUsuarioCRUD controladorUsuario = new BD_ControladorUsuarioCRUD();

	public static Conductor modificarConductor(String codconductor) {
		boolean repetir = true;
		do {


			// Creamos un objeto de tipo conductor
			Conductor conduct = new Conductor();

			// declaracion de las variables que contendran los valores que van dentro de
			// cada campo
			boolean disponible = true;
			boolean confimacionDisponiblidad = true;
			String numCuentBanc = "";
			String numSegSocial = "";
			String[] botonesDatos = { "¿Esta Disponible?", "numero cuenta bancaria", "numero SS", "salir" };

			Conductor userModificar = controladorConductor.findByPK(Integer.parseInt(codconductor));
			if (userModificar != null) {
				int ventanaModificar = JOptionPane.showOptionDialog(null, "¿Qué desea modificar?", " ",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botonesDatos, botonesDatos[0]);
				switch (ventanaModificar) {
				case 0:
					disponible = elegirDisponiblidadConductor();
					userModificar.setDispo(disponible);
					controladorConductor.modifyUsuario(userModificar);
					break;
				case 1:
					numCuentBanc = JOptionPane.showInputDialog("Ingrese un numero de cuenta nuevo");
					userModificar.setNumCuentBanc(numCuentBanc);
					controladorConductor.modifyUsuario(userModificar);
					break;
				case 2:
					numSegSocial =controladorNumSSCorrecto();
					userModificar.setNumSegSocial(numSegSocial);
					controladorConductor.modifyUsuario(userModificar);
					break;
				
				case 3:
					JOptionPane.showMessageDialog(null, "Pulse aceptar para salir a la pantalla de inicio.");
					// al pulsar en case 5 se pone reptir en false por lo tanto sale del programa y
					// del bucle
					repetir = false;

					break;
				}
			}
			return conduct;
		} while (repetir);
	}

	// metodo que devuelve un string que corresponde a un dni correcto 12345678M
	private static String controladorNumSSCorrecto() {
		boolean comprobacion = false;
		String numSegSocial = "";
		do {
			numSegSocial = JOptionPane.showInputDialog("Ingrese el numero de seguridad social");
//Condicion que nos controla que el numero de caracteres no sea distinto a nueve 
			// y que el caracter en la posicion 8 es decir el noveno sea una letra
			if (numSegSocial.length() < 9 || numSegSocial.length() > 15) {
				JOptionPane.showMessageDialog(null, "Numero incorrecto, el numero de caracteres debe estar entre 9 y 15 :");
				comprobacion = true;
			} else {
				comprobacion = false;
			}
		} while (comprobacion);

		return numSegSocial;
	}


	public static Conductor InsertarDatosConductor() {

		// BD_ControladorUsuarioCRUD controladorUsuario = new
		// BD_ControladorUsuarioCRUD();

		// Creamos un objeto de tipo usuario
		Conductor conductor = new Conductor();

		// declaracion de las variables que contendran los valores que van dentro de
		// cada campo
		String codUsuario = "";
		boolean disponiblidad = false;
		String numSegSocial = "";
		String numCuentBanc = "";
		boolean comprobacion = true;

		do {
			codUsuario = JOptionPane.showInputDialog("Introduce el codigo de usuario al que vas a asociar el conductor"+"\n"+controladorUsuario.findAll());
			if (comprobarExistenciaUsuario(Integer.parseInt(codUsuario))) {
				JOptionPane.showMessageDialog(null, "El usuario introducido no existe.");

			} else {

				Usuario usuarioAsociado = controladorUsuario.findByPK(Integer.parseInt(codUsuario));
				conductor.setUsuario(usuarioAsociado);
				comprobacion = false;

			}
		} while (comprobacion);
		
		disponiblidad = elegirDisponiblidadConductor();
		numSegSocial = JOptionPane.showInputDialog("Introduce el numero de la seguridad social");
		numCuentBanc = JOptionPane.showInputDialog("Introduce el numero de la seguridad social");
		conductor.setNumSegSocial(numSegSocial);
		conductor.setNumCuentBanc(numCuentBanc);
		conductor.setDispo(disponiblidad);
		controladorConductor.createUsuario(conductor);
		return conductor;
	}

	public static Conductor borrarConductor() {
		boolean repetir = false;
		Conductor conductor = new Conductor();
		String apellido = "";
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
					codigo = JOptionPane.showInputDialog("Introduce  el codigo del conductor a borrar");
					if (comprobarExistenciaUsuario(Integer.parseInt(codigo))) {
						JOptionPane.showMessageDialog(null, "El conductor buscado no existe.");

					} else {

						Conductor codUserBorrar = controladorConductor.findByPK(Integer.parseInt(codigo));
						controladorConductor.borrarConductor(codUserBorrar);
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

			return conductor;
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
	
	public static boolean comprobarExistenciaConductor(int codusuario) {

		try {
			controladorConductor.findByPK(codusuario);
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	public static boolean elegirDisponiblidadConductor() {
		boolean confimacionDisponiblidad =  true;
		int disponible = 0;
		disponible = JOptionPane.showConfirmDialog(null,
                "¿Está disponible?", "Seleccione una",
                JOptionPane.YES_NO_OPTION);
		if(disponible==0) {
			confimacionDisponiblidad = true;
		}else {
			if(disponible==1) {
				confimacionDisponiblidad = false;
			}
		}
		return confimacionDisponiblidad;
		
	}
}
