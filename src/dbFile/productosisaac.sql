-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2022 a las 20:42:12
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productosisaac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `CategoriaID` int(11) NOT NULL,
  `Categoria` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`CategoriaID`, `Categoria`) VALUES
(1, 'Ropa'),
(2, 'Higiene'),
(3, 'Alimentacion'),
(4, 'Videojuegos'),
(5, 'Mobiliario'),
(6, 'Juguetes'),
(7, 'Cosmeticos'),
(8, 'Informatica y electronica'),
(9, 'Mascotas'),
(10, 'Salud'),
(11, 'Electrodomesticos'),
(12, 'Libros'),
(13, 'Cine'),
(14, 'Música'),
(15, 'Papeleria'),
(16, 'Calzado'),
(17, 'Bisuteria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ProductoID` int(11) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Precio` float NOT NULL,
  `CategoriaID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ProductoID`, `Nombre`, `Precio`, `CategoriaID`) VALUES
(1, 'Tomate', 3.5, 3),
(2, 'Arroz', 8, 3),
(3, 'Paracetamol', 10.25, 10),
(4, 'Pantalón chandal gris', 30, 1),
(5, 'Pokemon Escarlata', 60, 4),
(6, 'Pokemon Purpura', 60, 4),
(7, 'Pokemon Espada', 60, 4),
(8, 'Pokemon Escudo', 60, 4),
(9, 'Libreta cuadros Oxford azul', 6.35, 15),
(10, 'Mesa de cocina', 119.99, 5),
(11, 'Muñeca Nancy', 35.5, 6),
(12, 'Pinta uñas azul', 10, 7),
(13, 'Ratón inalámbrico NISU', 4.99, 8),
(14, 'Comida para perros', 27.8, 9),
(15, 'Lavadora LG 5KG', 326.75, 11),
(16, 'Manga 1 Naruto', 7.45, 12),
(17, 'Película Bichos', 11, 13),
(18, 'Disco Hombres G Voy a pasármelo bien', 23.49, 14),
(19, 'Tacones de aguja negros', 100, 16),
(20, 'Anillo de diamantes NISU', 56.25, 17),
(21, 'Arroz negro', 12, 3),
(22, 'Macarrones', 8.5, 3),
(23, 'Manga 2 Naruto', 7.45, 12),
(24, 'Manga 3 Naruto', 7.45, 12),
(25, 'Manga 4 Naruto', 7.45, 12),
(26, 'Manga 5 Naruto', 7.45, 12),
(27, 'Manga 1 Dragon Ball', 7.45, 12),
(28, 'Manga 2 Dragon Ball', 7.45, 12),
(29, 'Manga 3 Dragon Ball', 7.45, 12),
(30, 'Manga 1 Dragon Ball Z', 7.45, 12),
(31, 'Manga 2 Dragon Ball Z', 7.45, 12),
(32, 'Manga 3 Dragon BallZ', 7.45, 12),
(33, 'Manga 1 One Piece', 8, 12),
(34, 'Manga 2 One Piece', 8, 12),
(35, 'Manga 3 One Piece', 8, 12),
(36, 'Lavadora LG 10KG', 376.85, 11),
(37, 'Lavadora LG 20kg', 420.75, 11),
(38, 'Spinner blanco', 1.2, 6),
(39, 'Spinner rojo', 1.2, 6),
(40, 'Spinner negro', 1.2, 6),
(41, 'Spinner marrón', 1.2, 6),
(42, 'Spinner azul', 1.2, 6),
(43, 'Spinner cian', 1.2, 6),
(44, 'Spinner amarillo', 1.2, 6),
(45, 'Spinner verde', 1.2, 6),
(46, 'Spinner rosa', 1.2, 6),
(47, 'Libreta cuadros Oxford verde', 6.35, 15),
(48, 'Libreta cuadros Oxford roja', 6.35, 15),
(49, 'Libreta cuadros Oxford negra', 6.35, 15),
(50, 'Libreta cuadros Oxford amarilla', 6.35, 15),
(51, 'Libreta cuadros Oxford naranja', 6.35, 15),
(52, 'Comida para gatos', 17.8, 9),
(53, 'Metformina 850g', 15, 10),
(54, 'Apiretal', 5.25, 10),
(55, 'Gelocatil', 10, 10),
(56, 'Pantalón chandal negro', 30, 1),
(57, 'Pantalón chandal azul oscuro', 30, 1),
(58, 'Pantalón chandal gris oscuro', 30, 1),
(59, 'Tacones negros', 30, 16),
(60, 'Sandalias marrones Geox', 69.99, 16),
(61, 'Mesa de comedor', 149.99, 5),
(62, 'Sillón rojo', 299.99, 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`CategoriaID`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ProductoID`),
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `FK_CategoriaID` (`CategoriaID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ProductoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `FK_CategoriaID` FOREIGN KEY (`CategoriaID`) REFERENCES `categorias` (`CategoriaID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
