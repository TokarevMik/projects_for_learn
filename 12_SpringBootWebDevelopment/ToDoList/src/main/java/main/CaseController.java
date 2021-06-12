package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class CaseController {
    @GetMapping
    public List<Case> toDoList() {
        return CasesList.getToDoList();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCaseFromList(@PathVariable int id) {
        Case currentCase = CasesList.getCase(id);
        if (currentCase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(currentCase, HttpStatus.OK);
    }

    @PostMapping
    public int postNewCase(@RequestBody Case c) {
        return CasesList.adCase(c);
    }

    @PostMapping("/{id}")
    public ResponseEntity postById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity putOneCase(@PathVariable int id,@RequestBody Case c) {
        if (CasesList.containCase(id)) {
            CasesList.toDoListUpdater(id, c);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping
    public List<Case> allCasesUpdater(@RequestParam List<Case> casesArrey) {
        CasesList.removeAllCases();
        casesArrey.stream().forEach(CasesList::adCase);
        return CasesList.getToDoList();
    }

    @DeleteMapping
    public void deleteAllCases() {
        CasesList.removeAllCases();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        if (!CasesList.containCase(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            CasesList.removeCase(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }
}

