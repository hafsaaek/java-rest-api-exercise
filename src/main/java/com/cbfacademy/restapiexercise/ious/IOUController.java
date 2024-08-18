package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/ious")
public class IOUController {
    private final IOUService iouService;

    @Autowired
    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    // GET /api/ious Retrieve a list of (optionally filtered) IOUs
    @GetMapping(produces = "application/json")
    public List<IOU> getListOfIOUs() {
        try {
            return this.iouService.getAllIOUs();
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU not found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    // GET /api/ious/{id} Retrieve a specific IOU by its ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public IOU getIOUByID(@PathVariable UUID id) {
        try {
            return this.iouService.getIOU(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found");
        } catch (RuntimeException exception) {
            System.err.println("Internal Server Error: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
        }
    }

    // POST /api/ious Create a new IOU
    @PostMapping(produces = "application/json")
    public ResponseEntity<IOU> postIOU(@RequestBody IOU iou) {
        try {
            IOU createdIOU = this.iouService.createIOU(iou);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIOU);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid IOU data", exception);
        } catch (OptimisticLockingFailureException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict occurred while saving the IOU", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while processing the request", exception);
        }
    }

    // PUT /api/ious/{id} Update an existing IOU by ID
    @PutMapping(value = "/{id}", produces = "application/json")
    public IOU putIOUById(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        try {
            return this.iouService.updateIOU(id, updatedIOU);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid IOU data", exception);
        } catch (OptimisticLockingFailureException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflict occurred while updating the IOU",
                    exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while processing the request", exception);
        }
    }

    // DELETE /api/ious/{id} Delete an IOU by ID
    @DeleteMapping("/{id}")
    public void deleteIOUById(@PathVariable UUID id) {
        try {
            this.iouService.deleteIOU(id);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while processing the request", exception);
        }
    }

}

// Create an IOUController class that implements the endpoints below. Ensure
// your service class is injected as a dependency and apply the appropriate
// annotations
// Start your API and confirm there are no errors
// Method Endpoint Description
// GET /api/ious Retrieve a list of (optionally filtered) IOUs
// GET /api/ious/{id} Retrieve a specific IOU by its ID
// POST /api/ious Create a new IOU
// PUT /api/ious/{id} Update an existing IOU by ID
// DELETE /api/ious/{id} Delete an IOU by ID