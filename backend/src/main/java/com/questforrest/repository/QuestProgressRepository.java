package com.questforrest.repository;

import com.questforrest.model.QuestProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Repository
public interface QuestProgressRepository extends JpaRepository<QuestProgress, Long> {
    @Query(value = "select qp from QuestProgress qp where qp.quest.id = :questId and qp.code = :questCode")
    QuestProgress findQuestProgress(Long questId, String questCode);

    @Query(value = "select p.questProgress from Participant p where p.questProgress.quest.id = :questId and p.user.token = :token")
    QuestProgress findQuestProgressByQuestIdAndUserToken(Long questId, String token);
}
