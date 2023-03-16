package com.example.tl01e10417.Configuracion;

public class Transacciones
{
    //nombre de la base de datos
    public static final String NameDatabase = "TLENG";

    // Creacion de tabla y objetos
    public static final String tablacontacto = "contactos";


    /* Campos de la tabla contactos */
    public static String id = "id";
    public static String pais= "pais";
    public static String nombre = "nombre";
    public static String telefono = "telefono";
    public static String nota= "nota";
    public static String imag= "imag";

    // Consultas SQL DDL
    public static String CreateTBContactos = "CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pais TEXT , nombre TEXT , telefono TEXT , nota TEXT , imag byte)";

    //Eliminar la tabla
    public static String DropTBContactos = "DROP TABLE IF EXISTS contactos";


}
