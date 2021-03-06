package com.itstep.seven;

public class HWgenerics {

    public static void main(String[] args) {
        MyArray<Integer>integerMyArray = new MyArray<> (5);
        MyArray<String>stringMyArray = new MyArray<> (5);
        integerMyArray.add (1);
        integerMyArray.add (2);
        integerMyArray.add (3);
        integerMyArray.add (4);
        integerMyArray.add (5);
        stringMyArray.add ("1");
        stringMyArray.add ("2");
        stringMyArray.add ("3");
        stringMyArray.add ("4");
        stringMyArray.add ("5");
        System.out.println (integerMyArray);
        System.out.println (stringMyArray);
        stringMyArray.reverse ();
        System.out.println (stringMyArray);
        integerMyArray.shuffle ();
        System.out.println (integerMyArray);
        integerMyArray.clear ();
        System.out.println (integerMyArray);
        integerMyArray.trimToSize ();
        integerMyArray.add ( 1 );
        integerMyArray.add ( 2 );
        System.out.println (integerMyArray);
    }
}
