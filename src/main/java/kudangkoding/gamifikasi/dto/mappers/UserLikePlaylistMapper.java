package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.TrxUserLikePlaylistDto;
import kudangkoding.gamifikasi.models.TrxUserLikePlaylist;

public class UserLikePlaylistMapper {

    public static TrxUserLikePlaylistDto toDto(TrxUserLikePlaylist model) {
        TrxUserLikePlaylistDto userLikePlaylistDto = new TrxUserLikePlaylistDto();
        userLikePlaylistDto.setPlaylist_id(model.getPlaylist_id()).setUser_id(model.getUser_id()).setId(model.getId()).setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        return userLikePlaylistDto;
    }

}
