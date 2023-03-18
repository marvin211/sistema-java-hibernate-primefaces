/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function foco(idElemento){
    document.getElementById(idElemento).focus();
}
function ocultarGuardar(idGuardar,idModificar){
    document.getElementById(idGuardar).style.display='none';
    document.getElementById(idModificar).style.display='block';
}
function focoBoton(botonGuardar,botonModificar){
    if(document.getElementById(botonGuardar).style.display==='block'){
        document.getElementById(botonGuardar).focus();
    }
    if(document.getElementById(botonModificar).style.display==='block'){
        document.getElementById(botonModificar).focus();
    }
}

