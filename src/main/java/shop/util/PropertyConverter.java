package shop.util;

import shop.bean.Property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 将List<Property>转换为Map
 */
public class PropertyConverter {
    public static Map<String, List<String>> convert(List<Property> list){
        Map<String,List<String>> map=new HashMap<>();
        list.stream().forEach((Property prop)->{
            if(map.containsKey(prop.getName()))
                map.get(prop.getName()).add(prop.getValue());
            else
                map.put(prop.getName(), Stream.of(prop.getValue()).collect(Collectors.toList()));
        });
        return map;
    }
}
