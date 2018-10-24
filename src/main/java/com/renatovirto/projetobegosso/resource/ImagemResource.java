package com.renatovirto.projetobegosso.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renatovirto.projetobegosso.model.Imagem;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ImagemRepository;
import com.renatovirto.projetobegosso.service.ImagemService;

@RestController
@RequestMapping("/imagens")
public class ImagemResource {

	@Autowired
	private ImagemService imagemService;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@PutMapping("/upload/{idProduto}")
	public HttpStatus salvarImagem(@RequestParam MultipartFile arquivo, @PathVariable Long idProduto) {
		if(imagemService.salvarArquivo(arquivo, idProduto)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	@GetMapping("/{id}")
	public List<Imagem> buscarImagensDoProduto(@PathVariable Long id) {
		Produto produto = new Produto();
		produto.setId(id);
		return imagemRepository.findByProduto(produto);
	}
}
