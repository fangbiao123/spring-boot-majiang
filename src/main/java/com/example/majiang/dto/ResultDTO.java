package com.example.majiang.dto;

import com.example.majiang.exception.CoustomizeMessage;
import com.example.majiang.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO {
    private Integer code;
    private String msg;

    public static ResultDTO error(Integer code, String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }

    public static Object error(CoustomizeMessage errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    public static Object success(){
        return error(200,"请求成功！");
    }

    public static ResultDTO error(CustomizeException ex) {
        return error(ex.getCode(), ex.getMessage());
    }
}
