package abc.tzous4j.kpsys.controller;

import abc.tzous4j.kpsys.annotation.FireAuthority;
import abc.tzous4j.kpsys.helper.ResultTypeEnum;
import abc.tzous4j.kpsys.model.Authority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("index.do")
    @FireAuthority(resultType = ResultTypeEnum.page)
    public ModelAndView index(HttpServletRequest request,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Authority author = session.getAttribute("loginUser")==null? null : (Authority) session.getAttribute("loginUser");
        if(author != null)
        	modelAndView.addObject("tbuser",author.getTbuser());
        modelAndView.setViewName( "/index" );

        return modelAndView;
    }

}
