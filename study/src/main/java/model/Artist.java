package model;

import lombok.Data;
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

}
