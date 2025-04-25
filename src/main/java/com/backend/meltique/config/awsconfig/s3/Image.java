package com.backend.meltique.config.awsconfig.s3;

import com.backend.meltique.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileUrl;
    private String contentType;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
