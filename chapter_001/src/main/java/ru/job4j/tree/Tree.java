package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * class Tree - элементарная структура дерево.
 *
 * @author Bruki Mammad (bruki.mammad@mail.ru)
 * @version $2.0$
 * @since 08.02.2021
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Ссылка на корневой элемент дерева.
     */
    private final Node<E> root;

    /**
     * Формирует начальное состояние дерева, устанавливает переданный элемент в кач-ве корневого.
     *
     * @param root - значение корневого элемента.
     */
    Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * method add проверяет, что значение child нет в дереве
     * a значение parent есть в дереве,
     * то добавляет child в дерево,
     * если значение child есть в дереве, то метод возвращает false.
     *
     * @param parent - значение родителя.
     * @param child  - значение дочернего узла.
     * @return rsl - результат.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> parentEl = findBy(parent);
            if (parentEl.isPresent()) {
                rsl = parentEl.get().leaves().add(new Node<>(child));
            }
        }
        return rsl;
    }

    /**
     * Осуществляет поиск заданного элемента в дереве.
     *
     * @param value искомое значение.
     * @return контейнер, кот. содержит искомый элемент (если присутствует).
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return this.findNode(eNode -> eNode.eqValue(value));
    }

    /**
     * Проверяет является ли дерево бинарным (кол-во потомков каждого узла не превышает два).
     * С помощью {@link #findNode(Predicate)} ищет узел с кол-вом потомков больше двух.
     * По наличию/отсутствию такого узла возращает true/false.
     *
     * @return true, если узлов с потомками > 2 не найдено.
     */
    public boolean isBinary() {
        return findNode(eNode -> eNode.leaves().size() > 2).isEmpty();
    }

    /**
     * Возвращает узел дерева, удовлетворяющий переданному условию.
     *
     * @param predicate условие.
     * @return контейнер, кот. содержит искомый элемент (если присутствует)
     */
    private Optional<Node<E>> findNode(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.leaves());
        }
        return rsl;
    }
}
