package cn.tedu.straw.api.ex;

public class FileEmptyExeption extends FileUploadException{
    public FileEmptyExeption() {
    }

    public FileEmptyExeption(String message) {
        super(message);
    }

    public FileEmptyExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyExeption(Throwable cause) {
        super(cause);
    }

    public FileEmptyExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
