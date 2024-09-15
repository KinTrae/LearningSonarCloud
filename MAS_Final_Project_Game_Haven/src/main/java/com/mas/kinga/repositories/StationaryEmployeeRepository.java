package com.mas.kinga.repositories;

import com.mas.kinga.models.StationaryEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationaryEmployeeRepository extends JpaRepository<StationaryEmployee, Long> {

}
