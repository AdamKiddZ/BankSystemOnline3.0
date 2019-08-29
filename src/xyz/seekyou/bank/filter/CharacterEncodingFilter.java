package xyz.seekyou.bank.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*",initParams = {@WebInitParam(name = "charset",value = "utf-8")})
public class CharacterEncodingFilter implements Filter {
    public static String charset;

    public CharacterEncodingFilter(){
        System.out.println("Filter创建了……");
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(charset);
//        System.out.println("##############过滤器过滤，初始化指定编码："+charset);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.charset=config.getInitParameter("charset");
        System.out.println("Filter初始化……");
    }

}
