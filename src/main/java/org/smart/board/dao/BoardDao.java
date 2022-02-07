package org.smart.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.smart.board.entity.MyBoard;

import java.util.List;

@Mapper
public interface BoardDao {
    public List<MyBoard> findAll();
    public int insert(MyBoard board);
    public int delete(Long boardseq);
    public int update(MyBoard board);
    public MyBoard findOne(Long boardseq);
    public int hitCount(Long boardseq);
}
