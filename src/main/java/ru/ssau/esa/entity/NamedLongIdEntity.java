package ru.ssau.esa.entity;

import javax.persistence.*;

@MappedSuperclass
public class NamedLongIdEntity {
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name="NAME")
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
