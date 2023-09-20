import myfirstpackage.*;

class MyFirstClass {
    public static void main(String[] s) {
        myfirstpackage.MySecondClass o = new myfirstpackage.MySecondClass(1, 2);
        System.out.println(o.multiply());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setParam1(i);
                o.setParam2(j);
                System.out.print(o.multiply());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

