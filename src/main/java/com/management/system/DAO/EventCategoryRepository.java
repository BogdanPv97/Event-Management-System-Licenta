package com.management.system.DAO;

import com.management.system.Entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {

    @Query(nativeQuery = true, value = "select category_name from event_category c where c.event_category_id=?1")
    public String getCategoryNameById(long categoryId);

    @Query(nativeQuery = true, value = "select * from event_category e where e.event_category_id=?1")
    public EventCategory getCategoryById(long categoryId);
}
