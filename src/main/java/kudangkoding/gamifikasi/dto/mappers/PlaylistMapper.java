package kudangkoding.gamifikasi.dto.mappers;

import kudangkoding.gamifikasi.dto.models.MstPlaylistDto;
import kudangkoding.gamifikasi.dto.models.MstPlaylistVideoDto;
import kudangkoding.gamifikasi.dto.models.MstPlaylistVideoIllustrationDto;
import kudangkoding.gamifikasi.models.MstPlaylist;
import kudangkoding.gamifikasi.models.MstPlaylistVideo;
import kudangkoding.gamifikasi.models.MstPlaylistVideoIllustration;

import java.util.ArrayList;
import java.util.List;

public class PlaylistMapper {

    public static MstPlaylistDto toDto(MstPlaylist model) {
        MstPlaylistDto playlistDto = new MstPlaylistDto();
        playlistDto.setName(model.getName())
                .setDescription(model.getDescription())
                .setUrl_picture(model.getUrl_picture())
                .setTopic_id(model.getTopic_id())
                .setTotal_chapter(model.getTotal_chapter())
                .setTotal_runtime(model.getTotal_runtime())
                .setTotal_like(model.getTotal_like())
                .setIs_favorite(model.getIs_favorite())
                .setAuthor(model.getAuthor())
                .setCategory(model.getCategory())
                .setIs_all_viewed(model.getIs_all_viewed())
                .setId(model.getId())
                .setCreated_at(model.getCreated_at())
                .setUpdated_at(model.getUpdated_at());

        if (model.getList_playlist_video() != null) {
            List<MstPlaylistVideoDto> playlistVideoDtos = new ArrayList<>();
            for (MstPlaylistVideo mstPlaylistVideo: model.getList_playlist_video()) {
                MstPlaylistVideoDto playlistVideoDto = new MstPlaylistVideoDto();
                playlistVideoDto.setPlaylist_id(mstPlaylistVideo.getPlaylist_id())
                        .setEpisode(mstPlaylistVideo.getEpisode())
                        .setTitle(mstPlaylistVideo.getTitle())
                        .setUrl(mstPlaylistVideo.getUrl())
                        .setRuntime(mstPlaylistVideo.getRuntime())
                        .setRuntime_persecond(mstPlaylistVideo.getRuntime_persecond())
                        .setXp(mstPlaylistVideo.getXp())
                        .setId(mstPlaylistVideo.getId())
                        .setCreated_at(mstPlaylistVideo.getCreated_at())
                        .setUpdated_at(mstPlaylistVideo.getUpdated_at());


                if (mstPlaylistVideo.getList_playlist_video_illustration() != null) {
                    List<MstPlaylistVideoIllustrationDto> playlistVideoIllustrationDtos = new ArrayList<>();
                    for (MstPlaylistVideoIllustration playlistVideoIllustration: mstPlaylistVideo.getList_playlist_video_illustration()) {
                        MstPlaylistVideoIllustrationDto playlistVideoIllustrationDto = new MstPlaylistVideoIllustrationDto();
                        playlistVideoIllustrationDto
                                .setPlaylist_video_id(playlistVideoIllustration.getPlaylist_video_id())
                                .setUrl(playlistVideoIllustration.getUrl())
                                .setIllustration_order(playlistVideoIllustration.getIllustration_order())
                                .setId(playlistVideoIllustration.getId())
                                .setCreated_at(playlistVideoIllustration.getCreated_at())
                                .setUpdated_at(playlistVideoIllustration.getUpdated_at());

                        playlistVideoIllustrationDtos.add(playlistVideoIllustrationDto);
                    }
                    playlistVideoDto.setPlaylist_video_illustration_list(playlistVideoIllustrationDtos);
                }
                playlistVideoDtos.add(playlistVideoDto);
            }
            playlistDto.setPlaylist_video_list(playlistVideoDtos);
        }

        if(model.getTopic_data() != null) {
            playlistDto.setTopic_name(model.getTopic_data().getName());
        }

        return playlistDto;
    }

}
