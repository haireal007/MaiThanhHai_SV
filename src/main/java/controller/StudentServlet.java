package controller;

import dao.ClassStudentDao;
import dao.StudentDao;
import model.ClassStudent;
import model.Login;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    StudentDao studentDao = new StudentDao();
    ClassStudentDao classStudentDao = new ClassStudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "singout":
                singout(req,resp);
                break;
            default:
                show(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                int id = studentDao.getAll().size() + 1;
                String name = request.getParameter("name");
                LocalDate birth = LocalDate.parse(request.getParameter("birth"));
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                int idClass = Integer.parseInt(request.getParameter("class"));

                Student st = new Student(id, name, birth, address, phone, email, classStudentDao.findById(idClass));
                studentDao.create(st);
                resp.sendRedirect("/student");
                break;
            case "edit":
                int ide = Integer.parseInt(request.getParameter("id"));
                String namee = request.getParameter("name");
                LocalDate birthe = LocalDate.parse(request.getParameter("birth"));
                String addresse = request.getParameter("address");
                String phonee = request.getParameter("phone");
                String emaile = request.getParameter("email");
                int idClasse = Integer.parseInt(request.getParameter("class"));

                Student ste = new Student(ide, namee, birthe, addresse, phonee, emaile, classStudentDao.findById(idClasse));
                studentDao.edit(ide,ste);
                resp.sendRedirect("/student");
                break;
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("classStudent", classStudentDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        req.setAttribute("students", studentDao.getAllByName(search));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentDao.getAll();
        int id = Integer.parseInt(req.getParameter("id"));
        studentDao.delete(id);
        req.setAttribute("classStudent", students);
        resp.sendRedirect("/student");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDao.findById(id);
        List<ClassStudent> classStudent = classStudentDao.getAll();
        req.setAttribute("student",student );
        req.setAttribute("classStudent",classStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(req, resp);
    }
    private void singout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Login.account = null;
        resp.sendRedirect("/student");
    }
}
