package org.smart.board.service;

import org.smart.board.entity.MyBoard;

import java.util.List;

public interface BoardService {
    // 게시글 전체 데이터 가져오기
    public List<MyBoard> findAll();
    public int insert(MyBoard board);
    public int delete(Long boardseq);
    public int update(MyBoard board);
    public MyBoard findOne(Long boardseq);
    public int hitCount(Long boardseq);
}
