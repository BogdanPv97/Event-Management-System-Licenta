package com.management.system.DAO;

import com.management.system.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {


}
