package ru.job4j.generic;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class RoleStoreTest - тестирование хранилище ролей.
 *
 * @author Bruki Mammad.
 * @version $1.0$
 * @since 15.08.2020
 */
public class RoleStoreTest {

    @Test
    public void whenAddElementAndGetThenHisGet() {
        RoleStore store = new RoleStore();
        Role[] roles = new Role[]{
                new Role("1"),
                new Role("2"),
                new Role("3"),
        };
        store.add(roles[0]);
        store.add(roles[1]);
        store.add(roles[2]);
        assertThat(store.findById("2"), is(roles[1]));
    }

    /**
     * Тестируем замену роли.
     */
    @Test
    public void whenReplaceElementThenNewElement() {
        RoleStore store = new RoleStore();
        Role[] roles = new Role[]{
                new Role("1"),
                new Role("2"),
                new Role("3"),
        };
        store.add(roles[0]);
        store.add(roles[1]);
        assertThat(store.replace("2", roles[2]), is(true));
        assertThat(store.findById("3"), is(roles[2]));
    }

    /**
     * Тестируем удаление роли.
     */
    @Test
    public void whenDeleteElementThenMemNotHis() {
        RoleStore store = new RoleStore();
        Role[] roles = new Role[]{
                new Role("1"),
                new Role("2"),
                new Role("3"),
        };
        store.add(roles[0]);
        store.add(roles[1]);
        store.add(roles[2]);
        assertThat(store.findById("2"), is(roles[1]));
        assertThat(store.delete("2"), is(true));
        assertThat(store.findById("2"), Matchers.nullValue());
    }
}
