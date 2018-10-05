package com.renatovirto.projetobegosso.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.renatovirto.projetobegosso.service.ImagemService;

@RestController
@RequestMapping("/imagens")
public class ImagemResource {

	@Autowired
	private ImagemService imagemService;
	
	@PutMapping("/upload/{idProduto}")
	public HttpStatus salvarImagem(@RequestParam MultipartFile arquivo, @PathVariable Long idProduto) {
		if(imagemService.salvarArquivo(arquivo, idProduto)) {
			return HttpStatus.OK;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
