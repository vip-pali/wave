package com.wave.utils;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;
import org.hibernate.impl.SessionFactoryImpl;




/**
 * This class provides some of the common utility functions
 */
public class Util
{
	private static Logger logger = LogManager.getLogger(Util.class);

	/**
	 * Default format for display if the user doesn't have any preferences
	 */
	public static String dFDefaultStr = "MM/dd/yyyy";
	public static String tFDefaultStr = "hh:mm a";
	public static SimpleDateFormat dFDefault = new SimpleDateFormat(dFDefaultStr);
	public static SimpleDateFormat tFDefault = new SimpleDateFormat(tFDefaultStr);

	/**
	 * Miscellanious date formats
	 */
	public static SimpleDateFormat dFEEEdMMMyyyyhhmma = new SimpleDateFormat("EEE, d MMM yyyy hh:mm a");
	public static SimpleDateFormat dFMMMyyyy = new SimpleDateFormat("MMM yyyy");
	public static SimpleDateFormat dFMMMd = new SimpleDateFormat("MMM d");
	public static SimpleDateFormat dFyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dFMMMW = new SimpleDateFormat("MMM W");
	public static SimpleDateFormat dFEEEdMMMyyyy = new SimpleDateFormat("EEE, d MMM yyyy");
	public static SimpleDateFormat dFEEEMMd = new SimpleDateFormat("EEE, MM/d");
	public static SimpleDateFormat dFhhmma = new SimpleDateFormat("hh:mm a");
	public static SimpleDateFormat dFMMddyyyyhhmmss = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	public static SimpleDateFormat dFDDmmyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
	public static SimpleDateFormat dFyyyyMMddhhmmss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat dFyyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dFkmm = new SimpleDateFormat("k:mm");

	public static SimpleDateFormat dFHmma = new SimpleDateFormat("H:mm a");
	public static SimpleDateFormat dFddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
	public static SimpleDateFormat dFddMMyyyyhhmmss = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	public static SimpleDateFormat uniqueNumber = new SimpleDateFormat("ddMMyyyyhhmmss");
	public static SimpleDateFormat dFddMMyyyyHHmmss = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	/**
	 * Decimal number format
	 * */
	public static String largeDoublePrecision = ".0000";

	/**
	 * Decimal number format
	 * */
	public static String doublePrecision = ".00";

	// private PlayerDAO playerDAO;

	/**
	 * @throws java.text.ParseException
	 *
	 */
	public static Date parseDefault(String dateStr) throws ParseException
	{
		return dFDefault.parse(dateStr);
	}

	/**
	 * @throws java.text.ParseException
	 *
	 */
	public static Date parseEEEdMMMyyyyhhmma(String dateStr) throws ParseException
	{
		return dFEEEdMMMyyyyhhmma.parse(dateStr);
	}

	/**
	 *
	 */
	public static Date parseyyyyMMdd(String dateStr) throws ParseException
	{
		return dFyyyyMMdd.parse(dateStr);
	}

	/**
	 *
	 */
	public static Date parseEEEdMMMyyyy(String dateStr) throws ParseException
	{
		return dFEEEdMMMyyyy.parse(dateStr);
	}

	/**
	 *
	 */
	public static Date parseyyyyMMddhhmmss(String dateStr) throws ParseException
	{
		return dFyyyyMMddhhmmss.parse(dateStr);
	}

	/**
	 * Temporary code
	 *
	 * @param list
	 * @return
	 */
	public static boolean containsOnlyZeros(List<Float> list)
	{
		for (Float item : list)
		{
			if (item != new Float(0)) {
				return false;
			}
		}
		return list.isEmpty() ? false : true;
	}

	/**
	 * Checks whether given string is null or blank
	 *
	 * @param str String to be checked for null or blank
	 *
	 * @return Returns true if the give string is null or blank
	 *
	 **/
	public static boolean isNullOrBlank(String str)
	{
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isNullOrBlankOrSpeltSo(String str)
	{
		return ((str == null) || (str.trim().length() == 0) || (str.indexOf("null") >= 0));
	}

	/**
	 * Checks whether given Object is null or blank
	 *
	 * @param obj object to be checked for null
	 *
	 * @return Returns true if the give object is null or blank
	 *
	 **/
	public static boolean isNotNull(Object obj)
	{
		return (obj != null);
	}

	/**
	 * Caps the first letter of the String
	 *
	 * @param s String to be fisrt capitalized
	 * @return
	 */
	public static String capFirstLetter(String s)
	{
		String first, rest;
		first = s.substring(0, 1);
		rest = s.substring(1);
		first = first.toUpperCase();
		rest = rest.toLowerCase();
		s = first + rest;
		return s;
	}

	/**
	 * Gets the default date format String ("MM/dd/YYYY")
	 */
	public static String getDefaultFormatStr()
	{
		return dFDefaultStr;
	}

	/**
	 * Gets the Formated Time stamp in given format (Default MM/dd/yyyy)
	 *
	 * @param d
	 * @param dFStr
	 * @return
	 */
	public static String getFormatedDate(Date d, String dFStr)
	{
		if (d == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(isNullOrBlank(dFStr) ? dFDefaultStr : dFStr);
		return sdf.format(d);
	}

	/**
	 * Gets the Formated Time stamp in Input format (Default yyyy-MM-dd hh:mm:ss)
	 *
	 * @param d
	 * @param dFStr
	 * @return
	 */
	public static String getFormatedInputDate(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFyyyyMMddhhmmss.format(d);
	}

	/**
	 * Gets the Formated Time stamp in k:mm format
	 *
	 * @param d
	 * @return
	 */
	public static String getkmmFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFkmm.format(d);
	}

	/**
	 * Gets the date and time in MM/dd/yyyy HH:mm:ss format
	 *
	 * @param d date
	 * */
	public static String getdFddMMyyyy(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFddMMyyyy.format(d);
	}

	/**
	 * Gets the Formated Time stamp in MMM YYYY format
	 *
	 * @param d
	 * @return
	 */
	public static String getDefaultFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFDefault.format(d);
	}

	/**
	 *
	 */
	public static String getEEEdMMMyyyyhhmmaFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFEEEdMMMyyyyhhmma.format(d);
	}

	/**
	 * Gets the Formated Time stamp in format H:mm a 24 hours time
	 *
	 * @param d
	 * @return
	 */
	public static String getHmmaFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFHmma.format(d);
	}

	/**
	 * Gets the Formated Time stamp in MMM YYYY format
	 *
	 * @param d
	 * @return
	 */
	public static String getMMMyyyyFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFMMMyyyy.format(d);
	}

	/**
	 * Gets the Formated Time stamp in MMM d format
	 *
	 * @param d
	 * @return
	 */
	public static String getMMMdFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFMMMd.format(d);
	}

	/**
	 * Gets the Formated Time stamp in MMM d format
	 *
	 * @param d
	 * @return
	 */
	public static String getyyyyMMddFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFyyyyMMdd.format(d);
	}

	/**
	 * Gets the Formated Time stamp in dd-MM-YYYY H:mm format
	 *
	 * @param d
	 * @return
	 */
	public static String getdFddMMyyyyhhmmss(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFddMMyyyyhhmmss.format(d);
	}

	/**
	 * Gets the Formated Time stamp in dd-MM-YYYY HH:mm format
	 *
	 * @param d
	 * @return
	 */
	public static String getdFddMMyyyyHHmmss(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFddMMyyyyHHmmss.format(d);
	}

	/**
	 * Gets the Formated Time stamp in MMM W format
	 *
	 * @param d
	 * @return
	 */
	public static String getMMMWFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFMMMW.format(d);
	}

	/**
	 * Gets the date and time in MM/dd/yyyy HH:mm:ss format
	 *
	 * @param d date
	 * */
	public static String getdFMMddyyyyhhmmss(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFMMddyyyyhhmmss.format(d);
	}

	/**
	 * Gets the date and time in MM/dd/yyyy HH:mm:ss format
	 *
	 * @param d date
	 * */
	public static String getDFDDmmyyyyhhmmss(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFDDmmyyyyhhmmss.format(d);
	}

	/**
	 * Gets the Formated Time stamp in EEE, d MMM yyyy format
	 *
	 * @param d
	 * @return
	 */
	public static String getEEEdMMMyyyyFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFEEEdMMMyyyy.format(d);
	}

	/**
	 * Gets the Formated Time stamp in format EEE, MM/d
	 *
	 * @param d
	 * @return
	 */
	public static String getEEEMMdFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFEEEMMd.format(d);
	}

	/**
	 * Gets the Formated Time stamp in format hh:mm a
	 *
	 * @param d
	 * @return
	 */
	public static String gethhmmaFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFhhmma.format(d);
	}

	/**
	 * Gets the Formated Time stamp in format yyyy-MM-dd hh:mm:ss
	 *
	 * @param d
	 * @return
	 */
	public static String getyyyyMMddhhmmssFormat(Date d)
	{
		if (d == null) {
			return null;
		}
		return dFyyyyMMddhhmmss.format(d);
	}

	public static String formatDate(Date date, SimpleDateFormat format)
	{
		String str = "";

		if (date != null && format != null)
		{
			str = format.format(date);
		}

		return str;
	}

	/**
	 * Gets the Formated Time stamp in format yyyy-MM-dd hh:mm:ss
	 *
	 * @param d
	 * @return
	 */
	public static String getyyyyMMddhhmmssFormat(String d)
	{
		if (d == null) {
			return null;
		}
		return dFyyyyMMddhhmmss.format(Timestamp.valueOf(d));
	}

	/**
	 * This method formats the date with the given format
	 *
	 * @param string format
	 * @param object date
	 * @return string formatted date
	 * */
	public static String doFormatDate(String format, Date obDate)
	{
		String strFormat = "";
		// Instantiating the object of date format
		if (!Util.isNullOrBlank(format))
		{
			SimpleDateFormat obSDF = new SimpleDateFormat(format);

			// if date object is not null
			if (Util.isNotNull(obDate))
			{
				// format the date with the given format
				strFormat = obSDF.format(obDate);
			}

		}
		// return the formatted date.
		return strFormat;
	}

	/**
	 * For a given Month, date, year format seperated by /, returns the date without the time.
	 */
	public static Date getDate(String date)
	{
		if (date == null) {
			return null;
		}

		String[] splits = date.split("/");
		Calendar cal = Calendar.getInstance();
		Date retDate = null;
		try
		{
			if (splits.length >= 3)
			{
				cal.set(Integer.parseInt(splits[2]), Integer.parseInt(splits[0]) - 1, Integer.parseInt(splits[1]));
			}
			retDate = cal.getTime();
		}
		catch (NumberFormatException ne)
		{
		}
		return retDate;
	}

	public static Calendar getCalendar(String date, int hour, int minutes)
	{
		String[] splits = date.split("/");
		Calendar cal = Calendar.getInstance();

		if (splits.length == 3)
		{

			cal.set(Integer.parseInt(splits[2]), Integer.parseInt(splits[0]) - 1, Integer.parseInt(splits[1]), hour, minutes);
		}
		return cal;
	}

	/**
	 * This method compares the two date. Condition: If Date 1 is less than Date 2 then, 1 will be returned else if Date 2 is less
	 * than Date 1 then, -1 will be returned else both are equal, 0 will be returned
	 *
	 * @param Date date1
	 * @param Date date2
	 * @return int result
	 * */
	public static int compareDate(Date date1, Date date2)
	{
		int result;

		return result = date1.compareTo(date2);
	}

	/*
	 * ************************************************************* Number format methods
	 *
	 * @author Venkataramanan K
	 *
	 * @time 12-09-07 ************************************************************
	 */

	/**
	 * It formats the number in double precision format
	 *
	 * @param double number
	 * @return string formatted number
	 * */
	public static String numDoublePrecision(double number)
	{

		// Instantiate an object of number format
		NumberFormat obFormat = new DecimalFormat(doublePrecision);

		// returns the number in a specified format
		return obFormat.format(number);
	}

	/**
	 * It formats the number in double precision format
	 *
	 * @param double number
	 * @return string formatted number
	 * */
	public static String largeDoublePrecision(double number)
	{

		// Instantiate an object of number format
		NumberFormat obFormat = new DecimalFormat(largeDoublePrecision);

		// returns the number in a specified format
		return obFormat.format(number);
	}

	/**
	 * It generates the unique number from the date object
	 *
	 * @return long unique number
	 * */
	public static String getUniqueNumber()
	{
		Date obCur = new Date();

		return uniqueNumber.format(obCur);
	}

	/**
	 * @param date in the format of MM/dd/yyyy
	 * @return boolean
	 */
	public static boolean isValidDate(String date)
	{
		try
		{
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			sdf.parse(date);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	public static boolean isNullOrZero(Long id)
	{
		Long zero = 0L;
		if (id == null || zero.equals(id))
		{
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Double id)
	{
		Double zero = 0D;
		if (id == null || zero.equals(id))
		{
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Float id)
	{
		Float zero = 0F;
		if (id == null || zero.equals(id))
		{
			return true;
		}
		return false;
	}

	public static boolean isNullOrZeroOrNegative(Long id)
	{
		return isNullOrZero(id) || id.intValue() < 0;
	}

	public static boolean isNullOrZero(Integer id)
	{
		Integer zero = 0;
		if (id == null || zero.equals(id))
		{
			return true;
		}
		return false;
	}

	public static String chkString(String str)
	{
		if (str == null)
		{
			return "";
		}
		return str.trim();
	}

	public static boolean isDatesValid(String fromDate, String toDate) throws Exception
	{
		return getDateInStandardFormat(fromDate).before(getDateInStandardFormat(toDate));
	}

	public static boolean isDateValid(String fromDate, String toDate) throws Exception
	{
		return getDateStandardFormat(fromDate).before(getDateStandardFormat(toDate));
	}

	public static boolean isValidDates(String fromDate, String toDate) throws Exception
	{
		return getDateInStandard(fromDate).before(getDateInStandard(toDate));
	}

	public static Date getDateInStandardFormat(String str) throws Exception
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
	}

	public static Date getDateStandardFormat(String str) throws Exception
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	public static Date getDateInStandard(String str) throws Exception
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
	}

	public static boolean isDateEqual(String fromDate, String toDate) throws Exception
	{
		return getYYMMDDFormat(fromDate).equals(getYYMMDDFormat(toDate));
	}

	public static Date getYYMMDDFormat(String str) throws Exception
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(str);
	}

	public static boolean isNumeric(String obj)
	{
		return obj.matches("[0-9]*");
	}

	public static boolean isAlphaNumeric(String obj)
	{
		return obj.matches("[0-9A-Za-z]*");
	}

	public static Long getLongValue(String id)
	{
		if (isNullOrBlank(id))
		{
			return null;
		}

		return Long.valueOf(id);
	}

	public static Integer getIntValue(String id)
	{
		if (isNullOrBlank(id)) {
			return null;
		}

		return new Integer(id);
	}

	public static String getStringValue(Long id)
	{
		if (isNullOrZero(id))
		{
			return null;
		}

		return Long.toString(id);
	}

	public static boolean isEmptyList(List list)
	{
		return ((list == null) || list.isEmpty());
	}

	public static boolean isEmptyCollection(Collection c)
	{
		return ((c == null) || c.isEmpty());
	}

	public static boolean isEmptyArray(Object[] arr)
	{
		return (arr == null || arr.length == 0);
	}

	public static String trimString(String str)
	{
		if (str == null)
		{
			return "";
		}

		return str.trim();
	}

	/**
	 * Generates a Random Password for the given length.
	 *
	 * @param n
	 * @return
	 */
	public static String getPassword(int n)
	{
		char[] pw = new char[n];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < n; i++)
		{
			r1 = (int) (Math.random() * 3);
			switch (r1)
			{
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}

	public static void sort(List list)
	{
		if (list != null) {
			Collections.sort(list);
		}
	}

	/**
	 * Returns a pseudorandom number minimum of five digits
	 *
	 * @return
	 */
	public static int generateRandomNumber()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(2147483647);

		if (randomNumber < 99999)
		{
			randomNumber += 11111;
		}

		return randomNumber;
	}

	public static int generateRandomNumber4digit()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(884711);
		return randomNumber;
	}

	public static String escapeSpecialChars(String str)
	{
		if (!isNullOrBlank(str))
		{
			str = str.replace("'", "\\'");
		}

		return str;
	}

	public static boolean isModified(String oldVal, String newVal)
	{
		boolean retVal;

		if (oldVal == null) {
			oldVal = "";
		}
		if (newVal == null) {
			newVal = "";
		}

		retVal = !oldVal.equals(newVal);

		return retVal;
	}

	public static boolean isModified(Object oldVal, Object newVal)
	{
		boolean retVal;
		if (oldVal == null)
		{
			if (newVal != null) {
				retVal = true;
			} else {
				retVal = false;
			}
		}
		else
		{
			retVal = !oldVal.equals(newVal);
		}

		return retVal;
	}

	public static boolean isDate1ExistOverDate2(Date date1Start, Date date1End, Date date2Start, Date date2End)
	{
		return date2Start.after(date1Start) && date2End.before(date1End);
	}

	/**
	 * Returns true if email valid else return false
	 *
	 * @return
	 */

	public static boolean isEmailAddressValid(String emailAddress)
	{
		if (emailAddress != null)
		{
			if (!((emailAddress.contains("..")) || (emailAddress.contains("__")) || (emailAddress.contains("._"))
					|| (emailAddress.contains("_.")) || (emailAddress.contains(".@")) || (emailAddress.contains("@.")) || (emailAddress
							.contains("_@"))))
			{
				if ((emailAddress.length() > 4) && (emailAddress.length() <= 128))
				{
					Pattern email_pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._-]*[@][a-zA-Z0-9.]+[.][a-zA-Z]+[a-zA-Z]");
					Matcher email_matcher = email_pattern.matcher(emailAddress);
					boolean email_matches = email_matcher.matches();
					// System.out.println("Email Validation>>>>>>>>>>>>>>>>>>> "+email_matches);
					return email_matches;
				}
				else
				{
					return false;
				}
			} else {
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public static boolean isValidNickName(String nickName)
	{
		if (nickName != null)
		{
			if ((nickName.length() > 4) && (nickName.length() <= 10))
			{
				Pattern nickName_pattern = Pattern.compile("[a-zA-Z0-9_-]+");
				Matcher nickName_matcher = nickName_pattern.matcher(nickName);
				boolean nickName_matches = nickName_matcher.matches();

				return nickName_matches;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public static boolean isValidPassword(String password)
	{
		if (password != null)
		{
			if ((password.length() > 5) && (password.length() <= 8))
			{
				Pattern password_pattern = Pattern.compile("[a-z][a-z0-9]+");
				Matcher password_matcher = password_pattern.matcher(password);
				boolean password_matches = password_matcher.matches();

				if (password.equals("password"))
				{

					return false;
				}
				return password_matches;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public static String getRandomId(String nickName)
	{

		char[] chars = new char[nickName.length() + 36];
		int i = 0;
		for (i = 0; i < nickName.length(); i++)
		{
			chars[i] = nickName.charAt(i);
		}
		i = nickName.length();

		for (char c = '0'; c <= '9'; c++)
		{
			chars[i++] = c;
		}

		for (char c = 'a'; c <= 'z'; c++)
		{
			chars[i++] = c;
		}

		StringBuffer code = new StringBuffer("");
		for (i = 0; i < 16; i++)
		{
			char c = chars[(int) (Math.random() * chars.length)];
			code = code.append(c);
		}

		for (int nInnerLoop = 0x00; nInnerLoop < code.length(); nInnerLoop++)
		{
			int rand = (int) (Math.random() * chars.length);
			if (code.charAt(nInnerLoop) == '_' || code.charAt(nInnerLoop) == '-' || (Character.isLetter(code.charAt(nInnerLoop)) && nInnerLoop == rand))
			{
				code.replace(nInnerLoop, nInnerLoop + 1, String.valueOf((nInnerLoop + code.charAt(nInnerLoop)) % 0x0a));
			}
		}

		return code.toString();
	}

	public static String getCAPTCHAId()
	{

		char[] chars = new char[36];
		int i = 0;
		for (char c = '0'; c <= '9'; c++)
		{
			chars[i++] = c;
		}

		for (char c = 'a'; c <= 'z'; c++)
		{
			chars[i++] = c;
		}

		StringBuffer code = new StringBuffer("");
		for (i = 0; i < 4; i++)
		{
			char c = chars[(int) (Math.random() * chars.length)];
			code = code.append(c);
		}
		return code.toString();
	}

	public static String forHTMLTag(String aTagFragment)
	{
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != CharacterIterator.DONE)
		{
			if (character == '<')
			{
				result.append("&lt;");
			}
			else if (character == '>')
			{
				result.append("&gt;");
			}
			else if (character == '\"')
			{
				result.append("&quot;");
			}
			else if (character == '\'')
			{
				result.append("&#039;");
			}
			else if (character == '\\')
			{
				result.append("&#092;");
			}
			else if (character == '&')
			{
				result.append("&amp;");
			}
			else
			{
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static String encryptString(String password)
	{

		/*
		 * Encryption Process starts
		 */
		String alphabets = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`~!@#$%^&*()-_+=/,.<>?\\;:\"'1234567890{}[]|";
		// System.out.println ( alphabets.length());
		String string;
		String remainingString;
		String key = "abcdef";
		char[] chars = new char[key.length()];
		char[] remainingChars = new char[password.length() - key.length()];

		// System.out.println ("String passed " + password );

		for (int i = 0; i < key.length(); i++)
		{
			chars[i] = password.charAt(i);
		}

		for (int i = key.length(); i < password.length(); i++)
		{
			remainingChars[i - key.length()] = password.charAt(i);
		}

		string = new String(chars);
		remainingString = new String(remainingChars);
		Integer[] intKey = new Integer[key.length()];

		for (int i = 0; i < key.length(); i++)
		{
			intKey[i] = alphabets.indexOf(key.charAt(i));
		}

		Integer[] intString = new Integer[string.length()];
		for (int i = 0; i < string.length(); i++)
		{
			intString[i] = alphabets.indexOf(string.charAt(i));
		}

		/*
		 * Function for encryption
		 */

		Integer[] encryptedString = new Integer[key.length()];
		char[] encryptString = new char[key.length()];
		for (int i = 0; i < key.length(); i++)
		{
			int temp_int = (intKey[i] + intString[i]) % alphabets.length();
			encryptedString[i] = temp_int;

			encryptString[i] = alphabets.charAt(temp_int);
		}

		String encryptedPassword = new String(encryptString);

		encryptedPassword = encryptedPassword.concat(remainingString);
		// System.out.println ( "Encrypted Password " + encryptedPassword );

		return encryptedPassword;
	}

	public static String decryptPassword(String password)
	{
		/*
		 * Function for decryption
		 */
		// System.out.println ( "Inside Decryption "+password);
		String alphabets = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`~!@#$%^&*()-_+=/,.<>?\\;:\"'1234567890{}[]|";

		String string;
		String remainingString;
		String key = "abcdef";
		char[] chars = new char[key.length()];
		char[] remainingChars = new char[password.length() - key.length()];

		// System.out.println ("String passed for decryption " + password );
		// System.out.println ("Key Passed " + key);

		for (int i = 0; i < key.length(); i++)
		{
			chars[i] = password.charAt(i);
		}
		// System.out.println ( "Value of chars[i]  " + new String(chars));

		for (int i = key.length(); i < password.length(); i++)
		{
			remainingChars[i - key.length()] = password.charAt(i);
		}

		// System.out.println ( "Value of remaining chars[i]  " + new String(remainingChars));

		string = new String(chars);
		remainingString = new String(remainingChars);
		Integer[] intKey = new Integer[key.length()];

		for (int i = 0; i < key.length(); i++)
		{
			intKey[i] = alphabets.indexOf(key.charAt(i));
		}

		Integer[] intString = new Integer[string.length()];
		for (int i = 0; i < string.length(); i++)
		{
			intString[i] = alphabets.indexOf(string.charAt(i));
		}

		// System.out.println ( "The String " + string );

		// System.out.println ( "Decrypted String " );
		char[] decryptedString = new char[key.length()];
		for (int i = 0; i < key.length(); i++)
		{
			if (intString[i] > intKey[i])
			{
				int temp_int = Math.abs(intString[i] - intKey[i]) % alphabets.length();
				decryptedString[i] = alphabets.charAt(temp_int);
			}
			else
			{
				int temp_int = ((intString[i] + alphabets.length()) - intKey[i]) % alphabets.length();
				decryptedString[i] = alphabets.charAt(temp_int);
			}
		}
		String decrypt = new String(decryptedString);
		decrypt = decrypt.concat(remainingString);
		// System.out.println ( decrypt);
		/*
		 * Decryption done
		 */
		return decrypt;
	}

	/**
	 * This method takes inputstream and writes that to output steam
	 *
	 * @param in - inputstream
	 * @param out - outputstream
	 */
	public static final synchronized void copyStream(InputStream in, OutputStream out)
	{
		try
		{
			byte buffer[] = new byte[1024];
			do
			{
				int read = in.read(buffer);
				if (read == -1) {
					break;
				}
				out.write(buffer, 0, read);
				out.flush();
			}
			while (true);
		}
		catch (Exception e)
		{
			logger.error("Exception encountered while copying the stream", e);
		}
		finally
		{
			try
			{
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
			catch (IOException e)
			{
				logger.error("Exception encountered while closing the stream", e);
			}
		}
	}

	/**
	 * This static method used for getting the sql connection object which is binded with hibernate session factory. This connection
	 * is used for generating reports
	 *
	 * @return connection if exists otherwise null
	 */
	/*public static final synchronized Connection getConnection()
	{
		try
		{
			return ((SessionFactoryImpl) SpringUtil.WEB_APP_CONTEXT.getBean("sessionFactory")).getConnectionProvider().getConnection();
		}
		catch (Exception e)
		{
			return null;
		}
	}*/



	public static String getDesiredFormat(String inputString, int requiredLength)
	{
		String finalString = "";
		int lenInputString = inputString.length();

		if (lenInputString == requiredLength)
		{
			return inputString;
		}

		int diffInLengths = requiredLength - lenInputString;

		for (int ittr = 0; ittr < diffInLengths; ittr++)
		{
			finalString += "0";
		}
		finalString = finalString + inputString;

		return finalString;
	}

	// /**
	// * This method is used for check that, input string has contain special character (other then 0-9, a-z and A-Z).
	// *
	// * @param inputString - {@link String}, a string, which is need to be validate.
	// * @return - {@link Boolean}, If input string has no special character then return false else return true.
	// */
	// public static boolean isContainSpecialCharacter(String inputString)
	// {
	// Pattern pattern = Pattern.compile("^[0-9a-zA-Z-,+() ./]*$");
	// Matcher matcher = pattern.matcher(inputString);
	// if (matcher.matches())
	// {
	// return false;
	// }
	// return true;
	// }
	//
	// /**
	// * This method is checked that input string has only number or not.
	// *
	// * @param inputString - {@link String}, a string, which is need to be validate.
	// * @return - {@link Boolean}, If input string has only number then return true else return false.
	// */
	// public static boolean isStringHasNumberOnly(String inputString)
	// {
	// Pattern pattern = Pattern.compile("^[0-9]*$");
	// Matcher matcher = pattern.matcher(inputString);
	// if (matcher.matches())
	// {
	// return true;
	// }
	// return false;
	// }

	public  String cssClass(float percent)
	{
		String cssClass="";
		if(percent<1)
		{
			cssClass="class=pwhite";
		}else if(percent<26&&percent>1)
		{
			cssClass="class=pred";
		}
		else if(percent<56&&percent>25)
		{
			cssClass="class=porange";
		}
		else if(percent<76&&percent>55)
		{
			cssClass="class=pyellow";
		}
		else if(percent<100&&percent>75)
		{
			cssClass="class=pgreen1";
		}
		else if(percent==100)
		{
			cssClass="class=pgreen2";
		}
		else if(percent>100)
		{
			cssClass="class=pgreen2";
		}
		//System.out.println(cssClass);
		return cssClass;
	}

	public static float Round(float value) {

		float p = (float)Math.pow(10,2);
		value = value * p;
		float tmp = Math.round(value);
		float percent;
		if(tmp/p>100)
		{
			percent=100;
		}else
		{
			percent=tmp/p;
		}
		return percent;
	}

	public static void sendEmail(String emailAddress, String subject, String body) {

		if(emailAddress == null || emailAddress.equals("")) {

			logger.info("Email cannot be sent, as email id is blank.");
			return;
		}

		try {

			logger.info("Setting up email configuration");

			//InputStream emailPropertiesIS = Util.class.getResourceAsStream("/email.properties");

			Properties emailProperties = new Properties();

			//emailProperties.load(emailPropertiesIS);

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "helpdesk-aishe@nic.in");

			Session session = Session.getDefaultInstance(emailProperties);

			//session.setDebug(false);

			Message emailMessage = new MimeMessage(session);

			InternetAddress addressFrom = new InternetAddress(emailProperties.getProperty("mail.smtp.user"));
			emailMessage.setFrom(addressFrom);
			logger.info("To Recepient"+emailAddress);
			InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[1];
			addressTo[0] = new InternetAddress(emailAddress);
			emailMessage.setRecipients(Message.RecipientType.TO, addressTo);

			InternetAddress[] addressBCC = new javax.mail.internet.InternetAddress[2];
			addressBCC[0] = new InternetAddress("prakash.bhanu@nic.in");
			if(subject.contains("Uploaded"))
			{
				addressBCC[1] = new InternetAddress("anshul@nic.in");
			}
			else
			{
				addressBCC[1] = new InternetAddress("prakash.bhanu@nic.in");
			}


			emailMessage.setRecipients(Message.RecipientType.BCC, addressBCC);

			emailMessage.setSubject(subject);

			emailMessage.setContent(body, "text/html");

			emailMessage.saveChanges();

			String toAddressString = "";

			for(InternetAddress toAddress : addressTo) {

				toAddressString = toAddressString + ", " + toAddress.getAddress();
			}

			logger.info("Sending emails to " + toAddressString + " with subject as " + subject);

			Transport.send(emailMessage);

			logger.info("Emails sent successfully.");

			//transport.close();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static boolean sendEmailToUser(String emailId, String subject, String body, String emailFrom, File file, String FileName,String txtCCEmail) {

		if(emailId == null || emailId.equals("")) {

			logger.info("In sendEmailToUser(). Email cannot be sent, as email id is blank.");
			return false;
		}

		try {

			Properties emailProperties = new Properties();
			logger.info("In sendEmailToUser(), setting up email configuration");

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "helpdesk-aishe@nic.in");


			List<InternetAddress> addressCCList = new ArrayList<InternetAddress>();
			List<InternetAddress> addressBCCList = new ArrayList<InternetAddress>();

			//String [] countries = list.toArray(new String[list.size()]);

			InternetAddress addressFrom = new InternetAddress(emailFrom);

			InternetAddress[] addressCC = null ;
			InternetAddress[] addressBCC = null ;

			addressBCCList.add(new InternetAddress(emailId));

			addressCCList.add(new InternetAddress(emailFrom));


			if(null!=txtCCEmail && txtCCEmail.length()>0)
			{

				System.out.println("adding addressCC :: in CC ");
				addressCCList.add(new InternetAddress(txtCCEmail));

			}

			String emailddressString = "";

			Session session = Session.getDefaultInstance(emailProperties);

			addressBCC = addressBCCList.toArray(new InternetAddress[addressBCCList.size()]);
			addressCC  = addressCCList.toArray(new InternetAddress[addressCCList.size()]);

			for(InternetAddress emailAddress : addressBCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}

			for(InternetAddress emailAddress : addressCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}


			logger.info("Sending emails to " + emailddressString + " with subject as " + subject);

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(addressFrom);

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, addressBCC);

			System.out.println("addressBCC ::"+addressBCC.toString());

			if(null!=addressCC && addressCC.length > 0)
			{
				System.out.println("sending addressCC :: in CC ");
				message.addRecipients( Message.RecipientType.CC, addressCC);
			}

			System.out.println("addressCC ::"+addressCC.toString());

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			if(null!=file && file.exists())
			{
				System.out.println("file name"+file.getName());
				DataSource source = new FileDataSource(file);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(FileName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart );

			// Send message
			Transport.send(message);
			logger.info("Emails sent successfully.");
		}
		catch (Exception e) {

			logger.info("There is some problem in sending email, Please contact System Administartor");
			logger.debug("There is some problem in sending email, Please contact System Administartor");

			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * SSLSocketFactory which trusts all the server certificates.
	 * @see SSLNOTES.txt of javamail.zip (Mail API from Sun)
	 * http://java.sun.com/products/javamail/
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static SSLSocketFactory getTrustedSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {

		// Initializing ssl connection object
		SSLContext sslCont = SSLContext.getInstance("TLS");

		sslCont.init(null, getAllTrustManager(), null);

		return sslCont.getSocketFactory();
	}

	private static TrustManager[] getAllTrustManager() {

		return new TrustManager[] { new X509TrustManager() {

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
				//Auto-generated method stub
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
				//Auto-generated method stub
			}

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				//Auto-generated method stub
				return null;
			}
		}};
	}

	public static String getTimeStamp() {

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSa");
		String timeStamp = sdf.format(new Date());
		return timeStamp;
	}

	public static String sendEmailToUser(List<String> UserMasterEmailList, String subject, String body, String emailFrom, File file, String FileName,String txtCCEmail) {

		if(UserMasterEmailList == null || UserMasterEmailList.equals("")) {

			logger.info("In sendEmailToUser(). Email cannot be sent, as email id is blank.");
			return "0";
		}

		String response ="2";

		try {

			Properties emailProperties = new Properties();
			logger.info("In sendEmailToUser(), setting up email configuration");

			emailProperties.put("mail.smtp.host", "relay.nic.in");
			emailProperties.put("mail.smtp.auth", "false");
			emailProperties.put("mail.smtp.port", "25");
			emailProperties.put("mail.smtp.user", "helpdesk-aishe@nic.in");


			List<InternetAddress> addressCCList = new ArrayList<InternetAddress>();
			List<InternetAddress> addressBCCList = new ArrayList<InternetAddress>();



			//			String [] countries = list.toArray(new String[list.size()]);

			InternetAddress addressFrom = new InternetAddress(emailFrom);

			InternetAddress[] addressCC = null ;
			InternetAddress[] addressBCC = null ;

			for(String emails : UserMasterEmailList)
			{
				addressBCCList.add(new InternetAddress(emails));
			}


			addressCCList.add(new InternetAddress(emailFrom));


			if(null!=txtCCEmail && txtCCEmail.length()>0)
			{

				System.out.println("adding addressCC :: in CC ");
				addressCCList.add(new InternetAddress(txtCCEmail));

			}

			String emailddressString = "";





			Session session = Session.getDefaultInstance(emailProperties);


			addressBCC = addressBCCList.toArray(new InternetAddress[addressBCCList.size()]);
			addressCC  = addressCCList.toArray(new InternetAddress[addressCCList.size()]);

			for(InternetAddress emailAddress : addressBCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}

			for(InternetAddress emailAddress : addressCC) {

				emailddressString = emailddressString + ", " + emailAddress.getAddress();
			}


			logger.info("Sending emails to " + emailddressString + " with subject as " + subject);
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(addressFrom);

			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.BCC, addressBCC);

			System.out.println("addressBCC ::"+addressBCC.toString());

			if(null!=addressCC && addressCC.length > 0)
			{
				System.out.println("sending addressCC :: in CC ");
				message.addRecipients( Message.RecipientType.CC, addressCC);
			}

			System.out.println("addressCC ::"+addressCC.toString());

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);



			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			//   String filename = "/tmp/test.txt";
			if(null!=file && file.exists())
			{
				System.out.println("file name"+file.getName());
				DataSource source = new FileDataSource(file);

				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(FileName);
				multipart.addBodyPart(messageBodyPart);
			}

			// Send the complete message parts
			message.setContent(multipart );

			// Send message
			//	Transport.send(message);
			logger.info("Emails sent successfully.");
		}
		catch (Exception e) {

			logger.info("There is some problem in sending email, Please contact System Administartor");
			logger.debug("There is some problem in sending email, Please contact System Administartor");

			e.printStackTrace();
			response ="1";
		}

		return response;
	}

	public  static String getURL()
	{
		InputStream emailPropertiesIS = Util.class.getResourceAsStream("/email.properties");
		Properties configProp = new Properties();

		try {
			configProp.load(emailPropertiesIS);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(configProp.getProperty("email.url"));

		return configProp.getProperty("email.url");
	}


	public  static String getFolderPath()
	{
		InputStream dataSharingPropertiesIS = Util.class.getResourceAsStream("/dataSharing.properties");
		Properties configProp = new Properties();

		try {
			configProp.load(dataSharingPropertiesIS);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return configProp.getProperty("dataSharingFilePath");
	}

	public  static String getDCFDataFolderPath()
	{
		InputStream dataSharingPropertiesIS = Util.class.getResourceAsStream("/dataSharing.properties");
		Properties configProp = new Properties();

		try {
			configProp.load(dataSharingPropertiesIS);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(configProp.getProperty("dcfDataSharingFilePath"));

		return configProp.getProperty("dcfDataSharingFilePath");
	}


	public static List<String> getBytesToArray(byte[] convertObject){

		List<String> object = null;

		ByteArrayInputStream bais;

		ObjectInputStream ins;

		try {

			bais = new ByteArrayInputStream(convertObject);

			ins = new ObjectInputStream(bais);

			object =(List<String>)ins.readObject();


			ins.close();


		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return object;
	}


	public static byte[]  getArrayToBytes(List<String> object) throws IOException{

		byte[] bytes ;

		ByteArrayOutputStream out = new ByteArrayOutputStream() ;
		ObjectOutputStream objOut = new ObjectOutputStream(out) ;
		objOut.writeObject(object) ;
		objOut.flush() ;
		bytes = out.toByteArray() ;
		objOut.close() ;

		return bytes;

	}

	public static Date getMinimumSupportedDate() {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, 4713); //PostgreSQL Minimum Date
		cal.set(Calendar.ERA, GregorianCalendar.BC);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getMaximumSupportedDate() {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, 294276); //PostgreSQL Maximum Date
		cal.set(Calendar.ERA, GregorianCalendar.AD);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/*
	 * Get a new date object with same date and time set to minimum. This is useful for comparing the date with time stamp
	 */
	public static Date getMimimumTimeDate(Date date) {

		return DateUtils.truncate(date, Calendar.DATE);
	}

	/*
	 * Get a new date object with same date and time set to maximum. This is useful for comparing the date with time stamp
	 */
	public static Date getMaximumTimeDate(Date date) {

		Date lastMidnight = DateUtils.truncate(date, Calendar.DATE);
		return DateUtils.addMilliseconds(DateUtils.addDays(lastMidnight, 1), -1);
	}
}