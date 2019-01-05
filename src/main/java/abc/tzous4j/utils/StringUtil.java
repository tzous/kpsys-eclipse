package abc.tzous4j.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by zry on 15-9-21.
 */
public final class StringUtil {
    /**
     */
    public static boolean isEmpty(String str) {
        if(str != null) {
            str = str.trim();
        }
        return StringUtil.isEmpty(str);
    }

    /**
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     */
    public static String[] splitString(String source,String separatorChars) {
        return StringUtils.split(source,separatorChars);
    }
}

