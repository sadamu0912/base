package com.xjx.jdktest.concurrent;

import java.io.*;

public class PipedStreamConnect {
    public static class Write extends Thread{
        public PipedOutputStream pos = null;

        //获取线程中的管道输出流
        public PipedOutputStream getPos(){
            pos = new PipedOutputStream();
            return pos;
        }
        @Override
        public void run(){
            PrintStream p = new PrintStream(pos);
            for(int i=1;i<1000;i++){
                p.println("hello");
                p.flush();
            }
            p.close();
        }
    }

    public static class Read extends Thread{
        public PipedInputStream pis = null;
        public String line = "null";

        //获得线程中的管道输入流
        public PipedInputStream getPis(){
            pis = new PipedInputStream();
            return pis;
        }
        @Override
        public void run(){
            BufferedReader r = new BufferedReader(new InputStreamReader(pis));
            try {
                while(line!=null){
                    line = r.readLine();
                    System.out.println("read: "+line);
                }
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]) throws InterruptedException, IOException{
        Write write = new Write();
        Read read = new Read();
        //连接两个线程的管道流
        write.getPos().connect(read.getPis());
        write.start();
        read.start();
    }
}

