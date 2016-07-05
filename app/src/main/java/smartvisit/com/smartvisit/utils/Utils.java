package smartvisit.com.smartvisit.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import smartvisit.com.smartvisit.AppController;
import smartvisit.com.smartvisit.db.CompanyInfo;
import smartvisit.com.smartvisit.db.VisitorsCheckIn;

public class Utils {

	public final static String FB_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss+SSSS";
	public final static String TW_DATEFORMAT = "EEE MMM dd HH:mm:ss ZZZZ yyyy";
	public final static int TW_DATETYPE = 45;
	public final static int FW_DATETYPE = 46;

	private static final String TAG = "Utils";
	String uniqueID = UUID.randomUUID().toString();//

	public static Date getLocalDateFormat(String dateString, int date_type) {

		Date _localdate = null;
	//	Log.d("Utils", "getLocalDateFormat:" + dateString);
		switch (date_type) {
		case TW_DATETYPE:
		//the Locale/lang of the constructor need to modify as per req
		//parse exception when use the getDefalult() because EEE MMM dd HH:mm:ss ZZZZ yyyy format also need to be change as per locale.
		//	SimpleDateFormat tw_sdf = new SimpleDateFormat(TW_DATEFORMAT,Locale.getDefault());
		
			SimpleDateFormat tw_sdf = new SimpleDateFormat(TW_DATEFORMAT,new Locale("en"));
			tw_sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

			try {		
				_localdate = tw_sdf.parse(dateString);
				Calendar tw_calendar = new GregorianCalendar(TimeZone.getDefault());
				tw_calendar.setTime(_localdate);
				_localdate = tw_calendar.getTime();

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case FW_DATETYPE:
			SimpleDateFormat fb_sdf = new SimpleDateFormat(FB_DATEFORMAT,new Locale("en"));
			fb_sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

			try {

				_localdate = fb_sdf.parse(dateString);
				//Log.d("Utils", "FB formated date :" + _localdate.toString());
				Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
				//Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
				calendar.setTime(_localdate);
				_localdate = calendar.getTime();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		default:
			break;
		}

		//Log.d("Utils", "localdate timezone :" + _localdate.toString());

		return _localdate;

	}

	public static String getSimpleDateFormat(Date from_date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy",new Locale("en"));
		String format = sdf.format(from_date);
		return format;
	}
	public static String getSimpleTimeFormat(Date from_date) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh.mm.a",new Locale("en"));
		String format = sdf.format(from_date);
		return format;
	}

	public static long getUNIXtsForFB(Date gmtDate) {

		long unix_ts = 0;
		Log.e(TAG, " getUNIXtsForFB :gmtDate :" + gmtDate);
		unix_ts = (long) gmtDate.getTime() / 1000L;
		return unix_ts;

	}


	public static List<CompanyInfo> getCompantDataF(String email,String password) {


		 Log.d(TAG, "View teachers Details");
		List<CompanyInfo> listofcompany = null;

		try {


			Dao<CompanyInfo, Integer> accountsDao = AppController.getInstance().getHelper().getCompanyDao();

			// Get our query builder from the DAO
			final QueryBuilder<CompanyInfo, Integer> queryBuilder = accountsDao.queryBuilder();

			// We need only Students who are associated with the selected Teacher, so build the query by "Where" clause
			queryBuilder.where().eq(CompanyInfo.COM_FIELD_EMAIL, email).and().eq(CompanyInfo.COM_FIELD_PASSWORD, password);

			// Prepare our SQL statement
			final PreparedQuery<CompanyInfo> preparedQuery = queryBuilder.prepare();

			// Fetch the list from Database by querying it

		 listofcompany = accountsDao.query(preparedQuery);

			///accountsDao.queryForMatching()


			//accountsDao.queryForSameId()

		/*	// Log.d(TAG, "View teachers Details" + listofAccount);
			// final Iterator<Accounts> accountitem = accountsDao.query(preparedQuery).iterator();
			final Iterator<CompanyInfo> accountitem = listofAccount.iterator();


			// Iterate through the StudentDetails object iterator and populate the comma separated String
			while (accountitem.hasNext()) {


			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listofcompany;
	}
	public static List<VisitorsCheckIn> getVisitorsList() {


		 Log.d(TAG, "getVisitorsList");
		List<VisitorsCheckIn> list = null;

		try {


			Dao<VisitorsCheckIn, Integer> accountsDao = AppController.getInstance().getHelper().getVisitorsDao();

			// Get our query builder from the DAO
			final QueryBuilder<VisitorsCheckIn, Integer> queryBuilder = accountsDao.queryBuilder();

			// We need only Students who are associated with the selected Teacher, so build the query by "Where" clause
			//queryBuilder.where().eq(CompanyInfo.COM_FIELD_EMAIL, email).and().eq(CompanyInfo.COM_FIELD_PASSWORD, password);

			// Prepare our SQL statement
			queryBuilder.limit(20);
			final PreparedQuery<VisitorsCheckIn> preparedQuery = queryBuilder.prepare();

			// Fetch the list from Database by querying it


			 list = accountsDao.query(preparedQuery);


			///accountsDao.queryForMatching()
			//accountsDao.queryForSameId()


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


}
