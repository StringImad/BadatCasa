package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Viaje;

public class BD_ControladorViajeCRUD {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bd_Imad_Movimad");
	private EntityManager em;
	private Query consulta;
	public void borrarViaje(Viaje user) {
		this.em = entityManagerFactory.createEntityManager();
		Viaje aux = null;
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

	public void modifyViaje(Viaje user) {
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

	public void createViaje(Viaje user) {
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

	public Viaje findByPK(int codconductor) {
		this.em = entityManagerFactory.createEntityManager();
		Viaje aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from viaje where codviaje = ?", Viaje.class);
		this.consulta.setParameter(1, codconductor);
		aux = (Viaje) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	public Viaje findByCodConductor(int codviaje) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Viaje.findCodViaje");
		this.consulta.setParameter("codviaje", codviaje);
		Viaje user = (Viaje) consulta.getSingleResult();
		this.em.close();
		return user;
	}

	public List<Viaje> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Viaje.findAll");
		List<Viaje> listaViajes = (List<Viaje>) consulta.getResultList();
		this.em.close();
		return listaViajes;
	}


}
