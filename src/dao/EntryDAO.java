package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Entry;

public class EntryDAO implements IEntryDAO {

	private static EntryDAO instance = null;

	@PersistenceContext
    private EntityManager em;
	
	private EntryDAO() {

	}

	public static EntryDAO getInstance() {

		if (instance == null) {
			instance = new EntryDAO();
		}
		return instance;

	}
	
	public void addEntry(Entry entry){
		
	}

}
