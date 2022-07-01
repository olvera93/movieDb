# MovieDB


## Se uso lo siguiente: 
* Uso de arquitectura MVVM
* Uso de Rx
* Uso de ViewBinding
* Uso de Navigation
* Uso de LiveData
* Uso de Room
* Uso de ViewModelProvider
* Scope functions
* Hace null la instancia de viewbinding
* Librería lottie para algunas animaciones
* Animaciones para el cambio de pantalla
* Librería Toasty para darle un aspecto diferente a los Toast habituales


### Se realizaron dos clases abstractas para los fragments BaseFragment y BaseViewModelFragment y son de las buenas prácticas a la hora de escribir código es la “Reusabilidad”. En lugar de escribir el mismo código varias veces, deberíamos escribirlo una vez y usarlo donde sea necesario. ¡Simple como eso!


## 😔 Cosas que faltaron 
* Fue realizar pruebas unitarias de los endpoints: en este caso se tenía que hacer una test sobre los viewModels para comprobar que si funciona correctamente los endpoints en obtener la información. Lo unico que realice fueron pruebas unitarias pero fue sobre el repository para comprobar el insertado en la base de datos.
* Fue mostrar las películas en modo offline: lo que se tiene que hacer es guardar todos los datos que contiene la película en una base de datos local y luego hacer una consulta para obtener esos mismos datos y mostrarlos. Porque lo único que realice fue guardar las películas que le gusto al usuario, es decir, el usuario puede cerrar la aplicación y la vuelve abrir y esas series o películas siguen en sus favoritos.

