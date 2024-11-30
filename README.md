-CORRECCIONES DEL SISTEMA  DE TICKETS PARA VANESA (29-11-24)

- Se corrigo la estructura y algunos errores en la API de PHP. 
- Se corrigieron algunos detalles minimos en las dependencias ( sintaxis )
- Se mantiene la ruta de acceso original del archivo login2.php ( C:\xampp\htdocs\TicketAM )
- Recordar que la IP local, varia segun la configuracion del sistema.<br> 
Se puede verificar esto, ejecutando en la linea de comando ( cmd ) como administrador y luego ipconfig y presionar ENTER <br>
Tomar la ip que figura y reemplazarla en el BASE URL del archivo RetrofitClient.kt ( se adjunta imagen ) <br>
![Base URL](https://github.com/user-attachments/assets/678bd4e9-4ff5-44b3-b9ec-32ab2b882cdd)

- Se adjunta video del programa funcionando su Login con base de datos, donde se prueba login de usuarios ya pre cargados y usuarios incorrectos.
se debe implementar toda la logica de las vistas, y la correcion del metodo de "cambio de contraseña obligatorio". <br>


https://github.com/user-attachments/assets/4aa821ee-e8b2-4b2e-90d5-80013e2c5023


- Se adjunta el nuevo archivo corregido login2.php

https://www.mediafire.com/file/yt8nrteq5amzpbr/login2.php/file

- Se adjunta script de base de datos con la tabla usuarios cargada y funcional.
  https://www.mediafire.com/file/j12fsdytnsqrek1/usuarios.sql/file <br>

  - Procedimiento para IMPORTAR el script desde panel de PHPMyAdmin <br>
  
https://github.com/user-attachments/assets/92bbbb39-722b-476e-91d2-ab28b7a69308






