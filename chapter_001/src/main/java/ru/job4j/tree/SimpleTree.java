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
public interface SimpleTree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
