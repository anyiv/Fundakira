$('.ui .dropdown')
  .dropdown()
;

$('.ui.accordion')
  .accordion()
;

$(document).ready(function () {
  var tabla = $('#tabla').DataTable({
    "language": {
      "sProcessing": "Procesando...",
      "sLengthMenu": "Mostrar _MENU_ registros",
      "sZeroRecords": "No se encontraron resultados",
      "sEmptyTable": "Ningún dato disponible en esta tabla =(",
      "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
      "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
      "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
      "sInfoPostFix": "",
      "sSearch": "Buscar:",
      "sUrl": "",
      "sInfoThousands": ",",
      "sLoadingRecords": "Cargando...",
      "oPaginate": {
        "sFirst": "Primero",
        "sLast": "Último",
        "sNext": "Siguiente",
        "sPrevious": "Anterior"
      },
      "oAria": {
        "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
      },
      "buttons": {
        "copy": "Copiar",
        "colvis": "Visibilidad"
      }
    }
  });
  
  $('#filtroSolicitudes').on('change', function () {
    if (this.value == 'Todos') {
        tabla.search('').draw();
    } else {
        tabla.search(this.value).draw();
    }
});

});

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
          $("#crearbeneficiario").css("display", "");
          Swal.fire("Erorr", "El beneficiario no existe. Introduzca sus datos para registrarlo.", "error");
          $("#nombreB").val("");
          $("#apellidoB").val("");
          $("#direccionB").val("");
          $("#telefonoB").val("");
          $("#correoB").val("");
          $("#lblcon").css("display", "")
          $("#contrasenna").css("display", "");
          $("#lblcc").css("display", "")
          $("#conf_contrasenna").css("display", "");
        } else {
          $("#nombreB").val(data.nombre);
          $("#apellidoB").val(data.apellido);
          $("#direccionB").val(data.direccion);
          $("#telefonoB").val(data.telefono);
          $("#correoB").val(data.correo);
          Swal.fire("Éxito", "El beneficiario " + data.nombre + " " + data.apellido + " ha sido encontrado.", "success");
        }
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log(xhr.status);
        console.log(xhr.responseText);
        console.log(thrownError);
        Swal.fire("Error", "Error desconocido en el servidor.", "error");
      }
    });
  }
}

function confirmarCreacionBeneficiario() {
  Swal.fire({
    title: 'Confirmar creación',
    text: "¿Seguro que deseas crear el beneficiario?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Incluir',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      if( $('#contrasenna').val() == $('#conf_contrasenna').val() ){
      $('#formBeneficiario').submit();
    }else{
      Swal.fire("Error", "Las contraseñas no coinciden.", "error");
    }
    }
  })
};
