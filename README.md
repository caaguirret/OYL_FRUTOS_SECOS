# OYL Frutos Secos🌰

Aplicación móvil Android para **OYL Frutos Secos**, emprendimiento chileno de venta al detalle de frutos secos, semillas, deshidratados y productos naturales. Proyecto académico (MVP de simulación) de la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles**, Duoc UC, Evaluación Parcial 2.

## Integrantes

| Nombre | Usuario GitHub |
|---|---|
| Catalina Aguirre | [ca.aguirret@duocuc.cl] |
| Bastián Campos | [bas.camposs@duocuc.cl] |
| Suimey Flores | [sui.flores@duocuc.cl] |

- Sección: SEC004D
- Docente: Cristian Armando Gómez Vega

## Problema

Hoy la tienda física y los canales en línea (WhatsApp Business y Mercado Libre) se gestionan por separado. El stock se revisa a mano y los pedidos se anotan en papel, lo que provoca descuadres de inventario y sobreventa. La app centraliza el catálogo, los pedidos y el control de existencias.

## Perfiles de usuario

- **Cliente:** se registra, revisa el catálogo por categorías, escanea QR de productos, arma su carrito, solicita pedidos y sigue su estado.
- **Administrador:** gestiona productos, precios e inventario, y actualiza el estado de los pedidos. Usa una cuenta de demostración preconfigurada (`admin@admin.cl`), sin credenciales reales.

## Funcionalidades

Marca con ✅ lo que ya esté implementado.

- [ ] Pantalla de bienvenida con animación
- [ ] Registro de cliente con formulario validado
- [ ] Inicio de sesión por rol, con sesión local
- [ ] Dashboard según rol
- [ ] Catálogo con búsqueda y filtros por categoría
- [ ] Detalle de producto
- [ ] Carrito persistente por cliente
- [ ] Confirmación de pedido
- [ ] Historial y estados de pedidos
- [ ] Gestión de productos (admin)
- [ ] Gestión de inventario (admin)
- [ ] Escáner QR (cámara)
- [ ] Mapa de tienda y puntos de retiro
- [ ] Notificaciones
- [ ] Perfil y configuración

## Tecnologías

- Kotlin, Jetpack Compose, Material Design 3
- Arquitectura MVVM con gestión de estado (`StateFlow`)
- Room (SQLite) para persistencia local
- Navigation Compose
- Recursos nativos: cámara, lectura de QR, mapas, notificaciones
- Git/GitHub y Trello

## Estructura del proyecto

```
app/src/main/java/.../
├── ui/          # Pantallas y componentes Composable
├── viewmodel/   # ViewModels: lógica de presentación y validaciones
├── model/       # Entidades, DAOs y clases de datos
└── repository/  # Acceso a datos (Room; luego API REST)
```

## Cómo ejecutar

1. Instalar Android Studio actualizado.
2. Clonar el repositorio:
   ```bash
   git clone https://github.com/caaguirret/OYL_FRUTOS_SECOS.git
   ```
3. Abrir la carpeta en Android Studio y esperar la sincronización de Gradle.
4. Crear un emulador en Device Manager (API 29 o superior) o conectar un teléfono Android.
5. Ejecutar con **Run ▶ app**.

Cuenta de demostración: `admin@admin.cl` (administrador). Los clientes se registran desde la app.

## Flujo de trabajo en equipo

- Rama `main` estable; una rama por funcionalidad (`feature/nombre`).
- Cambios por Pull Request, revisados por la otra integrante.
- Commits con mensajes técnicos y descriptivos.
- Tareas asignadas y seguidas en Trello (Por hacer / En curso / Finalizado).
-[Ver tablero de OYL Frutos Secos](https://trello.com/invite/b/6ad3ceedbbbdab2e4139f303/ATTI00e981b4f464c6aeab61096be6fbb17fD8965DCF/oyl-frutos-secos-desarrollo-de-aplicacion)

## Datos y privacidad

Solo se usan datos ficticios o anonimizados, conforme a la Ley N.º 19.628 y la Ley N.º 21.719. No se incluyen credenciales ni datos comerciales reales.
