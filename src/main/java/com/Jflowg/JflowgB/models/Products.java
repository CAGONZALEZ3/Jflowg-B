package com.Jflowg.JflowgB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "jf_product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private long id;

    @Column(name="PRO_TIP_ID")
    private long p_t_i;

    @Column(name="PRO_NAME")
    private String name;

    @Column(name="PRO_DESCRIPTION")
    private String description;

    @Column(name="PRO_IMAGE")
    private int image;

    @Column(name="PRO_CANTIDAD")
    private int cantidad;

    @Column(name = "PRO_PRECIO")
    private double precio;

    @Column(name ="PRO_DESCUENTO")
    private char descuento;
    

}
