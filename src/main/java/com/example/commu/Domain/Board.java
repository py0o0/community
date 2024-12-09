package com.example.commu.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
    private Long boardId;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private Long boardPass;
    private Long boardLike;
    private String boardCreated;
}
