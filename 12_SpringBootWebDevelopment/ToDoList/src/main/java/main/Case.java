package main;

public class Case {
    private String name;
    private int id;
    Case(){}
    Case(String name){
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
