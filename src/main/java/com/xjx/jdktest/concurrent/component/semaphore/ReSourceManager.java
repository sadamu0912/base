package com.xjx.jdktest.concurrent.component.semaphore;

import com.xjx.jdktest.concurrent.component.semaphore.DCResource;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ReSourceManager {
    private static final int ResourceNumber =10;
    private  Semaphore multiLock ;
    private  DCResource[] resources ;
    private  ReentrantLock lock;
    public ReSourceManager(){
        multiLock = new Semaphore(ResourceNumber,false);
        resources = new DCResource[ResourceNumber];
        lock = new ReentrantLock();
        for(int i=0;i<ResourceNumber;i++){
            DCResource resource = new DCResource();
            resource.setInUse(false);
            resource.setResourceId(i);
            resource.setResourceName("resource"+i);
            resources[i] = resource;
        }
    }

    public void useResource(int userId) {
        try{
            multiLock.acquire();
            int id = getResourceId();//占到一个坑
            System.out.print("userId:"+userId+"正在使用资源，资源id:"+id+"\n");
            Thread.sleep(6000);//do something，相当于于使用资源
            resources[id].setInUse(false);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            multiLock.release();//释放信号量，计数器加1
        }

    }

    private int getResourceId(){
        int id = -1;
        lock.lock();
        try {
            //lock.lock();//虽然使用了锁控制同步，但由于只是简单的一个数组遍历，效率还是很高的，所以基本不影响性能。
            for(int i=0; i<10; i++){
                if(!resources[i].isInUse()){
                    resources[i].setInUse(true);
                    id = i;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }

}
