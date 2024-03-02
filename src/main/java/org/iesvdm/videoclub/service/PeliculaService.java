package org.iesvdm.videoclub.service;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    private final IdiomaRepository idiomaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository, IdiomaRepository idiomaRepository) {
        this.peliculaRepository = peliculaRepository;
        this.idiomaRepository = idiomaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public List<Pelicula> ordenCampos(String[] camposArray) {
        if (camposArray.length == 2 && !camposArray[0].contains(",") && !camposArray[1].contains(",")) {
            log.info("OPCION 1");
            // Considero que habrá dos campos en la query -> [campo, sentido], si no se devuelve error
            String campo = !camposArray[0].isEmpty() ? camposArray[0] : ""; // campo a buscar
            String orden = camposArray[1].length() > 1 ? camposArray[1] : ""; // orden ASC o DESC

            if (orden.equals("asc")) {
                return this.peliculaRepository.findAll(Sort.by(campo).ascending());
            } else if (orden.equals("desc")) {
                return this.peliculaRepository.findAll(Sort.by(campo).descending());
            }
        } else if (camposArray.length >= 2) {

            List<Order> orderList = getOrders(camposArray);
            log.info("OPCION 2 " + orderList.size());
            return this.peliculaRepository.findAll(Sort.by(orderList));
        }

        log.info("OPCION 3");
        return this.peliculaRepository.findAll();
    }

    private static List<Order> getOrders(String[] camposArray) {
        List<Order> orderList = new ArrayList<>();
        for (String str : camposArray) {
            String[] campoSentido = str.split(",");
            String campo = campoSentido[0];
            String orden = campoSentido[1];
            Order order;

            if (orden.equals("desc")) {
                order = new Order(Sort.Direction.DESC, campo);
            } else {
                order = new Order(Sort.Direction.ASC, campo);
            }
            orderList.add(order);
        }
        return orderList;
    }

    public Map<String, Object> paginado(String[] paginado) {
        int pagina = Integer.parseInt(paginado[0]);
        int tamanio = Integer.parseInt(paginado[1]);

        Pageable paginacion = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").ascending());
        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginacion);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Pelicula save(Pelicula pelicula) {
        idiomaRepository.findById(pelicula.getIdIdioma()).ifPresentOrElse(pelicula::setIdioma, () -> new Exception("No existe ese idioma"));
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map(p -> (id.equals(pelicula.getIdPelicula()) ?
                        this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {
            this.peliculaRepository.delete(p);
            return p;
        }).orElseThrow(() -> new PeliculaNotFoundException(id));
    }
}
