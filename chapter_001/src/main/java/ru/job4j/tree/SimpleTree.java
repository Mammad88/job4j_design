package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс SimpleTree - интерфейс элементарного структуры дерево.
 *
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $1.0$
 * @since 24.01.2021
 */
public interface SimpleTree<E extends Comparable<E>> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}
