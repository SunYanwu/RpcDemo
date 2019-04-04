package RemoteServer.app;

import RemoteServer.remoteServices.Calculator;
import RemoteServer.remoteServices.impl.CalculatorImpl;
import entity.CalculateRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @className providerApp
 * @Author SunYanwu
 * @Description：
 * @Date 2019/4/5 1:20
 */
public class providerApp {

    public static void main(String[] args) throws IOException {
        new providerApp().doCalculate();
    }

    private void doCalculate() throws IOException {
        ServerSocket listener = new ServerSocket(8888);
        Calculator calculator = new CalculatorImpl();
        try {
            while(true){
                Socket socket = listener.accept();
                try {
                    //将请求参数反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    //执行远程服务
                    int result = 0;
                    if(object instanceof CalculateRequest){
                        CalculateRequest calculateRequest = (CalculateRequest) object;
                        String method = calculateRequest.getMethod();
                        if("add".equals(method)){
                            result = calculator.add(calculateRequest.getA(),calculateRequest.getB());
                        }
                    }

                    //将结果进行返回
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(new Integer(result));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            listener.close();
        }
    }
}
