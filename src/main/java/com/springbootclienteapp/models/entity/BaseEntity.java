package com.springbootclienteapp.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter @Setter
@NoArgsConstructor
@MappedSuperclass()
public class BaseEntity {

	  @Id
	  @Column(name = "id", nullable = false, insertable = false)
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
}
