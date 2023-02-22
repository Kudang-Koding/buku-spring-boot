package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewVideoDto;
import kudangkoding.gamifikasi.models.TrxPlaylistUserViewVideo;

public class PlaylistUserViewVideoMapper {

    public static TrxPlaylistUserViewVideoDto toDto(TrxPlaylistUserViewVideo model) {
        TrxPlaylistUserViewVideoDto playlistUserViewVideoDto = new TrxPlaylistUserViewVideoDto();
        playlistUserViewVideoDto.setUser_id(model.getUser_id()).setPlaylist_id(model.getPlaylist_id())
                .setPlaylist_video_id(model.getPlaylist_video_id())
                .setWatch_time(model.getWatch_time())
                .setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return playlistUserViewVideoDto;
    }

}
