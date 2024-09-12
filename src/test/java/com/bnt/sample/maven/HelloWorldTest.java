package com.bnt.sample.maven;

import com.bnt.sample.model.maven.HelloWorld;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * TODO
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/8/26 下午4:47 bnt
 * @history
 */
public class HelloWorldTest {

    @Test
    public void testSayHello() {
        String s = new HelloWorld().sayHello();
        assertEquals("Hello Maven", s);
    }
}
