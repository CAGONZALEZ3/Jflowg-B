package com.Jflowg.JflowgB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "photos")
public class Images {

    @Id
    @Column(name ="id")
    private int idimg;

    @Column(name ="path")
    private String pathimg;

    @Column(name ="name")
    private String nameimg;
    
}
