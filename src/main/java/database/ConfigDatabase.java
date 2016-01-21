package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.*;


import exeptions.WebtasksException;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;

public class ConfigDatabase {

	private static final Logger LOGGER = Logger.getLogger(ConfigDatabase.class);

	public static Connection createConnection() throws WebtasksException {
		Connection conn = null;
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:database/myDatabase");
		ds.setUser("sa");
		ds.setPassword("sa");
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.debug("SQLException: " + e.getMessage(), e);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOGGER.debug("SQLException: " + e.getMessage(), e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.debug("SQLException: " + e.getMessage(), e);
			}
		}
	}

	public static void closeConnState(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOGGER.debug("SQLException: " + e.getMessage());
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.debug("SQLException: " + e.getMessage());
			}
		}
	}

	public static void runStatement(String sqlQuery, Object[] values)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = createConnection();
			pstmt = conn.prepareStatement(sqlQuery);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setString(i + 1, values[i].toString());
				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public static void runStatementDropTable(String tableName)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = createConnection();
			pstmt = conn.prepareStatement("DROP TABLE " + tableName);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeConnState(pstmt, conn);
		}
	}

	public static List<Student> doQueryListStudent(int idStudent)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> result = null;
		try {
			conn = createConnection();
			String query;
			if (idStudent == 0) {
				query = "SELECT * FROM students";
				pstmt = conn.prepareStatement(query);
			} else {
				query = "SELECT * FROM students WHERE id_student = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idStudent);
			}
			rs = pstmt.executeQuery();
			result = new ArrayList<Student>();
			while (rs.next()) {
				Student st = new Student();
				st.setId(rs.getInt("id_student"));
				st.setLastName(rs.getString("last_name"));
				st.setFirstName(rs.getString("first_name"));
				st.setGroup(rs.getString("groupSt"));
				st.setDate(rs.getString("dateSt"));
				result.add(st);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static Mark doQueryMark(int idStudent, int idTerm, int idDiscipline)
			throws WebtasksException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Mark result = new Mark();
		try {
			conn = createConnection();
			String query1 =  "SELECT * FROM terms_disciplines WHERE id_term = ? and id_discipline = ?";
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, idTerm);
			pstmt.setInt(2, idDiscipline);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idTermDisc = rs.getInt(1);
				String query = "SELECT * FROM student_term_marks WHERE id_student = ? and id_pair_term_discipline = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idStudent);
				pstmt.setInt(2, idTermDisc);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int markValue = rs.getInt("mark");
					result.setId(markValue);
					result.setValue(markValue);
					return result;
				}
			}
			
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Discipline> doQueryListDisciplines(int idDiscipline)
			throws WebtasksException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Discipline> result = null;
		try {
			conn = createConnection();
			String query;
			if (idDiscipline == 0) {
				query = "SELECT * FROM disciplines";
				pstmt = conn.prepareStatement(query);
			} else {
				query = "SELECT * FROM disciplines WHERE id_discipline = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idDiscipline);
			}
			rs = pstmt.executeQuery();
			result = new ArrayList<Discipline>();
			while (rs.next()) {
				Discipline ds = new Discipline();
				ds.setId(rs.getInt("id_discipline"));
				ds.setName(rs.getString("discipline_name"));
				result.add(ds);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Integer> doQueryListIdDisciplines(int termId)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> result = new ArrayList<Integer>();
		try {
			conn = createConnection();
			String query = "SELECT id_term, id_discipline FROM terms_disciplines WHERE id_term = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, termId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int idDisc = rs.getInt("id_discipline");
				result.add(idDisc);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Term> doQueryListTerms(int IdTerm)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Term> result = null;
		try {
			conn = createConnection();
			String query;
			if (IdTerm == 0) {
				query = "SELECT * FROM terms";
				pstmt = conn.prepareStatement(query);
			} else {
				query = "SELECT id_term, duration FROM terms WHERE id_term = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, IdTerm);
			}
			rs = pstmt.executeQuery();
			result = new ArrayList<Term>();
			while (rs.next()) {
				Term term = new Term();
				Integer id = rs.getInt("id_term");
				term.setId(id);
				term.setName(id.toString());
				term.setDuration(rs.getInt("duration"));
				result.add(term);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Account> doQueryListAccounts() throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Account> result = null;
		try {
			conn = createConnection();
			String query = "SELECT * FROM accounts ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			result = new ArrayList<Account>();
			while (rs.next()) {
				Account account = new Account();
				int id = rs.getInt("id_account");
				account.setId(id);
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				result.add(account);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Role> doQueryListRoles() throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Role> result = null;
		try {
			conn = createConnection();
			String query = "SELECT * FROM roles ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			result = new ArrayList<Role>();
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id_role"));
				role.setName(rs.getString("role_name"));
				result.add(role);
			}
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}
	
	public static Integer doQueryListPareTermDisciplide(Integer idTerm, Integer idDiscipline) throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = createConnection();
			String query = "SELECT * FROM terms_disciplines WHERE id_term = ? and id_discipline = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idTerm);
			pstmt.setInt(2, idDiscipline);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id_pair_term_discipline");
				return id;
			}
			throw new WebtasksException("data not found");
		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
	}


	public static List<Integer> doQueryListAccountRoles(int idAccount)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> result = null;
		try {
			conn = createConnection();
			String query = "SELECT * FROM accounts_roles WHERE id_account = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idAccount);
			rs = pstmt.executeQuery();
			result = new ArrayList<Integer>();
			while (rs.next()) {
				result.add(rs.getInt("id_role"));
			}

		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static List<Role> doQueryListRole(int idRole)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Role> result = null;
		try {
			conn = createConnection();
			String query;
			if (idRole == 0) {
				query = "SELECT * FROM roles";
				pstmt = conn.prepareStatement(query);
			} else {
				query = "SELECT * FROM roles WHERE id_role = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idRole);
			}
			rs = pstmt.executeQuery();
			result = new ArrayList<Role>();
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id_role"));
				role.setName(rs.getString("role_name"));
				result.add(role);
			}

		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return result;
	}

	public static void doQueryAddTerms(Term term, String[] disciplines)
			throws WebtasksException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = createConnection();
			String query = "insert into terms (duration) values (?)";
			pstmt = conn.prepareStatement(query,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, term.getDuration());
			pstmt.executeUpdate();
			ResultSet results = pstmt.getGeneratedKeys();
			long id = 0;
			if (results.next()) {
				id = results.getLong(1);
			}

			for (int i = 0; i < disciplines.length; i++) {
				String query2 = "insert into terms_disciplines (id_term, id_discipline) values (?, ?)";
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, (int) id);
				pstmt.setInt(2, Integer.parseInt(disciplines[i]));
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			LOGGER.debug("SQLException: " + e.getMessage());
			throw new WebtasksException("SQLException: " + e.getMessage(), e);
		} finally {
			closeAll(rs, pstmt, conn);
		}
	}

	public static void createTables() {

		try {
			runStatement(
					"create table students (id_student varchar AUTO_INCREMENT, last_name varchar(50) NOT NULL, first_name varchar (50) NOT NULL, groupSt varchar (20) NOT NULL, dateSt varchar(50) NOT NULL, PRIMARY KEY (id_student)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table disciplines (id_discipline varchar (10) AUTO_INCREMENT, discipline_name varchar(100) NOT NULL, PRIMARY KEY (id_discipline)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table terms (id_term varchar (10) AUTO_INCREMENT,  duration varchar (10) NOT NULL, PRIMARY KEY (id_term)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table terms_disciplines (id_pair_term_discipline varchar (10) AUTO_INCREMENT, id_term varchar (10) NOT NULL, id_discipline varchar (10) NOT NULL, PRIMARY KEY (id_pair_term_discipline)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table accounts (id_account varchar (10) AUTO_INCREMENT, username varchar(100) NOT NULL, password varchar (10) NOT NULL, PRIMARY KEY (id_account)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table roles (id_role varchar (10) AUTO_INCREMENT, role_name varchar(100) NOT NULL, PRIMARY KEY (id_role));",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table accounts_roles (id_accounts_roles varchar (10) AUTO_INCREMENT, id_account varchar(100) NOT NULL, id_role varchar(100) NOT NULL,PRIMARY KEY (id_accounts_roles));",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatement(
					"create table student_term_marks (id_student_term_marks varchar(10) AUTO_INCREMENT, id_student varchar (100) NOT NULL, id_pair_term_discipline varchar(10) NOT NULL, mark varchar (10) NULL, PRIMARY KEY (id_student_term_marks)); ",
					null);
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

	}

	public static void fillTables() throws WebtasksException {
		String st1[] = { "Student1", "Student1", "e-21", "01/09/2011" };
		String st2[] = { "Student1", "Student1", "w-21", "01/09/2011" };
		String st3[] = { "Student1", "Student1", "t-21", "01/09/2011" };
		String st4[] = { "Student1", "Student1", "q-21", "01/09/2011" };
		String st5[] = { "Student1", "Student1", "v-21", "01/09/2011" };
		runStatement(
				"insert into students (last_name, first_name, groupSt, dateSt) values (?, ?, ? ,?)",
				st1);
		runStatement(
				"insert into students (last_name, first_name, groupSt, dateSt) values (?, ?, ? ,?)",
				st2);
		runStatement(
				"insert into students (last_name, first_name, groupSt, dateSt) values (?, ?, ? ,?)",
				st3);
		runStatement(
				"insert into students (last_name, first_name, groupSt, dateSt) values (?, ?, ? ,?)",
				st4);
		runStatement(
				"insert into students (last_name, first_name, groupSt, dateSt) values (?, ?, ? ,?)",
				st5);

		String dsp1[] = { "Biologi" };
		String dsp2[] = { "Biologi" };
		String dsp3[] = { "Biologi" };
		String dsp4[] = { "Biologi" };
		String dsp5[] = { "Biologi" };
		String dsp6[] = { "Biologi" };
		String dsp7[] = { "Biologi" };
		String dsp8[] = { "Biologi" };
		String dsp9[] = { "Biologi" };
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp1);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp2);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp3);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp4);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp5);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp6);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp7);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp8);
		runStatement("insert into disciplines (discipline_name) values (?)",
				dsp9);

		String dr1[] = { "24" };
		String dr2[] = { "18" };
		runStatement("insert into terms (duration) values (?)", dr1);
		runStatement("insert into terms (duration) values (?)", dr2);

		String td1[] = {"1", "1" };
		String td2[] = {"1", "2" };
		String td3[] = {"1", "3" };
		String td4[] = {"1", "4" };
		String td5[] = {"1", "5" };
		String td6[] = {"2", "1" };
		String td7[] = {"2", "2" };
		String td8[] = {"2", "3" };
		String td9[] = {"2", "5" };
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td1);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td2);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td3);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td4);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td5);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td6);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td7);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td8);
		runStatement(
				"insert into terms_disciplines ( id_term, id_discipline) values (?, ?)",
				td9);

		String up1[] = { "admin", "password" };
		String up2[] = { "student", "password" };
		runStatement("insert into accounts (username, password) values (?, ?)",
				up1);
		runStatement("insert into accounts (username, password) values (?, ?)",
				up2);

		String r1[] = { "Admin" };
		String r2[] = { "Student" };
		runStatement("insert into roles (role_name) values (?)", r1);
		runStatement("insert into roles (role_name) values (?)", r2);

		String ar1[] = { "1", "1" };
		String ar2[] = { "1", "2" };
		String ar3[] = { "2", "2" };
		runStatement(
				"insert into accounts_roles (id_account, id_role) values (?, ?)",
				ar1);
		runStatement(
				"insert into accounts_roles (id_account, id_role) values (?, ?)",
				ar2);
		runStatement(
				"insert into accounts_roles (id_account, id_role) values (?, ?)",
				ar3);

		String stm1[] = { "1", "1", "4" };
		String stm2[] = { "1", "2", "3" };
		String stm3[] = { "1", "3", "4" };
		String stm4[] = { "1", "4", "3" };
		String stm5[] = { "1", "5", "5" };
		String stm6[] = { "1", "6", "3" };
		String stm7[] = { "1", "7", "5" };
		String stm8[] = { "1", "8", "5" };
		String stm9[] = { "1", "9", "2" };
		String stm10[] = { "1", "10", "3" };
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm1);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm2);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm3);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm4);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm5);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm6);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm7);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm8);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm9);
		runStatement(
				"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
				stm10);
	}

	public static void deleteTables() {

		try {
			runStatementDropTable("students");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("disciplines");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("terms_disciplines");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("terms");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("student_term_disciplines_marks");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("accounts");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("roles");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}

		try {
			runStatementDropTable("accounts_roles");
		} catch (WebtasksException e) {
			LOGGER.debug("SQLException: " + e.getMessage(), e);
		}
	}

}