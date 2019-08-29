package xyz.seekyou.bank.servlet.AjaxServlet;

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
import java.math.BigDecimal;
import java.net.URLEncoder;

@WebServlet(name = "ProcessDealServlet",urlPatterns = "/ProcessDeal.ajax")
public class ProcessDealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private iManagerDao md;

    public ProcessDealServlet() {
        super();
        System.out.println("ProcessDealServlet初始化……");
        md= ManagerDaoImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd=request.getParameter("cmd");
        System.out.println("Processing……"+cmd+"@user"+request.getSession().getAttribute("user"));
        switch (cmd){
            case "withdraw":withdraw(request,response);break;
            case "deposit":deposit(request,response);break;
            case "transfer":transfer(request,response);break;
            default:
                String message="交易失败！";
                message= URLEncoder.encode(message,"utf-8");
                response.getWriter().print(message);
                break;
        }
    }

    private void withdraw(HttpServletRequest request,HttpServletResponse response) throws IOException {
        UserBean user=(UserBean) request.getSession().getAttribute("user");
        BigDecimal amount=null;
        try{
            amount=new BigDecimal(request.getParameter("amount"));
//            System.out.println("取款的金额为："+amount);
        }catch (Exception e){
            String message="取款金额格式错误！";
            message= URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        }
        try {
            String message=md.withdraw(user, amount);
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        } catch (BankException e) {
//            e.printStackTrace();
//            response.getWriter().print(false);
            String message=e.getMessage();
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
        }
    }

    private void deposit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        UserBean user=(UserBean) request.getSession().getAttribute("user");
        BigDecimal amount=null;
        try{
            amount=new BigDecimal(request.getParameter("amount"));
        }catch (Exception e){
            String message="存款金额格式错误！";
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        }
        try {
            String message=md.deposit(user, amount);
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        } catch (BankException e) {
//            e.printStackTrace();
//            response.getWriter().print(false);
            String message=e.getMessage();
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
        }
    }

    private void transfer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        UserBean user=(UserBean) request.getSession().getAttribute("user");
        int receiverID=Integer.parseInt(request.getParameter("userid"));
        BigDecimal amount=null;
        try{
            amount=new BigDecimal(request.getParameter("amount"));
        }catch (Exception e){
            String message="转账金额格式错误！";
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        }
        try {
            String message=md.transfer(user,receiverID, amount);
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
            return;
        } catch (BankException e) {
//            e.printStackTrace();
//            response.getWriter().print(false);
            String message=e.getMessage();
            message=URLEncoder.encode(message,"utf-8");
            response.getWriter().print(message);
        }
    }


}
