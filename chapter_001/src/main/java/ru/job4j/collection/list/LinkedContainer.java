package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class LinkedContainer - реализация контейнера на базе связанного списка(на подобие LinkedList),
 * контейнер увеличивается по мере добавление элементов в конец  или начало списка.
 *
 * @param <E> - тип эл-тов связанного списка.
 * @author Bruki mammad.
 * @version $2.0$
 * @since 01.09.2020
 */
public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {

    /**
     * firstNode - указатель на первый элемент.
     */
    private Node<E> firstNode;

    /**
     * указатель на последний элемент.
     */
    private Node<E> lastNode;

    /**
     * Счетчик изменений контейнера - это показатель,
     * который нам говорит, сколько раз коллекция была изменена с момента ее создания.
     */
    private int modCount = 0;
    /**
     * фактический размер списка (также указатель на первую ячейку).
     */
    private int size = 0;

    /**
     * конструктор для инициализаций первой и поседней ноды.
     */
    public LinkedContainer() {
        lastNode = new Node<E>(null, firstNode, null);
        firstNode = new Node<E>(null, null, lastNode);
    }

    @Override
    public void addLast(E value) {
        Node<E> prev = lastNode;
        prev.setCurrentElement(value);
        lastNode = new Node<E>(null, prev, null);
        prev.setNextElement(lastNode);
        size++;
        modCount++;
    }

    @Override
    public void addFirst(E value) {
        Node<E> next = firstNode;
        next.setCurrentElement(value);
        firstNode = new Node<E>(null, null, next);
        next.setPrevElement(firstNode);
        size++;
        modCount++;
    }

    /**
     * Method return number of elements in list.
     *
     * @return number of elements in list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает элемент, расположенный по указанному индексу.
     *
     * @param counter - счетчик искомого индекса в связанном списке.
     * @return найденный индекс связанного списка.
     */
    @Override
    public E getElementByIndex(int counter) {
        Objects.checkIndex(counter, size);
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    /**
     * реализация обратного итератора.
     *
     * @return первый элемент.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {

            /**
             * указатель на последний элемент списка.
             */
            Node<E> node = lastNode;

            /**
             * поле хранения начального состояния изменения связанного списка.
             */
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                node = node.getPrevElement();
                return node.getCurrentElement();
            }
        };
    }

    /**
     * реализация итератора по списку.
     *
     * @return последний элемент списка.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            /**
             * указатель на текущую позицию.
             */
            private int counter = 0;

            /**
             * поле хранения начального состояния изменения связанного списка.
             */
            private final int expectedModCount = modCount;

            /**
             * проверяют условие, если условие выполнено (expectedModCount != modCount),
             * значит на момент итерирования была изменена коллекция,
             * вылетает исключение ConcurrentModificationException.
             *
             * @return true, если остались ещё не выданные эл-ты.
             * @throws ConcurrentModificationException - выбрасывает исклюбчение, если была измена колекция.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return counter < size;
            }

            /**
             * Проверяем, если есть доступный эл-т,
             * то передвигаем указатель позиции на следующий эл-т,
             * иначе выбрасываем ошибку NoSuchElementException.
             *
             * @return следующий элемент.
             * @throws NoSuchElementException если отсутствуют эл-ты к выдаче.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return getElementByIndex(counter++);
            }
        };
    }

    private static class Node<E> {
        /**
         * текуший элемент
         */
        private E currentElement;

        /**
         * указатель на следующий элемент.
         */
        private Node<E> nextElement;

        /**
         * указатель на предыдущий элемент.
         */
        private Node<E> prevElement;

        /**
         * конструктор для инициализаций списка с помошью указателей.
         *
         * @param currentElement - текущий элемент.
         * @param prevElement    - предыдущий элемент.
         * @param nextElement    - следующий элемент.
         */
        private Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }
    }
}
