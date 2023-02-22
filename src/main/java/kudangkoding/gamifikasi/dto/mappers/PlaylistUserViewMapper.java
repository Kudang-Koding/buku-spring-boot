package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.dto.models.TrxPlaylistUserViewDto;
import kudangkoding.gamifikasi.dto.models.VwPlaylistUserViewDto;
import kudangkoding.gamifikasi.models.TrxPlaylistUserView;
import kudangkoding.gamifikasi.models.VwPlaylistUserView;

import java.util.ArrayList;
import java.util.List;

public class PlaylistUserViewMapper {

    public static TrxPlaylistUserViewDto toDto(TrxPlaylistUserView model) {
        TrxPlaylistUserViewDto playlistUserViewDto = new TrxPlaylistUserViewDto();
        playlistUserViewDto.setUser_id(model.getUser_id()).setPlaylist_id(model.getPlaylist_id())
                .setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return playlistUserViewDto;
    }

    public static VwPlaylistUserViewDto toDto(VwPlaylistUserView model) {
        VwPlaylistUserViewDto playlistUserViewDto = new VwPlaylistUserViewDto();
        playlistUserViewDto.setUser_id(model.getUser_id()).setPlaylist_id(model.getPlaylist_id())
                .setId(model.getId());

        if (model.getPlaylist_data() != null) {
            MstPlaylistDto playlistDto = new MstPlaylistDto();
            playlistDto
                    .setName(model.getPlaylist_data().getName())
                    .setDescription(model.getPlaylist_data().getDescription())
                    .setCategory(model.getPlaylist_data().getCategory())
                    .setUrl_picture(model.getPlaylist_data().getUrl_picture())
                    .setTopic_id(model.getPlaylist_data().getTopic_id())
                    .setTopic_name(model.getPlaylist_data().getTopic_data().getName())
                    .setIs_all_viewed(model.getPlaylist_data().getIs_all_viewed())
                    .setTotal_chapter(model.getPlaylist_data().getTotal_chapter())
                    .setTotal_like(model.getPlaylist_data().getTotal_like())
                    .setTotal_runtime(model.getPlaylist_data().getTotal_runtime())
                    .setAuthor(model.getPlaylist_data().getAuthor())
                    .setCreated_at(model.getPlaylist_data().getCreated_at())
                    .setUpdated_at(model.getPlaylist_data().getUpdated_at())
                    .setId(model.getPlaylist_data().getId());

            playlistUserViewDto.setPlaylist_data(playlistDto);
        }

        return playlistUserViewDto;
    }

}
