package br.com.kiev.bluefood.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

	public static <T> List<T> listOf(T... objs) {
		if (objs == null) {
			return Collections.EMPTY_LIST;
		}
		
//		List<T> list = new ArrayList<>(objs.length);
//		for (T obj : objs) {
//			list.add(obj);
//		}
		return Arrays.stream(objs).collect(Collectors.toList());
	}

}
