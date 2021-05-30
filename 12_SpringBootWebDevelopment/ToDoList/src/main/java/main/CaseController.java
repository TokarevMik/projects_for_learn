package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CaseController {
    @GetMapping("/cases")
    public List<Case> toDoList() {
        return CasesList.getToDoList();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity getCaseFromList(@PathVariable int id) {
        Case currentCase = CasesList.getToDoList().get(id);
        if (currentCase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(currentCase, HttpStatus.OK);
    }

    @PostMapping("/cases")
    public int postNewCase(@RequestParam String name) {
        Case c = new Case();
        c.setName(name);
        return CasesList.addCase(c);
    }

    @PostMapping("/cases/{id}")
    public ResponseEntity postById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/cases/{id}")
    public ResponseEntity putOneCase(@PathVariable int id, @RequestParam String name) {
        if (CasesList.containCase(id)) {
            CasesList.toDoListUpdater(id, name);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/cases")
    public List<Case> allCasesUpdater(@RequestParam List<Case> casesArrey) {
        CasesList.removeAllCases();
        casesArrey.stream().forEach(CasesList::addCase);
        return CasesList.getToDoList();
    }

    @DeleteMapping("/cases")
    public void deleteAllCases() {
        CasesList.removeAllCases();
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        if (!CasesList.containCase(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            CasesList.removeCase(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}

