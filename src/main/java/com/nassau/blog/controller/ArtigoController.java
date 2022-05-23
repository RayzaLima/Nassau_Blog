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
    public @ResponseBody Artigo adicionarArtigos(@RequestBody ArtigoDTO artigoDTO) {
        Artigo artigo = new Artigo();

        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setTexto(artigoDTO.getTexto());

        return artigoLocalService.criarArtigo(artigo);
    }
    
    @GetMapping(path = "/artigo")
    public @RequestBody Page<Artigo> buscarArtigos (
           @RequestParam(valeu = "page", defaultValue = "0") String page,
           @requestParam(value = "size", defaultValue = "0") String size) {
    
        Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        
        return artigoLocalService.buscarArtigos(paging);
    }
    
    @GetMapping(path = "/artigo/{id}")
    public @ResponseBody Object buscarArtigoPeloId(@PathVariable("id) String id) {
    
        try {
            return artigoLocalService.buscarArtigoPeloId(Integer.parseInt(id))
            } cath (ArtigoInexistenteException e) {
                return e.getMensage()
            }
    }
    
    @PutMapping(path = "/artigo/{id}")
    public @ResponseBody String atualizarArtigo(
            @PathVariable("id") String id, @RequestBody ArtigoDTO artigoDTO) {
        try {
            artigoLocalService.atualizarArtigo(Integer.parseInt(id), 
            artigoDTO.getTitulo(), artigoDTO.getAutor(),
            ArtigoHelper.converterStringParaDate(artigoDTO.getData()), artigoDTO.getTexto());
            
            return "Artigo Atualizado.";
       } cath (ArtigoInexistenteException e) {
                return e.getMensage()
       }
    }

    @Autowired
    private ArtigoLocalService artigoLocalService;
}
