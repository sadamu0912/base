package com.xjx.jdktest;

/** 
 * Item类 
 * 重写hashCode和equals方法 
 */  
class Item {  
    public Item(String[] strs) {  
        columns = strs;  
    }  
  
    public boolean equals(Object otherObject) {  
        if (this == otherObject)  
            return true;  
        if (otherObject == null)  
            return false;  
        if (getClass() != otherObject.getClass())  
            return false;  
        if (!(otherObject instanceof Item))  
            return false;  
        Item other = (Item) otherObject;  
        if (this.columns.length != other.columns.length)  
            return false;  
        for (int i = 0; i < this.columns.length; i++) {  
            if (this.columns[i] != null && !this.columns[i].equals(other.columns[i]))  
                return false;  
        }  
        return true;  
    }  
  
    public int hashCode() {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < this.columns.length; i++) {  
            sb.append(this.columns[i]);  
        }  
        return this.Time33(sb.toString());  
    }  
  
    private int Time33(String str) {  
        int len = str.length();  
        int hash = 0;  
        for (int i = 0; i < len; i++)  
            hash = (hash << 5) + hash + (int) str.charAt(i);  
        return hash;  
    }  
  
    private String[] columns;  
}  
