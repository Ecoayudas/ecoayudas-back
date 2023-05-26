package com.numerus.ecoayudas.v1.app.security.constants;

/**
 * This class contains the constants used for security configuration.
 */
public class SecurityConstants {
    private SecurityConstants() {
    }

    /**
     * HTTP method constant for POST.
     */
    public static final String METHOD_POST = "POST";

    /**
     * Secret key used for token encryption and decryption.
     */
    public static final String SECRET_KEY = "hKj$#dLm7@*fT9z";

    /**
     * Expiration time for the authentication token (in milliseconds).
     */
    public static final int EXPIRATION_TIME = 3600000;

    /**
     * Prefix for the authorization token in the HTTP header.
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Header key for the authorization token.
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * URL for the login endpoint.
     */
    public static final String LOGIN_URL = "/api/v1/login";

    /**
     * URL pattern for the cliente endpoints.
     */
    public static final String CLIENTE_URL = "/api/v1/clientes";
    public static final String CLIENTE_URL1 = "/api/v1/clientes/**";

    /**
     * URL pattern for the API endpoints.
     */
    public static final String API_URL = "/api/v1/**";

    /**
     * URL pattern for the instalador endpoints.
     */
    public static final String INSTALADOR_URL = "/api/v1/instaladores";
    public static final String INSTALADOR_URL1 = "/api/v1/instaladores/**";

    /**
     * URL for the file upload endpoint.
     */
    public static final String UPLOAD_URL = "/api/v1/upload";

    /**
     * URL for the logout endpoint.
     */
    public static final String LOGOUT_URL = "/api/v1/logout";

    /**
     * Content type for JSON.
     */
    public static final String APP_JSON = "application/json";

    /**
     * Key for the token in the JSON response.
     */
    public static final String TOKEN_JSON = "token";

    /**
     * URL for the Angular frontend application.
     */
    public static final String ANGULAR_URL = "http://localhost:4200";

    /**
     * Authorization role for the cliente.
     */
    public static final String AUTH_CLIENTE = "[CLIENTE]";

    /**
     * Authorization role for the instalador.
     */
    public static final String AUTH_INSTALADOR = "[INSTALADOR]";
}
