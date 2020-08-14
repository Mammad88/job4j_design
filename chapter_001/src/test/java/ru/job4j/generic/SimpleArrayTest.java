package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования обертки для массивов.
 *
 * @author Bruki Mammad.
 */
public class SimpleArrayTest {
    /**
     * Проверка добавление элементов.
     */
    @Test
    public void whenAddElementsThenGetsHis() {
        SimpleArray<Integer> it = new SimpleArray<>(4);
        it.add(1);
        it.add(3);
        it.add(5);
        it.add(7);
        assertThat(it.get(0), is(1));
        assertThat(it.get(1), is(3));
        assertThat(it.get(2), is(5));
        assertThat(it.get(3), is(7));
    }

    /**
     * проверка на ошибку при добавлений больше элементов чем размер массива.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddManyItemsThenErrors() {
        SimpleArray<Integer> it = new SimpleArray<>(2);
        it.add(1);
        it.add(3);
        it.add(5);
    }

    /**
     * прогверка замены элемента по индексу.
     */
    @Test
    public void whenSetElementsFromIndexThenEditElement() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(3);
        it.add(5);
        it.set(0, 8);
        assertThat(it.get(0), is(8));
        assertThat(it.get(1), is(3));
        assertThat(it.get(2), is(5));
    }

    /**
     * Поверка метода на удаление элемента.
     */
    @Test
    public void whenDeleteElementThenNotHaveHis() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(2);
        it.add(4);
        it.add(6);
        it.remove(2);
        assertThat(it.get(0), is(2));
        assertThat(Objects.checkIndex(0, 2), is(0));
        assertThat(it.get(1), is(4));
        assertThat(Objects.checkIndex(1, 2), is(1));
    }

    /**
     * Тестирование работы итератора.
     */
    @Test
    public void whenGetIteratorThenHaveIterator() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        Iterator<Integer> iterator = it.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        iterator.remove();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        iterator.remove();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
        assertThat(Objects.checkIndex(0, 3), is(0));
    }

    /**
     * Тестирование ошибки итератора при двойном удалений подряд.
     */
    @Test(expected = IllegalStateException.class)
    public void whenIteratorRemoveTwoElementThenError() {
        SimpleArray<Integer> it = new SimpleArray<>(3);
        it.add(1);
        it.add(2);
        it.add(3);
        Iterator<Integer> iterator = it.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenNotHaveIndexElementThenError() {
        SimpleArray<Integer> it = new SimpleArray<>(2);
        it.add(1);
        it.remove(0);
        it.set(0, 1);
        assertThat(Objects.checkIndex(0, 0), is(0));
    }
}
