package com.github.hanpyo.repository;

import com.github.hanpyo.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<TimeTable, Long> {
}
