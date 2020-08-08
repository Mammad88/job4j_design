package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * class FlatMap - итератор итераторов.
 *
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $1.0$
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
     * @param data - вложенный итератор.
     */
    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        this.cursor = data.hasNext() ? data.next() : null;
    }

    /**
     * определяем есть ли ещё эл-ты в оставшихся итераторах
     * после работы {@link #check()} данное поле хранит ссылку на гарантированно не пустой
     * итератор (если null — эл-тов больше нет)
     *
     * @return true, если есть
     */
    @Override
    public boolean hasNext() {
        this.check();
        return this.cursor != null;
    }

    /**
     * выдаём следующий элемент, обращаясь к полю {@link #cursor}
     * если эл-тов больше нет — кидаем исключение
     *
     * @return следующее число
     * @throws NoSuchElementException если эл-тов не осталось
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("больше нет Элементов!");
        }
        return cursor.next();
    }

    /**
     * проверяем, что итератор в поле {@link #cursor} имеет ещё эл-ты, иначе передвигаем
     * указатель на следующий итератор (если невозможно — null) и рекурсивно вызываем
     * метод снова на случай, если след. итератор пуст по дефолту.
     */
    private void check() {
        if (this.cursor != null && !this.cursor.hasNext()) {
            this.cursor = data.hasNext() ? data.next() : null;
            this.check();
        }
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
