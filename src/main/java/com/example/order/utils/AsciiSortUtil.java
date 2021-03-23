package com.example.order.utils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 参数名按ASCII码从小到大排序
 * @return
 */
public class AsciiSortUtil {

	public static StringBuffer AsciiSort(Map<String,Object> parameters) {
		StringBuffer sbkey = new StringBuffer();
		// entrySet 所有参与传参的参数按照accsii排序（升序）
		Set es = parameters.entrySet();
		Iterator it = es.iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			//空值不传递，不参与签名组串
			if (null != v && !"".equals(v)) {
				sbkey.append(k + "=" + v + "&");
			}
		}
//        sbkey = sbkey.append("key=" + key);
//        System.out.println(sbkey);
		return sbkey;
	}
}
