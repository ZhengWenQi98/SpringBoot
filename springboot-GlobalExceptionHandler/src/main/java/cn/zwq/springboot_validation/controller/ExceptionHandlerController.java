package cn.zwq.springboot_validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlerController {

    /*@ExceptionHandler(RuntimeException.class)
    public String exception(Exception e){
        e.printStackTrace();
        return "exception";
    }*/
    /*@ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public MyResponse<String> exception(RuntimeException e){
        //在控制台打印
        e.printStackTrace();
        MyResponse<String> response = new MyResponse();
        //出现的异常都返回500状态码
        response.setStatusCode(500);
        response.setData(e.getMessage());
        return response;
    }*/

    @RequestMapping("/exception")
    public void exception(){
        int i = 5/0;
    }
}