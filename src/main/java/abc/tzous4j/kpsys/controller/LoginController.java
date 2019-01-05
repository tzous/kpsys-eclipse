package abc.tzous4j.kpsys.controller;

import abc.tzous4j.kpsys.model.Tbuser;
import abc.tzous4j.kpsys.annotation.FireAuthority;
import abc.tzous4j.kpsys.helper.ResultTypeEnum;
import abc.tzous4j.kpsys.model.Authority;
import abc.tzous4j.kpsys.service.IAuthorService;
import abc.tzous4j.utils.IPv4Util;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZQ on 2016-05-06.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private IAuthorService authorService;

    @RequestMapping("login.do")
    public ModelAndView login(HttpServletRequest request,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/login");
        session.removeAttribute("loginUser");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("USERLogin.do")
    @FireAuthority(resultType = ResultTypeEnum.json)
    public ModelAndView USERLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        boolean bret = false;
        session.removeAttribute("loginUser");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        int ip = IPv4Util.ipToInt(IPv4Util.getIpAddr(request));
        String condition = request.getParameter("condition") == null ? "" : java.net.URLDecoder.decode(request.getParameter("condition"),"UTF-8");
        if(null!=condition && !condition.isEmpty()){
            JSONObject obj = JSONObject.fromObject(condition);

            String LOGINNAME =obj.containsKey("LOGINNAME")? obj.getString("LOGINNAME"):"";
            String PASSWORD =obj.containsKey("PASSWORD")? obj.getString("PASSWORD"):"";
 //System.out.printf(LOGINNAME + PASSWORD + "\r\n");
            Tbuser tbuser = authorService.CheckAuthor(LOGINNAME, PASSWORD,ip);
            if(tbuser != null) {
                Authority auth = new Authority(tbuser,true);
                session.setAttribute("loginUser", auth);
                bret = true;
            }
        }
        if(bret) {
            dataMap.put("retCode","0");
            dataMap.put("retMessage","Success");
        } else {
            dataMap.put("retCode","9999");
            dataMap.put("retMessage","Fail");
        }
        return new ModelAndView(new MappingJackson2JsonView(),dataMap);
    }

}
