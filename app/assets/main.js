$('.ui .dropdown')
    .dropdown();

$('.ui.accordion')
    .accordion();

$('.item')
    .popup({
        inline: true,
        hoverable: true
    })
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

    $('#responsables').on('change', function () {
        if (this.value == '') {
            tabla.search('').draw();
        } else {
            tabla.search(this.value).draw();
        }
    });

    $('#estatus_pre').on('change', function () {
        if (this.value == 'Todos') {
            tabla.search('').draw();
        } else {
            tabla.search(this.value).draw();
        }
    });

});
//Funciones para las Fundaciones
function enviarFormularioFundacion() {
    if (document.getElementById('cod_fundacion') != null) {
        confirmarModifFundacion();
    } else {
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

//Funciones para los servicios
function enviarFormularioServicio() {
    if (document.getElementById('codServicio') != null) {
        confirmarModifServicio();
    } else {
        confirmarCreacionServicio();
    }
};

function confirmarCreacionServicio() {
    Swal.fire({
        title: 'Confirmar inclusión',
        text: "¿Seguro que deseas incluir el servicio?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Incluir',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            $('#formServicio').submit();
        }
    })
};

function confirmarModifServicio() {
    Swal.fire({
        title: 'Confirmar modificación',
        text: "¿Seguro que deseas modificar el servicio?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Modificar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            $('#formServicio').submit();
        }
    })
};

function confirmarEliminarServicio(id, codF) {
    Swal.fire({
        title: 'Confirmar eliminación',
        text: "¿Seguro que deseas eliminar el servicio?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            location.href = "/eliminarS/?id=" + id + codF;
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
            .transition('fade');
    });

$('.trigger.example .accordion')
    .accordion({
        selector: {
            trigger: '.title .icon'
        }
    });

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
            if ($('#contrasenna').val() == $('#conf_contrasenna').val()) {
                $('#formBeneficiario').submit();
            } else {
                Swal.fire("Error", "Las contraseñas no coinciden.", "error");
            }
        }
    })
};

//FUNCIONES PARA LOS EMPLEADOS
function confirmarCreacionEmpleado() {
    Swal.fire({
        title: 'Confirmar creación',
        text: "¿Seguro que deseas incluir al empleado?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Incluir',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            if ($('#fundacion').val() != '0') {
                if ($('#contrasenna').val() == $('#conf_contrasenna').val()) {
                    $('#fromEmpleado').submit();
                } else {
                    Swal.fire("Error", "Las contraseñas no coinciden.", "error");
                }
            } else {
                Swal.fire("Error", "Las debe seleccionar una fundación.", "error");
            }
        }
    })
};

function confirmarEliminarEmpleado(id) {
  Swal.fire({
    title: 'Confirmar eliminación',
    text: "¿Seguro que deseas eliminar al empleado?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Eliminar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      location.href = "/eliminar_empleado/?id=" + id;
    }
  })
};

function confirmarModificarEmpleado() {
  Swal.fire({
    title: 'Confirmar modificación',
    text: "¿Seguro que deseas modificar los datos del empleado?",
    type: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Modificar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {
    if (result.value) {
      $('#formEmpleado').submit();
    }
  })
};

function guardarSolicitud() {
    var cedulab = $("#cedulaB").val();
    var servicios = [];
    $("#servicios").val().forEach(function (servicio, indice, array) {
        servicios.push(servicio);
    });
    var pri = document.querySelector('input[name="prioridad"]:checked').value;
    var otradona = $("#otrasDonaciones").val();
    var razon = $("#razon").val();
    if (cedulab == '' || $('select[id=servicios] option:selected').length == 0) {
        Swal.fire("Error", "Debe llenar el formulario completo.", "warning")
    } else {
        var token = $('input[name="csrfToken"]').attr('value')
        $.ajaxSetup({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Csrf-Token', token);
            }
        });
        var data = {
            'cedulab': cedulab,
            'servicios': servicios,
            'prioridad': pri,
            'otradonac': otradona,
            'razon': razon
        };
        console.log(data);
        $.ajax({
            url: '/ajax/crearSolicitud/',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'JSON',
            success: function (data) {
                Swal.fire("Respuesta", data.resultado, "success");
                $("#cedulaB").val("");
                $("#nombreB").val("");
                $("#apellidoB").val("");
                $("#direccionB").val("");
                $("#telefonoB").val("");
                $("#correoB").val("");
                $("#servicios").val("");
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

function confirmarCreacionSolicitud() {
    Swal.fire({
        title: 'Confirmar modificación',
        text: "¿Seguro que deseas crear la solicitud?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Crear',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            guardarSolicitud();
        }
    })
};

function confirmarAprobarSolicitud(id) {
    Swal.fire({
        title: 'Confirmar actualización',
        text: "¿Seguro que deseas aprobar la solicitud?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Aprobar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            location.href = "/solicitud/aprobar/" + id;
        }
    })
};

function confirmarNegarSolicitud(id) {
    Swal.fire({
        title: 'Confirmar actualización',
        text: "¿Seguro que deseas negar la solicitud?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Negar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            location.href = "/solicitud/rechazar/" + id;
        }
    })
};

function generarReporteSolicitantes(){
    if($("#fundaciones_rs").val()!=""){
        window.location.replace("/reporte/rsolicitantantes/" + $("#fundaciones_rs").val());
    } else {
        Swal.fire("Error","Seleccione una fundación para consultar.","error");
    }
}

function calcularPresupuesto() {
    var fundacion = $("#fundacion").val();
    if (fundacion == '') {
        Swal.fire("Error", "Debe introducir una fundación.", "warning")
    } else {
        var token = $('input[name="csrfToken"]').attr('value')
        $.ajaxSetup({
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Csrf-Token', token);
            }
        });
        var data = { 'fundacion': fundacion };
        $.ajax({
            url: '/ajax/calcularPresup/',
            contentType: 'application/json',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'JSON',
            success: function (data) {
                $("#bs").val(data.disponible);
                $("#porcentaje").val(data.porcgastado);
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
