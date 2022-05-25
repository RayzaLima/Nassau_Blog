package com.nassau.blog.service.persistence;
import com.nassau.blog.model.Artigo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ArtigoPersistence extends CrudRepository<Artigo, Integer>{
    
    // serve para limitar quantos arquivos vao aparecer
    Page<Artigo> findAll(Pageable pageable);
}
