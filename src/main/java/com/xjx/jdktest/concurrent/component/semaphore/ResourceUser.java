package com.xjx.jdktest.concurrent.component.semaphore;

import com.xjx.jdktest.concurrent.component.semaphore.ReSourceManager;

public class ResourceUser implements Runnable {
    private ReSourceManager reSourceManager;
    private int userId;

    public ResourceUser(ReSourceManager reSourceManager, int userId) {
        this.reSourceManager = reSourceManager;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.print("userId:"+userId+"准备使用资源...\n");
        reSourceManager.useResource(userId);
        System.out.print("userId:"+userId+"使用资源完毕...\n");

    }
}
