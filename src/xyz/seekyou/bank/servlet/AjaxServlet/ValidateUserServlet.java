package xyz.seekyou.bank.servlet.AjaxServlet;

import xyz.seekyou.bank.Dao.DaoImpl.UserDaoImpl;
import xyz.seekyou.bank.Dao.iUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ValidateUserServlet",urlPatterns = "/ValidateUser.ajax")
public class ValidateUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private iUserDao ud;

    public ValidateUserServlet() {
        super();
        System.out.println("ValidateUserServlet初始化……");
        ud= UserDaoImpl.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid=0;
        try {
            userid=Integer.valueOf(request.getParameter("userid"));
        }catch(Exception e){
            response.getWriter().print(false);
            return;
        }
//        System.out.println("ValidateUser……@"+userid);
//        System.out.println(ud.isUserExisted(userid));
        response.getWriter().print(ud.isUserExisted(userid));
    }
}
