package com.springrest.LiveasyLogistics.controller;

import com.springrest.LiveasyLogistics.entities.Loads;
import com.springrest.LiveasyLogistics.services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MyController {
  @Autowired // gives you the object of the implementation class
    private LoadService loadService;
    @GetMapping("/home")
    public String home(){
        return "This is Home Page";
    }

    @GetMapping("/loads")
    public ResponseEntity<List<Loads> > getLoads(){
     try{
       List<Loads> loads = this.loadService.getLoads();
       if(loads.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       return ResponseEntity.ok(loads);
     }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }}
    @GetMapping("/loads/{loadId}")
    public ResponseEntity<Loads> getLoad(@PathVariable String loadId){
       try{
          Loads load = this.loadService.getLoad(loadId);
          if(load == null) ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
          return ResponseEntity.ok(load);
       }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }

    @GetMapping("/loads/shipper")
    public ResponseEntity<List<Loads>> getLoadByShipperId(@RequestParam("shipperId") UUID shipperId){
          try{

//   UUID Validation
   String regex = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";
   if (!shipperId.toString().matches(regex)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

   List<Loads> shipperLoads = this.loadService.getLoadByShipperId(shipperId.toString());

    if(shipperLoads == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    return ResponseEntity.ok(shipperLoads);

          }catch (Exception e){
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
          }
    }

    @PostMapping(path = "/loads", consumes = "application/json")
    public ResponseEntity<String> addLoad(@RequestBody Loads load){
        try{
            Loads newLoad = this.loadService.addLoad(load);
            if (newLoad == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not create a new Load");
            return ResponseEntity.status(HttpStatus.CREATED).body("loads details added successfully ");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/loads/{loadId}")
    public ResponseEntity<String> updateLoad(@PathVariable String loadId , @RequestBody Loads load){
     try{
     Loads existingLoad = this.loadService.getLoad(loadId);
     if(existingLoad == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Load Found with Id" + loadId);

     Loads updatedLoad = this.loadService.updateLoad(loadId , load);
     if(updatedLoad == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not update load");

     return ResponseEntity.status(HttpStatus.CREATED).body("Details updated Successfully");
     }catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
     }
    }

    @DeleteMapping("/loads/{loadId}")
    public ResponseEntity<HttpStatus> deleteLoad(@PathVariable String loadId){
        try{
            Loads existingLoad = this.loadService.getLoad(loadId);

            if (existingLoad == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            this.loadService.deleteLoad(loadId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
