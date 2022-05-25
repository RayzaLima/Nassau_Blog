package com.nassau.blog.controller;

import com.nassau.blog.dto.ArtigoDTO;
import com.nassau.blog.exception.ArtigoInexistenteException;
import com.nassau.blog.helper.ArtigoHelper;
import com.nassau.blog.model.Artigo;
import com.nassau.blog.service.ArtigoLocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/blog")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtigoController {

    @PostMapping(path = "/artigo")
    public @ResponseBody Artigo adicionarArtigos(@RequestBody ArtigoDTO artigoDTO) {
        Artigo artigo = new Artigo();

        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setData(ArtigoHelper.converterStringParaDate(artigoDTO.getData()));
        artigo.setTexto(artigoDTO.getTexto());

        return artigoLocalService.criarArtigo(artigo);
    }

    @GetMapping(path = "/artigo")
    public @ResponseBody Page<Artigo> buscarArtigo(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "5") String size) {

        Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));

        return artigoLocalService.buscarArtigo(paging);
    }

    @GetMapping(path = "/artigo/{id}")
    public @ResponseBody Object buscarArtigoPeloId(@PathVariable("id") String id) {

        try {

            return artigoLocalService.buscarArtigoPeloId(Integer.parseInt(id));

        } catch (ArtigoInexistenteException e) {

            return e.getMessage();
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

        } catch (ArtigoInexistenteException e) {

            return e.getMessage();
        }
    }

    @DeleteMapping(path = "/artigo/{id}")
    public @ResponseBody String deletarArtigo(@PathVariable("id") String id) {
        try {
            artigoLocalService.deletarArtigo(Integer.parseInt(id));

            return "Artigo deletado com sucesso";

        } catch (ArtigoInexistenteException e) {

            return e.getMessage();
        }
    }

    @Autowired
    private ArtigoLocalService artigoLocalService;
}
