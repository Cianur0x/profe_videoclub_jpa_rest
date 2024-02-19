import {Idioma} from "./Idioma.interface";

export interface Pelicula {
  idPelicula: number;

  titulo: string;

  descripcion: string;

  idioma: Idioma

  ultimaActualizacion: string;
}
