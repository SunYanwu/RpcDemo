package entity;

import java.io.Serializable;

/**
 * @className CalculateRequest
 * @Author SunYanwu
 * @Description：封装RPC请求的请求数据等信息的对象
 * @Date 2019/4/4 21:32
 */
public class CalculateRequest implements Serializable {
    private static final long serialVersionUID = -8124292144512793338L;
    private String method;
    private int a;
    private int b;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "CalculateRequest{" +
                "method='" + method + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
