	package com.renatovirto.projetobegosso.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carrinho")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="carrinho_produtos",
			joinColumns = @JoinColumn(name = "carrinho_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_carrinho_id"))
	private List<ProdutoCarrinho> produtoCarrinho;
	
	private String status;
	
	

	public Carrinho() {
		this.status = "A";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProdutoCarrinho> getProdutoCarrinho() {
		return produtoCarrinho;
	}

	public void setProdutoCarrinho(List<ProdutoCarrinho> produtoCarrinho) {
		this.produtoCarrinho = produtoCarrinho;
	}
	
	
}
