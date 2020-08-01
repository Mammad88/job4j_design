package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Цель итератора переместить указатель на нужную ячейку.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 01.08.2020
 */

public class MatrixIt implements Iterator<Integer> {
    /**
     * двумерный массив.
     */
    private final int[][] data;
    /**
     * Счетчик строк массива.
     */
    private int row = 0;
    /**
     * Счетчик столбцов массива.
     */
    private int column = 0;

    /**
     * конструктор для инициализаций массива.
     *
     * @param data - двумерный массив.
     */
    public MatrixIt(int[][] data) {
        this.data = data;
    }


    @Override
    public boolean hasNext() {
        /**
         * для того, чтоб пропустить возможные "пустые" строки
         * пока элемент в строке меньше длины строки и элемент строки равен нулю
         * мы переводим указатель к следующей строке.
         */
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        /**
         * запоминаем текущий элемент
         * и переходим к следующему элементу.
         */
        int element = data[row][column++];
        /**
         * если в столбце больше или равно длине строк,
         * то мы преходим к следующей строке.
         */
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return element;
    }
}
