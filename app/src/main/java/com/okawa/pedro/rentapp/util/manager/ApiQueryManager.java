package com.okawa.pedro.rentapp.util.manager;

import java.util.Map;

/**
 * Created by pokawa on 28/01/16.
 */
public class ApiQueryManager {

    private static final String BASE_PARAMETER_API_KEY = "\"api_key\":";
    private static final String BASE_PARAMETER_QUERY = "\"query\":";

    public static final String PARAMETER_PER_PAGE = "\"perpage\":";
    public static final String PARAMETER_PAGE = "\"page\":";

    private String baseQuery;

    public ApiQueryManager(String apiKey) {
        initializeQueries(apiKey);
    }

    private void initializeQueries(String apiKey) {
        /* BASE */
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(BASE_PARAMETER_API_KEY);
        stringBuffer.append("\"");
        stringBuffer.append(apiKey);
        stringBuffer.append("\"");

        baseQuery = stringBuffer.toString();
    }

    public String generateQuery() {
        return generateQuery(null);
    }

    public String generateQuery(Map<String, Object> parameters) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("{");
        stringBuffer.append(baseQuery);

        if(parameters != null) {
            String divider = "";

            stringBuffer.append(",");
            stringBuffer.append(BASE_PARAMETER_QUERY);
            stringBuffer.append("{");

            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                stringBuffer.append(divider);
                stringBuffer.append(entry.getKey());
                stringBuffer.append("\"");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("\"");
                divider = ",";
            }

            stringBuffer.append("}");
        }

        stringBuffer.append("}");

        return stringBuffer.toString();
    }
}
