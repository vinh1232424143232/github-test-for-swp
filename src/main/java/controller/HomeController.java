package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Student;

import java.util.ArrayList;
import java.util.List;
@Controller
public class HomeController {
    private List<Student> studentList;
    public HomeController() {
        studentList = new ArrayList<>();
    }
    @GetMapping("/")
    public ModelAndView showStudent(HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("studentList", studentList);
        return mav;
    }
    @RequestMapping(value="/manageStudent")
    public String managerStudent(HttpServletRequest req){
        String type= req.getParameter("btnManageStudent");
        int studentId = Integer.parseInt(req.getParameter("txtID"));
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String firstName = req.getParameter("txtFirstName");
        String lastName = req.getParameter("txtLastName");
        int marks = Integer.parseInt(req.getParameter("txtMark"));
        Student student = new Student(studentId, email, password, firstName, lastName, marks);
        switch (type) {
            case "add":
                studentList.add(student);
                break;
            case "update":
                int index = getStudentIndex(studentId);
                if(index != -1){
                    studentList.get(index).setEmail(email);
                    studentList.get(index).setPassword(password);
                    studentList.get(index).setFirstname(firstName);
                    studentList.get(index).setLastname(lastName);
                    studentList.get(index).setMarks(marks);
                }
                break;
            case "delete":
                int index2 = getStudentIndex(studentId);
                if(index2 != -1){
                    studentList.remove(index2);
                }
                break;
            default:
                break;
        }
        return "redirect:/";
        }
    public int getStudentIndex(int studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId() == studentId){
                return i;
            }
        }
        return -1;
    }



}
