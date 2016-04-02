package dao;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Entry;

public class EntryDAO implements IEntryDAO {

	private static EntryDAO instance = null;

	@PersistenceContext(unitName = "Bloggie")
	private EntityManager em;

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bloggie");
	EntityManager ems = emf.createEntityManager();

	private EntryDAO() {

	}

	public static EntryDAO getInstance() {

		if (instance == null) {
			instance = new EntryDAO();
		}
		return instance;

	}

	public void addEntry(Entry entry) {
		if (entry != null) {
			em.persist(entry);
		}
	}

	public static void main(String[] args) {

		Entry ent = new Entry();
		EntryDAO ins = EntryDAO.getInstance();
		ent.setContent("alabala");
		Date data = new Date(65468433);
		ent.setDateOfWrire(data);
		ent.setTitle("kurec");

		ins.addEntry(ent);

	}
}
