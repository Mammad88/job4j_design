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

    /**
     * метод добавление элемента в список.
     *
     * @param value - добавляемый элемент.
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод удаление первого элемента в списке.
     *
     * @return temp - удаление первого элемента.
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> temp = head;
        head = head.next;
        temp.next = null;
        return temp.value;

    }

    /**
     * Удаляем последний элемент.
     */
    public T deleteLast() {
        T elem = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            elem = head.value;
            head = null;
        } else {
            var tail = head;
            while (tail.next.next != null) {
                tail = tail.next;
            }
            elem = tail.next.value;
            tail.next = null;
        }
        return elem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
