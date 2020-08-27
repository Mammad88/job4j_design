package ru.job4j.collection.list;

/**
 * интерфейс связанного списка.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 27.08.2020
 */
public interface Linked<E> {

    void addLast(E value);

    void addFirst(E value);

    int size();

    E getElementByIndex(int counter);
}
