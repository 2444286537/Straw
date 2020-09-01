package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 */
@Data
@Accessors(chain = true)
public class R<T> {
    /**
     * 响应状态码
     */
    private Integer state;
    /**
     * 出错时的错误提示信息
     */
    private String message;
    /**
     * 成功时响应给客户端的数据
     */


    private T data;


    public R() {
        super();
    }

    public static R ok(){
        return new R().setState(State.SUCCESS);
    }

    public static <T> R ok(T data){
        return R.ok().setData(data);
    }

    public static R failure(Integer failureState,Throwable e){
        return new R().setState(failureState).setMessage(e.getMessage());
    }

    public interface State{
        int SUCCESS=2000;
        int ERR_INVITE_CODE=4000;
        int ERR_PHONE_DUPLICATE=4001;
        int ERR_INSERT_FAIL=4002;
        int ERR_UNKNOWN=9000;
        int ERR_PARAMETER_VALIDATION=9002;
        int ERR_ENABLED_INVALID=4004;
        int ERR_UPLOAD_FILE_EMPTY = 4005;
        int ERR_UPLOAD_FILE_SIZE = 4006;
        int ERR_UPLOAD_FILE_TYPE = 4007;
        int ERR_UPLOAD_IO = 4008;
        int ERR_UPLOAD_FILE_STATE = 4009;
        int ERR_QUESTION_DETAIL_NOTFOUND=4010;
        int ERR_COMMENT_NOTFOUND=4011;
        int ERR_ACCESS_DENIED=4012;
        int ERR_DELETE_FAIL=4013;
    }
}
