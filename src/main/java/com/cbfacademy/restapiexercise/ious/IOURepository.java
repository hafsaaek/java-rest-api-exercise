package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface IOURepository extends ListCrudRepository<IOU, UUID> {

    List<IOU> findByBorrower(String borrower);

    @Query(value = "SELECT * FROM ious WHERE amount >  (SELECT AVG(amount) FROM ious)", nativeQuery = true) // native query needed for sql syntax
    List<IOU> findHighValueIOUs();

    @Query("SELECT i FROM IOU i WHERE i.amount <= (SELECT AVG(i2.amount) FROM IOU i2)")
    List<IOU> findLowValueIOUs();


}

// 1. Create a new API endpoint to return IOUs with above-average value:
    // 1. Create a method in your repository interface called findHighValueIOUs.
    // 2. Define a native @Query annotation that will return all IOUs with an above
    // average value. Hint: create a subquery using the AVG function
    // 3. Create a method in your service class called getHighValueIOUs.
    // 4. Create a getHighValueIOUS method in your controller, mapped to the /high
    // path.
// 2. Test the new endpoint
// 3. Commit your changes
// 4. Create a new endpoint at /low to return IOUs that are below or equal to
// the average value. Implement the repository method using JPQL instead of SQL
