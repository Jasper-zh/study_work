package mk3.three;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {

		//1.准备一个Map集合
		Map<String,Integer>m=new HashMap<String,Integer>();
		//2.准备一个String类型的对象描述原始字符串
		String str1=new String("123,456,123,789,456");
		//3.使用split方法对原始字符串按照字符串中的，拆分字符串，并在集合中查找
		String[]sArr=str1.split(",");
		for (int i = 0; i < sArr.length; i++) {
		    // 4.若集合中没有该字符串，则将该字符串和1组成一个键值对放入集合中
			if(!m.containsKey(sArr[i])) {
				m.put(sArr[i], 1);
			}
			// 5.若集合中有该字符串，则将该字符串对应的value值+1
			else {
				m.put(sArr[i], m.get(sArr[i])+1);
			}
		}

		for (Map.Entry<String, Integer> stringIntegerEntry : m.entrySet()) {
			System.out.println(stringIntegerEntry.getKey()+"出现了"+stringIntegerEntry.getValue()+"次！");
		}
	}  
}