class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(1, 2);
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

class MySecondClass{
    // имеет два приватных поля типа int
    private int param1;
    private int param2;

    // методы для получения и модификации их значений
    // возвращает парам1
    public int getParam1() {
        return param1;
    }
    // возвращает парам2
    public int getParam2() {
        return param2;
    }
    // устанавливает значение парам1
    public void setParam1(int param1) {
        this.param1 = param1;
    }
    // устанавливает значение парам2
    public void setParam2(int param2) {
        this.param2 = param2;
    }

    // конструктор, создающий объект и инициализирующий значения полей
    MySecondClass(int param1, int param2)
    {
        this.param1 = param1;
        this.param2 = param2;
    }

    // метод с возвращаемым типом int, варинт 14 - Умножение
    public int multiply(){
        return param1 * param2;
    }
}