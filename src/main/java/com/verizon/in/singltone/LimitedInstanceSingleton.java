package com.verizon.in.singltone;

import java.util.List;

public class LimitedInstanceSingleton {

    private static final int MAX_INSTANCE=5;
    private static int count=0;
    private static LimitedInstanceSingleton instance;

    private LimitedInstanceSingleton(){

    }

    public static synchronized LimitedInstanceSingleton getInstance(){
        if(count < MAX_INSTANCE){
            instance=new LimitedInstanceSingleton();
            count++;
            return instance;
        }else{
            return instance;
        }
       // throw new RuntimeException("Maximum number of instances reached");
    }

    public static void main(String[] aggr){
        LimitedInstanceSingleton instance1=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance2=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance3=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance4=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance5=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance6=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance7=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance8=LimitedInstanceSingleton.getInstance();
        LimitedInstanceSingleton instance9=LimitedInstanceSingleton.getInstance();

        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance3.hashCode());
        System.out.println(instance4.hashCode());
        System.out.println(instance5.hashCode());
        System.out.println(instance6.hashCode());
        System.out.println(instance7.hashCode());
        System.out.println(instance8.hashCode());
        System.out.println(instance9.hashCode());


    }

}//class
