package com.example.claudejavademo.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "customers")
class CustomerJpaEntity implements Persistable<String> {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private boolean isNew;

    protected CustomerJpaEntity() {}

    CustomerJpaEntity(String id, String name, String email, boolean isNew) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isNew = isNew;
    }

    @Override
    public String getId() { return id; }

    @Override
    public boolean isNew() { return isNew; }

    String getName()  { return name; }
    String getEmail() { return email; }
}
