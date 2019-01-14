package com.xjx.jdktest.concurrent.pool;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {


    private static final int POOL_SIZE = 20;

    private volatile static int connectionCount = 0;

    public static LinkedBlockingQueue<Connection> idleList = new LinkedBlockingQueue<>(POOL_SIZE);
    public static LinkedBlockingQueue<Connection> activeList = new LinkedBlockingQueue<>(POOL_SIZE);
    //刚开始的时候，就给他20个空闲的连接
    static {
        for(int i=0;i<POOL_SIZE;i++){
            idleList.add(DBUtil.getConnection());
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        Connection proxyConnection;
                try {
                    connection = idleList.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

             proxyConnection = ConnectionProxy.getConnectionProxy(connection,activeList,idleList);
            try {
                activeList.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return  proxyConnection;
    }
}
