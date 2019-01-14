package com.xjx.jdktest.datastruct;

class Key implements Comparable<Key> {
    // 业余草：www.xttblog.com
    private final int value;
    Key(int value) {
        this.value = value;
    }
    @Override
    public int compareTo(Key o) {
        return Integer.compare(this.value, o.value);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Key key = (Key) o;
        return value == key.value;
    }
    @Override
    public int hashCode() {
        return value;
    }
}
