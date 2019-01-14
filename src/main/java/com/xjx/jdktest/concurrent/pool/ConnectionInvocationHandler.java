package com.xjx.jdktest.concurrent.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class ConnectionInvocationHandler implements InvocationHandler {

    private Connection originalConn;
    private LinkedBlockingQueue<Connection> activeList;
    private LinkedBlockingQueue<Connection> idleList;


    public ConnectionInvocationHandler(Connection conn, LinkedBlockingQueue<Connection> activeList, LinkedBlockingQueue idleList) {
        this.originalConn = conn;
        this.activeList = activeList;
        this.idleList = idleList;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Exception {

        if ("close".equals(method.getName())) {
            idleList.put(originalConn);
            activeList.remove(originalConn);
            System.out.println("空闲连接数量： " + (idleList == null ? 0 : idleList.size()));
            System.out.println("活动连接数量： " + (activeList == null ? 0 : activeList.size()));
            return null;
        } else if("commit".equals(method.getName())){
                return null;
        }else
        {
            //如果不是close方法,则继续执行目标的行为
            return method.invoke(originalConn, args);
        }
    }
}

