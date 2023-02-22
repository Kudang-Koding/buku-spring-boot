package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewVideoWithTotalRuntimeDto;
import kudangkoding.gamifikasi.models.VwPlaylistUserViewVideoWithTotalRuntime;

public class PlaylistUserViewVideoWithTotalRuntimeMapper {

    public static VwPlaylistUserViewVideoWithTotalRuntimeDto toDto(VwPlaylistUserViewVideoWithTotalRuntime model) {
        VwPlaylistUserViewVideoWithTotalRuntimeDto playlistUserViewVideoWithTotalRuntimeDto =  new VwPlaylistUserViewVideoWithTotalRuntimeDto();
        playlistUserViewVideoWithTotalRuntimeDto
                .setId(model.getId())
                .setUser_id(model.getUser_id())
                .setPlaylist_id(model.getPlaylist_id())
                .setPlaylist_video_id(model.getPlaylist_video_id())
                .setRuntime_persecond(model.getRuntime_persecond())
                .setTotal_watch_time(model.getTotal_watch_time());

        return playlistUserViewVideoWithTotalRuntimeDto;
    }
}
