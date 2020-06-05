package com.rutkovski.employees.data;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rutkovski.employees.pojo.Employee;

import java.util.List;

@androidx.room.Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAllEmployees();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(List <Employee> employees);

    @Query("DELETE FROM employees")
    void deleteAllEmployees();


}
