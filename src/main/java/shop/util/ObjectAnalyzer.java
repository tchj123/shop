package shop.util;
import static java.lang.System.out;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("rawtypes")
public class ObjectAnalyzer {
	private static List<Object> visited;
	private static Class[] wrappedClass=new Class[] {Integer.class,Double.class,Float.class,Boolean.class,Character.class
			,Byte.class,Short.class,Long.class};
	public static void printObject(Object obj){
		try{
			visited=new ArrayList<>();
			printObject(obj,0);
		}catch(IllegalArgumentException e) {
			throw new RuntimeException("IllegalArgumentException");
		}
		catch(IllegalAccessException e) {
			throw new RuntimeException("IllegalAccessException");
		}
	}
	private static void printObject(Object obj,int depth) throws IllegalArgumentException, IllegalAccessException {
		if(obj==null) {
			out.println("null");
			return;
		}
		//如果obj是String类型、枚举类型或者日期类型
		Class objClass=obj.getClass();
		if(objClass.equals(String.class) || objClass.isEnum() || objClass.isAssignableFrom(Date.class)) {
			out.println(obj.toString());
			return;
		}

		//如果是包装类型
		if(Arrays.stream(wrappedClass).anyMatch((wClass)->{return wClass.equals(objClass);})) {
			out.println(obj.toString());
			return;
		}
		
		//避免循环引用导致死循环
		if(visited.contains(obj))
			return;
		else
			visited.add(obj);
		
		//如果obj是数组		
		if(objClass.isArray()) {
			out.println("[");
			int length=Array.getLength(obj);
			boolean first=true;
			for(int i=0;i<length;i++) {
				if(first)
					first=false;
				else {
					out.print(",");
					return;
				}
				out.print(Array.get(obj, i));
			}
			printTab(depth);
			out.println("]");
		}
		
		//如果obj是List
		if(List.class.isAssignableFrom(objClass)) {
			out.println();
			printTab(depth-1);
			out.println("[");
			List list=(List) obj;
			boolean first=true;
			for(int i=0;i<list.size();i++) {
				if(first)
					first=false;
				else {
					printTab(depth-1);
					out.println("--------------");
				}
				printObject(list.get(i),depth);
			}
			printTab(depth-1);
			out.println("]");
			return;
		}
		//如果obj是map类型
		if(Map.class.isAssignableFrom(objClass)){
			out.println();
			printTab(depth-1);
			out.println("[");
			Map map=(Map) obj;
			Set<Map.Entry> entrySet=map.entrySet();
			boolean first=true;
			for(Map.Entry entry:entrySet) {
				if(first)
					first=false;
				else {
					printTab(depth-1);
					out.println("--------------");
				}
				out.print(entry.getKey()+":");
				printObject(entry.getValue(),depth);
			}
			printTab(depth-1);
			out.println("]");
			return;
		}
		//输出所有字段名和字段值
		Field[] fields=objClass.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for(Field field:fields) {
			Object val=field.get(obj);
			printTab(depth-1);
			out.print(field.getName()+":");
			//如果字段值是原始类型，直接输出
			if(field.getType().isPrimitive()) {
				out.println(val);
			}
			//否则递归输出
			else {
				printObject(val,depth+1);
			}
		}
	}
	//辅助函数，打印times个\t
	private static void printTab(int times) {
		for(int i=0;i<times;i++)
			out.print("\t");
	}
}
