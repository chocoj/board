package org.smart.board.service;

import org.smart.board.dao.BoardDao;
import org.smart.board.entity.MyBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDao boardDao;

    @Override
    public List<MyBoard> findAll() {
        List<MyBoard> boardList=boardDao.findAll();

        return boardList;
    }

    @Override
    public int insert(MyBoard board) {
        int result = boardDao.insert(board);
        return 0;
    }

    @Override
    public int delete(Long boardseq) {
        return 0;
    }

    @Override
    public int update(MyBoard board) {
        return 0;
    }

    @Override
    public MyBoard findOne(Long boardseq) {
        MyBoard board = boardDao.findOne(boardseq);
        return board;
    }

    @Override
    public int hitCount(Long boardseq) {
        int hitcount = boardDao.hitCount(boardseq);
        return hitcount;
    }
}
