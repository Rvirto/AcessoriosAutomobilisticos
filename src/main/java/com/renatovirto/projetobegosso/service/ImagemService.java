package com.renatovirto.projetobegosso.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.renatovirto.projetobegosso.model.Imagem;
import com.renatovirto.projetobegosso.model.Produto;
import com.renatovirto.projetobegosso.repository.ImagemRepository;

@Service
public class ImagemService {
	
	@Autowired
	private ImagemRepository imagemRepository;

	public boolean salvarArquivo(MultipartFile arquivo, Long idViagem) {
		boolean retorno = false;
		if ("image/png".equals(arquivo.getContentType()) || "image/jpg".equals(arquivo.getContentType())
				|| "image/jpeg".equals(arquivo.getContentType())) {
			Path diretorioRaiz = Paths.get("/home/renato/Documentos/Faculdade/Begosso/2 Semestre/Desenvolvimento Angular/AcessoriosAutomobilisticos/src/assets");

			Path arquivoPath = diretorioRaiz.resolve(arquivo.getOriginalFilename());

			try {
				arquivo.transferTo(arquivoPath.toFile());
				salvarImagem(idViagem, arquivo);
				
			} catch (Exception e) {
				throw new RuntimeException("Erro ao salvar o arquivo", e);
			}
			retorno = true;
		}
		return retorno;
	}
	
	public void salvarImagem(Long idProduto, MultipartFile arquivo) {
		Imagem imagem = new Imagem();
		Produto produto = new Produto();
		produto.setId(idProduto);
		imagem.setNome(arquivo.getOriginalFilename());
		imagem.setProduto(produto);
		imagemRepository.save(imagem);
	}
}
