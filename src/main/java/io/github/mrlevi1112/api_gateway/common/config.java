package io.github.mrlevi1112.api_gateway.common;

/**
 * @deprecated This class is deprecated. Service URLs should be configured in application.yml
 * and injected using @Value annotations. This class is kept for backward compatibility only.
 * Use application.yml configuration instead:
 * <pre>
 * services:
 *   auth:
 *     url: ${AUTH_SERVICE_URL:http://localhost:8002}
 *   report:
 *     url: ${REPORT_SERVICE_URL:http://localhost:8003}
 * </pre>
 */
@Deprecated
public class Config {
    public static final String AUTH_SERVICE_URL = "http://localhost:8002";
    public static final String REPORT_SERVICE_URL = "http://localhost:8003";
    public static final String AUTH_SERVICE_NAME = "auth-service";
    public static final String REPORT_SERVICE_NAME = "report-service";
}
