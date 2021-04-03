-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2019 a las 21:09:29
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_boletin`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `curso_id` int(11) NOT NULL,
  `curso` int(11) NOT NULL,
  `division` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`curso_id`, `curso`, `division`) VALUES
(1, 1, 2),
(2, 3, 3),
(3, 1, 1),
(4, 1, 3),
(5, 2, 1),
(6, 2, 2),
(7, 2, 3),
(8, 3, 1),
(9, 3, 3),
(10, 4, 1),
(11, 4, 2),
(12, 4, 3),
(13, 5, 1),
(14, 5, 2),
(15, 5, 3),
(16, 6, 1),
(17, 6, 2),
(18, 6, 3),
(19, 1, 4),
(20, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fechas_trimestres`
--

CREATE TABLE `fechas_trimestres` (
  `num_trimestre` int(11) NOT NULL,
  `desde` date NOT NULL,
  `hasta` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `fechas_trimestres`
--

INSERT INTO `fechas_trimestres` (`num_trimestre`, `desde`, `hasta`) VALUES
(1, '2019-03-01', '2019-06-04'),
(2, '2019-06-05', '2019-09-11'),
(3, '2019-09-12', '2019-12-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inasistencias`
--

CREATE TABLE `inasistencias` (
  `inasistencia_id` int(11) NOT NULL,
  `inasistencia_dni_alumno` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `inasistencia_justificada` tinyint(1) NOT NULL,
  `inasistencia_valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listas`
--

CREATE TABLE `listas` (
  `id_lista` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `dni_alumno` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `lista_año` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `listas`
--

INSERT INTO `listas` (`id_lista`, `id_curso`, `dni_alumno`, `lista_año`) VALUES
(3, 1, '12312344', 2019),
(4, 1, '22334455', 2019),
(5, 5, '33445566', 2019),
(6, 4, '44556677', 2019);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `materia_id` int(11) NOT NULL,
  `materia_nombre` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `dni_profesor` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `id_curso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`materia_id`, `materia_nombre`, `dni_profesor`, `id_curso`) VALUES
(4, 'Matematica', '87654321', 3),
(5, 'Matematica', '87654321', 1),
(6, 'Matematica', '87654321', 4),
(7, 'Matematica', '87654321', 19),
(8, 'Matematica', '87654321', 20),
(9, 'Lengua', '12121212', 5),
(10, 'Lengua', '12121212', 6),
(11, 'Lengua', '12121212', 7),
(12, 'Historia', '23232323', 4),
(13, 'Historia', '23232323', 1),
(14, 'Historia', '23232323', 19),
(15, 'Historia', '23232323', 3),
(16, 'Historia', '23232323', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notas`
--

CREATE TABLE `notas` (
  `nota_id` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  `id_tipo_nota` int(11) NOT NULL,
  `nota_fecha` date NOT NULL,
  `nota_valor` float NOT NULL,
  `dni_alumno` varchar(8) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `notas`
--

INSERT INTO `notas` (`nota_id`, `id_materia`, `id_tipo_nota`, `nota_fecha`, `nota_valor`, `dni_alumno`) VALUES
(4, 5, 2, '2019-11-18', 6, '12312344');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_nota`
--

CREATE TABLE `tipos_nota` (
  `tiponota_id` int(11) NOT NULL,
  `tiponota_nombre` varchar(15) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipos_nota`
--

INSERT INTO `tipos_nota` (`tiponota_id`, `tiponota_nombre`) VALUES
(1, 'Oral'),
(2, 'Escrito'),
(3, 'Recuperatorio'),
(4, 'Práctico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_usuario`
--

CREATE TABLE `tipos_usuario` (
  `tipousuario_nombre` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `tipousuario_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipos_usuario`
--

INSERT INTO `tipos_usuario` (`tipousuario_nombre`, `tipousuario_codigo`) VALUES
('Escuela', 1),
('Profesor', 2),
('Alumno', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `usuario_dni` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_contrasena` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_codigo_tipo` int(11) NOT NULL,
  `domicilio` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `correo` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono_casa` int(11) NOT NULL,
  `telefono_celular` int(11) NOT NULL,
  `cuil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario_id`, `usuario_dni`, `usuario_nombre`, `usuario_contrasena`, `usuario_codigo_tipo`, `domicilio`, `correo`, `telefono_casa`, `telefono_celular`, `cuil`) VALUES
(1, '12345678', 'admin', '12345678', 1, '', '', 0, 0, 0),
(2, '87654321', 'profe', '87654321', 2, '', '', 0, 0, 0),
(3, '12312344', 'alumno', '12312344', 3, '', '', 0, 0, 0),
(4, '22334455', 'alumno2', '22334455', 3, '', '', 0, 0, 0),
(5, '33445566', 'alumno3', '33445566', 3, '', '', 0, 0, 0),
(6, '44556677', 'alumno4', '44556677', 3, '', '', 0, 0, 0),
(7, '12121212', 'profe2', '12121212', 2, '', '', 0, 0, 0),
(8, '23232323', 'profe3', '23232323', 2, '', '', 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`curso_id`);

--
-- Indices de la tabla `fechas_trimestres`
--
ALTER TABLE `fechas_trimestres`
  ADD PRIMARY KEY (`num_trimestre`);

--
-- Indices de la tabla `inasistencias`
--
ALTER TABLE `inasistencias`
  ADD PRIMARY KEY (`inasistencia_id`),
  ADD KEY `inasistencia_dni_alumno` (`inasistencia_dni_alumno`);

--
-- Indices de la tabla `listas`
--
ALTER TABLE `listas`
  ADD PRIMARY KEY (`id_lista`),
  ADD KEY `id_curso` (`id_curso`),
  ADD KEY `dni_alumno` (`dni_alumno`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`materia_id`),
  ADD KEY `dni_profesor` (`dni_profesor`),
  ADD KEY `id_curso` (`id_curso`);

--
-- Indices de la tabla `notas`
--
ALTER TABLE `notas`
  ADD PRIMARY KEY (`nota_id`),
  ADD KEY `id_materia` (`id_materia`),
  ADD KEY `dni_alumno` (`dni_alumno`),
  ADD KEY `id_tipo_nota` (`id_tipo_nota`);

--
-- Indices de la tabla `tipos_nota`
--
ALTER TABLE `tipos_nota`
  ADD PRIMARY KEY (`tiponota_id`);

--
-- Indices de la tabla `tipos_usuario`
--
ALTER TABLE `tipos_usuario`
  ADD PRIMARY KEY (`tipousuario_codigo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`),
  ADD UNIQUE KEY `usuario_dni_UNIQUE` (`usuario_dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `curso_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `inasistencias`
--
ALTER TABLE `inasistencias`
  MODIFY `inasistencia_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `listas`
--
ALTER TABLE `listas`
  MODIFY `id_lista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `materia_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `notas`
--
ALTER TABLE `notas`
  MODIFY `nota_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipos_nota`
--
ALTER TABLE `tipos_nota`
  MODIFY `tiponota_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inasistencias`
--
ALTER TABLE `inasistencias`
  ADD CONSTRAINT `inasistencias_ibfk_1` FOREIGN KEY (`inasistencia_dni_alumno`) REFERENCES `usuarios` (`usuario_dni`);

--
-- Filtros para la tabla `listas`
--
ALTER TABLE `listas`
  ADD CONSTRAINT `listas_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`curso_id`),
  ADD CONSTRAINT `listas_ibfk_2` FOREIGN KEY (`dni_alumno`) REFERENCES `usuarios` (`usuario_dni`);

--
-- Filtros para la tabla `materias`
--
ALTER TABLE `materias`
  ADD CONSTRAINT `materias_ibfk_1` FOREIGN KEY (`dni_profesor`) REFERENCES `usuarios` (`usuario_dni`),
  ADD CONSTRAINT `materias_ibfk_2` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`curso_id`);

--
-- Filtros para la tabla `notas`
--
ALTER TABLE `notas`
  ADD CONSTRAINT `notas_ibfk_1` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`materia_id`),
  ADD CONSTRAINT `notas_ibfk_2` FOREIGN KEY (`dni_alumno`) REFERENCES `usuarios` (`usuario_dni`),
  ADD CONSTRAINT `notas_ibfk_3` FOREIGN KEY (`id_tipo_nota`) REFERENCES `tipos_nota` (`tiponota_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
