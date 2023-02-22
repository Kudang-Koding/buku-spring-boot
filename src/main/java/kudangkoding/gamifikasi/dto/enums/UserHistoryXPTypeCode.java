package kudangkoding.gamifikasi.dto.enums;

public enum UserHistoryXPTypeCode {

    STREAK("STREAK"),
    WATCH_VIDEO("WATCH_VIDEO"),

    COMPLETE_DAILY_MISSION("COMPLETE_DAILY_MISSION"),

    COMPLETE_STREAK_MISSION("COMPLETE_STREAK_MISSION");

    private String val;

    UserHistoryXPTypeCode(String val) {
        this.val = val;
    }

    public String val() {
        return this.val;
    }

    public static UserHistoryXPTypeCode val(String val) {
        if (STREAK.val.equals(val)) {
            return UserHistoryXPTypeCode.STREAK;
        }
        else if (WATCH_VIDEO.val.equals(val)) {
            return UserHistoryXPTypeCode.WATCH_VIDEO;
        }

        return null;
    }

    public Integer xp(){
        if (STREAK.val.equals(this.val)) {
            return 20;
        }
        else if (WATCH_VIDEO.val.equals(this.val)) {
            return 15;
        }

        return 0;
    }

}
