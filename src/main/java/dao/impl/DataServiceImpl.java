package dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.DataService;
import database.ConfigDatabase;
import entity.*;


import exeptions.InvalidDataException;
import exeptions.WebtasksException;
import org.apache.commons.lang.StringUtils;


public class DataServiceImpl implements DataService {

	@Override
	public Account login(String username, String password, Integer role)
			throws WebtasksException, InvalidDataException {
		List<Account> accaunts = listAccounts();
		for (Account a : accaunts) {
			if (StringUtils.equals(username, a.getUsername())) {
				if (StringUtils.equals(password, a.getPassword())) {
					for (Role r : a.getRoles()) {
						if (r.getId().equals(role)) {
							return a;
						}
					}
					throw new InvalidDataException("role");
				} else {
					throw new InvalidDataException("invPass");
				}
			}
		}
		throw new InvalidDataException("notFound");
	}

	@Override
	public List<Role> listRoles() throws WebtasksException {
		return ConfigDatabase.doQueryListRoles();

	}

	private List<Account> listAccounts() throws WebtasksException {
		List<Account> result = ConfigDatabase.doQueryListAccounts();
		for (Account a : result) {
			List<Integer> idRoles = ConfigDatabase.doQueryListAccountRoles(a
					.getId());
			List<Role> roles = new ArrayList<Role>();
			for (Integer i : idRoles) {
				Role role = this.getRole(i);
				roles.add(role);
			}
			a.setRoles(roles);
		}
		return result;
	}

	@Override
	public List<Student> listStudent() throws WebtasksException {
		return ConfigDatabase.doQueryListStudent(0);
	}

	@Override
	public List<Discipline> listDisciplines() throws WebtasksException {
		return ConfigDatabase.doQueryListDisciplines(0);
	}

	@Override
	public List<Discipline> listTermDisciplines(int termId)
			throws WebtasksException {
		List<Integer> idDisc = ConfigDatabase.doQueryListIdDisciplines(termId);
		List<Discipline> result = new ArrayList<Discipline>();
		for (Iterator<Integer> iter = idDisc.iterator(); iter.hasNext();) {
			Discipline dsp = getDiscipline(iter.next());
			result.add(dsp);
		}
		return result;
	}

	@Override
	public List<Mark> listMarks(int idStudent, Term term)
			throws WebtasksException {
		List<Mark> marks = new ArrayList<Mark>();
		List<Discipline> disciplines = listTermDisciplines(term.getId());
		for (Iterator<Discipline> iter = disciplines.iterator(); iter.hasNext();) {
			Discipline dsp = iter.next();
			if (dsp != null){
				Mark mark = ConfigDatabase.doQueryMark(idStudent, term.getId(),
						dsp.getId());
				mark.setDiscipline(dsp);
				mark.setTerm(term);
				marks.add(mark);
			}
			
		}
		return marks;
	}

	@Override
	public List<Term> listTerm() throws WebtasksException {
		return ConfigDatabase.doQueryListTerms(0);
	}

	@Override
	public void addStudent(Student s) throws WebtasksException {
		String[] values = { s.getLastName(), s.getFirstName(), s.getGroup(),
				s.getDate() };
		ConfigDatabase
				.runStatement(
						"insert into students(last_name, first_name, groupSt, dateSt) values (?, ?, ?, ?)",
						values);
	}

	@Override
	public void addDiscipline(Discipline dcp) throws WebtasksException {
		String[] values = { dcp.getName() };
		ConfigDatabase.runStatement(
				"insert into disciplines(discipline_name) values (?)", values);
	}

	@Override
	public void addTerm(Term term, String[] dsp) throws WebtasksException {
		ConfigDatabase.doQueryAddTerms(term, dsp);

	}

	@Override
	public void addMark(Integer idStudent, Integer idTerm,
			Integer idDiscipline, String marks) throws WebtasksException {
		
		Integer ids_pair = ConfigDatabase.doQueryListPareTermDisciplide(idTerm, idDiscipline);
		String[] values = { idStudent.toString(), ids_pair.toString(), marks };
		ConfigDatabase
				.runStatement(
						"insert into student_term_marks (id_student, id_pair_term_discipline, mark) values (?, ?, ?)",
						values);

	}

	@Override
	public void deleteStudents(List<Integer> idStudents)
			throws WebtasksException {
		for (Iterator<Integer> iter = idStudents.iterator(); iter.hasNext();) {
			String[] values = { iter.next().toString() };
			ConfigDatabase.runStatement(
					"DELETE FROM students WHERE id_student = ?", values);
			ConfigDatabase.runStatement(
					"DELETE FROM student_term_marks WHERE id_student = ?", values);
		}
	}

	@Override
	public void deleteDiscipline(Integer idDiscipline) throws WebtasksException {
		String[] values = { idDiscipline.toString() };
		ConfigDatabase.runStatement(
				"DELETE FROM student_term_marks where id_pair_term_discipline in (select id_pair_term_discipline from terms_disciplines where id_discipline = ?)", values);
		ConfigDatabase.runStatement(
				"DELETE FROM terms_disciplines where id_discipline = ?", values);
		ConfigDatabase.runStatement(
				"DELETE FROM disciplines where id_discipline = ?", values);
	}

	@Override
	public void deleteTerm(Integer idTerm) throws WebtasksException {
		String[] values = { idTerm.toString() };
		ConfigDatabase.runStatement("DELETE FROM terms WHERE id_term = ?",
				values);
		ConfigDatabase.runStatement(
				"DELETE FROM student_term_marks where id_pair_term_discipline in (select id_pair_term_discipline from terms_disciplines where id_term = ?)", values);

	}

	@Override
	public Student getStudent(int idStudent) throws WebtasksException {
		List<Student> st = ConfigDatabase.doQueryListStudent(idStudent);
		Student result = null;
		for (Iterator<Student> iter = st.iterator(); iter.hasNext();) {
			result = iter.next();
		}
		return result;
	}

	@Override
	public Role getRole(int idRole) throws WebtasksException {
		List<Role> roles = ConfigDatabase.doQueryListRole(idRole);
		Role result = null;
		for (Iterator<Role> iter = roles.iterator(); iter.hasNext();) {
			result = iter.next();
		}
		return result;

	}

	@Override
	public Discipline getDiscipline(int idDiscipline) throws WebtasksException {
		List<Discipline> dcp = ConfigDatabase
				.doQueryListDisciplines(idDiscipline);
		Discipline result = null;
		for (Iterator<Discipline> iter = dcp.iterator(); iter.hasNext();) {
			result = iter.next();
		}
		return result;
	}

	@Override
	public Term getTerm(int idTerm) throws WebtasksException {
		List<Term> term = ConfigDatabase.doQueryListTerms(idTerm);
		Term result = null;
		for (Iterator<Term> iter = term.iterator(); iter.hasNext();) {
			result = iter.next();
		}
		return result;
	}

	@Override
	public void updateStudent(Student st) throws WebtasksException {
		String[] values = { st.getLastName(), st.getFirstName(), st.getGroup(),
				st.getDate(), st.getId().toString() };
		ConfigDatabase
				.runStatement(
						"UPDATE students SET last_name = ?, first_name = ?, groupSt = ?, dateSt = ? WHERE id_student= ?;",
						values);
	}

	@Override
	public void updateDiscipline(Discipline dcp) throws WebtasksException {
		String[] values = { dcp.getName(), dcp.getId().toString() };
		ConfigDatabase
				.runStatement(
						"UPDATE disciplines SET discipline_name = ? WHERE id_discipline = ?;",
						values);
	}

	@Override
	public void updateTerm(Term term, String[] dsp) throws WebtasksException {
		String[] values = { term.getDuration().toString(),
				term.getId().toString() };
		String[] deleteValues = { term.getId().toString() };
		ConfigDatabase.runStatement(
				"UPDATE terms SET duration =? WHERE id_term = ?;", values);
		ConfigDatabase
				.runStatement(
						"DELETE FROM terms_disciplines WHERE id_term = ?",
						deleteValues);

		for (int i = 0; i < dsp.length; i++) {
			String[] values2 = { term.getId().toString(), (dsp[i]) };
			ConfigDatabase
					.runStatement(
							"insert into terms_disciplines (id_term, id_discipline) values (?, ?)",
							values2);
		}

	}

	@Override
	public void close() {

	}

}
