package shop;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class PlainTest {

	private Class[] wrappedClass=new Class[] {Integer.class,Double.class,Float.class,Boolean.class,Character.class
			,Byte.class,Short.class,Long.class};
	@Test
	public void test() {
		Integer obj=new Integer(2);
		Class objClass=obj.getClass();
		if(Arrays.stream(wrappedClass).anyMatch((wClass)->{return wClass.equals(objClass);}))
			out.println(obj.toString());
	}
}
