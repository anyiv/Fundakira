$('#select')
  .dropdown()
;

function enviarFormularioFundacion() {
    if (document.getElementById('cod_fundacion') != null){
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
  .on('click', function() {
    $(this)
      .closest('.message')
      .transition('fade')
    ;
  })
;