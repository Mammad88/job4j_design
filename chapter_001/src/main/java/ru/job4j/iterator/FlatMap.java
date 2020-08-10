package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class FlatMap - итератор итераторов.
 *
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $2.0$
 * @since 08.08.2020
 */
public class FlatMap<T> implements Iterator<T> {
    /**
     * поле, храняшее вложенные итераторы.
     */
    private final Iterator<Iterator<T>> data;
    /**
     * поле для хранения указателя на текущий итератор.
     */
    private Iterator<T> cursor;

    /**
     * конструктор для инициализаций вложенных итераторов.
     *
     * @param data - вложенный итератор.
     */
    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        this.cursor = data.hasNext() ? data.next() : null;
    }

    /**
     * Проверяем, пока итератор в поле {@link #cursor} не имеет элементов, мы передвигаем
     * указатель на следующий итератор, иначе если есть элементы, мы выходим из цикла.
     *
     * @return true, если есть
     */
    @Override
    public boolean hasNext() {
        while (!cursor.hasNext() && data.hasNext()) {
            cursor = data.next();
            if (cursor.hasNext()) {
                break;
            }
        }
        return cursor.hasNext();
    }

    /**
     * выдаём следующий элемент, обращаясь к полю {@link #cursor}
     * если эл-тов больше нет — кидаем исключение
     *
     * @return следующее число.
     * @throws NoSuchElementException если эл-тов не осталось.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("больше нет элементов!");
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
