package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<IOU> getListOfIOUs(@PathVariable UUID id) {
        return this.iouService.getAllIOUs();
    }

    // GET /api/ious/{id} Retrieve a specific IOU by its ID
    @RequestMapping("/api/iou")
    @GetMapping(name = "/{id}", produces = "application/json")
    public IOU getIOUByID(@PathVariable UUID id) {
        return this.iouService.getIOU(id);
    }

    // POST /api/ious Create a new IOU
    @PostMapping(produces = "application/json")
    public IOU postIOU(IOU iou) {
        return this.iouService.createIOU(iou);
    }

    // PUT /api/ious/{id} Update an existing IOU by ID
    @PutMapping(name = "/{id}", produces = "application/json")
    public IOU putIOUById(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        return this.iouService.updateIOU(id, updatedIOU);
    }

    // DELETE /api/ious/{id} Delete an IOU by ID
    @DeleteMapping("/{id}")
    public void deleteIOUById(@PathVariable UUID id) {
        this.iouService.deleteIOU(id);
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