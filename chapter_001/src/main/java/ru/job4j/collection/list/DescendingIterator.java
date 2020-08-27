package ru.job4j.collection.list;

import java.util.Iterator;
/**
 * интерфейс обратного итератора связанного списка.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 27.08.2020
 */
public interface DescendingIterator<E> {
    Iterator<E> descendingIterator();
}
