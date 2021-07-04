package main.model;

import javax.persistence.*;

@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    private String name;

    Deal() {
    }

    Deal(String name) {
        this.name = name;
        id = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
