package org.smart.board.service;
import org.smart.board.entity.Guestbook;
import java.util.List;
import java.util.Map;

public interface GuestbookService {
    //목록 요청
    public List<Guestbook> guestbookList();

    //글쓰기
    public int guestbookWrite(Guestbook guestbook);

    //글 삭제
    public int guestbookDelete(Map<String, Object> map);
}
