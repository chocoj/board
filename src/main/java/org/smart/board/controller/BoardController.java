package org.smart.board.controller;

import org.smart.board.entity.MyBoard;
import org.smart.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/boardList")
    public String boardList(Model model){
        List <MyBoard> boardList = boardService.findAll();

        model.addAttribute("boardList",boardList);
        //db
        return "board/boardList";
    }
    @GetMapping("/boardWrite")
    public String boardWrite(){

        return "board/boardWrite";
    }
    @PostMapping("boardWrite")
    public String boardWrite(MyBoard board){
        System.out.println("==================="+board);

        boardService.insert(board);
        //DB
        return "redirect:/board/boardList";
    }
    @GetMapping("/boardDetail")
    public String boardDetail(Long boardseq, Model model){
        //1) db에서 boardseq에 해당하는 하나의 글을 질의해옴
        MyBoard board= boardService.findOne(boardseq);
        int hitcount = boardService.hitCount(boardseq);

        //2)model에 저장
        //2-2) hitcount를 model에 저장
        board.setHitcount(hitcount);
        model.addAttribute("board",board);
        //3) view로 forward
        return "board/boardDetail";
    }
}
