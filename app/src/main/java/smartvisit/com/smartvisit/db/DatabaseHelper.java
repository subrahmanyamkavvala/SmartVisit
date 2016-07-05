package smartvisit.com.smartvisit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;



import java.sql.SQLException;

import smartvisit.com.smartvisit.R;

/**
 * Created by subramanyam on 04-12-2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{




    final static String TAG = DatabaseHelper.class.getName();

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "ccstorage.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<CompanyInfo, Integer> runtimeCompDao = null;

    private Dao<CompanyInfo, Integer> companyDao;
    private Dao<VisitorsCheckIn, Integer> visitorsDao;



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormdb_config);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {


        try {
            Log.i(TAG, "onCreate");
            sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            TableUtils.createTable(connectionSource, CompanyInfo.class);
            TableUtils.createTableIfNotExists(connectionSource, VisitorsCheckIn.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Components class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
   /* public RuntimeExceptionDao<CompanyInfo, Integer> getCompanyDao() {

        if (runtimeCompDao == null) {
            runtimeCompDao = getRuntimeExceptionDao(CompanyInfo.class);
        }
        return runtimeCompDao;
    }*/



    public Dao<VisitorsCheckIn, Integer> getVisitorsDao() throws SQLException {
        if (visitorsDao == null) {
            visitorsDao = getDao(VisitorsCheckIn.class);
        }
        return visitorsDao;
    }

    public Dao<CompanyInfo, Integer> getCompanyDao() throws SQLException {
        if (companyDao == null) {
            companyDao = getDao(CompanyInfo.class);
        }
        return companyDao;
    }


}
