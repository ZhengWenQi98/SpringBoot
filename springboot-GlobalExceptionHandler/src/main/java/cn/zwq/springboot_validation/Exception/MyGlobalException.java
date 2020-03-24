package cn.zwq.springboot_validation.Exception;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class MyGlobalException implements HandlerExceptionResolver {

    @Override
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",e.getMessage());
        mv.setViewName("/exception");
        e.printStackTrace();
        return mv;
    }
}