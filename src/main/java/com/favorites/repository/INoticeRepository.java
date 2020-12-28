package com.favorites.repository;

import com.favorites.domain.Notice;
import com.favorites.domain.enums.NoticeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface INoticeRepository extends JpaRepository<Notice, Long> {

    /*通过id查询*/
    Notice findById(long id);

    /*通过类型查找*/
    //@Query("select '*' from Notice where type = ?1 and readed = ?2")
    List<Notice> findByTypeAndReaded(NoticeType type, String readed);

    /*已读标记*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Notice n set n.readed = ?1 where n.userId = ?2 and n.type = ?3 and n.readed='unread'")
    int updateReadedByUserId(String readed, long userId, String type);

    /*统计消息数量*/
    Long countByUserIdAndTypeAndReaded(Long userId,String type,String readed);
}
