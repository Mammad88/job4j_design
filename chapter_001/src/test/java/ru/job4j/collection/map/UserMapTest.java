package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * тестирование коллекций Hashmap.
 *
 * @author Bruki mammad.
 * @version $1.0$
 * @since 15.09.2020
 */
public class UserMapTest {
    @Test
    public void whenOutputMap() {
        var first = new User("Alex", 3, new GregorianCalendar(2017, Calendar.APRIL, 21));
        var second = new User("Alex", 3, new GregorianCalendar(2017, Calendar.APRIL, 21));
        var third = new User("Alexei", 4, new GregorianCalendar(2016, Calendar.APRIL, 21));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "First User");
        map.put(second, "Second User");
        map.put(third, "Third User");
        System.out.println("first hashcode :" + first.hashCode());
        System.out.println("second hashcode :" + second.hashCode());
        System.out.println("third hashcode :" + third.hashCode());
        System.out.println(map);
        var four = new User("Alexei", 4, new GregorianCalendar(2016, Calendar.APRIL, 21));
        boolean result = map.containsKey(four);
        System.out.println("result = " + result);
        System.out.println();
        System.out.println(System.identityHashCode(first));
        System.out.println(System.identityHashCode(second));
        System.out.println(System.identityHashCode(third));
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println();
            System.out.println(map.keySet());
            System.out.println(map.values());
        }
    }
}
