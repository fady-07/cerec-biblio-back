package cerec.biblio.demo.Constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME=60000;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String JWT_TOKEN_HEADER="Jwt-Token";
    public static final String REFRESH_TOKEN="Refresh-Token";
    public static final String TOEKEN_CANNOT_BE_VERIFIED="Token Cannot be verified";
    public static final String GET_ARRAYS_LLC="EDAC, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION="User Management Portail";
    public static final String AUTHORITIES="Authorities";
    public static final String FORBIDDEN_MESSAGE="Vous devez vous authenitifier pour acceder à cette page";
    public static final String ACCESS_DENIED_MESSAGE="Vous n'avez pas accès à cette page";
    public static final String OPTION_HTTP_METHOD="OPTIONS";
    public static final String[] PUBLIC_URLS={"/user/login","/user/register","/user/resetpassword/**","/user/image/**","/**"};
    public static final String[] PRIVATE_URLS={"/auth/**"};
}
