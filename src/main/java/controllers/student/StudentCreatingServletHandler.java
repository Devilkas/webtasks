package controllers.student;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Student;
import exeptions.InvalidDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentCreatingServletHandler extends
        AbstractWebtasksServletHandler {
    private static final long serialVersionUID = 4628130653812142355L;

    @Override
    protected void handleRequest(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            request.setAttribute(CURRENT_BUTTON, "1");
            gotoToJSP("main/student/studentsCreatingModifying.jsp", request,
                    response);
        } else {
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String group = request.getParameter("group");
            String dateString = request.getParameter("date");
            try {
                validateRequestStudent(lastName, firstName, group, dateString);
                Student std = new Student(firstName, lastName, group,
                        dateString);
                getDataService().addStudent(std);
                String mapping = request.getSession()
                        .getAttribute("CURRENT_MAPPING").toString();
                redirectRequest(mapping + "/studentsList.php", request,
                        response);

            } catch (InvalidDataException e) {
                request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
                        e.getMessage());
                request.setAttribute(CURRENT_BUTTON, "1");
                gotoToJSP("main/student/studentsCreatingModifying.jsp",
                        request, response);
            }

        }

    }

}
