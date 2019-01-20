package ue.trans.user.jobs.utils.exception.handler;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {


    private ExceptionCodeEnum code;

    public ClientException(String devMessage, ExceptionCodeEnum code) {
        super(devMessage);
        this.code = code;
    }

}
