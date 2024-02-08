package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.Comentario;
import org.iesvdm.videoclub.domain.Tutorial;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
        // si lo quitamos el proxy, no puede accerder a los datos pq la sesión esta cerrada
        // si fuesemos con eager, no va a haber proxy intermediario
    void pruebaRepository() {
        var lista = tutorialRepository.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    void onetomayTest() {
        Tutorial tutoNormal = new Tutorial();

        Tutorial tuto = Tutorial.builder()
                .titulo("tutorial")
                .publicado(true)
                .descripcion("no lo sé")
                .fechaPublicacion(new Date())
                .comentarios(new HashSet<>())
                .build();

        Comentario coment = Comentario.builder()
                .texto("comentario1")
                .build();

        Comentario coment2 = Comentario.builder()
                .texto("comentario1")
                .build();

        tuto.addComentario(coment);
        tutorialRepository.save(tuto);

        tuto.addComentario(coment2);
        tutorialRepository.flush();
    }

    @Test
    @Transactional
    void priebaBorrar() {
        var optionalTutorial = this.tutorialRepository.findById((long) 1);

        optionalTutorial.ifPresent(tutorial -> {
            tutorial.getComentarios().forEach(System.out::println);
            var optionalComentario = tutorial.getComentarios().stream().findFirst();

            tutorial.removeComentario(optionalComentario.get());
            this.tutorialRepository.save(tutorial);
        });

        this.tutorialRepository.delete(optionalTutorial.get());
        this.tutorialRepository.flush(); // cierra y haz las acciones
    }

}
