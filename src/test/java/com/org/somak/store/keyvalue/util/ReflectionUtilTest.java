package com.org.somak.store.keyvalue.util;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class ReflectionUtilTest {

    @ParameterizedTest
    @ValueSource(strings = {"com.org.somak.store.keyvalue.factory.ProductFactory", "com.org.somak.store.keyvalue.factory.PenFactory"})
    public void testLoadingClass(String className) {

        //when
        Object response = null;
        try {
            response = ReflectionUtil.of(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //then
        assertNotNull(response);

    }

    @Test
    public void testFailedLoadingClass() {
        //given
        String className = "com.org.somak.store.keyvalue.dto.ProductNotAvailable";
        //when and then
        assertThrows(ClassNotFoundException.class,   ()-> ReflectionUtil.of(className));
    }


    @Test
    public void testNoArgsConstructorMissing(){

        class Dummy{
            public Dummy(String args){

            }
        }
        //given
        String className = Dummy.class.getClass().getCanonicalName();
        //when

        try {
            Object response = ReflectionUtil.of(className);
            assertNull(response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
