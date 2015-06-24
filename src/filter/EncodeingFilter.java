package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodeingFilter
 */
@WebFilter(description = "文字列エンコード用フィルター", urlPatterns = { "/*" })
public class EncodeingFilter implements Filter {

    /** エンコード */
    private final static String encoding = "UTF-8";

    /**
     * Default constructor.
     */
    public EncodeingFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here

        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO 自動生成されたメソッド・スタブ

    }
}