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
    private Node<T> head;

    /**
     * метод добавление элемента в список.
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
     * метод удаление (обнуление ссылки на следующий элемент) в списке.
     */
    public void deleteFirst() {
        Node<T> temp = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = temp.next;
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

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
