package ru.karelin.tmwebspring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    boolean success;
    String message;
    public Result(){
        this.success=true;
    }
    public Result(boolean isSuccess){
        this.success=isSuccess;
    }
    public Result (boolean isSuccess, String message){
        this.success = isSuccess;
        this.message = message;
    }
}
