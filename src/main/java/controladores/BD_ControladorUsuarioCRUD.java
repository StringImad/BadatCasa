package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import entidades.Usuario;

public class BD_ControladorUsuarioCRUD {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_Imad_Movimad");
	private EntityManager em;
	private Query consulta;
	public void borrarUsuario(Usuario user) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null;
		this.em.getTransaction().begin();
		// Si user no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(user)) {
			// Carga user en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(user);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(aux);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}
	
	public Usuario borrarByApellido(String apellido) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null; 
		this.em.getTransaction().begin();
		
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Delete * from usuario where apellido = ?", Usuario.class);
		this.consulta.setParameter(1, apellido);
		aux = (Usuario) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	public void modifyUsuario(Usuario user) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que user existe en
		// el
		// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
		// como entidad gestionada
		// Si el objeto user no existe en la base de datos, se comporta como persist() y
		// la
		// entidad gestionada es la devuelta por merge(), por lo que user es una entidad
		// desconectada
		this.em.merge(user);
		this.em.getTransaction().commit();
		this.em.close();

	}

	public void createUsuario(Usuario user) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// user es una entidad conectada
		this.em.persist(user);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	public Usuario findByPK(int codusuario) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null; 
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from usuario where codusuario = ?", Usuario.class);
		this.consulta.setParameter(1, codusuario);
		aux = (Usuario) consulta.getSingleResult();
		this.em.close();
		return aux;

		
	}
	
	public Usuario findByDni(int dni) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null; 
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from usuario where dni = ?", Usuario.class);
		this.consulta.setParameter(1, dni);
		aux = (Usuario) consulta.getSingleResult();
		this.em.close();
		return aux;

		
	}

	public Usuario findByApellido(String apellido) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findApellido");
		this.consulta.setParameter("apellido", apellido);
		Usuario user = (Usuario) consulta.getSingleResult();
		this.em.close();
		return user;
	}

	public List<Usuario> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findAll");
		List<Usuario> listaUsuarios = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuarios;
	}

	// En este caso se va a utilizar una nativeQuery, que permite pasar código
	// SQL directamente a la base de datos
	public List<Usuario> findByNombre(String nombre) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from usuario where nombre=?", Usuario.class);
		this.consulta.setParameter(1, nombre);
		List<Usuario> listaUsuarios = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuarios;
	}
	
	public List<Usuario> buscarByApellido(String apellido) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNativeQuery("Select * from usuario where apellido=?", Usuario.class);
		this.consulta.setParameter(1, apellido);
		List<Usuario> listaUsuarios = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuarios;
	}
}
