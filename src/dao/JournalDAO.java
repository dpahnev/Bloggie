package dao;

public class JournalDAO implements IJournalDAO{

	private static JournalDAO instance = null;

	private JournalDAO() {

	}

	public static JournalDAO getInstance() {

		if (instance == null) {
			instance = new JournalDAO();
		}
		return instance;

	}
	
}
