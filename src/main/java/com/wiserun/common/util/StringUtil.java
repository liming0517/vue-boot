package com.wiserun.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 字串工具类
 * 
 * @author chenKeYu
 * 
 * @date 2014年4月11日
 */
public class StringUtil {
	
	private static final String FONT_CODE = "UTF-8";

	private StringUtil() {
	}

	/**
	 * 将一个Double转为int的String，将省略小数点后面的值
	 * 
	 * @param d
	 * @return
	 */
	public static String doubleToIntString(Double d) {
		int value = ((Double) d).intValue();
		return String.valueOf(value);
	}

	/**
	 * 得到一个数字的大写(一到十之内)
	 * 
	 * @param num
	 * @return
	 */
	public static String getChineseNum(int num) {
		String[] chineseNum = new String[] { "一", "二", "三", "四", "五", "六", "七",
				"八", "九", "十" };
		return chineseNum[num];
	}

	public static String replaceEnter(String str) {
		if (str == null)
			return null;
		return str.replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 去除HTML 元素
	 * 
	 * @param element
	 * @return
	 */
	public static String getTxtWithoutHTMLElement(String element) {
		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);
		String temp = txt.toString().replaceAll("\n", "");
		temp = temp.replaceAll(" ", "");
		return temp;
	}

	public static String decode(String keyword) {
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return keyword;
	}

	/**
	 * 进行解析
	 * 
	 * @param regex
	 * @param rpstr
	 * @param source
	 * @return
	 */
	public static String doFilter(String regex, String rpstr, String source) {
		Pattern p = Pattern.compile(regex, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(source);
		return m.replaceAll(rpstr);
	}

	/**
	 * 脚本过滤
	 * 
	 * @param source
	 * @return
	 */
	public static String formatScript(String source) {
		source = source.replaceAll("javascript", "&#106avascript");
		source = source.replaceAll("jscript:", "&#106script:");
		source = source.replaceAll("js:", "&#106s:");
		source = source.replaceAll("value", "&#118alue");
		source = source.replaceAll("about:", "about&#58");
		source = source.replaceAll("file:", "file&#58");
		source = source.replaceAll("document.cookie", "documents&#46cookie");
		source = source.replaceAll("vbscript:", "&#118bscript:");
		source = source.replaceAll("vbs:", "&#118bs:");
		source = doFilter("(on(mouse|exit|error|click|key))", "&#111n$2",
				source);
		return source;
	}

	/**
	 * 格式化HTML代码
	 * 
	 * @param htmlContent
	 * @return
	 */
	public static String htmlDecode(String htmlContent) {
		htmlContent = formatScript(htmlContent);
		htmlContent = htmlContent.replaceAll(" ", "&nbsp;")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				.replaceAll("\n\r", "<br>").replaceAll("\r\n", "<br>")
				.replaceAll("\r", "<br>");
		return htmlContent;
	}

	public static Collection<Integer> stringToInteger(Object obj) {
		List<Integer> ids = new ArrayList<Integer>();
		if (obj == null) {
			return ids;
		}
		if (obj instanceof String) {
			ids.add(Integer.valueOf(obj.toString()));
		} else if (obj instanceof String[]) {
			String[] strIds = (String[]) obj;
			for (int i = 0; i < strIds.length; i++) {
				ids.add(Integer.valueOf(strIds[i]));
			}
		}
		return ids;
	}

	/**
	 * 将 java 字段转换为数据库字段
	 * 
	 * @param property
	 * @return
	 */
	public static String propertyToColumn(String property) {
		if (null == property) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer columnName = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isUpperCase(c)) {
				columnName.append("_");
			}
			columnName.append(c);
		}
		return columnName.toString().toUpperCase();
	}

	/**
	 * 将 数据库字段 字段转换为 java
	 * 
	 * @param property
	 * @return
	 */
	public static String columnToProperty(String columnName) {
		if (null == columnName) {
			return "";
		}
		columnName = columnName.toLowerCase();
		char[] chars = columnName.toCharArray();
		StringBuffer property = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				int j = i + 1;
				if (j < chars.length) {
					property.append(String.valueOf(chars[j]).toUpperCase());
					i++;
				}
			} else {
				property.append(c);
			}
		}
		return property.toString();
	}

	/**
	 * 获取两个字符串从开始的地方相同的部分
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getSameString(String str1, String str2) {
		char[] a1 = str1.toCharArray();
		char[] b1 = str2.toCharArray();
		int c = a1.length < b1.length ? a1.length : b1.length;
		int i = 0;
		for (; i < c; i++) {
			if (a1[i] != b1[i]) {
				break;
			}
		}
		return str1.substring(0, i);
	}

	/**
	 * 从两个字符串获取整个范围内的所有字段名称 例如：pminus01，pminus30获取的是pminus01..30，这样30个字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static List<String> getStringBetween(String str1, String str2) {
		List<String> retList = new ArrayList<String>();
		String sameStr = getSameString(str1, str2);
		int fomatLen = str1.replace(sameStr, "").length();
		int fromPoint = Integer.parseInt(str1.replace(sameStr, ""));
		int endPoint = Integer.parseInt(str2.replace(sameStr, ""));
		String fmtStr = "%0" + fomatLen + "d";
		for (int i = 0; i <= endPoint - fromPoint; i++) {
			retList.add(sameStr + String.format(fmtStr, fromPoint + i));
		}
		return retList;
	}

	public static String getPercentFormat(String taxRate) {
		if (StringUtils.isBlank(taxRate)) {
			return null;
		}
		BigDecimal t = new BigDecimal(taxRate);
		t = t.multiply(new BigDecimal(100));
		try {
			int i = t.intValueExact();
			return i + "%";
		} catch (ArithmeticException e) {
			return t.doubleValue() + "%";
		}
	}

	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c)
			throws UnsupportedEncodingException {
		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		return String.valueOf(c).getBytes(FONT_CODE).length > 1;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String substring(String orignal, int count)
			throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), FONT_CODE);
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if (count > 0 && count < orignal.getBytes(FONT_CODE).length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						count=count-2;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}

	public static List<String> getStringAryByteLen(String srcStr, int byteLen) {
		int strLen = srcStr.getBytes().length;
		List<String> retList = new ArrayList<String>();
		for (int i = 0; i < Math.ceil((double) strLen / (double) byteLen); i++) {
			try {
				String subStr = StringUtil.substring(srcStr, byteLen);
				retList.add(subStr);
				srcStr = srcStr.replace(subStr, "");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return retList;
	}
	
	/**
	 * 格式化数字为千分位显示；
	 * 
	 * @param 要格式化的数字
	 *            ；
	 * @return
	 */
	public static String fmtMicrometer(String text) {
		DecimalFormat df = null;
		if (text.indexOf(".") > 0) {
			if (text.length() - text.indexOf(".") - 1 == 0) {
				df = new DecimalFormat("###,##0.");
			} else if (text.length() - text.indexOf(".") - 1 == 1) {
				df = new DecimalFormat("###,##0.0");
			} else {
				df = new DecimalFormat("###,##0.00");
			}
		} else {
			df = new DecimalFormat("###,##0");
		}
		double number = 0.0;
		try {
			number = Double.parseDouble(text);
		} catch (Exception e) {
			number = 0.0;
		}
		return df.format(number);
	}

	/**
	 * 字符串转LIST （以，作为分割符号）
	 * @param s
	 */
	public static List<?> convert2List(String s) {
        //String s = "64,127,248,569";
        String d[] = s.split(",");
        List<String> list = new ArrayList<String>();
         
        for (int i = 0; i < d.length; i++) {
        	list.add(d[i]);
        }
        return list;
    }

}
