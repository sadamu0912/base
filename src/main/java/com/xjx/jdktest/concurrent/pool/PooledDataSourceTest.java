package com.xjx.jdktest.concurrent.pool;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PooledDataSourceTest {

    static PooledDataSource pool = new  PooledDataSource(DBUtil.driver,DBUtil.url,DBUtil.userName,DBUtil.password);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;
    public static void main(String[] args) throws Exception {
// 线程数量，可以修改线程数量进行观察
        int threadCount = 300;  //当调整参数到201以上，就会报错，【too many connections】
        //查看mysql show status like '%connect%';    Max_used_connections，服务器启动后已经同时使用的连接的最大数量。
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new PooledDataSourceTest.ConnetionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            thread.start();
        }
        System.out.println("线程启动完成");
        start.countDown();
        long start = System.currentTimeMillis();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
        System.out.println("time elapsed ===== " + (System.currentTimeMillis()-start)+"ms");
    }
    static class ConnetionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }
        public void run() {
            System.out.println("进入线程");
            try {
                start.await();
            } catch (Exception ex) {
            }
            while (count > 0) {
                try {
                    Connection connection = pool.getConnection();
                    System.out.println("获取连接");
                    if (connection != null) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeQuery("select * from user;");
                        } finally {
                            got.incrementAndGet();
                            connection.close();
                            System.out.println("释放连接");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    notGot.incrementAndGet();
                } finally {
                    count--;
                }}
            end.countDown();
        }
    }
}
