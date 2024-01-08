package br.com.sunshine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "name")
	@NotBlank(message = "O nome não pode ser vazio")
	private String name;
	
	@Column(name = "email", unique = true)
	@NotBlank(message = "O email não pode ser vazio")
	private String email;
	
	@Column(name = "senha")
	@NotBlank(message = "A senha não pode ser vazia")
	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	private String tokenAccess;
}
