package com.favorites.repository;

import com.favorites.domain.Notice;
import com.favorites.domain.enums.NoticeType;
import com.favorites.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface INoticeRepository extends JpaRepository<Notice, Long> {

    public String baseSql="select c.id as id,c.title as title, c.type as type,c.url as url," +
            "c.favoritesId as favoritesId,c.remark as favoriteName,c.logoUrl as logoUrl,c.userId as userId, "
            + "c.remark as remark,c.description as description,c.lastModifyTime as lastModifyTime, "
            + "u.userName as userName,u.profilePicture as profilePicture,n.operId as operId "
            + "from Notice n,Collect c,User u WHERE n.collectId=c.id and c.userId=u.id";

    @Query(baseSql+ " and n.userId=?1 and n.type=?2")
    Page<CollectView> findViewByUserIdAndType(Long userId, String type, Pageable pageable);
    /*通过id查询*/
    Notice findById(long id);

    /*通过类型查找*/
    @Query("select '*' from Notice where type = ?1 and readed = ?2")
    List<Notice> findByTypeAndReaded(NoticeType type, String readed);

    /*已读标记*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Notice n set n.readed = ?1 where n.userId = ?2 and n.type = ?3 and n.readed='unread'")
    int updateReadedByUserId(String readed, long userId, String type);

    /*统计消息数量*/
    Long countByUserIdAndTypeAndReaded(Long userId,String type,String readed);

    @Query("select count(1) from Notice n,Praise p where n.operId=p.id and type='praise' and n.userId=?1 and n.readed=?2")
    Long countPraiseByUserIdAndReaded(Long userId,String readed);

}
