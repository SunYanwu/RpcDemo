package client.app;

import client.services.Calculator;
import client.services.impl.CalcutorImpl;

/**
 * @className ComsumerApp
 * @Author SunYanwu
 * @Description：
 * @Date 2019/4/5 0:32
 */
public class ComsumerApp {
    public static void main(String[] args) {
        Calculator calculator = new CalcutorImpl();
        int result = calculator.add(2,3);
        System.out.println(result);
    }
}
