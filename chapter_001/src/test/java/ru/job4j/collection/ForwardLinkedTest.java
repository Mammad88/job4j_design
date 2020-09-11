package ru.job4j.collection;

import org.junit.Test;


import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * тестирование метода revert - перестановка элементов в обратном порядке.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 10.09.2020
 */
public class ForwardLinkedTest {

    /**
     * тестирование метода add - добавление элемента в список.
     */
    @Test
    public void whenAddThenIt() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    /**
     * тестирование метода revert - переворачивание связанного списка.
     */
    @Test
    public void whenAddAndRevertThenIterator() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
