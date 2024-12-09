package com.example.commu.Service;

import com.example.commu.Domain.Board;
import com.example.commu.Domain.Comment;
import com.example.commu.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.write(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(int boardId) {
        return boardRepository.findById(boardId);
    }

    public void updatePass(int boardId) {
        boardRepository.updatePass(boardId);
    }

    public void modifyPage(Board board) {
        boardRepository.modifyPage(board);
    }

    public Boolean like(int boardId, String userId) {
        return boardRepository.like(boardId,userId);
    }

    public void boardLike(int boardId, String userId) {
        boardRepository.boardLike(boardId, userId);
    }

    public void remove(int boardId) {
        boardRepository.remove(boardId);
    }


    public void commentWrite(Comment comment) {
        boardRepository.commentWrite(comment);
    }


    public List<Comment> findCommentById(int boardId) {
        return boardRepository.findCommentById(boardId);
    }

    public List<Board> findByUserId(String userId) {
        return boardRepository.findByUserId(userId);
    }

    public List<Board> findByUserIdComment(String userId) {
        return boardRepository.findByUserIdComment(userId);
    }

    public List<Board> findByUserIdLike(String userId) {
        return boardRepository.findByUserIdLike(userId);
    }
}
