package xinweilai.com.bit.common.base;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class Data<T> {

    /**
     * resultCode : 200
     * resultMessage :
     * picPaths :
     */

    private int resultCode;
    private String resultMessage;
    private T data;
    private List<Data3> data3;
    private String data2;
    boolean success;


    public boolean isSuccess() {
        return success;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Data3> getData3() {
        return data3;
    }

    public void setData3(List<Data3> data3) {
        this.data3 = data3;
    }

    public String getData2() {
        return data2;
    }

}
