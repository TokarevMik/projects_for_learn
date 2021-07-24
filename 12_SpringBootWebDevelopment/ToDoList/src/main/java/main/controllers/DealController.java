package main.controllers;

import main.model.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealRepository dealRepository;

    @GetMapping
    public List<Deal> toDoList() {
        Iterable<Deal> dealIterable = dealRepository.findAll();
        List<Deal> deals = new ArrayList<>();
        for (Deal d : dealIterable) {
            deals.add(d);
        }
        return deals;
    }

    @GetMapping("/{id}")
    public ResponseEntity getDealFromList(@PathVariable int id) {
        if (!dealRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Optional<Deal> optionalDeal = dealRepository.findById(id);
            Deal currentDeal = optionalDeal.get();
            return new ResponseEntity(currentDeal, HttpStatus.OK);
        }
    }

    @PostMapping
    public long postNewDeal(@RequestBody Deal c) {
        Deal newDeal = (Deal) dealRepository.save(c);

        return newDeal.getId();
    }

    @PostMapping("/{id}")
    public ResponseEntity postById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity putOneDeal(@PathVariable int id, @RequestBody Deal c) {
        if (dealRepository.existsById(id)) {
            c.setId(id);
            dealRepository.save(c);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping
    public List<Deal> allDealsUpdater(@RequestParam List<Deal> dealsArrey) {
        dealRepository.deleteAll();
        dealsArrey.stream().forEach(deal -> dealRepository.save(deal));
        return toDoList();
    }

    @DeleteMapping
    public void deleteAllDeals() {
//        DealsList.removeAllDeals();
        dealRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        if (!dealRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            dealRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}

