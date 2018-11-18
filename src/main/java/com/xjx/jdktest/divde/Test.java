package com.xjx.jdktest.divde;

public class Test {  
    public static void main(String[] args) {  
        int[] a = {-2, 11, -4, 13, -5, -2};//最大子序列和为20  
        int[] b = {-6, 2, 4, -7, 5, 3, 2, -1, 6, -9, 10, -2};//最大子序列和为16  
        int[] c ={4,-3,5,-2,-1,2,6,-2};
        System.out.println(maxSubSum3(c));  
       // System.out.println(maxSubSum4(b));  
    }  
    //最大子序列求和算法一  
    public static int maxSubSum1(int[] a){  
          
        int maxSum = 0;  
          
        //从第i个开始找最大子序列和  
        for(int i = 0; i < a.length; i++) {  
              
            //找第i到j的最大子序列和  
            for(int j = i; j<a.length; j++) {  
                  
                int thisSum = 0;  
                  
                //计算从第i个开始，到第j个的和thisSum  
                for(int k = i; k<=j; k++){  
                    thisSum += a[k];  
                }  
                //如果第i到第j个的和小于thisSum，则将thisSum赋值给maxSum  
                if(thisSum>maxSum) {  
                    maxSum = thisSum;  
                }  
            }  
        }  
        return maxSum;  
    }  
      
    public static int maxSubSum2(int[] a) {  
        int maxSum = 0;  
        for(int i = 0; i < a.length; i++) {  
            //将sumMax放在for循环外面，避免j的变化引起i到j的和sumMax要用for循环重新计算  
            int sumMax = 0;  
            for(int j = i; j < a.length; j++) {  
                sumMax += a[j];  
                if(sumMax>maxSum) {  
                    maxSum = sumMax;  
                }  
            }  
        }  
        return maxSum;  
    }  
      
    //递归，分治策略  
    //2分logn，for循环n，固O(nlogn)  
    public static int maxSubSum3(int[] a) {  
        return maxSumRec(a, 0, a.length - 1);  
    }  
    public static int maxSumRec(int[] a, int left, int right) {  
        //递归中的基本情况  
        if(left == right) {  
            if(a[left] > 0) return a[left];  
            else return 0;  
        }   
        int center = (left + right) / 2;  
        //最大子序列在左侧  
        int maxLeftSum = maxSumRec(a, left, center);  
        //最大子序列在右侧  
        int maxRightSum = maxSumRec(a, center+1, right);  
        //最大子序列在中间（左边靠近中间的最大子序列+右边靠近中间的最大子序列）  
        int maxLeftBorderSum = 0, leftBorderSum = 0;  
        for(int i = center; i>=left; i--) {  
            leftBorderSum += a[i];  
            if(leftBorderSum > maxLeftBorderSum) maxLeftBorderSum = leftBorderSum;  
        }  
        int maxRightBorderSum = 0, rightBorderSum = 0;  
        for(int i = center+1; i<= right; i++) {  
            rightBorderSum += a[i];  
            if(rightBorderSum > maxRightBorderSum) maxRightBorderSum = rightBorderSum;  
        }  
        //返回最大子序列在左侧，在右侧，在中间求出的值中的最大的  
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);  
    }  
    public static int max3(int a, int b, int c) {  
        return a > b?(a>c?a:c):(b>c?b:c);  
    }  
    //任何a[i]为负时，均不可能作为最大子序列前缀；任何负的子序列不可能是最有子序列的前缀  
    public static int maxSubSum4 (int [] a) {  
        int maxSum = 0, thisSum = 0;  
        for(int j = 0; j < a.length; j++) {  
            thisSum += a[j];  
            if(thisSum>maxSum) maxSum = thisSum;  
            else if (thisSum < 0) thisSum = 0;   
        }  
        return maxSum;  
    }  
}  
