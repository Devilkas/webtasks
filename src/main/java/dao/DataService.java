package dao;

import java.util.List;

import entity.*;
import exeptions.InvalidDataException;
import exeptions.WebtasksDataException;
import exeptions.WebtasksException;


public interface DataService extends IClosable {

	Account login(String username, String password, Integer role)
			throws InvalidDataException, WebtasksDataException, WebtasksException;

	List<Role> listRoles() throws WebtasksException;

	List<Discipline> listDisciplines() throws WebtasksException;

	List<Student> listStudent() throws WebtasksException;

	List<Term> listTerm() throws WebtasksException;

	List<Discipline> listTermDisciplines(int termId) throws WebtasksException;

	List<Mark> listMarks(int idStudent, Term term) throws WebtasksException;

	void addStudent(Student s) throws WebtasksException;

	void addDiscipline(Discipline dcp) throws WebtasksException;

	void addTerm(Term term, String[] dsp) throws WebtasksException;

	void addMark(Integer idStudent, Integer idTerm, Integer id, String marks)
			throws WebtasksException;

	void deleteStudents(List<Integer> idStudents) throws WebtasksException;

	void deleteDiscipline(Integer idDiscipline) throws WebtasksException;

	void deleteTerm(Integer idTerm) throws WebtasksException;

	void updateStudent(Student st) throws WebtasksException;

	void updateDiscipline(Discipline dcp) throws WebtasksException;

	void updateTerm(Term term, String[] dsp) throws WebtasksException;

	Student getStudent(int idStudent) throws WebtasksException;

	Discipline getDiscipline(int idDiscipline) throws WebtasksException;

	Term getTerm(int idTerm) throws WebtasksException;

	Role getRole(int idRole) throws WebtasksException;

}
