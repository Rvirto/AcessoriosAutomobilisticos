package com.renatovirto.projetobegosso.model;

import java.util.List;

import javax.persistence.Column;
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
@Table(name = "orcamentos")
public class Orcamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="orcamentos_produtos",
			joinColumns = @JoinColumn(name = "orcamento_id"),
			inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<ProdutoCarrinho> produtosCarrinho;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@Column(name = "valor_total")
	private Double valorTotal;
	
	private Double desconto;
	
	

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
		Orcamento other = (Orcamento) obj;
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

	public List<ProdutoCarrinho> getProdutos() {
		return produtosCarrinho;
	}

	public void setProdutos(List<ProdutoCarrinho> produtos) {
		this.produtosCarrinho = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	
}
