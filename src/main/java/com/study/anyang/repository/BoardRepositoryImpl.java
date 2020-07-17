package com.study.anyang.repository;

import java.util.List;

import com.study.anyang.domain.QBoard;
import com.study.anyang.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements CustomBoardRepository {

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> getSearchPage(String searchType, String keyword, Pageable pageable) {
        String title = keyword;
        String writer = keyword;
        String content = keyword;

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if(searchType != null && searchType.length() > 0) {
            if(searchType.equals("t")) {
                query.where(board.title.like("%" + title +"%"));
                query.orderBy(board.boardNo.desc());
            }
            else if(searchType.equals("w")) {
                query.where(board.writer.like("%" + writer +"%"));
                query.orderBy(board.boardNo.desc());
            }
            else if(searchType.equals("c")) {
                query.where(board.content.like("%" + content +"%"));
                query.orderBy(board.boardNo.desc());
            }
            else if(searchType.equals("tc")) {
                query.where(board.title.like("%" + title +"%").or(board.content.like("%" + content +"%")));
                query.orderBy(board.boardNo.desc());
            }
            else if(searchType.equals("cw")) {
                query.where(board.content.like("%" + content +"%").or(board.writer.like("%" + writer +"%")));
                query.orderBy(board.boardNo.desc());
            }
            else if(searchType.equals("tcw")) {
                BooleanBuilder builder = new BooleanBuilder();
                builder.and(board.title.like("%" + title +"%"))
                        .or(board.content.like("%" + content +"%"))
                        .or(board.writer.like("%" + writer +"%"));

                query.where(builder);
                query.orderBy(board.boardNo.desc());
            }
            else {
                query.where(board.boardNo.gt(0L));
                query.orderBy(board.boardNo.desc());
            }
        }
        else {
            query.where(board.boardNo.gt(0L));
            query.orderBy(board.boardNo.desc());
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Board> resultList = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(resultList, pageable, total);
    }
}
