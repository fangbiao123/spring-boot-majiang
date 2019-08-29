package com.example.majiang.advice;

import com.alibaba.fastjson.JSON;
import com.example.majiang.dto.ResultDTO;
import com.example.majiang.exception.CoustomizeMessage;
import com.example.majiang.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理(好像只有自己抛出的异常才会进入到这里)
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Throwable ex,
            Model model) {
        HttpStatus status = getStatus(request);
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){

            //判断是否是json请求，返回json格式
            ResultDTO resultDTO;
            if(ex instanceof CustomizeException){
                resultDTO = ResultDTO.error((CustomizeException)ex);
            }else{
                resultDTO = (ResultDTO) ResultDTO.error(CoustomizeMessage.SYSTEM_ERROR);
            }
            // 返回页面和json不能共存，使用write来返回json
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();

            }catch (IOException ioe){

            }
            return null;
        }else{
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CoustomizeMessage.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
