package activities.constant_details;

import core.api.Constants;

public class CompareJsonConstants extends Constants {
    public static final String headerDetails = contentTypeHeader;
    public static final String path = "api/users/{userId}";
    public static final String pathParam = "userId:%s";

    public static final String getPathParameter(String id) {
        return getFormatter().format(pathParam, id).toString();
    }
}