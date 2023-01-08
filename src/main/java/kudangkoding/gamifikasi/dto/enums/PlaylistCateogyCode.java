package kudangkoding.gamifikasi.dto.enums;

public enum PlaylistCateogyCode {

    VIDEO(1), ILLUSTRATION(2);

    private Integer val;

    PlaylistCateogyCode(Integer val){this.val = val;}

    public Integer val(){
        return this.val;
    }

    public String label(){
        if (VIDEO.val.equals(this.val)){
            return "Video";
        }
        else if(ILLUSTRATION.val.equals(this.val)){
            return "Ilustrasi";
        }
        return null;
    }

    public static PlaylistCateogyCode val(String val){
        if (VIDEO.val.equals(val)){
            return PlaylistCateogyCode.VIDEO;
        }
        else if (ILLUSTRATION.val.equals(val)){
            return PlaylistCateogyCode.ILLUSTRATION;
        }
        return null;
    }

}
