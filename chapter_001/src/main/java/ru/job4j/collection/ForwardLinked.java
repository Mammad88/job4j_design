package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * реализация метода delete для односвязного списка.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 01.09.2020
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * указатель на первый элемент.
     */
    private Node<T> head;

    private int currentSize;
    /**
     * метод добавление элемента в список.
     *
     * @param value - добавляемый элемент.
     */
    public void add(T value) {
        var node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        var tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод удаление первого элемента в списке.
     *
     */
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        var temp = head;
        head = head.next;
        temp.next = null;
        return temp.value;
    }

    /**
     * метод удаление последнего элемента в списке.
     * @return текущий элемент.
     */
    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("Element cant be deleted");
        }
        var current = head;
        var next = current.next;
        if (next == null) {
            currentSize--;
            return current.value;
        }
        while (next.next !=null) {
            current = next;
            next = next.next;
        }
        current.next = null;
        currentSize--;
        return next.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Переворачиваем односвязный список в обратном порядке.
     */
    public void revert() {
        Node<T> revert = null;
        for (T value : this) {
            revert = new Node<>(value, revert);
        }
        head = revert;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
