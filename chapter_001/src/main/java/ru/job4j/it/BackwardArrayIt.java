package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $1.0$
 * @since 26.07.2020
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * method hasNext проверяет, есть ли следующий элемент.
     *
     * @return result.
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * method next() сдвигает указатель итератора в обратном порядке.
     *
     * @return result.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1]--;
    }
}
