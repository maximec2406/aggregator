package ru.levelp.myapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulbas on 06.05.2018.
 */
@Entity
public class Supplier {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @OneToMany( mappedBy = "supplier", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY) // при вызове supplier не будет подгружаться весь сприсок parts
    private List<Part> parts = new ArrayList<Part>();          // иначе ставь EAGER

    public Supplier () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != supplier.id) return false;
        return name != null ? name.equals(supplier.name) : supplier.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
