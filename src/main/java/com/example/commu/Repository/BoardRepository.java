package com.example.commu.Repository;

import com.example.commu.Domain.Board;
import com.example.commu.Domain.Comment;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public void write(Board board) {
        sql.insert("Com.writeBoard",board);
    }

    public List<Board> findAll() {
        return sql.selectList("Com.findAllBoard");
    }

    public Board findById(int boardId) {
        return sql.selectOne("Com.findByIdBoard", boardId);
    }

    public void updatePass(int boardId) {
        sql.update("Com.updatePassBoard", boardId);
    }

    public void modifyPage(Board board) {
        sql.update("Com.modifyPageBoard", board);
    }

    public Boolean like(int boardId, String userId) {
        Map<String, Object> input = new HashMap<>();
        input.put("boardId", boardId);
        input.put("userId", userId);
        int cnt = sql.selectOne("Com.likeBoard", input);
        return (cnt==0);
    }

    public void boardLike(int boardId, String userId) {
        sql.update("Com.boardLikeBoard", boardId);

        Map<String, Object> input = new HashMap<>();
        input.put("userId", userId);
        input.put("boardId", boardId);

        sql.insert("Com.likeTableUpdate", input);
    }

    public void remove(int boardId) {
        sql.delete("Com.removeComment", boardId);
        sql.delete("Com.removeLike", boardId);
        sql.delete("Com.removeBoard", boardId);
    }


    public void commentWrite(Comment comment) {
        sql.insert("Com.commentWrite", comment);
    }

    public List<Comment> findCommentById(int boardId) {
        return sql.selectList("Com.findCommentById", boardId);
    }

    public List<Board> findByUserId(String userId) {
        return sql.selectList("Com.findByUserId", userId);
    }

    public List<Board> findByUserIdComment(String userId) {
        return sql.selectList("Com.findByUserIdComment", userId);
    }

    public List<Board> findByUserIdLike(String userId) {
        return sql.selectList("Com.findByUserIdLike", userId);
    }

    public List<Board> search(String keyword) {
        return sql.selectList("Com.searchBoard",keyword);
    }
}
