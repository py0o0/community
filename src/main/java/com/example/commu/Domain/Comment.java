package com.example.commu.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private int commentId;
    private String commentWriter;
    private int commentBoardId;
    private String commentContents;
}
