package com.favorites.repository;

import com.favorites.domain.LookRecord;
import com.favorites.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("lookRecordRepository")
public interface IlookRecordRepository extends JpaRepository<LookRecord, Long> {
    public String userLookRecordSql = "select c.id as id,c.title as title, c.type as type,c.url as url,c.logoUrl as logoUrl,c.userId as userId, "
            + "c.remark as remark,c.description as description,c.lastModifyTime as lastModifyTime,r.lastModifyTime as createTime, "
            + "u.userName as userName,u.profilePicture as profilePicture "
            + "from LookRecord r,Collect c,User u "
            + "WHERE c.userId = u.id and r.collectId = c.id and c.isDelete='NO'";

    @Query(userLookRecordSql+ " and r.userId=?1")
    Page<CollectView> findViewByUserId(Long userId, Pageable pageable);

    Integer countByUserIdAndCollectId(Long userId, Long collectId);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update LookRecord set lastModifyTime=:lastModifyTime where userId=:userId and collectId=:collectId")
    void updatelastModifyTime(@Param("userId") Long userId, @Param("collectId") Long collectId, @Param("lastModifyTime") Long lastModifyTime);

    @Transactional
    Long deleteByUserIdAndCollectId(Long userId,Long collectId);

    @Transactional
    Long deleteByUserId(Long userId);
}
