package xyz.seekyou.bank.servlet;

import xyz.seekyou.bank.Dao.DaoImpl.UserDaoImpl;
import xyz.seekyou.bank.Dao.iUserDao;
import xyz.seekyou.bank.bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private iUserDao ud;
    public UserServlet(){
        super();
        ud= UserDaoImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().println("#######"+ request.getParameter("userid"));
        String cmd=request.getParameter("cmd");
        switch (cmd){
            case "register" :register(request,response);break;
            case "login":login(request,response);break;
            case "update":update(request,response);break;
            case "delete":delete(request,response);break;
            default: return;
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //userid已由前端判断出是否符合要求
        String username=request.getParameter("username");
        String password=request.getParameter("password1");
        int res=ud.register(username,password);
        if (res>0){
            request.setAttribute("userid",res);
            request.getRequestDispatcher("register_succeeded.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("register_failed.jsp").forward(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //userid已由前端判断出是否符合要求
        int userid=Integer.parseInt(request.getParameter("userid"));
        String password=request.getParameter("password");
        iUserDao ud= UserDaoImpl.getInstance();
        UserBean user=ud.login(userid,password);
        if (user!=null){
            if(request.getParameter("isRemember")!=null) {
                Cookie userIDCookie = new Cookie("userid",String.valueOf(userid));
                Cookie userPassCookie = new Cookie("password",password);
                userIDCookie.setMaxAge(60*60*1);
                userPassCookie.setMaxAge(60*60*1);
                response.addCookie(userIDCookie);
                response.addCookie(userPassCookie);
            }
            request.getSession().setAttribute("user" ,user);
//            request.getRequestDispatcher("home.jsp").forward(request,response);
            response.sendRedirect("login_succeeded.jsp");
        }else{
            request.getRequestDispatcher("login_failed.jsp").forward(request,response);
        }
    }
}
