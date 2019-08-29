package xyz.seekyou.bank.listener;//package xyz.seekyou.bank.listener;
//
//import xyz.seekyou.bank.bean.UserBean;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSessionAttributeListener;
//import javax.servlet.http.HttpSessionBindingEvent;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@WebListener()
//public class UserListener implements ServletContextListener,
//        HttpSessionListener, HttpSessionAttributeListener {
//
//    private ServletContext application;
//    // Public constructor is required by servlet spec
//    public UserListener() {
//        System.out.println("监听器被加载了");
//    }
//
//    // -------------------------------------------------------
//    // ServletContextListener implementation
//    // -------------------------------------------------------
//    public void contextInitialized(ServletContextEvent sce) {
//      /* This method is called when the servlet context is
//         initialized(when the Web application is deployed).
//         You can initialize servlet context related data here.
//      */
//        System.out.println("ServletContext被加载了");
//        application=sce.getServletContext();
//        // ServletContext对象初始化时，向其中添加属性onlineUsers
//        // onlineUsers表示在线用户集合，使用ConcurrentHashMap存储在线用户
//        // 该Concurrent键值对类型为：用户ID--用户对象
//        application.setAttribute("onlineUsers",new ConcurrentHashMap<String,UserBean>());
//    }
//
//    public void contextDestroyed(ServletContextEvent sce) {
//      /* This method is invoked when the Servlet Context
//         (the Web application) is undeployed or
//         Application Server shuts down.
//      */
//    }
//
//    // -------------------------------------------------------
//    // HttpSessionListener implementation
//    // -------------------------------------------------------
//    public void sessionCreated(HttpSessionEvent se) {
//        /* Session is created. */
//        System.out.println("Session创建了");
//    }
//
//    public void sessionDestroyed(HttpSessionEvent se) {
//        /* Session is destroyed. */
//        se.getSession();
//        UserBean user=(UserBean)se.getSession().getAttribute("userSession");
//        if(user!=null){
//            ConcurrentHashMap onlineUsers=(ConcurrentHashMap)this.application.getAttribute("onlineUsers");
//
//            // 往application的onlineUsers的Map中添加该用户
//            onlineUsers.remove(user.getUserid());
//            try {
//                Context context=new InitialContext();
//            } catch (NamingException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    // -------------------------------------------------------
//    // HttpSessionAttributeListener implementation
//    // -------------------------------------------------------
//
//    public void attributeAdded(HttpSessionBindingEvent sbe) {
//
//         /* 当监听到Session中的属性增加了，
//            判断是否是用户登录成功往Session中添加验证信息，
//            因为用户登录成功往Session中添加的属性名为userSession
//            而属性值为一个封装了该用户信息的UserBean对象
//              */
//        if(sbe.getName().equals("userSession")) {
//            UserBean user=(UserBean)sbe.getValue();
//
//            // 后台输出用户登录信息
//            System.out.println("###用户ID：" +user.getUserid()+" 用户名："+ user.getUsername() + " 已上线……");
//            ConcurrentHashMap onlineUsers=(ConcurrentHashMap)this.application.getAttribute("onlineUsers");
//
//            // 往application的onlineUsers的Map中添加该用户
//            onlineUsers.put(((UserBean)sbe.getValue()).getUserid(),(UserBean)sbe.getValue());
//
//          //后台输出当前在线用户
//          Iterator<Map.Entry<String,UserBean>> iterator=((ConcurrentHashMap)this.application.getAttribute("onlineUsers")).entrySet().iterator();
//          iterator.forEachRemaining(p->{
//              System.out.println(((Map.Entry<String,UserBean>)p).getKey());
//              System.out.println(((Map.Entry<String,UserBean>)p).getValue().getUsername());
//          });
//      }
//    }
//
//    public void attributeRemoved(HttpSessionBindingEvent sbe) {
//      /* This method is called when an attribute
//         is removed from a session.
//      */
//    }
//
//    public void attributeReplaced(HttpSessionBindingEvent sbe) {
//      /* This method is invoked when an attibute
//         is replaced in a session.
//      */
//      sbe.getName();
//    }
//}
