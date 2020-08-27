package ru.job4j.collection.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование контейнера на базе связанного списка(на подобие LinkedList),
 * контейнер увеличивается по мере добавление элементов в конец  или начало списка.
 *
 * @author Bruki mammad.
 * @version $1.0$
 * @since 27.08.2020
 */
public class LinkedContainerTest {

    /**
     * тестируем метод добавление элемента в начало списка.
     */
    @Test
    public void whenAddFirst() {
        var linkedList = new LinkedContainer<>();
        linkedList.addFirst("one");
        linkedList.addFirst("two");
        linkedList.addFirst("three");
        assertThat(linkedList.getElementByIndex(2), is("one"));
    }

    /**
     * тестируем метод добавление элемента в конец списка.
     */
    @Test
    public void whenAddLast() {
        var linked = new LinkedContainer<>();
        linked.addLast(1);
        linked.addLast(2);
        linked.addLast(3);
        assertThat(linked.getElementByIndex(2), is(3));
    }

    /**
     * Тестируем получение размера связанного списка.
     */
    @Test
    public void whenGiveSizeElements() {
        LinkedContainer<String> linkedList = new LinkedContainer<>();
        linkedList.addFirst("one");
        linkedList.addFirst("two");
        linkedList.addFirst("three");
        var rsl = linkedList.size();
        assertThat(rsl, is(3));
    }

    /**
     * Тестируем получение элементов по индексу.
     */
    @Test
    public void whenGetElementByIndex() {
        LinkedContainer<String> linked = new LinkedContainer<>();
        linked.addFirst("one");
        linked.addFirst("two");
        linked.addFirst("three");
        assertThat(linked.getElementByIndex(1), is("two"));
    }

    /**
     * Тестируем ошибку на неверный индекс.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetBadIndexThenError() {
        var list = new LinkedContainer<>();
        list.addLast(1);
        list.getElementByIndex(2);
    }


    /**
     * Тестируем ошибку итератора при изменении списка.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenListChangeThenIteratorError() {
        var list = new LinkedContainer<>();
        list.addLast(1);
        var it = list.iterator();
        list.addLast(2);
        it.next();
    }

    /**
     * Тестируем  получение элемента по обратному итератору.
     */
    @Test
    public void whenGetItemsFromDescendingIterator() {
        var linkedList = new LinkedContainer<>();
        linkedList.addLast("one");
        linkedList.addLast("two");
        linkedList.addLast("three");
        var it = linkedList.descendingIterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("three"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));
    }

    /**
     * Тестируем получение элементов по итератору.
     */
    @Test
    public void whenGetItemsFromIterator() {
        var linkedList = new LinkedContainer<>();
        linkedList.addLast("one");
        linkedList.addLast("two");
        linkedList.addLast("three");
        var it = linkedList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("one"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("three"));
    }

    /**
     * Тестируем ошибку итератора при получение больше значений чем есть.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetMoreItemsFromIteratorThenError() {
        var list = new LinkedContainer<>();
        list.addLast(1);
        var it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
