package smartvisit.com.smartvisit.db;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by subbu on 11/3/16.
 */
public class CompanyInfo implements Serializable {

    public static final String COM_FIELD_ID = "com_id";


    public static final String COM_FIELD_NAME = "com_fullname";

    public static final String COM_FIELD_COMPANY_NAME = "com_company_name";
    public static final String COM_FIELD_TYPE = "com_type";

    public static final String COM_FIELD_EMAIL = "com_email";
    public static final String COM_FIELD_MOBILE = "com_mobile";


    public static final String COM_FIELD_PASSWORD = "com_password";


    public static final String COM_FIELD_KEY = "com_key";
    public static final String COM_FIELD_STATUS = "com_status";
    public static final String COM_FIELD_JOINDATE = "com_joindate";


    public static final String COM_FIELD_PROFILE_IMG = "com_profile_image";
    public static final String COM_FIELD_LOGO_IMG = "com_logo_image";



    //company Type...

    public static final String COM_RESIDENTIAL = "Residential";
    public static final String COM_COMMERTIAL = "Commertial";




    /**
     *  Model class for teacher_details database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName



    @DatabaseField(generatedId = false, columnName = COM_FIELD_ID)
    public int com_id;

    // Define a String type field to hold teacher's name
    @DatabaseField(columnName = COM_FIELD_TYPE)
    public String com_type;


    @DatabaseField(columnName = COM_FIELD_NAME)
    public String com_name;


    @DatabaseField(columnName = COM_FIELD_COMPANY_NAME)
    public String com_company_name;

    @DatabaseField(columnName = COM_FIELD_EMAIL)
    public String com_email;

    @DatabaseField(columnName = COM_FIELD_MOBILE)
    public String com_mobile;

    @DatabaseField(columnName = COM_FIELD_PASSWORD)
    public String com_password;

    @DatabaseField(columnName = COM_FIELD_KEY)
    public String com_key;

    @DatabaseField(columnName = COM_FIELD_STATUS)
    public String com_status;

    @DatabaseField(columnName = COM_FIELD_JOINDATE, dataType = DataType.DATE_STRING,format = "yyyy-MM-dd HH:mm:ss")
    public Date com_joineddate;


    // Default constructor is needed for the SQLite, so make sure you also have it
    public CompanyInfo(){
    }

    //For our own purpose, so it's easier to create a TeacherDetails object
//    public CompanyInfo(final String name, final String address){
//        this.teacherName = name;
//        this.address = address;
//    }
}