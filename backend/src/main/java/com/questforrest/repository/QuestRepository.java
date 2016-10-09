package com.questforrest.repository;

import com.questforrest.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 08.10.16.
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

    @Query(value = "select p.questProgress.quest from Participant p where p.user.id = :id")
    List<Quest> findQuestsUserEnrolled(@Param("id") Long id);
}
