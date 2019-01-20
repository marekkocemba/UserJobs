package ue.trans.user.jobs.utils.exception.handler;

import lombok.Builder;
import lombok.Data;
import ue.trans.user.jobs.enums.ExceptionCodeEnum;

@Data
@Builder
public class ClientExceptionObject {

    private String description;
    private ExceptionCodeEnum exceptionCode;
}
