package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {

    List<IOU> findByBorrower(String borrower);

}

// 1. Create a new API endpoint to return IOUs for a specific borrower:
// 1. Create a method in your repository interface called findByBorrower that
// accepts a string borrower parameter.
// 2. Create a method in your service class called getIOUsByBorrower.
// 3. Extend the getIOUS method of your controller to accept an optional query
// string parameter, e.g.: getIOUs(@RequestParam(required = false) String
// borrower)
// 4. Check the value of the borrower parameter to determine whether to call the
// existing service method or the new, filtered, one
// 2.Test the modified endpoint
// 3. Commit your changes