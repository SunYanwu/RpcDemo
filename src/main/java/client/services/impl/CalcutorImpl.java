package client.services.impl;

import entity.CalculateRequest;
import client.services.Calculator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @className CalcutorImpl
 * @Author SunYanwu
 * @Description： 客户端将要调用的本地服务（其内部使用远程调用，对于使用该服务的开发人员感觉像调用本地服务）
 * @Date 2019/4/4 20:57
 */
public class CalcutorImpl implements Calculator {
    public int add(int a, int b) {
        List<String> addressList = lookupProviders("Calculator.add");
        String address = chooseTarget(addressList);
        int port = 8888;
        try {
            Socket socket = new Socket(address,port);

            //将请求序列化
            CalculateRequest calculateRequest = generateRequest(a,b);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            //将请求发给服务提供方
            objectOutputStream.writeObject(calculateRequest);

            //将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            if(response instanceof Integer){
                return (Integer) response;
            }else{
                throw new InternalError("结果类型错误");
            }
            } catch (Exception e) {
                e.printStackTrace();
                throw new InternalError();
            }
    }

    private CalculateRequest generateRequest(int a, int b) {
        CalculateRequest calculateRequest = new CalculateRequest();
        calculateRequest.setA(a);
        calculateRequest.setB(b);
        calculateRequest.setMethod("add");
        return calculateRequest;
    }

    /*该方法在寻找到一个服务列表后,通过负载均衡的策略返回远程调用服务的IP地址*/
    private String chooseTarget(List<String> addressList) {
        return "127.0.0.1";
    }

    /*该方法寻找要调用的服务列表 */
    private List<String> lookupProviders(String s) {
        List<String> resList = new ArrayList<String>();
        resList.add("127.0.0.1");
        return resList;
    }
}