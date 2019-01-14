package com.xjx.jdktest.datastruct;

public class RingBufferFlipMarker {

    public Object[] elements = null;

    public int capacity = 0;
    public int writePos = 0;
    public int readPos  = 0;
    public boolean flipped = false;   //the flip marker

    public RingBufferFlipMarker(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    public void reset() {
        this.writePos = 0;
        this.readPos  = 0;
        this.flipped  = false;
    }

    public int available() {
        if(!flipped){
            return writePos - readPos;
        }
        return capacity - readPos + writePos;
    }

    public int remainingCapacity() {
        if(!flipped){
            return capacity - writePos;
        }
        return readPos - writePos;
    }

    public boolean put(Object element){
        if(!flipped){
            if(writePos == capacity){
                writePos = 0;
                flipped = true;

                if(writePos < readPos){
                    elements[writePos++] = element;
                    return true;
                } else {
                    return false;
                }
            } else {
                elements[writePos++] = element;
                return true;
            }
        } else {
            if(writePos < readPos ){
                elements[writePos++] = element;
                return true;
            } else {
                return false;
            }
        }
    }


    public Object take() {
        if(!flipped){
            if(readPos < writePos){
                return elements[readPos++];
            } else {
                return null;
            }
        } else {
            if(readPos == capacity){
                readPos = 0;
                flipped = false;

                if(readPos < writePos){
                    return elements[readPos++];
                } else {
                    return null;
                }
            } else {
                return elements[readPos++];
            }
        }
    }

    public static void main(String[] args) {
        RingBufferFlipMarker buffer = new RingBufferFlipMarker(10);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        buffer.put(5);
        buffer.put(6);
        buffer.put(7);
        buffer.put(8);
        buffer.put(9);
        buffer.put(10);
    }
}
