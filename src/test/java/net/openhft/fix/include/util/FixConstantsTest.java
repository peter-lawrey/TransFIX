package net.openhft.fix.include.util;

import org.junit.Test;

public class FixConstantsTest extends FixConstants{

	@Test
    public void test() {
        System.out.println(fieldsNumber.length);
        System.out.println(fieldsName.length);
        System.out.println(fieldsTypeOrdering.length);
        System.out.println(fieldsWithDefinedValues.length);
    }
}
