package smartvisit.com.smartvisit.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface ICommonUtil {
    public final int DRAWABLE_LEFT = 0;
    public final int DRAWABLE_TOP = 1;
    public final int DRAWABLE_RIGHT = 2;
    public final int DRAWABLE_BOTTOM = 3;

    public final String MOBILE_NO_PREFIX = "";
    int COUNTRY_SELECTION = 101;
    public Context context = null;
    public final String DEBUG_TAG = "System Out";
    Pattern pattern =null;
    Matcher matcher =null;
    String WEB_VIEW_DATA_TYPE = "text/html";
    String WEB_VIEW_ENCODING = "utf-8";
    String WEB_VIEW_HTML_TEXT = "<html><body style=\"text-align:justify\" \"> %s </body></Html>";
    String FONT_TYPE_APP = "fontawesome-webfont.ttf";

}
