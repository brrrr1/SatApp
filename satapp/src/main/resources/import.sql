INSERT INTO ubicacion(id, nombre) VALUES (nextval('ubicacion_seq'), 'Aula 1ºDAM');
INSERT INTO ubicacion(id, nombre) VALUES (nextval('ubicacion_seq'), 'Aula 2ºDAM');
INSERT INTO ubicacion(id, nombre) VALUES (nextval('ubicacion_seq'), 'Aula 1ºAyF');
INSERT INTO ubicacion(id, nombre) VALUES (nextval('ubicacion_seq'), 'Aula 2ºAyF');
INSERT INTO ubicacion(id, nombre) VALUES (nextval('ubicacion_seq'), 'Sala de profesores');


INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Pc Profesor', 'Rotura de pantalla', 1);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Aire Acondicionado', 'No funciona el botón de encendido', 51);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Pc 1', 'No enciende la pantalla', 151);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Pc 2', 'No enciende la pantalla', 151);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Pc 3', 'No enciende la pantalla', 151);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Pc 4', 'No enciende la pantalla', 151);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Mesa 1', 'Pata rota', 101);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Mesa 2', 'Pata rota', 101);
INSERT INTO equipo(id, nombre, caracteristicas, ubicacion_id) VALUES (nextval('equipo_seq'), 'Mesa 3', 'Pata rota', 101);


INSERT INTO usuario (id, nombre, username, password, email, role) VALUES
(nextval('usuario_seq'), 'Moisés Dorado', 'moidor', 'passwordmoidor', 'moi.dor@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Bruno Delgado', 'brudel', 'passwordbrudel', 'bruno.delgado@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Carlos Roman', 'carrom', 'passwordcarrom', 'carlos.roman@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Joaquin Carrascal', 'joacar', 'passwordjoacar', 'joaquin.carrascal@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Manuel Maman', 'manmam', 'passwordmanmam', 'manuel.maman@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Victor Levic', 'viclev', 'passwordviclev', 'victor.levic@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Carlos Ruiz', 'carrui', 'passwordcarrui', 'carlos.ruiz@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Rafa Hernandez', 'rafher', 'passwordrafher', 'rafa.hernandez@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Pablo Camara', 'pabcam', 'passwordpabcam', 'pablo.camara@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Pepe Segura', 'pepseg', 'passwordpepseg', 'pepe.segura@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Pedro Sanchez', 'pedsan', 'passwordpedsan', 'pedro.sanchez@gmail.com', 'USER'),
(nextval('usuario_seq'), 'Pablo Tey', 'pabtey', 'passwordpabtey', 'pablo.tey@gmail.com', 'ADMIN'),
(nextval('usuario_seq'), 'Alvaro Castilla', 'alvcas', 'passwordalvcas', 'alvaro.castilla@gmail.com', 'USER');


INSERT INTO alumno (id) VALUES
(1),
(101),
(201),
(301),
(401),
(501),
(601);


INSERT INTO historico_cursos (id, curso_escolar, curso, alumno_id) VALUES
(nextval('historico_cursos_seq'), '2019-2020', '1º', 1),
(nextval('historico_cursos_seq'), '2019-2020', '2º', 101),
(nextval('historico_cursos_seq'), '2019-2020', '1º', 201),
(nextval('historico_cursos_seq'), '2019-2020', '2º', 301),
(nextval('historico_cursos_seq'), '2020-2021', '1º', 401),
(nextval('historico_cursos_seq'), '2020-2021', '2º', 501),
(nextval('historico_cursos_seq'), '2020-2021', '1º', 601);


INSERT INTO tecnico (id) VALUES
(51),
(151),
(251),
(351);


INSERT INTO personal (id, tipo) VALUES
(451, 'PROFESOR'),
(551, 'PAS');


INSERT INTO categoria (id, nombre) VALUES
(nextval('categoria_seq'), 'Informática'),
(nextval('categoria_seq'), 'Electrónica'),
(nextval('categoria_seq'), 'Inmueble'),
(nextval('categoria_seq'), 'Material Escolar');


INSERT INTO categoria (id, nombre, categoria_relacion_id) VALUES
(nextval('categoria_seq'), 'Ordenadores', 1),
(nextval('categoria_seq'), 'Impresoras', 1),
(nextval('categoria_seq'), 'Proyectores', 1),
(nextval('categoria_seq'), 'Cables', 51),
(nextval('categoria_seq'), 'Placas Base', 51),
(nextval('categoria_seq'), 'Pantallas', 51),
(nextval('categoria_seq'), 'Mesas', 101),
(nextval('categoria_seq'), 'Sillas', 101),
(nextval('categoria_seq'), 'Pizarras', 101),
(nextval('categoria_seq'), 'Libros', 151),
(nextval('categoria_seq'), 'Cuadernos', 151),
(nextval('categoria_seq'), 'Bolígrafos', 151);


INSERT INTO incidencia(id, fecha_creacion, titulo, descripcion, estado, urgencia, ubicacion_id, equipo_id, categoria_id, usuario_id) VALUES
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'Rotura pantalla profesor', 'Se ha roto la patalla del profesor, pinta feo', 'ABIERTA', 'Urgente', 1, 1, 251, 1),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'Aire acondionado estropeado', 'No funciona el botón del aire', 'ABIERTA', 'Urgente', 51, 51, 251, 101),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla', 'No enciende la pantalla del Pc 1', 'ABIERTA', 'Urgente', 101, 101, 251, 101),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla', 'No enciende la pantalla del Pc 2', 'ABIERTA', 'Urgente', 101, 151, 251, 201),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla', 'No enciende la pantalla del Pc 3', 'ABIERTA', 'Urgente', 101, 201, 251, 301),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla', 'No enciende la pantalla del Pc 4', 'ABIERTA', 'Urgente', 101, 251, 251, 401),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'Pata rota', 'Pata rota de la mesa 1', 'ABIERTA', 'Urgente', 151, 301, 251, 501),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'Pata rota', 'Pata rota de la mesa 2', 'ABIERTA', 'Urgente', 151, 351, 251, 601),
(nextval('incidencia_seq'), '2024-02-03T12:00:00', 'Pata rota', 'Pata rota de la mesa 3', 'ABIERTA', 'Urgente', 151, 401, 251, 1);


INSERT INTO nota (id, fecha, contenido, autor, incidencia_id) VALUES
(nextval('nota_seq'), '2024-02-03T12:00:00', 'Se ha roto la pantalla del profesor, pinta feo', 'Moisés Dorado', 1),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'No funciona el botón del aire', 'Carlos Roman', 51),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla del Pc 1', 'Carlos Roman', 101),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla del Pc 2', 'Manuel Maman', 151),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla del Pc 3', 'Carlos Ruiz', 201),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'No enciende la pantalla del Pc 4', 'Pablo Camara', 251),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'Pata rota de la mesa 1', 'Pedro Sanchez', 301),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'Pata rota de la mesa 2', 'Alvaro Castilla', 351),
(nextval('nota_seq'), '2024-02-03T12:00:00', 'Pata rota de la mesa 3', 'Moisés Dorado', 401);