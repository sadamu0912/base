package com.xjx.jdktest.concurrent.pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2016/11/25. * 编写一个对象代理类 * 构造出一个我们传入对象的代理对象
 */
public class ConnectionProxy {
    static Connection getConnectionProxy(Connection conn, LinkedBlockingQueue<Connection> activeList,
                                         LinkedBlockingQueue idleList) {
        //构造一个回调处理器
        ConnectionInvocationHandler handler = new ConnectionInvocationHandler(conn, activeList,idleList);
        //返回一个被代理对象
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, handler);
    }
}

