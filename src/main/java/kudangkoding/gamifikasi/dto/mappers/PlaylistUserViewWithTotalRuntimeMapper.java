package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewWithTotalRuntimeDto;
import kudangkoding.gamifikasi.models.VwPlaylistUserViewWithTotalRuntime;

public class PlaylistUserViewWithTotalRuntimeMapper {

    public static VwPlaylistUserViewWithTotalRuntimeDto toDto(VwPlaylistUserViewWithTotalRuntime model) {
        VwPlaylistUserViewWithTotalRuntimeDto playlistUserViewWithTotalRuntimeDto = new VwPlaylistUserViewWithTotalRuntimeDto();
        playlistUserViewWithTotalRuntimeDto
                .setId(model.getId())
                .setPlaylist_id(model.getPlaylist_id())
                .setUser_id(model.getUser_id())
                .setTotal_runtime_persecond(model.getTotal_runtime_persecond())
                .setTotal_watch_time_video(model.getTotal_watch_time_video());

        return playlistUserViewWithTotalRuntimeDto;
    }

}
