package smartvisit.com.smartvisit.db;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by subbu on 11/3/16.
 */
public class VisitorsCheckIn {


    public static final String VIS_FIELD_ID = "_id";
    public static final String VIS_FIELD_TOMEET = "tomeet";
    public static final String VIS_FIELD_NAME = "v_name";
    public static final String VIS_FIELD_COMPANY = "v_company";
    public static final String VIS_FIELD_EMIAL = "vis_email";
    public static final String VIS_FIELD_MOBILE = "vis_mobile";
    public static final String VIS_FIELD_INDATE = "vis_indate";
    public static final String VIS_FIELD_OUTDATE = "vis_outdate";
    public static final String VIS_FIELE_imageurl = "vis_image_url";

    //company Type...

    public static final String COM_RESIDENTIAL = "Residential";
    public static final String COM_COMMERTIAL = "Commertial";




    /**
     *  Model class for teacher_details database table
     */
    private static final long serialVersionUID = -222864131214757024L;

    // Primary key defined as an auto generated integer
    // If the database table column name differs than the Model class variable name, the way to map to use columnName



    @DatabaseField(generatedId = true, columnName = VIS_FIELD_ID)
    public int vis_id;

    // Define a String type field to hold teacher's name
    @DatabaseField(columnName = VIS_FIELD_TOMEET)
    public String vis_tomeet;

    // Define a String type field to hold student's address
    @DatabaseField(columnName = VIS_FIELD_NAME)
    public String vis_fullname;

    @DatabaseField(columnName = VIS_FIELD_COMPANY)
    public String vis_company;

    @DatabaseField(columnName = VIS_FIELD_EMIAL)
    public String vis_email;


    @DatabaseField(columnName = VIS_FIELD_MOBILE)
    public String vis_mobile;

    @DatabaseField(columnName = VIS_FIELD_INDATE,dataType = DataType.DATE_STRING,format = "yyyy-MM-dd HH:mm:ss")
    public Date vis_indate;

    @DatabaseField(columnName = VIS_FIELD_OUTDATE,dataType = DataType.DATE_STRING,format = "yyyy-MM-dd HH:mm:ss")
    public Date vis_outdate;

    @DatabaseField(columnName = VIS_FIELE_imageurl)
    public String vis_url_image;




    // Default constructor is needed for the SQLite, so make sure you also have it
    public VisitorsCheckIn(){

    }


}
