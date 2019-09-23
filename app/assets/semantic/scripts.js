function confirmarCierreSesion() {
    Swal.fire({
        title: '¿Seguro que deseas cerrar sesión?',
        text: "Tus ítem guardados en el carrito de compras no se perderán.",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Cerrar sesión',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            location.href = "/logout/"
        }
    })
};

function confirmarCreacion() {
    formulario = document.getElementById('sign_up').elements;

    for (var i = 0, element; element = formulario[i++];) {
        if (element.id != "id_preferences" && element.id != "boton" && element.value == ""){
            return;
        }
    }

    Swal.fire({
        title: "Confirmar creación",
        text: "¿Está seguro que desea crear el usuario?",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#58a05d",
        cancelButtonColor: '#d33',
        confirmButtonText: "Sí",
        cancelButtonText: "Cancelar",
    }).then((result) => {
        if (result.value) {
            document.getElementById('sign_up').submit();
        }
    });
};

function Consultar(codigo) {
    var token = $('input[name="csrfmiddlewaretoken"]').val();
    $.ajax({
        url: 'ajax/getarticle/',
        type: 'POST',
        data: {
            'codigo': codigo,
            'csrfmiddlewaretoken': token
        },
        dataType: 'json',
        success: function(data) {
            Swal.fire({
                title: data.nombre,
                html: '<img class="ui centered circular small image" src="' + data.image + '"><br/>' +
                    '<strong>Nombre: </strong>' + data.nombre + '<br/>' +
                    '<strong>Descripción: </strong>' + data.descripcion + '<br/>' +
                    '<strong>Precio: </strong>' + data.precio,
                showConfirmButton: true,
                confirmButtonText: 'Añadir al Carrito',
                showCancelButton: true,
                cancelButtonText: 'Salir'
            }).then((result) => {
                if (result.value) {
                    $.ajax({
                        url: 'ajax/addarticle/',
                        type: 'POST',
                        data: {
                            'codigo': codigo,
                            'csrfmiddlewaretoken': token
                        },
                        dataType: 'json',
                        success: function(data) {
                            if (data['exito'] == true) {
                                Swal.fire({
                                    title: 'Éxito en el guardado',
                                    text: "El ítem ha sido guardado en el carrito" +
                                        " con éxito. Puedes seguir comprando!",
                                    type: 'success',
                                    showCancelButton: true,
                                    confirmButtonText: 'Ir al Carrito',
                                    cancelButtonText: 'Seguir Comprando',
                                    cancelButtonColor: 'teal'
                                }).then((result) => {
                                    if (result.value) {
                                        location.href = "/cart/"
                                    }
                                })
                            } else {
                                Swal.fire({
                                    title: 'Fracaso en el guardado',
                                    text: "El ítem no ha sido guardado en el carrito." +
                                        " Intente nuevamente.",
                                    type: 'warning',
                                    showCancelButton: false,
                                    confirmButtonText: 'Aceptar'
                                })
                            }
                        }
                    })
                }
            })
        }
    })
};

function ConsultarCarrito(codigo) {
    var token = $('input[name="csrfmiddlewaretoken"]').val();
    $.ajax({
        url: 'ajax/getarticle/',
        type: 'POST',
        data: {
            'codigo': codigo,
            'csrfmiddlewaretoken': token
        },
        dataType: 'json',
        success: function(data) {
            Swal.fire({
                title: data.nombre,
                html: '<img class="ui centered circular small image" src="' + data.image + '"><br/>' +
                    '<strong>Nombre: </strong>' + data.nombre + '<br/>' +
                    '<strong>Descripción: </strong>' + data.descripcion + '<br/>' +
                    '<strong>Precio: </strong>' + data.precio,
                showConfirmButton: true,
                confirmButtonText: 'Salir'
            })
        }
    })
};

function EliminarItemCarrito(codigo) {
    Swal.fire({
        title: '¿Seguro que deseas eliminar el artículo?',
        text: "Podrás volver a ingresarlo al carrito cuando desees.",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            var token = $('input[name="csrfmiddlewaretoken"]').val();
            $.ajax({
                url: 'ajax/removearticle/',
                type: 'POST',
                data: {
                    'codigo': codigo,
                    'csrfmiddlewaretoken': token
                },
                dataType: 'json',
                success: function(data) {
                    if (data['exito'] == true) {
                        Swal.fire('Éxito en la eliminación', 'El ítem ha sido removido de tu carrito con éxito.', 'success');
                        setTimeout(function() {
                            window.location.reload(false);
                        }, 500);
                    } else {
                        Swal.fire('Error al eliminar');
                    }
                }
            })
        }
    })
};

$('.ui.dropdown')
    .dropdown();

$('.ui.accordion')
    .accordion();

function Pagar(tipo) {
    Swal.fire({
        title: 'Confirmar pago.',
        text: "¿Seguro que desea continuar con el pago?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            var token = $('input[name="csrfmiddlewaretoken"]').val();
            $.ajax({
                url: 'ajax/pay/',
                type: 'POST',
                data: {
                    'tipo': tipo,
                    'csrfmiddlewaretoken': token
                },
                dataType: 'json',
                success: function(data) {
                    if (data['exito'] == true) {
                        Swal.fire({
                            title: 'Pago exitoso',
                            text: '¡Su pago ha sido exitoso! Gracias por comprar con nosotros.',
                            type: 'success',
                            showConfirmButton: false,
                            showCancelButton: false
                        });
                        setTimeout(function() {
                            window.location.replace('/purchases/');
                        }, 2500);
                    } else {
                        Swal.fire('Error al procesar el pago. Por favor intente nuevamente más tarde.');
                    }
                }
            })
        }
    })
};

$('#formulario_pago').submit(function() {
    Pagar('tc');
    return false;
});

$('#sign_up').submit(function() {
    confirmarCreacion();
    return false;
});

function VerDetFact(id) {
    $('#dimmer').addClass('active');
    var token = $('input[name="csrfmiddlewaretoken"]').val();
    tabla = 'detfact' + id;
    $('#' + tabla).empty();
    $.ajax({
        url: 'ajax/detail/',
        type: 'POST',
        data: {
            'id': id,
            'csrfmiddlewaretoken': token,
        },
        dataType: 'json',
        success: function(data) {
            $.each(data, function(i, articulo) {
                if (i != 0) {
                    articulo = '<tr>' +
                        '<td class="centered">' + articulo['articulo'] + '</td>' +
                        '<td><img class="ui circular tiny image centered" src="' + articulo['foto'] + '"></td>' +
                        '<td class="centered">$' + articulo['monto'] + '</td>' +
                        '</tr>';
                    $('#' + tabla).append(articulo);
                }
            })
        }
    })
    $('#dimmer').removeClass('active');
};

function Recomendar() {
    Swal.fire({
        title: 'Sugerencia de Ítems por IA',
        text: "Esta opción te permite obtener recomendaciones personalizadas para que compres basados en tu actividad. ¿Seguro que deseas recibir recomendaciones ahorita mismo?",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            var token = $('input[name="csrfmiddlewaretoken"]').val();
            $.ajax({
                url: 'ajax/recomendaciones/',
                type: 'POST',
                data: {
                    'csrfmiddlewaretoken': token
                },
                dataType: 'json',
                success: function(data) {
                    if (data['exito'] == true) {
                        Swal.fire({
                            title: 'Recomendaciones solicitadas con éxito.',
                            text: 'Hemos solicitado las recomendaciones a nuestro agente inteligente. Recargando...',
                            type: 'success',
                            showConfirmButton: false,
                            showCancelButton: false
                        });
                        setTimeout(function() {
                            window.location.reload();
                        }, 1000);
                    } else {
                        Swal.fire('Error solicitando las recomendaciones. Quizás nuestro agente no es tan inteligente...');
                    }
                }
            })
        }
    })
};

function Limpiar() {
    Swal.fire({
        title: 'Limpiar Carrito',
        text: "¿Estás seguro que deseas limpiar el carrito?",
        type: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.value) {
            var token = $('input[name="csrfmiddlewaretoken"]').val();
            $.ajax({
                url: 'ajax/limpiar/',
                type: 'POST',
                data: {
                    'csrfmiddlewaretoken': token
                },
                dataType: 'json',
                success: function(data) {
                    if (data['exito'] == true) {
                        Swal.fire({
                            title: 'Carrito limpiado con éxito.',
                            text: 'Hemos limpiado tu carrito con éxito. Recargando...',
                            type: 'success',
                            showConfirmButton: false,
                            showCancelButton: false
                        });
                        setTimeout(function() {
                            window.location.reload();
                        }, 1000);
                    } else {
                        Swal.fire('Error limpiando el carrito.');
                    }
                }
            })
        }
    })
};
