package ru.job4j.generic;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования обертки для массивов.
 *
 * @author Bruki Mammad.
 * @version $2.0$
 * @since 14.08.2020
 */
public class SimpleArrayTest {
    SimpleArray<Integer> result;

    /**
     * Проверка добавление элементов.
     */
    @Test
    public void whenAddElementsThenGetsHis() {
        result = new SimpleArray<>(4);
        result.add(1);
        result.add(3);
        result.add(5);
        result.add(7);
        assertThat(result.get(0), is(1));
        assertThat(result.get(1), is(3));
        assertThat(result.get(2), is(5));
        assertThat(result.get(3), is(7));
    }

    /**
     * проверка на ошибку при добавлений больше элементов чем размер массива.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddManyItemsThenErrors() {
        result = new SimpleArray<>(2);
        result.add(1);
        result.add(3);
        result.add(5);
    }

    /**
     * проверка замены элемента по индексу.
     */
    @Test
    public void whenSetElementsFromIndexThenEditElement() {
        result = new SimpleArray<>(3);
        result.add(1);
        result.add(3);
        result.add(5);
        result.set(0, 8);
        assertThat(result.get(0), is(8));
        assertThat(result.get(1), is(3));
        assertThat(result.get(2), is(5));
    }

    /**
     * Проверка метода на удаление элемента.
     */
    @Test
    public void whenDeleteElementThenNotHaveHis() {
        result = new SimpleArray<>(3);
        result.add(2);
        result.add(4);
        result.add(6);
        result.remove(2);
        assertThat(result.get(0), is(2));
        assertThat(result.get(1), is(4));
    }

    /**
     * Тестирование работы итератора.
     */
    @Test
    public void whenGetIteratorThenHaveIterator() {
        result = new SimpleArray<>(3);
        result.add(1);
        result.add(2);
        result.add(3);
        Iterator<Integer> iterator = result.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenNotHaveIndexElementThenError() {
        result = new SimpleArray<>(2);
        result.add(1);
        result.remove(0);
        result.set(0, 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNullOrEmpty() {
        Iterator<Object> iterator = Collections.emptyIterator();
        iterator.next();
        iterator.next();
    }
}
