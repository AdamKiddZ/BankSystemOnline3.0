package xyz.seekyou.bank.servlet;

import xyz.seekyou.bank.Dao.DaoImpl.ManagerDaoImpl;
import xyz.seekyou.bank.Dao.iManagerDao;
import xyz.seekyou.bank.bean.UserBean;
import xyz.seekyou.bank.util.Exception.BankException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BusinessManagementServlet",urlPatterns = {"/BusinessManagementServlet"})
public class BusinessManagementServlet extends HttpServlet {
    private iManagerDao md;

    public BusinessManagementServlet() {
        super();
        this.md = ManagerDaoImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd=request.getParameter("cmd");
        switch (cmd){
            case "getlogs":getLogs(request,response);break;
            case "getusers":getUsers(request,response);break;
            default:
                response.sendRedirect("home.jsp#log");
                break;
        }
    }

    private void getLogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user=(UserBean)request.getSession().getAttribute("user");
        ArrayList logs=new ArrayList();
        try {
            logs=md.getLogByUser(user);
        } catch (BankException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("logs",logs);
        response.sendRedirect("home.jsp#log");
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) {
    }
}
