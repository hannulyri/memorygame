package fi.hmp.memorygame.config;

/**
 * Application constants.
 */
public final class Constants {

    private Constants() {
    }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    //public static final String SPRING_PROFILE_DEVELOPMENT = "prod";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_FAST = "fast";
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String SYSTEM_ANONYMOUS = "anonymousUser";
    
    public static final int SYSTEM_HIGHSCORE_MAX = 100;
    public static final int SYSTEM_HIGHSCORE_MIN = 1;
    
}
