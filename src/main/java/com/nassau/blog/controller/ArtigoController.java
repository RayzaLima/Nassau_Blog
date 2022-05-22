package com.nassau.blog.controller;

import com.nassau.blog.dto.ArtigoDTO;
import com.nassau.blog.model.Artigo;
import com.nassau.blog.service.ArtigoLocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/blog")
public class ArtigoController {
    
    @PostMapping(path = "/artigo")
    public @ResponseBody Artigo adicionarArtigo(@RequestBody ArtigoDTO artigoDTO) {
        Artigo artigo = new Artigo();

        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setTexto(artigoDTO.getTexto());

        return artigoLocalService.criarArtigo(artigo);
    }

    @Autowired
    private ArtigoLocalService artigoLocalService;
}
