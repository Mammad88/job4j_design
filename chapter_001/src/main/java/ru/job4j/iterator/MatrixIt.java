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
        /*
         * для того, чтоб пропустить возможные "пустые" строки
         * пока элемент в строке меньше длины столбца и длина строки равна счетчику колоны,
         * то счетчик строк увеличивается и перебор начинается с первого элемента.
         */
        while (row < data.length && data[row].length == column) {
            column = 0;
            row++;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        /*
         * запоминаем текущий элемент
         * и переходим к следующему элементу.
         */
        return data[row][column++];
    }
}
