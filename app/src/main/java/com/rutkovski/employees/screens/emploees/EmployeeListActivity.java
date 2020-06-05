package com.rutkovski.employees.screens.emploees;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rutkovski.employees.R;
import com.rutkovski.employees.adapters.EmployeeAdapter;
import com.rutkovski.employees.pojo.Employee;
import com.rutkovski.employees.pojo.Speciality;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EmployeeAdapter employeeAdapter;
    private EmployeeViewModel viewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewEmployees);
        employeeAdapter = new EmployeeAdapter();
        employeeAdapter.setEmployees(new ArrayList<Employee>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(employeeAdapter);
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);


        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeAdapter.setEmployees(employees);

                if (employees!=null) {
                    for (Employee employee:employees) {
                        List<Speciality> specialities = employee.getSpecialty();
                        for (Speciality speciality: specialities) {
                            Log.i("Speciality",speciality.getName());
                        }

                    }
                }
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable!=null) {
                    Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadDate();

    }


}