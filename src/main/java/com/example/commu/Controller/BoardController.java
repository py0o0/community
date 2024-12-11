package com.example.commu.Controller;

import com.example.commu.Domain.Board;
import com.example.commu.Domain.Comment;
import com.example.commu.Service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String home(Model model){
        List<Board> board = boardService.findAll();
        model.addAttribute("board", board);
        return "home";
    }

    @GetMapping("/board/write")
    public String write(){
        return "write";
    }

   @PostMapping("/board/write")
    public String write(Board board,HttpSession session){
        String userId = (String) session.getAttribute("userId");
        board.setBoardWriter(userId);
        boardService.write(board);
        return "redirect:/";
   }

   @GetMapping("/board/{boardId}")
    public String boardPage(@PathVariable int boardId, Model model){
        boardService.updatePass(boardId); //조회수 업데이트
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);

       List<Comment> comment = boardService.findCommentById(boardId);
       model.addAttribute("comment", comment);

        return "boardPage";
   }

   @GetMapping("/board/modify/{boardId}")
    public String modifyPage(@PathVariable int boardId, Model model){
        model.addAttribute("boardId", boardId);
        return "boardModify";
   }

    @PostMapping("/board/modify/{boardId}")
    public String modifyPage(@ModelAttribute Board board){
        boardService.modifyPage(board);
        return "redirect:/board/" + board.getBoardId();
    }

    @GetMapping("/board/like")
    public ResponseEntity<Boolean> like(@RequestParam int boardId, @RequestParam String userId){
        Boolean response = boardService.like(boardId, userId);
        if(response)
          boardService.boardLike(boardId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/board/remove")
    public void remove(@RequestParam int boardId){
        boardService.remove(boardId);
    }

    @PostMapping("/board/commentWrite")
    public String commentWrite(@ModelAttribute Comment comment , HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null) userId = "ㅇㅇ";
        comment.setCommentWriter(userId);
        boardService.commentWrite(comment);
        return "redirect:/board/" + comment.getCommentBoardId();
    }

    @GetMapping("/board/myPage")
    public String mypage(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("userId", userId);
        return "myPage";
    }

    @GetMapping("/board/myWrite")
    public String myWrite(HttpSession session,Model model){
        String userId = (String) session.getAttribute("userId");
        List<Board> board = boardService.findByUserId(userId);
        model.addAttribute("board", board);
        model.addAttribute("state",1);
        return "myPageBoard";
    }

    @GetMapping("/board/myComment")
    public String myComment(HttpSession session,Model model){
        String userId = (String) session.getAttribute("userId");
        List<Board> board = boardService.findByUserIdComment(userId);
        model.addAttribute("board", board);
        model.addAttribute("state",2);
        return "myPageBoard";
    }

    @GetMapping("/board/myLike")
    public String myLike(HttpSession session,Model model){
        String userId = (String) session.getAttribute("userId");
        List<Board> board = boardService.findByUserIdLike(userId);
        model.addAttribute("board", board);
        model.addAttribute("state",3);
        return "myPageBoard";
    }

    @PostMapping("board/search")
    public String search(@RequestParam String keyword, Model model){
        List<Board> board = boardService.search(keyword);
        model.addAttribute("board", board);
        model.addAttribute("state",0);
        return "myPageBoard";
    }
}
