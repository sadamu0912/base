package com.xjx.jdktest.concurrent.component.semaphore;

import com.xjx.jdktest.concurrent.component.semaphore.ReSourceManager;
import com.xjx.jdktest.concurrent.component.semaphore.ResourceUser;

public class SemaphoreTest {
    public static void main(String[] args){
        ReSourceManager resourceManage = new ReSourceManager();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage,i));//创建多个资源使用者
            threads[i] = thread;
        }
        for(int i = 0; i < 10; i++){
            Thread thread = threads[i];
            try {
                thread.start();//启动线程
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
