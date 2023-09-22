package functions;

public interface TabulatedFunction extends MathFunction{
    //Метод получения количества табулированных значений
    int getCount();
    //Метод, получающий значение аргумента x по номеру индекса
    double getX(int index);
    //Метод, получающий значение y по номеру индекса
    double getY(int index);
    //Метод, задающий значение y по номеру индекса
    void setY(int index, double value);

}
