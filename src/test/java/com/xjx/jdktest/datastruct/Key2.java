package com.xjx.jdktest.datastruct;

class Key2 implements Comparable<Key2> {
    //...
    // 业余草：www.xttblog.com
    private  int value;
    Key2(int value) {
        this.value = value;
    }
    @Override
    public int hashCode() {
        return 1;
    }
    @Override
    public int compareTo(Key2 o) {
        return Integer.compare(this.value, o.value);
    }
}
