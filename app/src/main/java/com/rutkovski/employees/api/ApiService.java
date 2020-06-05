package com.rutkovski.employees.api;

import com.rutkovski.employees.pojo.Employee;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<List<Employee>> getEmployees();

}
