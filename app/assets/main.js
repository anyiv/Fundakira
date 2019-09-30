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
      title: '¿Seguro que deseas incluir la fundación?',
      text: "",
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
      title: '¿Seguro que deseas modificar la fundación?',
      text: "",
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
      title: '¿Seguro que deseas eliminar la fundación?',
      text: "",
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

function imprimirMensaje(mensaje) {
  Swal.fire({
      title: "Éxito",
      text: mensaje,
      type: 'success',
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

$('.trigger.example .accordion')
  .accordion({
    selector: {
      trigger: '.title .icon'
    }
  })
;