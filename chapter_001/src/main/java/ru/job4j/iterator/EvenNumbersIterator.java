package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор по массиву, возвращаюший только четные числа.
 *
 * @author Bruki Mammad.
 * @version $2.0$
 * @since 06.08.2020
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Позиция указателя в массиве.
     */
    private int position;
    /**
     * Массив с числами.
     */
    private final int[] numbers;

    /**
     * конструктор для инициализаций массива.
     *
     * @param numbers - массив.
     */

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return this.changeNextIndexEven() != -1;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Больше нет четных элементов");
        }
        return this.numbers[position++];
    }

    private Integer changeNextIndexEven() {
        int value = -1;
        for (int index = this.position; index < this.numbers.length; index++) {
            if (this.numbers[index] % 2 == 0) {
                position = index;
                value++;
                break;
            }
        }
        return value;
    }
}
