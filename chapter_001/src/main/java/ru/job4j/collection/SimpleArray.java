package ru.job4j.collection;

import java.util.*;

/**
 * Class SimpleArray - реализация динамического списка на массиве (на подобие Arraylist).
 * ArrayList - это массив, когда элементов становиться больше чем ячеек в массиве,
 * то ArrayList создает новый массив с большим размером.
 *
 * @param <T> - тип эл-тов массива.
 * @author Bruki mammad.
 * @version $2.0$
 * @since 14.08.2020
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * начальная емкость контейнера.
     */
    private Object[] container = new Object[10];

    /**
     * фактический размер массива (также указатель на первую ячейку).
     */
    private int position = 0;
    /**
     * Счетчик изменений контейнера - это показатель,
     * который нам говорит, сколько раз коллекция была изменена с момента ее создания.
     */
    private int modCount = 0;

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param index - индекс ячейки массива.
     * @return значение из ячейки.
     */
    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) container[index];
    }

    /**
     * Добавляет в массив очередной элемент, если фактический размер массива #position
     * больше начальной емкости контейнера (#link container = 10 элементов), то мы увеличиваем
     * начальный размер контейнера в два раза и добавляем указанный элемент в свободную ячейку,
     * увеличиваем счетчик изменений modCount++.
     *
     * @param model - добавляемый указанный элемент в первую свободную ячейку.
     */
    public void add(T model) {
        if (position + 1 >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[position++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * указатель на текущую позицию.
             */
            private int cursor;
            /**
             * поле хранения начального состояния изменеия колекций.
             */
            private final int expectedModCount = modCount;

            /**
             * проверяют условие, если условие выполнено (expectedModCount != modCount),
             * значит на момент итерирования была изменена коллекция,
             * вылетает исключение ConcurrentModificationException.
             *
             * @return true, если остались ещё не выданные эл-ты.
             * @throws ConcurrentModificationException - выбрасывает исклюбчение, если была измена колекция.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < position;
            }

            /**
             * Проверяем, если есть доступный эл-т,
             * то передвигаем указатель позиции на следующий эл-т,
             * иначе выбрасываем ошибку NoSuchElementException.
             *
             * @return следующий элемент.
             * @throws NoSuchElementException если отсутствуют эл-ты к выдаче.
             */
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[cursor++];
            }
        };
    }
}
