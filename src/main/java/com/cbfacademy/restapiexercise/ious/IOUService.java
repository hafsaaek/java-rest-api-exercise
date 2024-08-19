package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;


@Service
public class IOUService{
    public final IOURepository iouRepository;

    public IOUService(IOURepository iouRepository){
        this.iouRepository = iouRepository;
    }

    public List<IOU> getAllIOUs() {
        return this.iouRepository.findAll();
    }

    // public IOU getIOU(UUID id){
    //     return iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book with ID " + id + " not found"));
    // }
    public IOU getIOU(UUID id) throws NoSuchElementException {
        return this.iouRepository.findById(id).orElseThrow();
    }

    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException{
        return iouRepository.save(iou);
    }

    public IOU updateIOU(UUID id, IOU updatedIOU){
        IOU existingIOU = this.iouRepository.findById(id).orElseThrow(() -> new NoSuchElementException("IOU with ID " + id + " not found"));

        // Update fields of the given IOU object with the given data to update with 
        existingIOU.setBorrower(updatedIOU.getBorrower());
        existingIOU.setLender(updatedIOU.getLender());
        existingIOU.setAmount(updatedIOU.getAmount());
        existingIOU.setDateTime(updatedIOU.getDateTime());

        return this.iouRepository.save(existingIOU);
    }

    public void deleteIOU(UUID id) throws NoSuchElementException{
        this.iouRepository.deleteById(id);
    }   

    // Exercise 4: Create a new API endpoint to return IOUs for a specific borrower:
        // 1. Create a method in your repository interface called findByBorrower that accepts a string borrower parameter.
        // 2. Create a method in your service class called getIOUsByBorrower - returns IOU given a specific borrower - how? 
    public List<IOU> getIOUsByBorrower(String borrower){
        return this.iouRepository.findByBorrower(borrower);
    }
}

// Create an IOUService class that accepts an IOURepository as a dependency and implements the following methods:
    // List<IOU> getAllIOUs()
    // IOU getIOU(UUID id) throws NoSuchElementException
    // IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException
    // IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException
    // void deleteIOU(UUID id)
// Start your API and confirm there are no errors
