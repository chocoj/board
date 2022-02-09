package org.smart.board.controller;

import org.smart.board.entity.Board;
import org.smart.board.service.BoardService;
import org.smart.board.util.PageNavigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(@RequestParam(defaultValue ="1") int currentPage,
                            @RequestParam(defaultValue ="title") String searchItem,
                            @RequestParam(defaultValue ="") String searchWord,
                            Model model){

        //db에서 저장된 전체 글 개수를 얻어옴
        int totalRecordCount=boardService.getBoardCount(searchItem,searchWord);
        PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
        int countPerPage = navi.getCountPerPage();

        int srow= 1+(currentPage-1)*countPerPage;
        int erow= countPerPage+(currentPage-1)*countPerPage;
        //System.out.println("searchItem"+searchItem+", searchWord"+searchWord);
        List<Board> boardList = boardService.findAll(srow, erow, searchItem, searchWord);
        model.addAttribute("boardList",boardList);
        model.addAttribute("searchItem",searchItem);
        model.addAttribute("searchWord",searchWord);
        return "board/boardList";
    }
    @GetMapping("/boardWrite")
    public String boardWrite(){

        return "board/boardWrite";
    }
    //session
    @PostMapping("boardWrite")
    public String boardWrite(Board board, @AuthenticationPrincipal UserDetails user){

        board.setUsrid(user.getUsername());
        System.out.println("==================="+board);
        boardService.insert(board);
        //DB
        return "redirect:/board/boardList";
    }
    @GetMapping("/boardDetail")
    public String boardDetail(Long boardseq, Model model){
        //1) db에서 boardseq에 해당하는 하나의 글을 질의해옴
        Board board= boardService.findOne(boardseq);
        int hitcount = boardService.hitCount(boardseq);

        //2)model에 저장
        //2-2) hitcount를 model에 저장
        board.setHitcount(hitcount);
        model.addAttribute("board",board);
        //3) view로 forward
        return "board/boardDetail";
    }
    @GetMapping("/boardUpdate")
    public String boardUpdate(Long boardseq, Model model){
        Board board=boardService.findOne(boardseq);
        model.addAttribute("board",board);
        return"board/boardUpdate";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(Board board){
        System.out.println(board);
        board.setUsrid("aaa");
        boardService.update(board);

        return "redirect:/board/boardList";
    }
    @GetMapping("/boardDelete")
    public String boardDelete(Long boardseq){
        boardService.delete(boardseq);
        return "redirect:/board/boardList";
    }

}

