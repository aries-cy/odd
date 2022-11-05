package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创作音乐的个人或团队
 */
@Data
public class Artist {

    // 名称
    private String name;

    // 成员
    private String members;

    // 来自哪里
    private String origin;

    private List<Album> albums;

    // 专辑
    @Data
    class Album{
        // 专辑名称
        private String name;

        // 专辑中的一支曲目
        private List<Track> tracks;

        // 参与创作本专辑的艺术家列表
        private List<String> musicians;
    }

    // 专辑中的一支曲目
    @Data
    class Track{
        private String name;
    }

    public Artist getInstance(){
        Artist artist = new Artist();
        artist.setName("团队name");
        artist.setMembers("菜鸟1, 菜鸟2, 菜鸟3");
        artist.setOrigin("上海，北京，深圳");
        List<Album> albums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Album album = new Album();
            album.setName("album" + i);
            List<Track> tracks = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                Track track = new Track();
                track.setName("track" + j);
                tracks.add(track);
            }
            album.setTracks(tracks);
            albums.add(album);
        }
        artist.setAlbums(albums);
        return artist;
    }

}
