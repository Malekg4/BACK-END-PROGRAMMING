package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "divisions")
@Getter
@Setter

public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "division_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, insertable=false, updatable=false)
    private Country country;

    @Column(name = "division")
    private String division_name;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "division")
    private Set<Customer> customers = new HashSet<>();

    @Column(name = "Country_id")
    private long country_id;
    public void setCountry(Country country){
        setCountry_id(country.getId());
        this.country = country;
    }

}
