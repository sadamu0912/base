package com.xjx.jdktest.concurrent;


import java.io.*;

public class PipedStreamTest {
    public static class Write extends Thread{
        public PipedOutputStream pos;
        Write(PipedOutputStream pos){
            this.pos = pos;
        }
        public void run(){
            PrintStream p = new PrintStream(pos);
            for(int i=1;i<10;i++){
                p.println("hello");
                p.flush();
            }
            p.close();
        }
    }

    public static class Read extends Thread{
        public PipedInputStream pis;
        public String line = "null";
        Read(PipedInputStream pis){
            this.pis = pis;
        }
        public void run(){
            BufferedReader r = new BufferedReader(new InputStreamReader(pis));
            try {
                while(line!=null){
                    line = r.readLine();
                    System.out.println(getName()+": "+line);
                }
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws InterruptedException, IOException{
        //创建管道通信流
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        new Write(pos).start();
        new Read(pis).start();
    }
}
