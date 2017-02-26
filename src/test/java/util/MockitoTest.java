package util;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.mockito.Mockito.*;
/**
 * Created by asaetsu on 2017/02/26.
 */
public class MockitoTest {
    @Test
    public void verifyTest(){

        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.removeAll();

        verify(mockedList).add("one");
        verify(mockedList).removeAll();
    }

    @Test
    public void someStubbingTest() {

        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn(new RuntimeException());

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));
    }
}
