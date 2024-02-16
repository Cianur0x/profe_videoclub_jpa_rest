import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PeliculaService} from "../pelicula.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  form: FormGroup = new FormGroup({
    titulo: new FormControl('', []),
    fechaActualizacion: new FormControl(),
    idIdioma: new FormControl(0, [])
  });

  constructor(
    public peliculaService: PeliculaService,
    private router: Router
  ) {
  }

  ngOnInit(): void {

  }

  get f() {
    return this.form.controls;
  }

  submit() {
    console.log(this.form.value);
    this.peliculaService.create(this.form.value).subscribe(res => {
      console.log('Película creada correctamente! + res');
      this.router.navigateByUrl('pelicula/index').then();
    })
  }

}
