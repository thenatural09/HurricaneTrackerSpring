package com.company;

import javax.persistence.*;

/**
 * Created by Troy on 10/21/16.
 */
@Entity
@Table(name = "hurricanes")
public class Hurricane {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    Integer category;

    @Column(nullable = false)
    String image;

    public Hurricane(String name, String location, Integer category, String image) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.image = image;
    }

    public Hurricane() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
