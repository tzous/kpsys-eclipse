package abc.tzous4j.kpsys.Inteceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import abc.tzous4j.kpsys.annotation.FireAuthority;
import abc.tzous4j.kpsys.helper.ResultTypeEnum;
import abc.tzous4j.kpsys.model.Authority;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * MyInteceptor
 */
public class MyInteceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(getClass());

    private List<String> excludedUrls;
    private List<String> includedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setIncludedUrls(List<String> includedUrls) {
        this.includedUrls = includedUrls;
    }

    public List<String> getIncludedUrls() {
        return includedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        logger.debug("");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.contains(url)) {
                return true;
            }
        }
        for (String url : includedUrls) {
            if (!requestUri.contains(url)) {
                return true;
            }
        }
        //检测是否已登录
        HttpSession session = request.getSession();
        Authority author = session.getAttribute("loginUser")==null? null : (Authority) session.getAttribute("loginUser");

        if(author != null) {
        	return true;
        }
        //检测是否有认证权限控制，这里只检测web页面返回类型,page or json
        boolean bPage = true;
        HandlerMethod handler2=(HandlerMethod) handler;
        FireAuthority fireAuthority = handler2.getMethodAnnotation(FireAuthority.class);
        if(null != fireAuthority){
        	if(fireAuthority.resultType() == ResultTypeEnum.json)
        		bPage = false;
        }

        if (bPage) {
            StringBuilder sb = new StringBuilder();
            sb.append("/login.do");
            RequestDispatcher dispatcher = request.getRequestDispatcher(sb.toString());
            dispatcher.forward(request, response);
        } else {  //json
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            OutputStream out = response.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
            pw.println("{\"retcode\":9999,\"retMessage\":\"NOT_LOGINED\"}");
            pw.flush();
            pw.close();
        }
        return false;

        //return super.preHandle(request, response, handler);
    }

}
