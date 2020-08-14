package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Обертка над массивом с использованием generic.
 *
 * @param <T> - тип эл-тов массива.
 * @author Bruki mammad.
 * @version $1.0$
 * @since 14.08.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * Массив, в котором храним элементы.
     */
    private Object[] elements;
    /**
     * фактический размер массива (также указатель на первую ячейку).
     */
    private int position = 0;

    /**
     * конструктор - задаем при инициализаций необходимое количество ячеек.
     *
     * @param size - количество ячеек.
     */
    public SimpleArray(int size) {
        this.elements = new Object[size];
    }

    /**
     * Добавляет в массив очередной элемент.
     *
     * @param model - добавляемый указанный элемент в первую свободную ячейку.
     */
    public void add(T model) {
        if (position >= elements.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[position++] = model;
    }

    /**
     * Заменяет указанным элементом (model) элемент,  находяшийся по индексу (index).
     *
     * @param index - индекс ячейки массива.
     * @param model - устанавливаемое значение.
     */
    public void set(int index, T model) {
        if (index >= position) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = model;
    }

    /**
     * Удаляет элемент и сдвигает остальные элементы, уберая промежуток.
     *
     * @param index - индекс ячейки массива.
     */
    public void remove(int index) {
        if (index >= position) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        elements[--position] = null;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param index - индекс ячеки массива.
     * @return значение из ячейки.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= position) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**
             * указатель на текущую позицию.
             */
            private int cursor = 0;
            /**
             * указатель на только что выданный эл-т (используется для его удаления).
             */
            private int lastIndex = -1;

            /**
             * определяем наличие неполученных эл-тов по указателю.
             *
             * @return true, если остались ещё не выданные эл-ты.
             */
            @Override
            public boolean hasNext() {
                return cursor < position;
            }

            /**
             * выдаём следующий доступный эл-т, его индексом устанавливаем указатель на удаление,
             * передвигаем указатель позиции на следующий эл-т
             *
             * @return следующий эл-т.
             * @throws NoSuchElementException если отсутствуют эл-ты к выдаче.
             */
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastIndex = cursor;
                return (T) elements[cursor++];
            }

            /**
             * осуществляем удаление эл-та.
             * возможно только после получения эл-та чз {@link #next()}, иначе кидаем исключение
             *
             * @throws UnsupportedOperationException получение эл-та не предворяет вызов метода
             */
            @Override
            public void remove() {
                if (lastIndex < 0) {
                    throw new IllegalStateException();
                }
                SimpleArray.this.remove(lastIndex);
                cursor = lastIndex;
                lastIndex = -1;
            }
        };
    }
}
