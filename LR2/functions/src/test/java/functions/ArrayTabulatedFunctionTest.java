package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest
{
    double[] xValues = {2, 4, 6, 8, 10, 12};
    double[] yValues = {1, 3, 5, 7, 9, 11};

    ArrayTabulatedFunction res = new ArrayTabulatedFunction(xValues, yValues);

    @Test
    void getCount()
    {
        assertEquals(res.getCount(), 6);
    }

    @Test
    void getX()
    {
        assertEquals(res.getX(3), 8);
        assertEquals(res.getX(0), 2);
    }

    @Test
    void getY()
    {
        assertEquals(res.getY(1), 3);
        assertEquals(res.getY(5), 11);
    }

    @Test
    void setY()
    {
        ArrayTabulatedFunction res2 = new ArrayTabulatedFunction(xValues, yValues);
        res2.setY(0, 14);
        assertEquals(res2.getY(0), 14);
    }

    @Test
    void indexOfX()
    {
        assertEquals(res.indexOfX(10), 4);
        assertEquals(res.indexOfX(1), -1);
    }

    @Test
    void indexOfY()
    {
        assertEquals(res.indexOfY(3), 1);
        assertEquals(res.indexOfY(12), -1);
    }

    @Test
    void leftBound()
    {
        assertEquals(res.leftBound(), 2);
        assertNotEquals(res.leftBound(), 12);
    }

    @Test
    void rightBound()
    {
        assertEquals(res.rightBound(), 12);
        assertNotEquals(res.rightBound(), 10);
    }

    @Test
    void floorIndexOfX()
    {
        assertEquals(res.floorIndexOfX(4), 1);
        assertEquals(res.floorIndexOfX(7), 2);
        assertEquals(res.floorIndexOfX(1), 0);
        assertEquals(res.floorIndexOfX(15), 6);
    }

    @Test
    void extrapolateLeft()
    {
        assertEquals(res.extrapolateLeft(1), 0);
        assertEquals(res.extrapolateLeft(-2), -3);
    }

    @Test
    void extrapolateRight()
    {
        assertEquals(res.extrapolateRight(13), 12);
        assertEquals(res.extrapolateRight(20), 19);
    }

    @Test
    void interpolate()
    {
        assertEquals(res.interpolate(5, 2), 4);
        assertEquals(res.interpolate(11, 2), 10);
        assertEquals(res.interpolate(15, 1), 14);
    }

    @Test
    void apply()
    {
        assertEquals(res.apply(1), 0);
        assertEquals(res.apply(13), 12);
        assertEquals(res.apply(4), 3);
        assertEquals(res.apply(5), 4);
    }

    @Test
    void insert1() // x нашелся
    {
        ArrayTabulatedFunction res3 = new ArrayTabulatedFunction(xValues, yValues);
        res3.insert(2, 13);
        assertEquals(res3.getY(0), 13);
    }
    @Test
    void insert2() // x не нашелся, добавление элемента в начало
    {
        ArrayTabulatedFunction res3 = new ArrayTabulatedFunction(xValues, yValues);
        res3.insert(1, 0);
        assertEquals(res3.getX(0), 1);
        assertEquals(res3.getY(0), 0);
    }
    @Test
    void insert3() // x не нашелся, добавление элемента в конец
    {
        ArrayTabulatedFunction res3 = new ArrayTabulatedFunction(xValues, yValues);
        res3.insert(14, 13);
        assertEquals(res3.getX(6), 14);
        assertEquals(res3.getY(6), 13);
    }
    @Test
    void insert4() // x не нашелся, добавление элемента в середину
    {
        ArrayTabulatedFunction res3 = new ArrayTabulatedFunction(xValues, yValues);
        res3.insert(7, 6);
        assertEquals(res3.getX(3), 7);
        assertEquals(res3.getY(3), 6);
    }
}