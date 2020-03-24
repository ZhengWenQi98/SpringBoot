package cn.zwq.springboot_validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RedirectController {

    /**
     *  重定向的携带的数据在URL上
     * @param redirectAttributes
     * @return
     */
    /*@RequestMapping("redirect")
    public String redirect(RedirectAttributes redirectAttributes){
        System.out.println("redirect");
         redirectAttributes.addAttribute("name","xq");
         redirectAttributes.addAttribute("age",21);
         return "redirect:/userDetail";
    }*/

    @RequestMapping("redirect")
    public String redirect(RedirectAttributes redirectAttributes){
        System.out.println("redirect");
        redirectAttributes.addFlashAttribute("name","xxxq");
        redirectAttributes.addFlashAttribute("age",21);
        return "redirect:/userDetail";
    }

    @RequestMapping("/userDetail")
    @ResponseBody
    public Map<String,Object> user(@ModelAttribute("name") String name,
                                   @ModelAttribute("age") Integer age){
        System.out.println(name);
        System.out.println(age);
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        return map;
    }
}