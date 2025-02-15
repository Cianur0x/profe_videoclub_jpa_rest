import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CategoriaService} from "../categoria.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Categoria} from "../categoria";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  id: number = 0;
  categoria: Categoria = {id: 0, nombre: "VOID", ultimaActualizacion: "1970-01-01"};
  form: FormGroup = new FormGroup({
    id: new FormControl(0, [Validators.required]),
    nombre: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ \-\']+')]),
    ultimaActualizacion: new FormControl('', [Validators.required])
  });

  constructor(
    public categoriaService: CategoriaService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['idCategoria'];
    this.categoriaService.find(this.id).subscribe((data: Categoria) => {
      this.categoria = data;
      this.form.get('id')?.setValue(this.categoria.id);
      this.form.get('nombre')?.setValue(this.categoria.nombre);
      this.form.get('ultimaActualizacion')?.setValue(this.categoria.ultimaActualizacion);
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {

    this.categoriaService.update(this.id, this.form.value).subscribe(res => {
      console.log('Categoría actualizada satisfactoriamenxte!');
      this.router.navigateByUrl('categoria/index').then();
    })
  }
}
