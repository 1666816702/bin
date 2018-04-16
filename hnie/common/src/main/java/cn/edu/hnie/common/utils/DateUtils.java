package cn.edu.hnie.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理
 * 
 */
public class DateUtils {

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static DateUtils sysDate = new DateUtils();

	private static String sMaxDate = "2038-12-31";

	private static Date maxDate;

	// 数据库时间与WEB服务器时间差
	private static long diff = 0;

	private static int dateLength = 12;

	// 日期格式: yyyyMMdd
	private static DateFormat dateFormatSimple;

	// 日期格式: yyyyMM
	private static DateFormat dateFormatYearMonth;

	// 日期格式
	private static DateFormat dateFormatEn;

	// 日期时间格式
	private static DateFormat dateTimeFormatEn;

	// 不允许实例化SysDate对象
	private DateUtils() {
	}

	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/** 获取最大日期时间 */
	public static Date getMaxDate() {
		if (maxDate == null) {
			maxDate = DateUtils.getDate(sMaxDate);
		}
		return maxDate;
	}

	/** 获取最大日期时间 */
	public static Date addMonth(Date date, int month) {
		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		cal.setTime(date);
		// cal.set (date.getYear(), date.getMonth(),);
		// cal.roll(Calendar.MONTH,-month);
		cal.roll(Calendar.MONTH, month);
		return cal.getTime();
	}

	/** 获取系统日期时间 */
	public static Date getSysDate() {
		Date date = Calendar.getInstance().getTime();
		return new Timestamp(date.getTime() - diff);
	}

	// 将指定日期对象格式化成指定格式的日期字符串
	private static String getDate(Date date, DateFormat formator) {
		return formator.format(date);
	}

	// 将指定的日期字符串按照指定的格式解析成日期对象
	private static Date getDate(String date, DateFormat formator) {
		if ((date == null) || (date.length() <= 0)) {
			return null;
		}
		Date d = null;
		try {
			d = formator.parse(date);
		} catch (Exception e) {
			try {
				if (date.length() <= dateLength) {
					d = DateUtils.getDateFormat().parse(date);
				} else {
					d = DateUtils.getDateTimeFormat().parse(date);
				}
			} catch (ParseException e1) {
				// String format1 = SysConfig.getFormatDate();
				// String format2 = SysConfig.getFormatDateTime();
				// throw new ExceptionSys("日期格式错误，正确格式应该是：" , e);
			}
		}
		if (d != null) {
			return getTimestamp(d);
		}
		return null;
	}

	private static DateFormat getFormatYearMonth() {
		if (dateFormatYearMonth == null) {
			String format = "yyyyMM";
			dateFormatYearMonth = new SimpleDateFormat(format);
		}
		return dateFormatYearMonth;
	}

	/** 获取当前系统日期，并转换成字符串，格式：yyyyMM */
	public static String getDateYearMonth() {
		return getDate(getSysDate(), getFormatYearMonth());
	}

	/** 获取当前系统日期，并转换成字符串，格式：yyyyMM */
	public static Long getDateMonth() {
		return new Long(format(getSysDate(), "MM"));
	}

	/** 获取指定日期的字符串，格式：yyyyMM */
	public static String getDateYearMonth(Date date) {
		return getDate(date, getFormatYearMonth());
	}

	private static DateFormat getFormatSimple() {
		if (dateFormatSimple == null) {
			String format = "yyyyMMdd";
			dateFormatSimple = new SimpleDateFormat(format);
		}
		return dateFormatSimple;
	}

	/** 获取当前系统日期，并转换成字符串，格式：yyyyMMdd */
	public static String getDateSimple() {
		return getDate(getSysDate(), getFormatSimple());
	}

	/** 获取指定日期的字符串，格式：yyyyMMdd */
	public static String getDateSimple(Date date) {
		return getDate(date, getFormatSimple());
	}

	/**
	 * 取得日期格式，由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
	 */
	public static DateFormat getDateFormat() {
		if (dateFormatEn == null) {
			String format = "yyyy-MM-dd";
			dateFormatEn = new SimpleDateFormat(format);
		}
		return dateFormatEn;
	}

	/**
	 * 取得日期时间格式，由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
	 */
	public static DateFormat getDateTimeFormat() {
		if (dateTimeFormatEn == null) {
			String format = "yyyy-MM-dd HH:mm:ss";
			dateTimeFormatEn = new SimpleDateFormat(format);
		}
		return dateTimeFormatEn;
	}

	/**
	 * 获取当前系统日期，并转换成字符串，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
	 */
	public static String getDate() {
		return getDate(getSysDate(), getDateFormat());
	}

	/**
	 * 获取当前系统日期时间，并转换成字符串，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime() {
		return getDate(getSysDate(), getDateTimeFormat());
	}

	/**
	 * 将指定日期对象转换成字符串，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
	 */
	public static String getDate(Date date) {
		return getDate(date, getDateFormat());
	}

	/**
	 * 将指定日期对象转换成字符串，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(Date date) {
		return getDate(date, getDateTimeFormat());
	}

	/**
	 * 将字符串转换成日期对象，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
	 */
	public static Date getDate(String date) {
		return getDate(date, getDateFormat());
	}

	/**
	 * 将字符串转换成日期对象，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
	 */
	public static Date getDateTime(String date) {
		return getDate(date, getDateTimeFormat());
	}

	/** 取得指定日期所在月份的第一天，如果未指定日期，则默认为当前日期 */
	public static Date getMonthDateFirst(Date date) {
		Date nowDate = date;
		if (nowDate == null) {
			nowDate = getSysDate();
			if (nowDate == null) {
				return null;
			}
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DATE, 1);

		return calendar.getTime();
	}

	/** 取得指定日期所在月份的最后一天，如果未指定日期，则默认为当前日期 */
	public static Date getMonthDateLast(Date date) {
		Date nowDate = date;
		if (nowDate == null) {
			nowDate = getSysDate();
			if (nowDate == null) {
				return null;
			}
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);

		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, lastDay);

		return calendar.getTime();
	}

	private static Date getTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		if (date instanceof Timestamp) {
			return date;
		}
		return new Timestamp(date.getTime());
	}

	public static DateUtils getInstance() {
		return sysDate;
	}
}
