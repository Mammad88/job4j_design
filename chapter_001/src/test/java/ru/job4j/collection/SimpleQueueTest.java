package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Тестирование Queue (очереди) для связанного списка.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 12.09.2020
 */
public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }
}
