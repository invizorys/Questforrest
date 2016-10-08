package com.questforrest.repository;

import com.questforrest.model.QuestProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Repository
public interface QuestProgressRepository extends JpaRepository<QuestProgress, Long> {

    QuestProgress findQuestProgress(Long questId, String questCode);
}
