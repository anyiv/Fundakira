$('#select')
  .dropdown()
  ;

function enviarFormularioFundacion() {
  if (document.getElementById('cod_fundacion') != null) {
    confirmarModifFundacion();
  }
  else {
    confirmarCreacionFundacion();
  }
};

function confirmarCreacionFundacion() {
  Swal.fire({
    title: 'Confirmar inclusión',
    text: "¿Seguro que deseas incluir la fundación?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Incluir',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      $('#formFundacion').submit();
    }
  })
};

function confirmarModifFundacion() {
  Swal.fire({
    title: 'Confirmar modificación',
    text: "¿Seguro que deseas modificar la fundación?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Modificar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      $('#formFundacion').submit();
    }
  })
};

function confirmarEliminarFundacion(id) {
  Swal.fire({
    title: 'Confirmar eliminación',
    text: "¿Seguro que deseas eliminar la fundación?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Eliminar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      location.href = "/eliminarF/?id=" + id;
    }
  })
};

function cerrarSesion() {
  Swal.fire({
    title: 'Confirmar cierre de sesión',
    text: "¿Seguro que deseas cerrar sesión?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí',
    cancelButtonText: 'No'
  }).then((result) => {
    if (result.value) {
      location.href = "/logout/";
    }
  })
};

function imprimirMensaje(mensaje) {
  Swal.fire({
    title: "Éxito",
    text: mensaje,
    type: 'success',
    showCancelButton: false
  })
};

function imprimirError(mensaje) {
  Swal.fire({
    title: "Error",
    text: mensaje,
    type: 'error',
    showCancelButton: false
  })
};

$('.message .close')
  .on('click', function () {
    $(this)
      .closest('.message')
      .transition('fade')
      ;
  })
  ;

$('.trigger.example .accordion')
  .accordion({
    selector: {
      trigger: '.title .icon'
    }
  })
  ;

function validarIdentidad() {
  var cedulab = $("#cedulaB").val();
  if (cedulab == '') {
    Swal.fire("Error", "Debe introducir una Cédula de Identidad.", "warning")
  } else {
    var token = $('input[name="csrfToken"]').attr('value')
    $.ajaxSetup({
      beforeSend: function (xhr) {
        xhr.setRequestHeader('Csrf-Token', token);
      }
    });
    var data = { 'cedulab': cedulab };
    $.ajax({
      url: '/ajax/validarIdentidad/',
      contentType: 'application/json',
      type: 'POST',
      data: JSON.stringify(data),
      dataType: 'JSON',
      success: function (data) {
        if (data.error == true) {
          $("#crearbeneficiario").css("display","");
          Swal.fire("Erorr", "El beneficiario no existe. Introduzca sus datos para registrarlo.", "error");
        } else {
          $("#nombreB").val(data.nombre);
          $("#apellidoB").val(data.apellido);
          $("#direccionB").val(data.direccion);
          $("#telefonoB").val(data.telefono);
          $("#correoB").val(data.correo);
          Swal.fire("Éxito","El beneficiario " + data.nombre + " " + data.apellido + " ha sido encontrado.","success");
        }
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
        console.log(xhr.responseText);
        console.log(thrownError);
        Swal.fire("Error", "Error desconocido en el servidor.","error");
      }
    });
  }
}

