package cn.edu.hnie.common.utils;

import java.util.List;

public final class ValidateHelper {

	public static boolean isEmptyList(List<?> o) {
		if ((o == null) || o.isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmptyList(List<?> o) {
		return !isEmptyList(o);
	}
}
