package RemoteServer.remoteServices.impl;

import RemoteServer.remoteServices.Calculator;

/**
 * @className CalculatorImpl
 * @Author SunYanwu
 * @Description： 远程服务的具体实现
 * @Date 2019/4/5 1:23
 */
public class CalculatorImpl implements Calculator {
    public int add(int a, int b) {
        return a+b;
    }
}
