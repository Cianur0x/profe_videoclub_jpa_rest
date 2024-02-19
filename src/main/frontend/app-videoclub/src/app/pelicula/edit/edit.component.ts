import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Pelicula} from "../pelicula.interface";
import {PeliculaService} from "../pelicula.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  id: number = 0;
  pelicula: Pelicula = {
    idPelicula: 0,
    idioma: {id: 0, nombre: "", ultimaActualizacion: "1970/01/01"},
    titulo: "",
    descripcion: "",
    ultimaActualizacion: "1970/01/01"
  };
  form: FormGroup = new FormGroup({
    titulo: new FormControl('', [Validators.required]),
    idPelicula: new FormControl('', [Validators.required]),
    idIdioma: new FormControl('', [Validators.required]),
    ultimaActualizacion: new FormControl("1970/01/01", [])
  });

  constructor(
    public peliculaService: PeliculaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['idPelicula'];
    this.peliculaService.find(this.id).subscribe((data: Pelicula) => {
      this.pelicula = data;
      this.pelicula.idioma = data.idioma;
      this.form.get('idPelicula')?.setValue(this.pelicula.idPelicula);
      this.form.get('titulo')?.setValue(this.pelicula.titulo);
      this.form.get('idIdioma')?.setValue(this.pelicula.idioma.id);
      this.form.get('ultimaActualizacion')?.setValue(this.pelicula.ultimaActualizacion);
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    this.peliculaService.update(this.id, this.form.value).subscribe(res => {
      console.log('Película actualizada satisfactoriamenxte!');
      this.router.navigateByUrl('pelicula/index').then();
    })
  }

}
