package cn.tedu.straw.api.ex;

import javax.xml.ws.Service;

public class DeleteExcetion extends ServiceException {
    public DeleteExcetion() {
    }

    public DeleteExcetion(String message) {
        super(message);
    }

    public DeleteExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteExcetion(Throwable cause) {
        super(cause);
    }

    public DeleteExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
