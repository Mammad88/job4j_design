package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * class Tree - тестирование бинарного дерево.
 *
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $1.0$
 * @since 24.01.2021
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Проверка на бинарное дерево.
     */
    @Test
    public void whenAddElBinaryStructureThenBinaryTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(3, 8);
        tree.add(3, 9);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }

    /**
     * Проверка, что дерево не бинарное.
     */
    @Test
    public void whenAddElNotBinaryStructureThenNotBinaryTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(5, 10);
        tree.add(3, 8);
        tree.add(3, 9);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }
}
