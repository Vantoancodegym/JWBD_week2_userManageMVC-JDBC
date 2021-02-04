package controller;

import model.User;
import service.user.IUserService;
import service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private IUserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null)action="";
        switch (action){
            case "create":
                createNew(request,response);
                break;
            case "update":
                update(request,response);
            case "findEmail":
                Find(request,response);
                break;
        }
    }

    private void Find(HttpServletRequest request, HttpServletResponse response) {
        String email=request.getParameter("email");
        RequestDispatcher dispatcher;
        User user=userService.findByEmail(email);
        if (user==null){
            dispatcher=request.getRequestDispatcher("notFound.jsp");
        }else {
            request.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("findByEmail.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        User user=new User(id,name,email);
        boolean check=userService.update(id,user);
        RequestDispatcher dispatcher;
        if (check){
            showList(request,response,userService.findAll());
        }else {
            dispatcher=request.getRequestDispatcher("notFound.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void createNew(HttpServletRequest request, HttpServletResponse response) {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        int id= (int) (Math.random()*100000);
        User user=new User(id,name,email);
        boolean check=userService.create(user);
        RequestDispatcher dispatcher;
        if (check){
            dispatcher=request.getRequestDispatcher("create.jsp");
        }else {
            dispatcher=request.getRequestDispatcher("notFound.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null)action="";
        switch (action){
            case "create":
                showFormCreate(request,response);
                break;
            case "update":
                showUpdateForm(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "sort":
                showList(request,response,userService.sort());
                break;
            case "findEmail":
                showFormFind(request,response);
                break;
            default:
                showList(request,response,userService.findAll());
        }
    }

    private void showFormFind(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("findByEmail.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        boolean check=userService.delete(id);
        if (check) {
            showList(request,response,userService.findAll());
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("notFound.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        User user=userService.findById(id);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher=request.getRequestDispatcher("update.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response,List<User> list) {
        request.setAttribute("list",list);
        RequestDispatcher dispatcher=request.getRequestDispatcher("showList.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
