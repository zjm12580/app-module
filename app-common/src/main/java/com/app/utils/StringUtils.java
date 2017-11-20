package com.app.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 空字符串。
	 */
	public static final String EMPTY_STRING = "";
	public static final String EMAIL_REG = "^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$";
	public static final String MOBILE_REG = "^((13[0-9])|(15[0|1|2|3|5|6|7|8|9]|17[6|8])|(18[0-9]))\\d{8}$";

	/**
	 * 检查字符串是否是空白：null、空字符串""或只有空白字符。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果为空白, 则返回true
	 */
	public static boolean isEmpty(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查字符串是否不是空白：null、空字符串""或只有空白字符。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果为空白, 则返回true
	 */
	public static boolean isNotEmpty(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 如果字符串是null，则返回空字符串""，否则返回字符串本身。
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 字符串本身或空字符串""
	 */
	public static String defaultIfNull(String str) {
		return (str == null) ? EMPTY_STRING : str;
	}

	/**
	 * 除去字符串尾部的指定字符，如果字符串是null，依然返回null。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为null表示除去空白字符
	 * @return 除去指定字符后的的字符串，如果原字串为null，则返回null
	 */
	public static String trimEnd(String str, String stripChars) {
		return trim(str, stripChars, 1);
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是null，依然返回null。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为null表示除去空白字符
	 * @param mode
	 *            -1表示trimStart，0表示trim全部，1表示trimEnd
	 * @return 除去指定字符后的的字符串，如果原字串为null，则返回null
	 */
	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		int start = 0;
		int end = length;
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}
		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}
		return str;
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * @return 如果两个字符串相同，或者都是null，则返回true
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * @return 如果两个字符串相同，或者都是null，则返回true
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * 判断字符串是否只包含unicode字母。 null将返回false，空字符串""将返回true。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果字符串非null并且全由unicode字母组成，则返回true
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和空格' '。 null将返回false，空字符串""将返回true。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果字符串非null并且全由unicode字母和空格组成，则返回true
	 */
	public static boolean isAlphaSpace(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i)) && (str.charAt(i) != ' ')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode字母和数字。 null将返回false，空字符串""将返回true。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果字符串非null并且全由unicode字母数字组成，则返回true
	 */
	public static boolean isAlphanumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断字符串是否只包含unicode数字。 null将返回false，空字符串""将返回true。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果字符串非null并且全由unicode数字组成，则返回true
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 取得长度为指定字符数的最左边的子串。
	 * 
	 * @param str
	 *            字符串
	 * @param len
	 *            最左子串的长度
	 * @return 子串，如果原始字串为null，则返回null
	 */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len < 0) {
			return EMPTY_STRING;
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	/**
	 * 取得长度为指定字符数的最右边的子串。
	 * 
	 * @param str
	 *            字符串
	 * @param len
	 *            最右子串的长度
	 * @return 子串，如果原始字串为null，则返回null
	 */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len < 0) {
			return EMPTY_STRING;
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(str.length() - len);
		}
	}

	/**
	 * 取得从指定索引开始计算的、长度为指定字符数的子串。
	 * 
	 * @param str
	 *            字符串
	 * @param pos
	 *            起始索引，如果为负数，则看作0
	 * @param len
	 *            子串的长度，如果为负数，则看作长度为0
	 * @return 子串，如果原始字串为null，则返回null
	 */
	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}

		if ((len < 0) || (pos > str.length())) {
			return EMPTY_STRING;
		}

		if (pos < 0) {
			pos = 0;
		}

		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	/**
	 * 删除所有在Character.isWhitespace(char)中所定义的空白。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 去空白后的字符串，如果原始字符串为null，则返回null
	 */
	public static String deleteWhitespace(String str) {
		if (str == null) {
			return null;
		}

		int sz = str.length();
		StringBuffer buffer = new StringBuffer(sz);

		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}

		return buffer.toString();
	}

	public static Boolean checkInteger(String str) {
		Pattern p = Pattern.compile("^[0-9]{1,20}$");
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断日期
	 */
	public static boolean checkDate(String str) {
		if (str == null)
			return false;
		Pattern p = Pattern.compile("^[1-2][0-9]{3}-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$");
		Matcher m = p.matcher(str);
		Boolean correct = m.find();
		return correct;
	}

	/******************* 分割线以上addby：zhuqifeng，以下为nedyou原有方法 **********************/

	/**
	 * 取首发尾字符,模糊中间全部字符
	 */
	public static String blurString(String s) {
		if (StringUtils.isEmpty(s)) {
			return s;
		}
		int len = s.length();
		String first_letter = "";
		String end_letter = "";
		first_letter = StringUtils.substring(s, 0, 1);
		end_letter = (len > 1 ? StringUtils.substring(s, len - 1, len) : "");

		if (StringUtils.isNotEmpty(first_letter)) {
			s = first_letter + "**" + end_letter;
		}
		return s;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		email = email.trim();
		if (email.indexOf(' ') != -1)
			return false;

		int idx = email.indexOf('@');
		if (idx == -1 || idx == 0 || (idx + 1) == email.length())
			return false;
		if (email.indexOf('@', idx + 1) != -1)
			return false;
		if (email.indexOf('.') == -1)
			return false;
		return true;
		/*
		 * Pattern emailer; if(emailer==null){ String check =
		 * "^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
		 * ; emailer = Pattern.compile(check); } Matcher matcher =
		 * emailer.matcher(email); return matcher.matches();
		 */
	}

	/**
	 * 判断是不是一个合法的手机号码
	 */
	public static boolean isMobile(String s) {
		s = trim(s);
		if (isEmpty(s))
			return false;
		Pattern p = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	/**
	 * 判断字符串是否是一个IP地址
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr) {
		if (isEmpty(addr))
			return false;
		String[] ips = split(addr, '.');
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0 && ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 判断json字符串长度
	 */
	public static int sizeJSON(JSONArray arrayJSON) {
		if (arrayJSON == null)
			return 0;
		return arrayJSON.size();
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * Check whether the given String has actual text. More specifically,
	 * returns <code>true</code> if the string not <code>null</code>, its length
	 * is greater than 0, and it contains at least one non-whitespace character.
	 * <p/>
	 * <code>StringUtils.hasText(null) == false<br/>
	 * StringUtils.hasText("") == false<br/>
	 * StringUtils.hasText(" ") == false<br/>
	 * StringUtils.hasText("12345") == true<br/>
	 * StringUtils.hasText(" 12345 ") == true</code>
	 * <p/>
	 * <p>
	 * Copied from the Spring Framework while retaining all license, copyright
	 * and author information.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>, its
	 *         length is greater than 0, and it does not contain whitespace only
	 * @see Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a String that purely consists of
	 * whitespace.
	 * <p/>
	 * <code>StringUtils.hasLength(null) == false<br/>
	 * StringUtils.hasLength("") == false<br/>
	 * StringUtils.hasLength(" ") == true<br/>
	 * StringUtils.hasLength("Hello") == true</code>
	 * <p/>
	 * Copied from the Spring Framework while retaining all license, copyright
	 * and author information.
	 * 
	 * @param str
	 *            the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 首字母转小写
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (isEmpty(s))
			return s;
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (isEmpty(s))
			return s;
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 分割字符得到前后两个字符
	 * 
	 * @param str
	 * @param separatorChar
	 *            :分割符
	 * @return
	 */
	public static String[] splitFirstLast(String str, String separatorChar) {
		String[] s = StringUtils.split(str, separatorChar);

		String first = "";
		String last = "";
		if (s != null && s.length > 0) {
			first = s[0];
			if (s.length > 1) {
				last = s[s.length - 1];
			}
		}
		return new String[] { first, last };
	}

	/**
	 * 重组字符串
	 */
	public static String restructuringString(String separatorChar, String... args) {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i < args.length; i++) {
			String[] str_array = StringUtils.split(args[i], separatorChar);
			if (str_array != null && str_array.length > 0) {
				for (String str : str_array) {
					s.append(separatorChar).append(str);
				}
			}
		}
		String results = s.toString();
		if (StringUtils.isNotEmpty(results)) {
			// results=separatorChar+results;
		}
		return results;
	}

	/**
	 * 根据记录数和每页现实文章数确定页数
	 * 
	 * @param recordCount
	 *            :记录数
	 * @param perPage
	 *            :每页记录数
	 * @return
	 */
	public static int pageCount(int recordCount, int perPage) {
		int pc = (int) Math.ceil(recordCount / (double) perPage);
		if (pc == 0)
			pc = 1;
		return pc;
	}

	/**
	 * String数组转Long
	 */
	public static Long[] valueOf(String[] s) {
		if (s == null || s.length <= 0) {
			return null;
		}
		Long[] l = new Long[s.length];
		for (int i = 0; i < s.length; i++) {
			l[i] = Long.valueOf(s[i]);
		}
		return l;
	}
	
	
	/**
	 * jsonObj转换String
	 */
	public  static String convertObjToString(Object obj){
		return String.valueOf(obj);
	}

	
	/**
	 * 
	 * 将字符串拼接成List
	 * @time 2017年3月27日
	 * @author 朱佳敏
	 * @package com.haicang.custom.util
	 * @year 2017
	 * @remark 
	 */
	public static String[] splitList(String str){
		return str.replace(" ","").split(",");
	}
}
