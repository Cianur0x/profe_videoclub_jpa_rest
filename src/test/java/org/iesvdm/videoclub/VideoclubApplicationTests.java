package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.*;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    IdiomaRepository idiomaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void GuardarManyToMany() {
        Idioma idioma = new Idioma(0L, "Inglés", null, new HashSet<>());
        idiomaRepository.save(idioma);
        Idioma idioma2 = new Idioma(0L, "Español", null, new HashSet<>());
        idiomaRepository.save(idioma2);

        Pelicula pelicula = new Pelicula(0, "X-Men", "", null, null, new HashSet<>(), null);
        pelicula.setIdioma(idioma);
        pelicula.setIdIdioma(idioma.getId());
        peliculaRepository.save(pelicula);
        Pelicula pelicula1 = new Pelicula(0, "Logan", "", null, null, new HashSet<>(), null);
        pelicula1.setIdIdioma(idioma2.getId());
        pelicula1.setIdioma(idioma2);
        peliculaRepository.save(pelicula1);

        Categoria categoria1 = new Categoria(0, "Acción", new HashSet<>(), null);
        Categoria categoria2 = new Categoria(0, "Ciencia Ficción", new HashSet<>(), null);
        categoriaRepository.save(categoria1);
        categoriaRepository.save(categoria2);
    }

//    @Test
//    @Transactional
//        // si lo quitamos el proxy, no puede accerder a los datos pq la sesión esta cerrada
//        // si fuesemos con eager, no va a haber proxy intermediario
//    void pruebaRepository() {
//        var lista = tutorialRepository.findAll();
//
//        lista.forEach(System.out::println);
//    }
//
//    @Test
//    void onetomayTest() {
//        Tutorial tutoNormal = new Tutorial();
//
//        Tutorial tuto = Tutorial.builder()
//                .titulo("tutorial")
//                .publicado(true)
//                .descripcion("no lo sé")
//                .fechaPublicacion(new Date())
//                .comentarios(new HashSet<>())
//                .build();
//
//        Comentario coment = Comentario.builder()
//                .texto("comentario1")
//                .build();
//
//        Comentario coment2 = Comentario.builder()
//                .texto("comentario1")
//                .build();
//
//        tuto.addComentario(coment);
//        tutorialRepository.save(tuto);
//
//        tuto.addComentario(coment2);
//        tutorialRepository.flush();
//    }
//
//    @Test
//    @Transactional
//    void priebaBorrar() {
//        var optionalTutorial = this.tutorialRepository.findById((long) 1);
//
//        optionalTutorial.ifPresent(tutorial -> {
//            tutorial.getComentarios().forEach(System.out::println);
//            var optionalComentario = tutorial.getComentarios().stream().findFirst();
//
//            tutorial.removeComentario(optionalComentario.get());
//            this.tutorialRepository.save(tutorial);
//        });
//
//        this.tutorialRepository.delete(optionalTutorial.get());
//        this.tutorialRepository.flush(); // cierra y haz las acciones
//    }

}
