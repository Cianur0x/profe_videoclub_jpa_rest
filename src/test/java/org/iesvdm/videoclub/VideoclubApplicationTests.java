package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

//    @Test
//    void crearVideoclub() {
//        Idioma idioma1 = new Idioma();
//        idioma1.setNombre("Inglés");
//        idiomaRepository.save(idioma1);
//
//        Categoria cat1 = new Categoria();
//        cat1.setNombre("Ciencia Ficción");
//        categoriaRepository.save(cat1);
//
//        Categoria cat2 = new Categoria();
//        cat2.setNombre("Acción");
//        categoriaRepository.save(cat2);
//
//        Categoria cat3 = new Categoria();
//        cat3.setNombre("Fantasía");
//        categoriaRepository.save(cat3);
//
//        Set<Categoria> categoriasPeli1 = new HashSet<>();
//        categoriasPeli1.add(cat1);
//        categoriasPeli1.add(cat3);
//
//        Pelicula peli1 = new Pelicula();
//        peli1.setTitulo("X-Men");
//        peli1.setIdioma(idioma1);
//        peli1.setCategorias(categoriasPeli1);
//        peliculaRepository.save(peli1);
//
//        Set<Categoria> categoriasPeli2 = new HashSet<>();
//        categoriasPeli2.add(cat2);
//        categoriasPeli2.add(cat1);
//        Pelicula peli2 = new Pelicula();
//        peli2.setTitulo("Logan");
//        peli2.setIdioma(idioma1);
//        peli2.setCategorias(categoriasPeli2);
//        peliculaRepository.save(peli2);
//    }

    @Test
    void crearVideoclub2() {

        Idioma idioma1 = new Idioma();
        idioma1.setNombre("Inglés");
        idiomaRepository.save(idioma1);

        Idioma idioma2 = new Idioma();
        idioma2.setNombre("Español Castellano");
        idiomaRepository.save(idioma2);

        Idioma idioma3 = new Idioma();
        idioma3.setNombre("Español Latino");
        idiomaRepository.save(idioma3);

        Categoria cat1 = new Categoria();
        cat1.setNombre("Ciencia Ficción");
        cat1.setUltimaActualizacion(new Date(2024 - 1900, 2, 29, 0, 0, 0));
        categoriaRepository.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNombre("Acción");
        categoriaRepository.save(cat2);

        Categoria cat3 = new Categoria();
        cat3.setNombre("Fantasía");
        categoriaRepository.save(cat3);

        Categoria cat4 = new Categoria();
        cat4.setNombre("Animación");
        cat4.setUltimaActualizacion(new Date(2024 - 1900, 2, 29, 0, 0, 0));
        categoriaRepository.save(cat4);

        Set<Categoria> catsPeli1 = new HashSet<>();
        catsPeli1.add(cat1);

        Pelicula peli1 = new Pelicula();
        peli1.setTitulo("X-Men");
        peli1.setDescripcion("Magneto kidnaps Marie, a young mutant, with the intention of using her powers to destroy humanity. However, the X-Men and Wolverine team up to save her and stop Magneto.");
        peli1.setIdioma(idioma1);
        peli1.setCategorias(catsPeli1);
        peliculaRepository.save(peli1);

        Set<Categoria> catsPeli2 = new HashSet<>();
        catsPeli2.add(cat1);
        catsPeli2.add(cat2);

        Pelicula peli2 = new Pelicula();
        peli2.setTitulo("Logan");
        peli2.setDescripcion("Logan comes out of retirement to escort a young mutant named Laura to a safe place. He meets with other mutants, who run from an evil corporation that has been experimenting with them, along the way.");
        peli2.setIdioma(idioma1);
        peli2.setCategorias(catsPeli2);
        peliculaRepository.save(peli2);

        Set<Categoria> catsPeli3 = new HashSet<>();
        catsPeli3.add(cat3);
        catsPeli3.add(cat4);

        Pelicula peli3 = new Pelicula();
        peli3.setTitulo("Kung Fu Panda 4");
        peli3.setDescripcion("After Po is tapped to become the Spiritual Leader of the Valley of Peace, he needs to find and train a new Dragon Warrior, while a wicked sorceress plans to re-summon all the master villains whom Po has vanquished to the spirit realm.");
        peli3.setIdioma(idioma3);
        peli3.setCategorias(catsPeli3);
        peliculaRepository.save(peli3);

        Set<Categoria> catsPeli4 = new HashSet<>();
        catsPeli4.add(cat1);
        catsPeli4.add(cat2);

        Pelicula peli4 = new Pelicula();
        peli4.setTitulo("Dune II");
        peli4.setDescripcion("Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the universe, he must prevent a terrible future only he can foresee.");
        peli4.setIdioma(idioma2);
        peli4.setCategorias(catsPeli4);
        peliculaRepository.save(peli4);

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
