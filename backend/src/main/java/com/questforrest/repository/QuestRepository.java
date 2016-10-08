package com.questforrest.repository;

import com.questforrest.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 08.10.16.
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

}
